<%-- 
    Document   : login
    Created on : May 18, 2026, 10:18:07 PM
    Author     : drak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <link rel="stylesheet" href="estilos/style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Gerencia Biblioteca</title>
    </head>
    <body>
    
    

        
    
    <div class="login-container">
        <h1>Login</h1>
       <form action="UsuarioController" method="post">
            <label>Email:</label>
            <input type="email" name="email" required>

            <br><br>

            <label>Senha:</label>
            <input type="password" name="senha" required>

            <br><br>

            <button type="submit">Entrar</button>
            <input type="hidden" name="opcao" value="Login">
        </form>
    </div>
        

        <%
            String msg = (String) request.getAttribute("msg");
            if (msg != null) {
        %>
            <p style="color:red;"><%= msg %></p>
        <% } %>

    </body>
</html>
