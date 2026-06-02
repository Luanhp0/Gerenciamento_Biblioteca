<%-- 
    Document   : index
    Created on : May 20, 2026, 11:14:24 PM
    Author     : drak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Gerencia Biblioteca</title>
        <link rel="stylesheet" href="estilos/style.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div class="header-system">
            <h2 class="system-title">Sistema Gerencia Biblioteca</h2>
            <form name="entrada" action="LivroController" method="GET" class="action-bar">

                <button type="submit" name="opcao" value="ConsultarTodos" class="btn-action">Consultar Acervo Completo</button>
            </form>
            <form action="UsuarioController" method="GET" class="action-bar">
                <button type="submit" name="opcao" value="ConsultarTodos" class="btn-action">Clientes</button>              
            </form>
            <form action="EmprestimoController" method="POST" class="action-bar">                
                <button type="submit" name="opcao" value="ConsultarTodos" class="btn-action">
                    Consultar Empréstimos
                </button>
            </form>

            <%
                model.Usuario usuario =
                    (model.Usuario) session.getAttribute("usuarioLogado");

                if(usuario != null &&
                    (usuario.getRole().equals("ADMIN")
                    || usuario.getRole().equals("BIBLIOTECARIO"))) {
            %>

                <a href="novousuario.jsp"
                   class="btn-action"
                   style="text-decoration:none;">
                    Cadastrar Usuário
                </a>

            <%
                }
            %>

            <button type="submit" id="btn-novocadastro" class="btn-primary" href="novocadastro.jsp">
                Novo Cadastro Livro
            </button>
            <a href="UsuarioController?opcao=Logout">Sair</a>
            <script src="js/navigation.js"></script>
        </div>
    </body>
</html>
