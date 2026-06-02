/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author kcarl
 */
public class Conexao {
    public static Connection getConexaoMySQL() throws ClassNotFoundException, SQLException {
        String DRIVER = "com.mysql.cj.jdbc.Driver";
        String URL = "jdbc:mysql://localhost:3306/aulaPP";
        String USER = "root";
        String PASSWORD = "";
        Class.forName(DRIVER);
        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        return con;
    }
}
