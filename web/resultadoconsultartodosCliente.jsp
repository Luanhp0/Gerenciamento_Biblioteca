<%-- 
    Document   : resultadoconsultartodosCliente
    Created on : Apr 12, 2026, 3:38:05 PM
    Author     : drak
--%>
<%@page import="model.Usuario"%>
<%@page import="java.util.List"%>
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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuarios Cadastrados</title>
        <link rel="stylesheet" href="estilos/style.css">
    </head>
    <body>
        <%
            List<Usuario> listaUsuarios = (List<Usuario>) request.getAttribute("listaUsuarios");
            if (listaUsuarios != null && !listaUsuarios.isEmpty()) {
        %>
        <div class="container-acervo">
            <h1 class="titulo-pagina">Clientes Cadastrados</h1>
            <table border="1" class="tabela-livros">
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Email</th>
                    <th>Tipo de Usuário</th>
                    <th class="col-acao">Deletar</th>
                    <th class="col-acao">Editar</th>
                    
                </tr>

                <%
                    for (Usuario usuarioUnico : listaUsuarios) {
                %>
                <tr>
                    <td><%= usuarioUnico.getId() %></td>
                    <td><%= usuarioUnico.getNome() %></td>
                    <td><%= usuarioUnico.getEmail() %></td>
                    <td><%= usuarioUnico.getTipoUsuario() %></td>
                    <td class="col-acao">
                        <form action="UsuarioController" method="post" style="display:inline;">
                            <input type="hidden" name="opcao" value="Deletar">
                            <input type="hidden" name="txtid" value="<%= usuarioUnico.getId() %>">
                            <button type="submit" onclick="return confirm('Deseja realmente excluir este cliente?')" class="btn-deletar-icone">
                                🗑️
                            </button>
                        </form>
                    </td>
                    <td class="col-acao">
                        <form action="UsuarioController" method="get" style="display:inline;">
                            <input type="hidden" name="opcao" value="CarregarParaAtualizar">
                            <input type="hidden" name="txtid" value="<%= usuarioUnico.getId() %>" class="btn-editar">
                            <button type="submit">✏️</button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>

            <%
                } else {
            %>
                <p>Nenhum cliente cadastrado.</p>
            <%
                }
            %>

            <br>
        </div>
        <div class="nav-actions">
            <a href="index.jsp" class="nav-item" data-tooltip="Página Inicial">
                <i class="fas fa-home"></i> 🏠
            </a>

            <a href="novocliente.jsp" class="nav-item nav-item-add" data-tooltip="Novo Cadastro">
                <i class="fas fa-plus"></i> ➕
            </a>
        </div>
    </body>
</html>
