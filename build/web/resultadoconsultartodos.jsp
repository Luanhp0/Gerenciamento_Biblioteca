<%-- 
    Document   : resultadoconsultartodos
    Created on : 10 de abr. de 2026, 19:08:47
    Author     : kcarl
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="model.Livro"%>

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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Acervo Completo</title>
        <link rel="stylesheet" href="estilos/style.css">
    </head>
    <body>
        <%
            List<Livro> listaLivros = (List<Livro>) request.getAttribute("listaLivros");
        %>
        <div class="container-acervo">
            <h1 class="titulo-pagina">Acervo Completo</h1>
            <table border="1" class="tabela-livros">
                <tr>
                    <th>ID</th><th>Título</th> <th>Autor</th> <th>Resumo</th> <th>Gênero</th> 
                    <th>Páginas</th> <th>Ano de Publicação</th> <th>Editora</th> <th>Idioma</th> <th>Capa/Formato</th> <th>Disponível/Tamanho</th>
                    <th>Tipo</th>
                     <th class="col-acao">Deletar</th>
                    <th class="col-acao">Editar</th>
                    <th class="col-acao">Consultar</th>
                </tr>

                <%for(Livro livro : listaLivros) {%>
                    <%Map<String, Object> dadosTela = livro.getDadosEspecificos();%>
                    <tr>
                        <td><%out.print(livro.getId());%></td>
                        <td><%out.print(livro.getTitulo());%></td>
                        <td><%out.print(livro.getAutor());%></td>
                        <td><%out.print(livro.getDescricao());%></td>
                        <td><%= String.join(", ", livro.getGenero()) %></td>
                        <td><%out.print(livro.getPaginas());%></td>
                        <td><%out.print(livro.getAnoDePublicacao());%></td>
                        <td><%out.print(livro.getEditora());%></td>
                        <td><%out.print(livro.getIdioma());%></td>
                        <td><%out.print(dadosTela.get("CapaFormato"));%></td>
                        <td><%out.print(dadosTela.get("DisponivelTamanho"));%></td>
                        <td><%out.print(dadosTela.get("Tipo"));%></td>
                            
                            <td class="col-acao">
                                <form action="LivroController" method="POST" style="display:inline;">
                                    <input type="hidden" name="opcao" value="Deletar">
                                    <input type="hidden" name="txtid" value="<%= livro.getId() %>">
                                    <input type="hidden" name="txttipo" value="<%=livro.getTipo()%>">
                                    <button type="submit" onclick="return confirm('Deseja realmente excluir este livro?')" class="btn-deletar-icone">
                                        🗑️
                                    </button>
                                </form>
                            </td>
                            <td class="col-acao">
                                <form action="LivroController" method="POST" style="display:inline;">
                                    <input type="hidden" name="opcao" value="CarregarAtualizar">
                                    <input type="hidden" name="txtid" value="<%= livro.getId() %>" class="btn-editar">
                                    <input type="hidden" name="txttipo" value="<%=livro.getTipo()%>">
                                    <button type="submit">✏️</button>
                                </form>
                            </td>
                            <td class="col-acao">
                                <form action="LivroController" method="POST" style="display:inline;">
                                    <input type="hidden" name="opcao" value="ConsultarById">
                                    <input type="hidden" name="txtid" value="<%=livro.getId()%>">
                                    <input type="hidden" name="txttipo" value="<%=livro.getTipo()%>">
                                    <button type="submit">🔎</button>   
                                </form>
                            </td>
                            
                    </tr>
                <%}%>
            </table>
        </div>
        <div class="nav-actions">
            <a href="index.jsp" class="nav-item" data-tooltip="Página Inicial">
                <i class="fas fa-home"></i> 🏠
            </a>

            <a href="novocadastro.jsp" class="nav-item nav-item-add" data-tooltip="Novo Cadastro">
                <i class="fas fa-plus"></i> ➕
            </a>
        </div>
    </body>
</html>
