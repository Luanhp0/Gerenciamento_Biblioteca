/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commandfactory.Livro;

import DAO.LivroDAO;
import DAO.LivroDigitalDAO;
import DAO.LivroFisicoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Livro;
import model.LivroBuilder;
import model.LivroDigitalBuilder;
import model.LivroFisicoBuilder;

/**
 *
 * @author drak
 */
    
    public class AtualizarLivroAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LivroDAO livroDao;
        Livro livro;
        String pagina;

        try {
            String tipo = request.getParameter("txttipo");
            int id = Integer.parseInt(request.getParameter("txtid"));
            String titulo = request.getParameter("txttitulo");
            String autor = request.getParameter("txtautor");
            String descricao = request.getParameter("txtdescricao");
            String genero = request.getParameter("txtgenero");
            List<String> generos = new ArrayList<>();
            generos.add(genero);

            if(tipo.equals("Fisico")){
                String capa = request.getParameter("txttipocapa");
                livroDao = new LivroFisicoDAO();
                livro = new LivroFisicoBuilder()
                    .porId(id)
                    .comTitulo(titulo)
                    .comAutor(autor)
                    .comGenero(generos)
                    .comDescricao(descricao)
                    .comCapa(capa)
                    //.comDisponibilidade("")
                    .constroi();
            } else{
                String formato = request.getParameter("txtformato");
                double tamanho = Double.parseDouble(request.getParameter("txttamanho"));
                livroDao = new LivroDigitalDAO();
                livro = new LivroDigitalBuilder()
                        .porId(id)
                        .comTitulo(titulo)
                        .comAutor(autor)
                        .comGenero(generos)
                        .comDescricao(descricao)
                        .comFormato(formato)
                        .comTamanho(tamanho)
                        .constroi();
            }
            
            livroDao.atualizar(livro);
            List<Livro> listaLivros = livroDao.consultarTodos();
            request.setAttribute("listaLivros", listaLivros);
            pagina = "resultadoconsultartodos.jsp";
        } catch (ClassNotFoundException | SQLException | NumberFormatException ex) {
            request.setAttribute("msg", "Erro ao carregar livro para atualização.");
            pagina = "erro.jsp";
        }

        return pagina;
    }
}

