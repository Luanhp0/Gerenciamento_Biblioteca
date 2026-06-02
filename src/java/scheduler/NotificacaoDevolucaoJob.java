/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scheduler;

import DAO.EmprestimoDAO;
import DAO.NotificacaoDAO;
import java.time.LocalDate;
import java.util.List;
import model.Email;
import model.Emprestimo;
import model.EmprestimoBuilder;
import model.Livro;
import model.Notificacao;
import model.Usuario;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;

/**
 *
 * @author kcarl
 */
public class NotificacaoDevolucaoJob implements Job{
    public NotificacaoDevolucaoJob(){
        
    }
    
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try{
            //Teste 1
            System.out.println("Entrou no Job");
            int idEmprestimo = context.getJobDetail().getJobDataMap().getInt("idEmprestimo");
            //Teste 2
            System.out.println("Id retornado pelo Quartz: " + idEmprestimo);
            
            EmprestimoDAO emprestimoDao = new EmprestimoDAO();
            NotificacaoDAO notificacaoDao = new NotificacaoDAO();
            
            Emprestimo emprestimo = new EmprestimoBuilder()
                    .porId(idEmprestimo)
                    .constroi();
            
            Emprestimo consultaEmprestimo = emprestimoDao.consultarById(emprestimo);
            //Teste 3
            System.out.println("Objeto emprestimo encontrado? " + (consultaEmprestimo != null));
            Usuario usuario = consultaEmprestimo.getCliente();
            
            //Teste 4
            if(consultaEmprestimo == null){
                System.out.println("Emprestimo não encontrado no banco");
            }
            
            //Teste 5
            System.out.println("Emprestimo encontrado: " + consultaEmprestimo.getLivro().getTitulo());
            //Teste 6
            System.out.println("Data devolucao encontrada: " + consultaEmprestimo.getDataDevolucao() == null);
            
            //Teste 7
            if(consultaEmprestimo.getDataDevolucao() == null){
                System.out.println("Entrou no if de envio");
            } else{
                System.out.println("Bloqueado pelo if");
            }
            
            if(consultaEmprestimo != null && consultaEmprestimo.getDataDevolucaoRealizada() == null){
                //Teste 8
                System.out.println("Entrou no if verdadeiro");
                
                usuario = consultaEmprestimo.getCliente();
                //Teste 9

                System.out.println("Usuario Id: " + usuario.getId());
                Livro livro = consultaEmprestimo.getLivro();
                Notificacao notificacao = notificacaoDao.consultarById(usuario, livro);
                System.out.println("Notificacao criada: " + (notificacao != null));
                
             
                if (notificacao != null) {
                    System.out.println(notificacao.enviarNotificacao()); 
                } else {
                    System.out.println("Usuário sem preferencias de notificacao cadastradas.");
                }
            }
            
            //List<Emprestimo> emprestimos = emprestimoDao.consultarTodos();
            /**
            for(Emprestimo emp : emprestimos){                  
                Notificacao notificacao = notificacaoDao.consultarById(usuario);
                String mensagem = "Seu emprestimo do livro: ___ vence hoje";  
                System.out.println(mensagem);
                System.out.println(notificacao.enviarNotificacao());                
            }
            * */
            
        } catch(Exception e){
            System.out.println("Erro no scheduler: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
