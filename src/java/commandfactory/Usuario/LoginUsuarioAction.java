/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commandfactory.Usuario;

import DAO.UsuarioDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;

/**
 *
 * @author drak
 */
public class LoginUsuarioAction implements ICommand{
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        Usuario usuarioLogin = new Usuario(0,"", email, senha, "", "", "");
        
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.login(usuarioLogin);
 
        if (usuario != null) {
 
            HttpSession sessao = request.getSession();
            sessao.setAttribute("usuarioLogado", usuario);
 
            if (usuario.getRole().equals("LEITOR")) {
                response.sendRedirect("painelleitor.jsp");
            } else {
                response.sendRedirect("index.jsp");
            }
 
            return null;
 
        } else {
 
            request.setAttribute("msg", "Email ou senha inválidos.");
            return "login.jsp";
        }
    }
}
