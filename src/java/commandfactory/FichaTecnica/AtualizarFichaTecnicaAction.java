/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commandfactory.FichaTecnica;

import DAO.FichaTecnicaDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.FichaTecnica;
import model.FichaTecnicaBuilder;

/**
 *
 * @author drak
 */
public class AtualizarFichaTecnicaAction {
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mensagem;
        String pagina;
        FichaTecnicaDAO fichaTecnicaDao = new FichaTecnicaDAO();
        

        try {
            int paginas = Integer.parseInt(request.getParameter("txtpaginas"));
            int anoDePublicacao = Integer.parseInt(request.getParameter("txtanoDePublicacao"));
            
            String editora = request.getParameter("txteditora");
            String idioma = request.getParameter("txtidioma");
            int id = Integer.parseInt(request.getParameter("txtid"));
            
            // paginas, anoDePublicacao, editora, idioma, id
                FichaTecnica fichaTecnica = new FichaTecnicaBuilder()                    
                    .comPaginas(paginas)
                    .comAnoDePublicacao(anoDePublicacao)
                    .comEditora(editora)
                    .comIdioma(idioma)
                    .porId(id)
                    .constroi();

            fichaTecnicaDao.atualizar(fichaTecnica);
            mensagem = "Atualizado com sucesso.";
            pagina = "resultadoconsultatodos.jsp";
        } catch (ClassNotFoundException | SQLException | NumberFormatException ex) {
            mensagem = "Erro ao Atualizar.";
            pagina = "erro.jsp";
        }

        request.setAttribute("msg", mensagem);
        return pagina;
    }
}
