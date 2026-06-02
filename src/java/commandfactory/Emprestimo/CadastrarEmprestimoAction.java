/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commandfactory.Emprestimo;


import DAO.LivroDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Emprestimo;
import DAO.EmprestimoDAO;
import DAO.LivroDigitalDAO;
import DAO.LivroFisicoDAO;
import DAO.UsuarioDAO;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import model.EmprestimoBuilder;
import model.Livro;
import model.LivroDigitalBuilder;
import model.LivroFisico;
import model.LivroFisicoBuilder;
import model.Usuario;
import model.UsuarioBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import scheduler.NotificacaoDevolucaoJob;
import validacao.emprestimo.IValidadorEmprestimo;
import validacao.emprestimo.ValidarLivroClienteExiste;
import validacao.emprestimo.ValidarLivroJaEmprestado;
/**
 *
 * @author drak
 */
public class CadastrarEmprestimoAction implements ICommand{
    
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mensagem;
        String pagina;
        EmprestimoDAO emprestimoDao = new EmprestimoDAO();
        LivroDAO livroDao;
        Livro livro;
        UsuarioDAO usuarioDao = new UsuarioDAO();

        try {
            int idLivro = Integer.parseInt(request.getParameter("txtidlivro"));
            int idUsuario = Integer.parseInt(request.getParameter("txtidusuario"));
            String tipo = request.getParameter("txttipo");
            
            
            if(tipo.equals("FISICO")){
                livroDao = new LivroFisicoDAO();
                livro = new LivroFisicoBuilder()
                        .porId(idLivro)
                        .constroi();
                
            } else {
                livroDao = new LivroDigitalDAO();
                livro = new LivroDigitalBuilder()
                        .porId(idLivro)
                        .constroi();
            }
            
            

            Usuario usuario = new UsuarioBuilder()
                    .comIdUsuario(idUsuario)
                    .constroi();
            
            
            
            Livro livroConsulta = livroDao.consultarById(livro);
            Usuario usuarioConsulta = usuarioDao.consultarById(usuario);
            
            
            List<IValidadorEmprestimo> validadores = Arrays.asList(

                new ValidarLivroClienteExiste(livroConsulta, usuarioConsulta),

                new ValidarLivroJaEmprestado(emprestimoDao, usuario, livro)
            );

            
                
            int diasPrazo = usuarioConsulta.calcular();

            Date dataEmprestimo = new Date(System.currentTimeMillis());
            Date dataDevolucao = Date.valueOf(dataEmprestimo.toLocalDate().plusDays(diasPrazo));

            Emprestimo emprestimo = new EmprestimoBuilder()
                    .comLivro(livroConsulta)
                    .comUsuario(usuarioConsulta)
                    .comDataEmprestimo(dataEmprestimo)
                    .comDataDevolucao(dataDevolucao)
                    .constroi();


            Emprestimo emprestimoCadastrado = emprestimoDao.cadastrar(emprestimo);

            
            if(tipo.equals("FISICO")){
                LivroFisicoDAO livroFisicoDao = new LivroFisicoDAO();
                livro = (LivroFisico) new LivroFisicoBuilder()
                        .porId(idLivro)
                        .constroi();
               
                        
            }
            
            SchedulerFactory schedulerFactory = new org.quartz.impl.StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            
            JobDetail notificar = JobBuilder.newJob(NotificacaoDevolucaoJob.class)
                    .withIdentity("NotificacaoDevolucaoJob_" + emprestimoCadastrado.getId(), "GrupoBiblioteca")
                    .build();
            
            notificar.getJobDataMap().put("idEmprestimo", emprestimoCadastrado.getId());
            
           java.util.Date dataDisparo; dataDisparo = (Date) emprestimo.getDataDevolucao();
            
            // TESTE: Testar para daqui a 1 minuto no código:
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.add(java.util.Calendar.SECOND, 10);
            dataDisparo = cal.getTime();
            
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("DevolucaoEmprestimoTrigger_" + emprestimoCadastrado.getId(), "GrupoEmprestimo")
                    .startAt(dataDisparo)
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withRepeatCount(0))
                    .build();
            
            scheduler.scheduleJob(notificar, trigger);
            
            System.out.println("O Usuário será notificado no dia: " + emprestimoCadastrado.getDataDevolucao());

            request.setAttribute("emprestimos", emprestimoDao.consultarTodos());

            mensagem = "Empréstimo cadastrado com sucesso.";
            pagina = "resultadoconsultartodosEmprestimo.jsp";

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Erro no scheduler: " + ex.getMessage());
            mensagem = "Erro ao cadastrar emprestimo: " + ex.getMessage();

            request.setAttribute("mensagem", mensagem);

            pagina = "/erro.jsp";
        }

        request.setAttribute("mensagem", mensagem);

        return pagina;
    }
    
    
}

