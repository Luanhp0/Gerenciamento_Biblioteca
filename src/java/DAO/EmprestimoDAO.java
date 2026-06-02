/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.Statement;

import commandfactory.Livro.LivroFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Emprestimo;
import model.EmprestimoBuilder;
import model.Livro;
import model.LivroBuilder;
import model.LivroDigitalBuilder;
import model.LivroFisicoBuilder;
import model.Usuario;
import model.UsuarioBuilder;
import util.Conexao;

/**
 *
 * @author kcarl
 */
public class EmprestimoDAO {
    public Emprestimo cadastrar(Emprestimo emprestimo) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("insert into emprestimo (id_usuario, id_livro, dataEmprestimo, dataDevolucao) values (?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
        comando.setInt(1, emprestimo.getUsuarioId());
        comando.setInt(2, emprestimo.getLivroId());
        comando.setDate(3, new java.sql.Date(emprestimo.getDataEmprestimo().getTime()));
        comando.setDate(4, new java.sql.Date(emprestimo.getDataDevolucao().getTime()));
        comando.execute();

        ResultSet rs = comando.getGeneratedKeys();
        Emprestimo emprestimoGerado = null;
        if (rs.next()) {
            int idGerado = rs.getInt(1);
            emprestimoGerado = new EmprestimoBuilder()
                    .porId(idGerado)
                    .comUsuario(emprestimo.getCliente())
                    .comLivro(emprestimo.getLivro())
                    .comDataEmprestimo(emprestimo.getDataEmprestimo())
                    .comDataDevolucao(emprestimo.getDataDevolucao())
                    .constroi();
        }
        con.close();
        return emprestimoGerado;
    }
    
    public void deletar(Emprestimo emprestimo) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("delete from emprestimo where id_emprestimo = ?");
        comando.setInt(1, emprestimo.getId());
       
        comando.execute();
        con.close();
    }
    
    public void atualizar(Emprestimo emprestimo) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("update emprestimo set dataDevolucaoRealizada = ? where id_emprestimo = ?");
        comando.setDate(1, (Date) emprestimo.getDataDevolucaoRealizada());
        comando.setInt(2, emprestimo.getId());
        comando.executeUpdate();
        comando.close();
        con.close();
    }
    
    
    public Emprestimo consultarById(Emprestimo emprestimo) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("select e.*, l.tipo_livro from emprestimo e " + "inner join livros l on e.id_livro = l.id_livro " + "where e.id_emprestimo = ?");
        comando.setInt(1, emprestimo.getId());
        ResultSet rs = comando.executeQuery();
        Emprestimo consultaEmprestimo = null;
        if (rs.next()){
            UsuarioDAO usuarioDao = new UsuarioDAO();
            LivroDAO livroDAO = LivroFactory.instanciarDAO(rs);
            Usuario usuarioConsulta = new UsuarioBuilder()
                    .comIdUsuario(rs.getInt("id_usuario"))
                    .constroi();
            Usuario usuario = usuarioDao.consultarById(usuarioConsulta);
            Livro livroConsulta = LivroFactory.instanciarBuilder(rs)
                    .porId(rs.getInt("id_livro"))
                    .constroi();
            Livro livro = livroDAO.consultarById(livroConsulta);
            consultaEmprestimo = new Emprestimo( rs.getInt("id_emprestimo"),usuario,livro,rs.getDate("dataEmprestimo"),rs.getDate("dataDevolucao"),rs.getDate("dataDevolucaoRealizada"));           
        } 
        con.close();
        return consultaEmprestimo;
    }
    
    
    public List<Emprestimo> consultarTodos() throws ClassNotFoundException, SQLException {

        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("SELECT e.*, l.tipo_livro " + "FROM emprestimo e " + "INNER JOIN livros l ON e.id_livro = l.id_livro");
        ResultSet rs = comando.executeQuery();
        List<Emprestimo> emprestimos = new ArrayList<>();
        while (rs.next()) {

            UsuarioDAO usuarioDAO = new UsuarioDAO();

            Usuario usuarioConsulta = new UsuarioBuilder()
                    .comIdUsuario(rs.getInt("id_usuario"))
                    .constroi();

            Usuario usuario = usuarioDAO.consultarById(usuarioConsulta);

            LivroDAO livroDAO = LivroFactory.instanciarDAO(rs);
            Livro livroConsulta = LivroFactory.instanciarBuilder(rs)
                    .porId(rs.getInt("id_livro"))
                    .constroi();
            Livro livro = livroDAO.consultarById(livroConsulta);

            Emprestimo emprestimo = new Emprestimo(
                    rs.getInt("id_emprestimo"),
                    usuario,
                    livro,
                    rs.getDate("dataEmprestimo"),
                    rs.getDate("dataDevolucao"),
                    rs.getDate("dataDevolucaoRealizada")
            );

            emprestimos.add(emprestimo);
        }
        rs.close();
        comando.close();
        con.close();

        return emprestimos;
    }
    
    public void registrarDevolucao(Emprestimo emprestimo) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("update emprestimo set dataDevolucaoRealizada = curdate() where id_emprestimo = ?");
        comando.setInt(1, emprestimo.getId());
        comando.executeUpdate();
        con.close();
    }
    
    //novo metodo para o usuario leitor ver os emprestimos dele
    
    public List<Emprestimo> consultarPorUsuario(Usuario usuario) throws Exception {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement( "select e.*, l.titulo, c.nome " + "from emprestimo e " + "inner join livros l on e.id_livro = l.id_livro " + "inner join usuario c on e.id_usuario = c.id_usuario " + "where c.id_usuario = ?");
        comando.setInt(1, usuario.getId());
        ResultSet rs = comando.executeQuery();
        List<Emprestimo> lista = new ArrayList<>();
        while (rs.next()) {
            UsuarioDAO usuarioDao = new UsuarioDAO();
            LivroDAO livroDAO = LivroFactory.instanciarDAO(rs);
            Usuario usuarioConsulta = new UsuarioBuilder()
                    .comIdUsuario(rs.getInt("id_usuario"))
                    .constroi();

            usuario = usuarioDao.consultarById(usuarioConsulta);
            Livro livroConsulta = LivroFactory.instanciarBuilder(rs)
                    .porId(rs.getInt("id_livro"))
                    .constroi();

            Livro livro = livroDAO.consultarById(livroConsulta);
            Emprestimo emprestimo = new Emprestimo(
                    rs.getInt("id_emprestimo"),
                    usuario,
                    livro,
                    rs.getDate("dataEmprestimo"),
                    rs.getDate("dataDevolucao"),
                    rs.getDate("dataDevolucaoRealizada")
            );
            lista.add(emprestimo);
        }

        rs.close();
        comando.close();
        con.close();
        return lista;

    }
    public boolean clienteJaPegouLivroAberto(Usuario usuario, Livro livro) throws Exception {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("select count(*) as total from emprestimo " + "where id_usuario = ? and id_livro = ? and dataDevolucaoRealizada is null");
        comando.setInt(1, usuario.getId());
        comando.setInt(2, livro.getId());
        ResultSet rs = comando.executeQuery();

        boolean jaPegou = false;

        if (rs.next()) {
            jaPegou = rs.getInt("total") > 0;
        }

        rs.close();
        comando.close();
        con.close();

        return jaPegou;
    }
}