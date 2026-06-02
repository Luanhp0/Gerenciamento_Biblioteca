/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import commandfactory.Livro.LivroFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.FichaTecnica;
import model.Livro;
import model.LivroBuilder;
import util.Conexao;

/**
 *
 * @author kcarl
 */
public class FichaTecnicaDAO {
    public void cadastrar(FichaTecnica fichaTecnica, Livro livro) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("insert into fichaTecnica (id_livro, paginas, anoDePublicacao, editora, idioma) values (?, ?,?, ?, ?)");
        comando.setInt(1, livro.getId());
        comando.setInt(2, fichaTecnica.getPaginas());
        comando.setInt(3, fichaTecnica.getAnoDePublicacao());
        comando.setString(4, fichaTecnica.getEditora());
        comando.setString(5, fichaTecnica.getIdioma());
        comando.execute();
        con.close();
    }
    
    public void deletar(FichaTecnica fichaTecnica) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("delete from fichaTecnica where id = ?");
        comando.setInt(1, fichaTecnica.getId());
        comando.execute();
        con.close();
    }
    
    public void atualizar(FichaTecnica fichaTecnica) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("update fichaTecnica set paginas = ?, anoDePublicacao = ?, editora = ?, idioma = ? where id_ficha = ?");
        comando.setInt(1, fichaTecnica.getPaginas());
        comando.setInt(2, fichaTecnica.getAnoDePublicacao());
        comando.setString(3, fichaTecnica.getEditora());
        comando.setString(4, fichaTecnica.getIdioma());
        comando.setInt(5, fichaTecnica.getId());
        comando.execute();
        con.close();
    }

    public FichaTecnica consultarById(FichaTecnica fichaTecnica) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("select * from fichaTecnica where id_ficha = ?");
        comando.setInt(1, fichaTecnica.getId());
        ResultSet rs = comando.executeQuery();
        FichaTecnica fichaTecnicaConsulta = null;
        if (rs.next()){
            LivroDAO livroDAO = LivroFactory.instanciarDAO(rs);
            Livro livroConsulta = LivroFactory.instanciarBuilder(rs)
                    .porId(rs.getInt("id"))
                    .constroi();
            fichaTecnicaConsulta = new FichaTecnica(rs.getInt("id"),rs.getInt("paginas"), rs.getInt("anoDePublicacao"), 
                    rs.getString("editora"), rs.getString("idioma"));
        }  
        con.close();
        return fichaTecnicaConsulta;
    }
    
    /**
    public List<Livro> consultarTodos() throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("select * from livros");
        ResultSet rs = comando.executeQuery();
        
        List<Livro> livros = new ArrayList<Livro>();
        while(rs.next()){
            Livro livro = new Livro(rs.getString("titulo"), 
            rs.getString("autor"), rs.getString("descricao"));
            livros.add(livro);
        }        
        return livros;
    }
    * **/
    
}
