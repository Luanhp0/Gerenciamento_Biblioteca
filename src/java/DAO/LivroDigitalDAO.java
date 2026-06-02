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
import model.FichaTecnica;
import model.Livro;
import model.LivroDigital;
import model.LivroDigitalBuilder;
import util.Conexao;

/**
 *
 * @author kcarl
 */
public class LivroDigitalDAO implements LivroDAO<LivroDigital>{
    
    @Override
    public Livro cadastrar(LivroDigital livro) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("insert into livros (titulo, autor, genero, descricao, formato, tamanho, tipo_livro) values (?,?,?, ?,?,?, ?)",
        Statement.RETURN_GENERATED_KEYS);
        
        
        
        comando.setString(1, livro.getTitulo());
        comando.setString(2, livro.getAutor());
        comando.setString(3, String.join(", ", livro.getGenero()));
        comando.setString(4, livro.getDescricao());
        comando.setString(5, livro.getFormato());
        comando.setDouble(6, livro.getTamanho());
        comando.setString(7, livro.getTipo());
        comando.execute();
        
        
        ResultSet rs = comando.getGeneratedKeys();
        int idGerado = 0;
        Livro livroGerado = null;
        if (rs.next()) {
            idGerado = rs.getInt(1);
            livroGerado = new LivroDigitalBuilder()
                .porId(idGerado)
                .constroi();                   
        }
        con.close();
        return livroGerado;
    }
    
    @Override
    public void deletar(LivroDigital livro) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("delete from livros where id_livro = ?");
        comando.setInt(1, livro.getId());
        comando.execute();
        con.close();
    }
    
    @Override
    public void atualizar(LivroDigital livro) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("UPDATE livros SET titulo = ?, autor = ?, descricao = ? WHERE id_livro = ?");
        comando.setString(1, livro.getTitulo());
        comando.setString(2, livro.getAutor());
        comando.setString(3, livro.getDescricao());
        comando.setInt(4, livro.getId());
        comando.execute();
        con.close();
    }
    
    @Override
    public Livro consultarById(LivroDigital livro) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("select * from livros l left join fichatecnica f on l.id_livro = f.id_livro where l.id_livro = ?");
        comando.setInt(1, livro.getId());
        ResultSet rs = comando.executeQuery();
        Livro livroConsulta = null;
        if (rs.next()){          
            FichaTecnicaDAO fichaTecnicaDAO = new FichaTecnicaDAO();
            FichaTecnica fichaTecnica = new FichaTecnica(rs.getInt("id_ficha"), rs.getInt("paginas"), rs.getInt("anoDePublicacao"),
            rs.getString("editora"), rs.getString("idioma"));
            
            String generoBanco = rs.getString("genero");
            List<String> generos = new ArrayList<>();

            if (generoBanco != null && !generoBanco.isEmpty()) {
                generos = Arrays.asList(generoBanco.split(", "));
            }
            
            livroConsulta = new LivroDigitalBuilder()
                            .porId(rs.getInt("id_livro")) 
                            .comTitulo(rs.getString("titulo")) 
                            .comAutor(rs.getString("autor")) 
                            .comDescricao(rs.getString("descricao"))
                            .comGenero(generos) 
                            .comFicha(fichaTecnica)
                            .constroi();
        } 
        con.close();
        return livroConsulta;
    }
    
    @Override
    public List<Livro> consultarTodos() throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("select * from livros l left join fichatecnica f on l.id_livro = f.id_livro");
        ResultSet rs = comando.executeQuery();
        
        List<Livro> livros = new ArrayList<Livro>();
        while(rs.next()){
            if(rs.getString("tipo_livro").equals("DIGITAL")){
                FichaTecnica fichaTecnica = new FichaTecnica(rs.getInt("id_ficha"), rs.getInt("paginas"),rs.getInt("anoDePublicacao"),
                rs.getString("editora"), rs.getString("idioma"));
                
                String generoBanco = rs.getString("genero");
                List<String> generos = new ArrayList<>();

                if (generoBanco != null && !generoBanco.isEmpty()) {
                    generos = Arrays.asList(generoBanco.split(", "));
                }
                
                Livro livro = new LivroDigital(rs.getInt("id_livro"), rs.getString("titulo"), 
                rs.getString("autor"), rs.getString("descricao"), generos, fichaTecnica, rs.getString("tipo_livro"), rs.getString("formato"), rs.getDouble("tamanho"));
                livros.add(livro); 
            }         
        }   
        con.close();
        return livros;
    }
}
