<%-- 
    Document   : novoemprestimo
    Created on : Apr 11, 2026, 5:43:54 PM
    Author     : drak
--%>
<%@page import="model.Usuario"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
    // deixa que apenas o adm possa acessar essas telas
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    if (!usuario.getRole().equals("ADMIN")) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<%@page import="model.Cliente"%>
<%@page import="model.Livro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Novo Empréstimo</title>
        <link rel="stylesheet" href="estilos/style.css">
    </head>
    <body>
        <%
            Livro livro = (Livro) request.getAttribute("livro");
        %>

        <div class="mains-container">
            <h1 class="page-title">Cadastro de Empréstimo</h1>
            
            <% if (livro != null) { %>
                <form name="cadastroEmprestimo" action="EmprestimoController" method="GET">
                    <div class="grid-container">
                        <input type="hidden" name="txtidlivro" value="<%= livro.getId() %>">
                        <input type="hidden" name="txttipo" value="<%= livro.getTipo() %>">  
                        
                        <div class="form-group">
                            <label>ID do Livro:</label>
                            <input type="text" value="<%= livro.getId() %>" disabled>
                        </div>

                        <div class="form-group">
                            <label>Título:</label>
                            <input type="text" value="<%= livro.getTitulo() %>" disabled>
                        </div>

                        <div class="form-group">
                            <label>Autor:</label>
                            <input type="text" value="<%= livro.getAutor() %>" disabled>
                        </div>

                        <div class="form-group">
                            <label>ID do Cliente:</label>
                            <input type="number" name="txtidusuario" value="" placeholder="Digite o id do usuario">
                        </div>

                        

                        <button type="submit" name="opcao" value="Cadastrar" class="btn-cadastrar">Cadastrar Empréstimo</button>
                        <button type="submit" name="opcao" value="ConsultarTodos" class="btn-consultar">Consultar Empréstimos</button>
                    </div>
                </form>
            <% } else { %>
                <h2>Livro não encontrado para empréstimo.</h2>
            <% } %>

        </div>  
        <div class="nav-actions">
            <a href="index.jsp" class="nav-item" data-tooltip="Página Inicial">
                <i class="fas fa-home"></i> 🏠
            </a>
        </div>
    </body>
</html>