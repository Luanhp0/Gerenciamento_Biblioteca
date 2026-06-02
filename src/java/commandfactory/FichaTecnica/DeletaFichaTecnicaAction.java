/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commandfactory.FichaTecnica;

import DAO.FichaTecnicaDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.FichaTecnica;
import model.FichaTecnicaBuilder;
import java.sql.SQLException;
/**
 *
 * @author drak
 */
public class DeletaFichaTecnicaAction {
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String msg;
        String pagina;
        FichaTecnicaDAO fichaTecnicaDao = new FichaTecnicaDAO();
        FichaTecnica fichaTecnica;
        try {
            int id = Integer.parseInt(request.getParameter("txtid"));
            fichaTecnica = new FichaTecnicaBuilder()
                    .porId(id)
                    .constroi();
                
                
            fichaTecnicaDao.deletar(fichaTecnica);
            msg = "Deletado com sucesso.";
            System.out.println("Deletado com sucesso.");
            pagina = "resultadoconsultartodos.jsp";
        } catch (ClassNotFoundException | SQLException | NumberFormatException ex) {
            msg = "Erro ao deletar.";
            pagina = "erro.jsp";
        }
        request.setAttribute("msg", msg);
        return "resultado.jsp";
    }
}
