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
public class CarregarParaAtualizarUsuarioAction implements ICommand{
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mensagem;
        String pagina;
        UsuarioDAO clienteDao = new UsuarioDAO();

        try {
            int id = Integer.parseInt(request.getParameter("txtid"));

            Usuario usuario = new UsuarioBuilder()
                    .comIdUsuario(id)
                    .constroi();

            Usuario usuarioConsulta = clienteDao.consultarById(usuario);

            request.setAttribute("usuario", usuarioConsulta);
            pagina =  "atualizarcliente.jsp";

        } catch (ClassNotFoundException | SQLException | NumberFormatException ex) {
            mensagem = "Erro ao carregar cliente para atualização.";
            request.setAttribute("mensagem", mensagem);
            pagina =  "erro.jsp";
        }
        return pagina;
    }
}
