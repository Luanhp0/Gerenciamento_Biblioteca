<%-- 
    Document   : novousuario
    Created on : May 20, 2026, 10:29:23 PM
    Author     : drak
--%>
<%@page import="model.Usuario"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Novo Usuario</title>
        <link rel="stylesheet" href="estilos/style.css">
    </head>
    <body>
        <form action="UsuarioController" method="POST">

            Nome:
            <input type="text" name="txtnome">

            <br><br>

            Email:
            <input type="email" name="txtemail">

            <br><br>

            Senha:
            <input type="password" name="txtsenha">
            
            
            Celular:
            <input type="text" name="txtcelular">

            <br><br>

            Cargo:
            <select name="txtrole">

                <option value="1">LEITOR</option>
                <option value="2">BIBLIOTECARIO</option>
                <option value="3">ADMIN</option>

            </select>

            <br><br>

            Tipo Usuário:
            <select name="txttipousuario">

                <option value="ALUNO">ALUNO</option>
                <option value="PROFESSOR">PROFESSOR</option>
                <option value="PESQUISADOR">PESQUISADOR</option>

            </select>

            <br><br>
            
            <fieldSet name="tiposnotificacao">
                <legend>Escolha quais tipos de notificação gostaria de receber:</legend>
                
                <div>
                    <input type="checkbox" id="SMS" name="tiposnotificacao" value="sms">
                    <label for="tiponotificacao1">SMS</label> <br>
                </div>
                    
                <div>
                    <input type="checkbox" id="Whatsapp" name="tiposnotificacao" value="whatsapp">
                    <label for="tiponotificacao2">Whatsapp</label>
                </div>
                
            </fieldset>
            
            <br>
            
            <button type="submit" name="opcao" value="Cadastrar">
                Cadastrar Usuário
            </button>

        </form>
        <div class="nav-actions">
            <a href="index.jsp" class="nav-item" data-tooltip="Página Inicial">
                <i class="fas fa-home"></i> 🏠
            </a>

        </div>
    </body>
</html>
