<%-- 
    Document   : novocadastro
    Created on : 6 de abr. de 2026, 10:42:02
    Author     : kcarl
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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar Novo Livro</title>
        <link rel="stylesheet" href="estilos/style.css">
    </head>
    <body>
        <div class="mains-container">
            <h1 class="page-title">Novo Cadastro</h1>
            <form action="LivroController" method="GET" class="form_biblioteca">
                <div class="grid-container">
                    <div class="form-group">
                        <label>Título:</label>
                        <input type="text" name="txttitulo" placeholder="Digite o título do livro:">
                    </div>

                    <div class="form-group">
                        <label>Autor:</label>
                        <input type="text" name="txtautor" placeholder="Digite o nome do Autor:">
                    </div>

                    <div class="form-group">
                        <label>Gênero:</label>
                        <input type="text" name="txtgenero" placeholder="Digite o gênero:">
                    </div>

                    <div class="form-group">
                        <label>Páginas:</label>
                        <input type="number" name="txtpaginas" placeholder="Digite o número de páginas:">
                    </div>

                    <div class="form-group">
                        <label>Ano de Publicação:</label>
                        <input type="text" name="txtanoDePublicacao" placeholder="Digite o ano de Publicação">
                    </div>

                    <div class="form-group">
                        <label>Editora:</label>
                        <input type="text" name="txteditora" placeholder="Nome da editora:">
                    </div>

                    <div class="form-group">
                        <label>Idioma:</label>
                        <input type="text" name="txtidioma" placeholder="Digite o idioma do livro:">
                    </div>

                    <div class="form-group full-width">
                        <label>Resumo:</label>
                        <textarea name="txtdescricao" rows="3" placeholder="Digite o resumo do livro:"></textarea>
                    </div>
                    
                    <label>Tipo do Livro:</label>
                    <select name="txttipo">
                        <option value="FISICO">Físico</option>
                        <option value="DIGITAL">Digital</option>
                    </select>
                </div>
                <div class="form-group" id="camposFisico">
                    <label>Tipo de Capa:</label>
                    <select name="txttipocapa">
                        <option value="MOLE">Mole</option>
                        <option value="DURA">Dura</option>
                    </select>
                </div>

                <!-- Campos para Livro Digital -->
                <div class="form-group" id="camposDigital" style="display:none">
                    <label>Formato:</label>
                    <select name="txtformato">
                        <option value="PDF">PDF</option>
                        <option value="EPUB">EPUB</option>
                    </select>
                    <label>Tamanho (MB):</label>
                    <input type="number" step="0.1" name="txttamanho" placeholder="Tamanho em MB">
                </div>

                <button type="submit" name="opcao" class="btn-cadastrar" value="Cadastrar">Cadastrar Livro</button>
            </form>
        </div>
        <div class="nav-actions">
            <a href="index.jsp" class="nav-item" data-tooltip="Página Inicial">
                <i class="fas fa-home"></i> 🏠
            </a>
        </div>
        <script>
            document.querySelector('select[name="txttipo"]').addEventListener('change', function() {
                document.getElementById('camposFisico').style.display = this.value === 'FISICO' ? 'block' : 'none';
                document.getElementById('camposDigital').style.display = this.value === 'DIGITAL' ? 'block' : 'none';
            });
        </script>
    </body>
</html>
