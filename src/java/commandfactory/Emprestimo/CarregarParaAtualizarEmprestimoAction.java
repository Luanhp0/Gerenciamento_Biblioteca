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

/**
 *
 * @author drak
 */
public class CarregarParaAtualizarEmprestimoAction implements ICommand{
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mensagem;
        String pagina;
        EmprestimoDAO emprestimoDao = new EmprestimoDAO();
        

        try {
            int id = Integer.parseInt(request.getParameter("txtid"));


            Emprestimo emprestimo= new EmprestimoBuilder()
                    .porId(id)
                    .constroi();

            Emprestimo emprestimoConsulta = emprestimoDao.consultarById(emprestimo);

            request.setAttribute("emprestimo", emprestimoConsulta);
            
            pagina = "atualizaremprestimo.jsp";
        } catch (ClassNotFoundException | SQLException | NumberFormatException ex) {
            ex.printStackTrace();
            mensagem = "Erro ao carregar emprestimo";
            request.setAttribute("mensagem", mensagem);
            pagina = "erro.jsp";
        }

        return pagina;
    }
}
