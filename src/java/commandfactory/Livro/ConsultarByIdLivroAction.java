/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commandfactory.Livro;

import DAO.LivroDAO;
import DAO.LivroDigitalDAO;
import DAO.LivroFisicoDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import model.Livro;
import model.LivroBuilder;
import model.LivroDigitalBuilder;
import model.LivroFisicoBuilder;

/**
 *
 * @author drak
 */
public class ConsultarByIdLivroAction implements ICommand{
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LivroDAO livroDao;
        Livro livro;
        String pagina;

        try {
            int id = Integer.parseInt(request.getParameter("txtid"));
            String tipo = request.getParameter("txttipo");
            
            if(tipo.equals("FISICO")){
                livroDao = new LivroFisicoDAO();
                livro = new LivroFisicoBuilder()
                        .porId(id)
                        .constroi();
            } else{
                livroDao = new LivroDigitalDAO();
                livro = new LivroDigitalBuilder()
                        .porId(id)
                        .constroi();
            }

            Livro livroConsulta = livroDao.consultarById(livro);
            request.setAttribute("livro", livroConsulta);
            pagina = "/resultadoconsultarbyid.jsp";
        } catch (ClassNotFoundException | SQLException | NumberFormatException ex) {
            ex.printStackTrace();
            request.setAttribute("mensagem", "Erro ao consultar: " + ex.getMessage());
            pagina = "/erro.jsp";
        }

        return pagina;
    }
}
