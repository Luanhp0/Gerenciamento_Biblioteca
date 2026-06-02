/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commandfactory.FichaTecnica;

import DAO.FichaTecnicaDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.FichaTecnica;
import java.sql.SQLException;

/**
 *
 * @author drak
 */

/**
public class ConsultarTodosFichaTecnicaAction {
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        FichaTecnicaDAO fichaTecnicaDao = new FichaTecnicaDAO();
        try {
            List<FichaTecnica> listaFichaTecnica = fichaTecnicaDao.consultarTodos();
            request.setAttribute("listaFichaTecnica", listaFichaTecnica);
        } catch (ClassNotFoundException | SQLException | NumberFormatException ex) {
            System.out.println("ERRO: " + ex.getMessage());
        }
        return "resultadoconsultartodos.jsp";
    }
}
**/