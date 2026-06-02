<%-- 
    Document   : resultadoconsultarbyid
    Created on : 10 de abr. de 2026, 18:57:41
    Author     : kcarl
--%>

<%@page import="model.Livro"%>
<%@page import="model.Usuario"%>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="estilos/style.css">
    </head>
    <body>
        <%Livro livro = (Livro) request.getAttribute("livro");%>
        <div class="mains-container">
            <h1 class="page-title">Consulta do Livro</h1>
            <div class="form_biblioteca">
                <div class="grid-container">

                    <%if (livro != null) {%>
                    
                        <div class="form-group">
                        <label>Id: </label> <%=livro.getId()%> 
                        </div>

                        <div class="form-group">
                            <label>Título: </label> <%=livro.getTitulo()%>
                        </div>

                        <div class="form-group">
                            <label>Autor: </label> <%=livro.getAutor()%>
                        </div>

                        <div class="form-group">
                            <label>Resumo:  </label> <%=livro.getDescricao()%>
                        </div>

                        <div class="form-group">
                            <label>Gênero: </label> <%= String.join(", ", livro.getGenero()) %>
                        </div>

                        <div class="form-group">
                            <label>Páginas: </label> <%=livro.getPaginas()%>
                        </div>

                        <div class="form-group">
                            <label>Ano de Publicação </label> <%=livro.getAnoDePublicacao()%>
                        </div>

                        <div class="form-group">
                            <label>Editora </label> <%=livro.getEditora()%>
                        </div>

                        <div class="form-group">
                            <label>Idioma: </label> <%=livro.getIdioma()%>
                        </div>
                    <%} else {%>
                        <h2>Produto não encontrado.</h2>
                    <%}%>

                    

                </div>
                <% if (livro != null) { %>
                    

                    <form action="LivroController" method="post" style="display:inline;"">
                            <input type="hidden" name="opcao" value="Deletar">
                            <input type="hidden" name="txtid" value="<%= livro.getId() %>">
                            <button type="submit" onclick="return confirm('Deseja realmente excluir este livro?')" class="btn-deletar">DELETAR</button>                         
                    </form>
                    <form action="LivroController" method="GET">
                        <input type="hidden" name="txtid" value="<%= livro.getId() %>">
                        <input type="hidden" name="txttipo" value="<%= livro.getTipo() %>">
                        <button type="submit" name="opcao" value="CarregarEmprestimo" class="btn-cadastrar">Empréstimo</button>
                    </form>
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
