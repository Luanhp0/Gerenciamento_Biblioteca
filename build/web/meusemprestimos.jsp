<%-- 
    Document   : meusemprestimos
    Created on : May 21, 2026, 11:16:11 AM
    Author     : drak
--%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.util.List"%>
<%@page import="model.Emprestimo"%>
<%@page import="model.Usuario"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.sql.Date"%>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    if (!usuario.getRole().equals("LEITOR")) {
        response.sendRedirect("index.jsp");
        return;
    }

    List<Emprestimo> listaEmprestimos =
        (List<Emprestimo>) request.getAttribute("listaEmprestimos");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Meus Emprestimos</title>
        <link rel="stylesheet" href="estilos/style.css">
    </head>
    <body>
        
        <div class="meus-emprestimos">
            <h1>Meus Empréstimos</h1>

        <table border="1" class="tabela-emprestimos">
            <tr>
                <th>Livro</th>
                <th>Data do Empréstimo</th>
                <th>Data para Devolver</th>
                <th>Data Devolvida</th>
                <th>Status</th>
                <th>Dias Restantes</th>
            </tr>

            <%
                if (listaEmprestimos != null) {
                    for (Emprestimo e : listaEmprestimos) {

                        String status = "";
                        String diasRestantes = "-";

                        if (e.getDataDevolucaoRealizada() == null) {
                            status = "Em aberto";

                            LocalDate hoje = LocalDate.now();
                            LocalDate dataDevolucao = LocalDate.parse(e.getDataDevolucao().toString());

                            long dias = ChronoUnit.DAYS.between(hoje, dataDevolucao);

                            if (dias >= 0) {
                                diasRestantes = dias + " dias";
                            } else {
                                diasRestantes = "Atrasado " + Math.abs(dias) + " dias";
                            }

                        } else if (e.getDataDevolucaoRealizada().after(e.getDataDevolucao())) {
                            status = "Entregue com atraso";
                        } else {
                            status = "Entregue no prazo";
                        }
            %>

            <tr class="tabela-emprestimos">
                <td><%= e.getLivro().getTitulo() %></td>
                <td><%= e.getDataEmprestimo() %></td>
                <td><%= e.getDataDevolucao() %></td>
                <td><%= e.getDataDevolucaoRealizada() %></td>
                <td><%= status %></td>
                <td><%= diasRestantes %></td>
            </tr>

            <%
                    }
                }
            %>
        </table>

        <br>

        <a href="painelleitor.jsp">Voltar</a>

        </div>

        
    </body>
</html>
