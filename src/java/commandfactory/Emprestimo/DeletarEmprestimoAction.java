/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commandfactory.Emprestimo;

import DAO.EmprestimoDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import model.Emprestimo;
import model.EmprestimoBuilder;

/**
 *
 * @author drak
 */
public class DeletarEmprestimoAction implements ICommand{
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mensagem;
        String pagina;
        EmprestimoDAO emprestimoDao = new EmprestimoDAO();
        Emprestimo emprestimo;
        try {
            int id = Integer.parseInt(request.getParameter("txtid"));
            emprestimo = new EmprestimoBuilder()
                    .porId(id)
                    .constroi();
                
              
            emprestimoDao.deletar(emprestimo);

            mensagem = "Deletado com sucesso.";
            request.setAttribute("mensagem", mensagem);

            List<Emprestimo> emprestimos = emprestimoDao.consultarTodos();
            request.setAttribute("emprestimos", emprestimos);
            System.out.println("Deletado com sucesso.");
            pagina = "resultadoconsultartodosEmprestimo.jsp";
        } catch (ClassNotFoundException | SQLException | NumberFormatException ex) {
            mensagem = "Erro ao deletar.";
            request.setAttribute("mensagem", mensagem);
            pagina = "erro.jsp";
        }
        request.setAttribute("mensagem", mensagem);
        return pagina;
    }
}
