/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commandfactory.FichaTecnica;

import DAO.FichaTecnicaDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import model.FichaTecnica;
import model.FichaTecnicaBuilder;

/**
 *
 * @author drak
 */
public class ConsultarByIdFichaTecnicaAction {
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mensagem;
        String pagina;
        FichaTecnicaDAO fichaTecnicaDao = new FichaTecnicaDAO();
        FichaTecnica fichaTecnica;
        try {
            int id = Integer.parseInt(request.getParameter("txtid"));
            fichaTecnica = new FichaTecnicaBuilder()
                    .porId(id)
                    .constroi();
                    
            fichaTecnicaDao.consultarById(fichaTecnica);
            pagina = "resultadoconsultarbyid.jsp";
        } catch (ClassNotFoundException | SQLException | NumberFormatException ex) {
            mensagem = "Erro ao consultar";
            request.setAttribute("mensagem", mensagem);
            pagina = "erro.jsp";
        }
        return pagina;
    }
}
