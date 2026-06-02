/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commandfactory.Emprestimo;
import DAO.EmprestimoDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Emprestimo;
import model.Usuario;
/**
 *
 * @author drak
 */
public class ConsultarMeusEmprestimosEmprestimoAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        
        // precisa tirar isso
        if (usuario == null) {
            request.setAttribute("msg", "Você precisa fazer login.");
            return "login.jsp";
        }

        EmprestimoDAO dao = new EmprestimoDAO();

        List<Emprestimo> listaEmprestimos = dao.consultarPorUsuario(usuario);

        request.setAttribute("listaEmprestimos", listaEmprestimos);

        return "meusemprestimos.jsp";
    }
}
