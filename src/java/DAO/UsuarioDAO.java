/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Usuario;
import util.Conexao;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.UsuarioBuilder;
/**
 *
 * @author drak
 */
public class UsuarioDAO {
    public Usuario cadastrar(Usuario usuario)throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("insert into usuarios (nome, email, senha, id_role, tipo_usuario, celular) values (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
       
        comando.setString(1, usuario.getNome());
        comando.setString(2, usuario.getEmail());
        comando.setString(3, usuario.getSenha());
        comando.setInt(4, usuario.getIdRole());
        comando.setString(5, usuario.getTipoUsuario());
        comando.setString(6, usuario.getCelular());
        comando.execute();
        
        ResultSet rs = comando.getGeneratedKeys();
        int idGerado = 0;
        Usuario usuarioGerado = null;
        if (rs.next()) {
            idGerado = rs.getInt(1);
            usuarioGerado = new UsuarioBuilder()
                .comIdUsuario(idGerado)
                .constroi();                   
        }
        con.close();
        return usuarioGerado;
    }
    
    public void deletar(Usuario usuario) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("delete from usuarios where id_usuario = ?");
        comando.setInt(1, usuario.getId());
        comando.execute();
        con.close();
    }
    
    public void atualizar(Usuario usuario) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("update usuarios set nome = ?, email = ? where id_usuario = ?");
        comando.setString(1, usuario.getNome());
        comando.setString(2, usuario.getEmail());
        comando.setInt(3, usuario.getId());
        comando.executeUpdate();
        con.close();
    }

    public Usuario consultarById(Usuario usuario) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("select usuarios.*, role.nome as nome_role from usuarios inner join role on usuarios.id_role = role.id_role where id_usuario = ?");
        comando.setInt(1, usuario.getId());
        ResultSet rs = comando.executeQuery();
        Usuario usuarioConsulta = null;
        if (rs.next()){
            
            usuarioConsulta = new Usuario(rs.getInt("id_usuario"), rs.getString("nome"), rs.getString("email"),rs.getString("senha"), rs.getString("nome_role"), rs.getString("tipo_usuario"), rs.getString("celular"));
        }    
        con.close();
        return usuarioConsulta;
    }
    
    
    public List<Usuario> consultarTodos() throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("select usuarios.*, role.nome as nome_role from usuarios inner join role on usuarios.id_role = role.id_role");
        ResultSet rs = comando.executeQuery();
        
        List<Usuario> usuarios = new ArrayList<Usuario>();
        while(rs.next()){
            Usuario usuario = new Usuario(rs.getInt("id_usuario"), rs.getString("nome"), rs.getString("email"),rs.getString("senha"), rs.getString("nome_role"), rs.getString("tipo_usuario"), rs.getString("celular"));
            usuarios.add(usuario);
        }   
        con.close();
        return usuarios;
    }
    
    public Usuario login(Usuario usuario) throws Exception {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("SELECT u.id_usuario, u.nome, u.email, u.senha, u.tipo_usuario, u.celular, r.nome AS role " + "FROM usuarios u INNER JOIN role r ON u.id_role = r.id_role " + "WHERE u.email = ? AND u.senha = ?");
        comando.setString(1, usuario.getEmail());
        comando.setString(2, usuario.getSenha());
        ResultSet rs = comando.executeQuery();
        usuario = null;

        if (rs.next()) {
            usuario = new Usuario(
                rs.getInt("id_usuario"),
                rs.getString("nome"),
                rs.getString("email"),
                rs.getString("senha"),
                rs.getString("role"),
                rs.getString("tipo_usuario"),
                rs.getString("celular")
            );
        }

        con.close();
        return usuario;
    }
    
    
    
    public int buscarUltimoId() throws Exception {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("select max(id_usuario) as id from usuarios");
        ResultSet rs = comando.executeQuery();
        int id = 0;
        if(rs.next()){
            id = rs.getInt("id");
        }
        con.close();
        return id;
    }
}
