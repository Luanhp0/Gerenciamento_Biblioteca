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
import model.Livro;
import model.LivroBuilder;
import java.sql.SQLException;
import model.LivroDigitalBuilder;
import model.LivroFisicoBuilder;
/**
 *
 * @author drak
 */
public class CarregarAtualizarLivroAction implements ICommand{

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LivroDAO livroDao;
        Livro livro;

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

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("livro", null);
        }

        return "atualizarlivro.jsp";
    }
}

