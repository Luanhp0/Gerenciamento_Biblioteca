<%-- 
    Document   : atualizarlivro
    Created on : Apr 11, 2026, 4:11:35 PM
    Author     : drak
--%>


<%@page import="model.Usuario"%>
<%@page import="model.Livro"%>
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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Atualizar Livro</title>
        <link rel="stylesheet" href="estilos/style.css">
    </head>
    <body>
        <%
            Livro livro = (Livro) request.getAttribute("livro");
        %>

        <div class="mains-container">
            <h1 class="page-title">Atualizar Livro</h1>

            <div class="form_biblioteca">
                <% if (livro != null) { %>
                <form action="LivroController" method="POST">
                    <input type="hidden" name="txtid" value="<%=livro.getId()%>">
                    <input type="hidden" name="txttipo" value="<%=livro.getTipo()%>">

                    <div class="form-group">
                        <label>ID:</label>
                        <input type="text" value="<%=livro.getId()%>" disabled>
                    </div>

                    <div class="form-group">
                        <label>Título:</label>
                        <input type="text" name="txttitulo" value="<%=livro.getTitulo()%>">
                    </div>

                    <div class="form-group">
                        <label>Autor:</label>
                        <input type="text" name="txtautor" value="<%=livro.getAutor()%>">
                    </div>
                    
                    <div class="form-group">
                        <label>Resumo:</label>
                        
                        <textarea name="txtdescricao" rows="3" value="<%=livro.getDescricao()%>"><%=livro.getDescricao()%></textarea>
                    </div>

                    <button type="submit" name="opcao" value="Atualizar" class="btn-cadastrar">Atualizar</button>
                </form>
                <% } else { %>
                    <h2>Livro não encontrado para atualização.</h2>
                <% } %>
            </div>
        </div>
        <div class="nav-actions">
            <a href="index.jsp" class="nav-item" data-tooltip="Página Inicial">
                <i class="fas fa-home"></i> 🏠
            </a>
        </div>
    </body>
</html>
