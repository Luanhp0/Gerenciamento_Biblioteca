/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validacao.emprestimo;
import model.Usuario;
import model.Livro;
/**
 *
 * @author drak
 */
public class ValidarLivroClienteExiste implements IValidadorEmprestimo{
    private Livro livro;
    private Usuario usuario;

    public ValidarLivroClienteExiste(
            Livro livro,
            Usuario usuario
    ) {

        this.livro = livro;
        this.usuario = usuario;
    }

    @Override
    public void validar() throws Exception {

        if (livro == null || usuario == null) {
            throw new Exception(
                    "Livro ou Cliente não localizados!"
            );
        }
    }
}
