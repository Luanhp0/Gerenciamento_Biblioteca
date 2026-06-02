/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commandfactory.Livro;

import DAO.LivroDAO;
import DAO.LivroDigitalDAO;
import DAO.LivroFisicoDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Livro;
import model.LivroBuilder;
import model.LivroDigitalBuilder;
import model.LivroFisicoBuilder;

/**
 *
 * @author kcarl
 */
public class LivroFactory {
    
    public static LivroDAO instanciarDAO(ResultSet rs) throws SQLException{
        String tipo = rs.getString("tipo_livro");
        
        if(tipo.equals("FISICO")){
            return new LivroFisicoDAO();
        } else {
            return new LivroDigitalDAO();
        }
    }
    
    
    public static LivroBuilder instanciarBuilder(ResultSet rs) throws SQLException{
        String tipo = rs.getString("tipo_livro");
        
        if(tipo.equals("FISICO")){
            return new LivroFisicoBuilder();
        } else {
            return new LivroDigitalBuilder();
        }
    }
    
}
