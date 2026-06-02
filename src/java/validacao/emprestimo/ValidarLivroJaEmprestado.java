/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validacao.emprestimo;
import DAO.EmprestimoDAO;
import model.Livro;
import model.Usuario;
/**
 *
 * @author drak
 */
public class ValidarLivroJaEmprestado implements IValidadorEmprestimo {

    private EmprestimoDAO emprestimoDao;
    private Usuario usuario;
    private Livro livro;

    public ValidarLivroJaEmprestado(
            EmprestimoDAO emprestimoDao,
            Usuario usuario,
            Livro livro
    ) {
        this.emprestimoDao = emprestimoDao;
        this.usuario = usuario;
        this.livro = livro;
    }

    @Override
    public void validar() throws Exception {
        if (emprestimoDao.clienteJaPegouLivroAberto(usuario, livro)) {
            throw new Exception("Este usuário já está com esse livro emprestado.");
        }
    }
}
