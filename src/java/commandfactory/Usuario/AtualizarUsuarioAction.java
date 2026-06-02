/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commandfactory.Usuario;

import DAO.UsuarioDAO;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;
import model.UsuarioBuilder;

/**
 *
 * @author kcarl
 */
public class AtualizarUsuarioAction implements ICommand{
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mensagem;
        String pagina;
        UsuarioDAO usuarioDao = new UsuarioDAO();

        try {
            int id = Integer.parseInt(request.getParameter("txtid"));
            String nome = request.getParameter("txtnome");
            String email = request.getParameter("txtemail");
            String tipoUsuario = request.getParameter("txttipoUsuario");
            Usuario usuario = new UsuarioBuilder()
                    .comIdUsuario(id)
                    .comNome(nome)
                    .comEmail(email)
                    .comTipoUsuario(tipoUsuario)
                    .constroi();

            usuarioDao.atualizar(usuario);
            List<Usuario> listaUsuarios = usuarioDao.consultarTodos();
            request.setAttribute("listaUsuarios", listaUsuarios);
            
            mensagem = "Atualizado com sucesso.";
            pagina = "resultadoconsultartodosCliente.jsp";
        } catch (ClassNotFoundException | SQLException | NumberFormatException ex) {
            mensagem = "Erro ao Atualizar.";
            pagina = "erro.jsp";
        }

        request.setAttribute("msg", mensagem);
        return pagina;
    }
}
