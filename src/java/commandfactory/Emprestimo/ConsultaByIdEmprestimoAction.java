/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commandfactory.Emprestimo;

import DAO.EmprestimoDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import model.Emprestimo;
import model.EmprestimoBuilder;

/**
 *
 * @author drak
 */
public class ConsultaByIdEmprestimoAction {
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
                    
            emprestimoDao.consultarById(emprestimo);
            
            pagina = "atualizaremprestimo.jsp";
        } catch (ClassNotFoundException | SQLException | NumberFormatException ex) {
            mensagem = "Erro ao consultar";
            request.setAttribute("mensagem", mensagem);
            pagina = "erro.jsp";
        }
        return pagina;
    }
}
