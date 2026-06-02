<%-- 
    Document   : atualizarcliente
    Created on : Apr 12, 2026, 6:23:31 PM
    Author     : drak
--%>
<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Atualizar Cliente</title>
        <link rel="stylesheet" href="estilos/style.css">
    </head>
    <body>
        <%
            Usuario usuario = (Usuario) request.getAttribute("usuario");
        %>

        <div class="mains-container">
            <h1 class="page-title">Atualizar Cliente</h1>
            <% if (usuario != null) { %>
            <form action="UsuarioController" method="post" class="form_biblioteca">
                <input type="hidden" name="opcao" value="Atualizar">
                <input type="hidden" name="txtid" value="<%= usuario.getId() %>">

                <div class="form-group">
                    <label>ID:</label>
                    <input type="text" value="<%= usuario.getId() %>" disabled>
                </div>

                <br>

                <div class="form-group">
                    <label>Nome:</label>
                    <input type="text" name="txtnome" value="<%= usuario.getNome() %>" required>
                </div>

                <br>

                <div class="form-group">
                    <label>Email:</label>
                    <input type="email" name="txtemail" value="<%= usuario.getEmail() %>" required>
                </div>

                <br>

                <button type="submit" class="btn-cadastrar">Salvar Alterações</button>
            </form>
            <% } else { %>
                <p>Cliente não encontrado.</p>
            <% } %>
        </div>

        <br>
        <div>
        <a href="index.jsp">
            <figure>
                <img src="imagens/home.png" alt="Home">
            </figure>
        </a>
    </div>
    </body>
</html>
