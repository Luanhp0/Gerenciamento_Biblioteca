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
import java.util.List;
import model.LivroDigitalBuilder;
import model.LivroFisicoBuilder;
/**
 *
 * @author drak
 */
public class DeletarLivroAction implements ICommand{
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mensagem;
        String pagina;
        LivroDAO livroDao;
        Livro livro;
        
        try {
            int id = Integer.parseInt(request.getParameter("txtid"));
            String tipo = request.getParameter("txttipo");
            
            if(tipo.equals("Fisico")){
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
                
                
            livroDao.deletar(livro);
            List<Livro> listaLivros = livroDao.consultarTodos();
            request.setAttribute("listaLivros", listaLivros);
            mensagem = "Deletado com sucesso.";
            pagina = "resultadoconsultartodos.jsp";
        } catch (ClassNotFoundException | SQLException | NumberFormatException ex) {
            mensagem = "Erro ao deletar livro";
            pagina = "erro.jsp";
        }
        request.setAttribute("mensagem", mensagem);
        return pagina;
    }
}
