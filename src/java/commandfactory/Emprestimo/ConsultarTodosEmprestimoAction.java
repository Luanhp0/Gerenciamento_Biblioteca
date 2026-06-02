/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commandfactory.Emprestimo;

import DAO.EmprestimoDAO;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Emprestimo;

/**
 *
 * @author drak
 */
public class ConsultarTodosEmprestimoAction implements ICommand{

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        EmprestimoDAO emprestimoDao = new EmprestimoDAO();
        String pagina;
        try {
            List<Emprestimo> emprestimos = emprestimoDao.consultarTodos();
            request.setAttribute("emprestimos", emprestimos);
            pagina = "resultadoconsultartodosEmprestimo.jsp";
        } catch (ClassNotFoundException | SQLException | NumberFormatException ex) {
            request.setAttribute("mensagem", "Erro ao consultar empréstimos: " + ex.getMessage());
            pagina = "erro.jsp";
        }

        return pagina;
    }
}
