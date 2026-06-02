    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import model.FichaTecnica;
import model.Livro;
import model.LivroBuilder;
import util.Conexao;

/**
 *
 * @author kcarl
 */
public interface LivroDAO <T extends Livro>{
    
    public Livro cadastrar(T livro) throws ClassNotFoundException, SQLException;
    
    public void deletar(T livro) throws ClassNotFoundException, SQLException;
    
    public void atualizar(T livro) throws ClassNotFoundException, SQLException;
   
    public Livro consultarById(T livro) throws ClassNotFoundException, SQLException;
    
    public List<Livro> consultarTodos() throws ClassNotFoundException, SQLException;
    
    /**
    public Livro cadastrar(Livro livro) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("insert into livros (titulo, autor, genero, descricao,tipo) values (?,?,?, ?,?)",
        Statement.RETURN_GENERATED_KEYS);
        
        comando.setString(1, livro.getTitulo());
        comando.setString(2, livro.getAutor());
        comando.setString(3, String.join(", ", livro.getGenero()));
        comando.setString(4, livro.getDescricao());
        comando.setString(5, livro.getTipo());
        comando.execute();
        
        ResultSet rs = comando.getGeneratedKeys();
        int idGerado = 0;
        Livro livroGerado = null;
        if (rs.next()) {
            idGerado = rs.getInt(1);
            livroGerado = new LivroBuilder()
                    .porId(idGerado)
                    .constroi();           
        }
        con.close();
        return livroGerado;
    }
    
    public void deletar(Livro livro) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("delete from livros where id_livro = ?");
        comando.setInt(1, livro.getId());
        comando.execute();
        con.close();
    }
    
    public void atualizar(Livro livro) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("UPDATE livros SET titulo = ?, autor = ?, descricao = ? WHERE id_livro = ?");
        comando.setString(1, livro.getTitulo());
        comando.setString(2, livro.getAutor());
        comando.setString(3, livro.getDescricao());
        comando.setInt(4, livro.getId());
        comando.execute();
        con.close();
    }
    
    
    public Livro consultarById(Livro livro) throws ClassNotFoundException, SQLException {
    Connection con = Conexao.getConexaoMySQL();
    PreparedStatement comando = con.prepareStatement("select * from livros l left join fichatecnica f on l.id_livro = f.id_livro where l.id_livro = ?");
    comando.setInt(1, livro.getId());
    ResultSet rs = comando.executeQuery();
    Livro livroConsulta = null;

    if (rs.next()) {
        FichaTecnica fichaTecnica = new FichaTecnica(
            rs.getInt("id_ficha"),
            rs.getInt("paginas"),
            rs.getInt("anoDePublicacao"),
            rs.getString("editora"),
            rs.getString("idioma")
        );

        String generoBanco = rs.getString("genero");
        List<String> generos = new ArrayList<>();

        if (generoBanco != null && !generoBanco.isEmpty()) {
            generos = Arrays.asList(generoBanco.split(", "));
        }

        livroConsulta = new LivroBuilder()
            .porId(rs.getInt("id_livro"))
            .comTitulo(rs.getString("titulo"))
            .comAutor(rs.getString("autor"))
            .comDescricao(rs.getString("descricao"))
            .comGenero(generos)
            .comQuantidade(rs.getInt("quantidade"))
            .comFicha(fichaTecnica)
            .comTipo(rs.getString("tipo"))
            .constroi();
        }

        con.close();
        return livroConsulta;
    }
 
    
    
    public List<Livro> consultarTodos() throws ClassNotFoundException, SQLException {
    Connection con = Conexao.getConexaoMySQL();

    PreparedStatement comando = con.prepareStatement("select * from livros l left join fichatecnica f on l.id_livro = f.id_livro");
    ResultSet rs = comando.executeQuery();
    List<Livro> livros = new ArrayList<>();
    while (rs.next()) {
        FichaTecnica fichaTecnica = new FichaTecnica(rs.getInt("id_ficha"),rs.getInt("paginas"),rs.getInt("anoDePublicacao"),rs.getString("editora"),rs.getString("idioma"));
        String generoBanco = rs.getString("genero");
        List<String> generos = new ArrayList<>();

        if (generoBanco != null && !generoBanco.isEmpty()) {
            generos = Arrays.asList(generoBanco.split(", "));
        }

        Livro livro = new LivroBuilder()
            .porId(rs.getInt("id_livro"))
            .comTitulo(rs.getString("titulo"))
            .comAutor(rs.getString("autor"))
            .comDescricao(rs.getString("descricao"))
            .comGenero(generos)
            .comQuantidade(rs.getInt("quantidade"))
            .comFicha(fichaTecnica)
            .comTipo(rs.getString("tipo"))
            .constroi();

        livros.add(livro);
        }

        con.close();
        return livros;
    }
    
    public boolean temEstoque(int idLivro) throws Exception {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("select quantidade from livros where id_livro = ?");
        comando.setInt(1, idLivro);
        ResultSet rs = comando.executeQuery();
        boolean tem = false;
        if (rs.next()) {
            tem = rs.getInt("quantidade") > 0;
        }
        con.close();
        return tem;
    }
    
    public void debitarEstoque(int idLivro) throws Exception {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("update livros set quantidade = quantidade - 1 where id_livro = ? and quantidade > 0");
        comando.setInt(1, idLivro);
        comando.executeUpdate();
        con.close();
    }
    
    public void adicionarQuantidade(int idLivro, int quantidade)
        throws Exception {

        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("update livros set quantidade = quantidade + ? where id_livro = ?");
        comando.setInt(1, quantidade);
        comando.setInt(2, idLivro);
        comando.executeUpdate();
        con.close();
    }
    
    public void devolverEstoque(int idLivro) throws Exception {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("update livros set quantidade = quantidade + 1 where id_livro = ?");
        comando.setInt(1, idLivro);
        comando.executeUpdate();
        con.close();
    }
    **/
}
