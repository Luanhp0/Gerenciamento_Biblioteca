<%-- 
    Document   : painelleitor
    Created on : May 21, 2026, 11:02:36 AM
    Author     : drak
--%>
<%@page import="model.Usuario"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

    if(usuario == null){
        response.sendRedirect("login.jsp");
        return;
    }

    if(!usuario.getRole().equals("LEITOR")){
        response.sendRedirect("index.jsp");
        return;
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" href="estilos/style.css">
    </head>
    <body>
        
        <div class="painel-leitor">
            <h1>Meu Painel</h1>

        <form action="EmprestimoController" method="post">
            <input type="hidden" name="opcao" value="ConsultarMeusEmprestimos">
            <button type="submit">Ver meus empréstimos</button>
        </form>
        
        <a href="UsuarioController?opcao=Logout">Sair</a>
        </div>
    </body>
</html>
