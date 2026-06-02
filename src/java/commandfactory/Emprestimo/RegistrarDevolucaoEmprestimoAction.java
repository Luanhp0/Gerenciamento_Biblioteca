/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commandfactory.Emprestimo;

import DAO.EmprestimoDAO;
import DAO.LivroFisicoDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Emprestimo;
import model.EmprestimoBuilder;
import model.LivroFisico;
import model.LivroFisicoBuilder;

public class RegistrarDevolucaoEmprestimoAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        EmprestimoDAO emprestimoDao = new EmprestimoDAO();
        LivroFisicoDAO livroDao = new LivroFisicoDAO();

        int id = Integer.parseInt(request.getParameter("txtid"));

        Emprestimo emprestimo = new EmprestimoBuilder()
                .porId(id)
                .constroi();

        Emprestimo emprestimoConsulta = emprestimoDao.consultarById(emprestimo);
        emprestimoDao.registrarDevolucao(emprestimo);

        LivroFisico livro = (LivroFisico)new LivroFisicoBuilder()
                .porId(emprestimoConsulta.getLivro().getId())
                .constroi();

       

        request.setAttribute("emprestimos", emprestimoDao.consultarTodos());
        request.setAttribute("msg", "Livro devolvido com sucesso.");

        return "resultadoconsultartodosEmprestimo.jsp";
    }
}
