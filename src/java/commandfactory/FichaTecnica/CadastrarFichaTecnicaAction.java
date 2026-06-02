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
public class CadastrarFichaTecnicaAction {
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String msg = "";
        FichaTecnicaDAO cdao = new FichaTecnicaDAO();
        FichaTecnica ft;
        
        /**
        try {
            //paginas, anoDePublicacao, editora, idioma
            int paginas = Integer.parseInt(request.getParameter("txtpaginas"));
            int anoDePublicacao = Integer.parseInt(request.getParameter("txtanoDePublicacao"));
            String editora = request.getParameter("txteditora");
            String idioma = request.getParameter("txtidioma");

            ft = new FichaTecnicaBuilder()
                    .comPaginas(paginas)
                    .comAnoDePublicacao(anoDePublicacao)
                    .comEditora(editora)
                    .comIdioma(idioma)
                    .constroi();
            
                    
            cdao.cadastrar(ft);
            msg = "Cadastrado com sucesso.";
            System.out.println("Cadastrado com sucesso.");
        } catch (ClassNotFoundException | SQLException | NumberFormatException ex) {
            msg = "Erro ao cadastrar.";
        }
        * **/
        request.setAttribute("msg", msg);
        return "resultado.jsp";
    }
}
