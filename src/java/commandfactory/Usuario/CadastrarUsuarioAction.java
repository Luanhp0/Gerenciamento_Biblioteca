/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commandfactory.Usuario;

import DAO.NotificacaoDAO;
import DAO.UsuarioDAO;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Email;
import model.Notificacao;
import model.SMS;
import model.Usuario;
import model.UsuarioBuilder;
import model.Whatsapp;

/**
 *
 * @author drak
 */
public class CadastrarUsuarioAction implements ICommand{
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mensagem;
        String pagina;
        try{
            String nome = request.getParameter("txtnome");
            String email = request.getParameter("txtemail");
            String senha = request.getParameter("txtsenha");
            String tipoUsuario = request.getParameter("txttipousuario");
            String[] notificacoes = request.getParameterValues("tiposnotificacao");
            String celular = request.getParameter("txtcelular");
            String role;
            int idRole = Integer.parseInt(request.getParameter("txtrole"));
            switch (idRole) {
                case 1: role = "ADMIN"; break;
                case 2: role = "BIBLIOTECARIO"; break;
                default: role = "LEITOR"; break;
            }


            UsuarioDAO usuarioDao = new UsuarioDAO();
            NotificacaoDAO notificacaoDao = new NotificacaoDAO();
            
            Notificacao notificacao = new Email();

            int idUsuario = usuarioDao.buscarUltimoId();

            Usuario usuario = new UsuarioBuilder()
                    .comNome(nome)
                    .comEmail(email)
                    .comSenha(senha)
                    .comRole(role)
                    .comTipoUsuario(tipoUsuario)
                    .comIdUsuario(idUsuario)
                    .comCelular(celular)
                    .constroi();
            
            
            Usuario usuarioGerado = usuarioDao.cadastrar(usuario);
            notificacaoDao.cadastrar(notificacao, usuarioGerado);
            
            request.setAttribute("msg", "Usuário cadastrado com sucesso!");

            pagina = "resultadoconsultartodosCliente.jsp";
        } catch (ClassNotFoundException | SQLException | NumberFormatException ex){
            ex.printStackTrace();
            request.setAttribute("mensagem", "Erro ao cadastrar: " + ex.getMessage());
            pagina =  "/erro.jsp";
        }
        return pagina;
    }
        
}
