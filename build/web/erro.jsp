<%-- 
    Document   : erro
    Created on : 10 de abr. de 2026, 19:57:11
    Author     : kcarl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Erro</title>
</head>
<body>
    <%
        String mensagem = (String) request.getAttribute("mensagem");
    %>
    <h1><%out.print(mensagem);%></h1>
    
</body>
</html>
