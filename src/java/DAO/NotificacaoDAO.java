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
import java.util.List;
import java.util.Map;
import model.Email;
import model.Livro;
import model.Notificacao;
import model.SMS;
import model.Usuario;
import model.Whatsapp;
import util.Conexao;

/**
 *
 * @author kcarl
 */
public class NotificacaoDAO {
    public void cadastrar(Notificacao notificacao, Usuario usuario) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("insert into notificacoes (email, sms, whatsapp, id_usuario) values (?, ?, ?, ?)");
        Map <String, Boolean> preferenciasNotificacoes = notificacao.getNotificacoes();
        comando.setBoolean(1, preferenciasNotificacoes.getOrDefault("Email", true));
        comando.setBoolean(2, preferenciasNotificacoes.getOrDefault("SMS", false));
        comando.setBoolean(3, preferenciasNotificacoes.getOrDefault("Whatsapp", false));
        comando.setInt(4, usuario.getId());
        comando.execute();
        con.close();
    }
    
    public Notificacao consultarById(Usuario usuario, Livro livro) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("select * from notificacoes where id_usuario = ?");
        comando.setInt(1, usuario.getId());
        ResultSet rs = comando.executeQuery();
        Notificacao notificacao = null;
        if (rs.next()){
            boolean sms = rs.getBoolean("sms");
            boolean whatsapp = rs.getBoolean("whatsapp");
            
            notificacao = new Email(livro);   
                     
            if(sms){
                notificacao = new SMS(notificacao, livro);
            } 
            if(whatsapp){
                notificacao = new Whatsapp(notificacao);
            }
        }    
        con.close();
        return notificacao;
    }
    
    public List<Notificacao> consultarTodos() throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("select usuarios.*, role.nome as nome_role from usuarios inner join role on usuarios.id_role = role.id_role");
        ResultSet rs = comando.executeQuery();
        
        List<Notificacao> notificacoes = new ArrayList<Notificacao>();
        while(rs.next()){
            Usuario usuario = new Usuario(rs.getInt("id_usuario"), rs.getString("nome"), rs.getString("email"),rs.getString("senha"), rs.getString("nome_role"), rs.getString("tipo_usuario"), rs.getString("celular"));
            //usuarios.add(usuario);
        }   
        con.close();
        return notificacoes;
    }
}
