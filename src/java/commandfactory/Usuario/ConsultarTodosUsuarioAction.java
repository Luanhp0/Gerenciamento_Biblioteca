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

/**
 *
 * @author kcarl
 */
public class ConsultarTodosUsuarioAction implements ICommand{
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception{
        UsuarioDAO usuarioDao = new UsuarioDAO();
        String pagina;
        try {
            List<Usuario> listaClientes = usuarioDao.consultarTodos();
            request.setAttribute("listaUsuarios", listaClientes);
            pagina = "resultadoconsultartodosCliente.jsp";
        } catch (Exception ex) {
                ex.printStackTrace(); // aparece no log do servidor
                request.setAttribute("mensagem", "Erro: " + ex.getMessage());
                pagina = "erro.jsp";
        }
        return pagina;
    }
}
