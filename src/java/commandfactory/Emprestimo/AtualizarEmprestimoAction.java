/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commandfactory.Emprestimo;

import DAO.EmprestimoDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Emprestimo;
import model.EmprestimoBuilder;
import java.sql.Date;


/**
 *
 * @author drak
 */
public class AtualizarEmprestimoAction implements ICommand{
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mensagem;
        String pagina;
        EmprestimoDAO emprestimoDao = new EmprestimoDAO();

        try {
            int id = Integer.parseInt(request.getParameter("txtid"));
            Date dataDevolucaoRealizada = Date.valueOf(request.getParameter("txtdataDevolucaoRealizada"));

            Emprestimo emprestimo= new EmprestimoBuilder()
                    .porId(id)
                    .comDataDevolucaoRealizada(dataDevolucaoRealizada)
                    .constroi();

            emprestimoDao.atualizar(emprestimo);
            mensagem = "Atualizado com sucesso.";
            request.setAttribute("emprestimos", emprestimoDao.consultarTodos());
            pagina = "resultadoconsultartodosEmprestimo.jsp";
        } catch (ClassNotFoundException | SQLException | NumberFormatException ex) {
            mensagem = "Erro ao Atualizar.";
            pagina = "erro.jsp";
        }

        request.setAttribute("mensagem", mensagem);
        return pagina;
    }
}
