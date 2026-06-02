/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commandfactory.Livro;

import DAO.FichaTecnicaDAO;
import DAO.LivroDAO;
import DAO.LivroDigitalDAO;
import DAO.LivroFisicoDAO;

import model.Livro;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.FichaTecnica;
import model.FichaTecnicaBuilder;
import model.LivroBuilder;
import model.LivroDigitalBuilder;
import model.LivroFisicoBuilder;
/**
 *
 * @author drak
 */
public class CadastrarLivroAction implements ICommand{
    
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mensagem;
        String pagina;
        LivroDAO livroDao;
        FichaTecnicaDAO fichaDAO = new FichaTecnicaDAO();
        Livro livro;
        try {
            String titulo = request.getParameter("txttitulo");
            String autor = request.getParameter("txtautor");
            String genero = request.getParameter("txtgenero");
            String descricao = request.getParameter("txtdescricao");
            
            int paginas = Integer.parseInt(request.getParameter("txtpaginas"));
            int anoDePublicacao = Integer.parseInt(request.getParameter("txtanoDePublicacao"));
            String editora = request.getParameter("txteditora");
            String idioma = request.getParameter("txtidioma");
            String tipo = request.getParameter("txttipo");


            if(tipo == null || tipo.isEmpty()){
                throw new Exception("Tipo do livro não informado.");
            }
            FichaTecnica fichaTecnica = new FichaTecnicaBuilder()
                    .comPaginas(paginas)
                    .comAnoDePublicacao(anoDePublicacao)
                    .comEditora(editora)
                    .comIdioma(idioma)
                    .constroi();
            
            List<String> generos = new ArrayList<>();
            generos.add(genero);
            
            if(tipo.equals("FISICO")){
                String capa = request.getParameter("txttipocapa");
                livroDao = new LivroFisicoDAO();
                livro = new LivroFisicoBuilder()
                    .comTitulo(titulo)
                    .comAutor(autor)
                    .comGenero(generos)
                    .comDescricao(descricao)
                    .comCapa(capa)
                    .comDisponibilidade(true)
                    .constroi();
            } else{
                String formato = request.getParameter("txtformato");
                String tamanhoStr = request.getParameter("txttamanho");
                double tamanho = (tamanhoStr != null && !tamanhoStr.isEmpty()) ? Double.parseDouble(tamanhoStr) : 0.0;
                livroDao = new LivroDigitalDAO();
                livro = new LivroDigitalBuilder()
                        .comTitulo(titulo)
                        .comAutor(autor)
                        .comGenero(generos)
                        .comDescricao(descricao)
                        .comFormato(formato)
                        .comTamanho(tamanho)
                        .constroi();
            }
            
            Livro livroGerado = livroDao.cadastrar(livro);
            fichaDAO.cadastrar(fichaTecnica, livroGerado);
            mensagem = "Cadastrado com sucesso.";
            System.out.println("Cadastrado com sucesso.");

            request.setAttribute("listaLivros", livroDao.consultarTodos());
            pagina = "resultadoconsultartodos.jsp";
        } catch (ClassNotFoundException | SQLException | NumberFormatException ex) {
            mensagem = "Erro ao cadastrar livro";
            request.setAttribute("mensagem", mensagem);
            pagina = "/erro.jsp";
        }
        request.setAttribute("mensagem", mensagem);
        return pagina;
    }
}
