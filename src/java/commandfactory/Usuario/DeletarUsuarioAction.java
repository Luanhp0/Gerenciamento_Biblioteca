/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commandfactory.Usuario;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;
import DAO.UsuarioDAO;
import model.UsuarioBuilder;

/**
 *
 * @author kcarl
 */
public class DeletarUsuarioAction implements ICommand{
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mensagem;
        String pagina;
        UsuarioDAO usuarioDao = new UsuarioDAO();
        Usuario usuario;
        try {
            int id = Integer.parseInt(request.getParameter("txtid"));
            usuario = new UsuarioBuilder()
                    .comIdUsuario(id)
                    .constroi();
                
                
            usuarioDao.deletar(usuario);
            List<Usuario> listaClientes = usuarioDao.consultarTodos();
            request.setAttribute("listaUsuarios", listaClientes);
            mensagem = "Deletado com sucesso.";
            System.out.println("Deletado com sucesso.");
            pagina = "resultadoconsultartodosCliente.jsp";
        } catch (ClassNotFoundException | SQLException | NumberFormatException ex) {
            mensagem = "Erro ao deletar.";
            pagina = "erro.jsp";
        }
        request.setAttribute("mensagem", mensagem);
        return pagina;
    }
}
