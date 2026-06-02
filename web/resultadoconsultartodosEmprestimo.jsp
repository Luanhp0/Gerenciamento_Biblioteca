<%-- 
    Document   : resultadoconsultartodosEmprestimo
    Created on : Apr 12, 2026, 1:08:05 PM
    Author     : drak
--%>

<%@page import="java.util.List"%>
<%@page import="model.Emprestimo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Usuario"%>


<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<%
    if (!usuario.getRole().equals("ADMIN")
            && !usuario.getRole().equals("BIBLIOTECARIO")) {

        response.sendRedirect("index.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Lista de Empréstimos</title>
        <link rel="stylesheet" href="estilos/style.css">
    </head>
    <body>
        <%
            String msg = (String) request.getAttribute("msg");    
        %>
            
        <%
            List<Emprestimo> emprestimos = (List<Emprestimo>) request.getAttribute("emprestimos");

        %>
        <div class="container-acervo">
            <h1 class="titulo-pagina">Empréstimos Cadastrados</h1>
            <%if (emprestimos != null && !emprestimos.isEmpty()) {%>
                <table border="1" cellpadding="10" class="tabela-livros">
                    <tr>
                        <th>ID</th>
                        <th>Livro</th>
                        <th>Cliente</th>
                        <th>Data Empréstimo</th>
                        <th>Data Devolução</th>
                        <th>Data Devolução Realizada</th>
                        <th>Status</th>
                        <th>Devolução</th>
                        
                        <th class="col-acao">Editar</th>
                        <th class="col-acao">Deletar</th>
                    </tr>

                    <%
                        for (Emprestimo emprestimo : emprestimos) {
                    %>
                    <tr>
                        <td><%= emprestimo.getId() %></td>
                        <td><%= (emprestimo.getLivro() != null) ? emprestimo.getLivro().getTitulo() : "Sem livro" %></td>
                        <td><%= (emprestimo.getCliente() != null) ? emprestimo.getCliente().getNome() : "Sem cliente" %></td>
                        <td><%= emprestimo.getDataEmprestimo() %></td>
                        <td><%= emprestimo.getDataDevolucao() %></td>
                        <td><%= emprestimo.getDataDevolucaoRealizada() %></td>
                        <td>
                            <% if (emprestimo.estaAtrasado()) { %>
                                <span style="color:red; font-weight:bold;">Atrasado</span>
                            <% } else { %>
                                <span style="color:green;">Em dia</span>
                            <% } %>
                        </td>
                        <td> <%-- pegando método dataDevolucaoRealizada para mostrar se foi devolvido --%>
                            <% if (emprestimo.getDataDevolucaoRealizada() == null) { %>
                                <form action="EmprestimoController" method="post">
                                    <input type="hidden" name="opcao" value="RegistrarDevolucao">
                                    <input type="hidden" name="txtid" value="<%= emprestimo.getId() %>">
                                    <button type="submit">Devolver</button>
                                </form>
                            <% } else { %>
                                <% if (emprestimo.foiDevolvidoComAtraso()) { %>
                                    <span style="color:red; font-weight:bold;">Devolvido com atraso</span>
                                <% } else { %>
                                    <span style="color:green; font-weight:bold;">Devolvido em dia</span>
                                <% } %>
                            <% } %>
                        </td>
                        <td class="col-acao">
                            <form action="EmprestimoController" method="get" style="display:inline;">
                                <input type="hidden" name="opcao" value="CarregarParaAtualizar">
                                <input type="hidden" name="txtid" value="<%= emprestimo.getId() %>">
                                <button type="submit">✏️</button>
                            </form>
                        </td>
                        <td class="col-acao">
                            <form action="EmprestimoController" method="post" style="display:inline;">
                                <input type="hidden" name="opcao" value="Deletar">
                                <input type="hidden" name="txtid" value="<%= emprestimo.getId() %>">
                                <button type="submit" onclick="return confirm('Deseja realmente excluir este empréstimo?')">
                                    🗑️
                                </button>
                            </form>
                        </td>
                    </tr>
                    <%}%>
                </table>
            <%} else {%>
                <p>Nenhum empréstimo cadastrado.</p>
            <%}%>

            <br>
        </div>
        <div class="nav-actions">
            <a href="index.jsp" class="nav-item" data-tooltip="Página Inicial">
                <i class="fas fa-home"></i> 🏠
            </a>
        </div>

    </body>
</html>
