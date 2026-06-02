/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commandfactory.Livro;

import DAO.LivroDAO;
import DAO.LivroDigitalDAO;
import DAO.LivroFisicoDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Livro;

/**
 *
 * @author drak
 */
public class ConsultarTodosLivroAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LivroDAO livroFisicoDao = new LivroFisicoDAO();
        LivroDAO livroDigitalDao = new LivroDigitalDAO();
        List<Livro> listaLivros = new ArrayList<Livro>();
        String pagina;

        try {            



            List<Livro> listaLivrosFisicos = livroFisicoDao.consultarTodos();
            List<Livro> listaLivrosDigitais = livroDigitalDao.consultarTodos();
            listaLivros.addAll(listaLivrosFisicos);
            listaLivros.addAll(listaLivrosDigitais);
            request.setAttribute("listaLivros", listaLivros);
            pagina = "resultadoconsultartodos.jsp";
        } catch (Exception ex) {
                ex.printStackTrace(); // aparece no log do servidor
                request.setAttribute("mensagem", "Erro: " + ex.getMessage());
                pagina = "erro.jsp";
        }

        return pagina;
    }
}
