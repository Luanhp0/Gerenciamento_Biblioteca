/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commandfactory.Usuario;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;
import DAO.UsuarioDAO;
import model.UsuarioBuilder;

/**
 *
 * @author kcarl
 */
public class ConsultarByIdUsuarioAction implements ICommand{
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
                    
            Usuario usuarioConsulta = usuarioDao.consultarById(usuario);
            request.setAttribute("usuario", usuarioConsulta);
            pagina = "resultadoconsultarbyid.jsp";
        } catch (ClassNotFoundException | SQLException | NumberFormatException ex) {
            mensagem = "Erro ao consultar";
            request.setAttribute("msg", mensagem);
            pagina = "erro.jsp";
        }
        return pagina;
    }
}
