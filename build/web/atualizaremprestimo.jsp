<%-- 
    Document   : atualizaremprestimo
    Created on : Apr 12, 2026, 1:58:36 PM
    Author     : drak
--%>
<%@page import="model.Emprestimo"%>
<%@page import="model.Usuario"%>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    if (!usuario.getRole().equals("ADMIN")
            && !usuario.getRole().equals("BIBLIOTECARIO")) {

        response.sendRedirect("index.jsp");
        return;
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Emprestimo emprestimo = (Emprestimo) request.getAttribute("emprestimo");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Atualizar Emprestimo</title>
        <link rel="stylesheet" href="estilos/style.css">
    </head>
    <body>
        <div class="mains-container">
            <h1 class="page-title">Atualizar Empréstimo</h1>
            <form action="EmprestimoController" method="post" class="form_biblioteca">
                <div class="grid-container">
                    <input type="hidden" >
                    <input type="hidden" name="txtid" value="<%=emprestimo.getId()%>">
                    
                    <div class="form-group">
                        <label>ID:</label>
                        <input type="text" value="<%=emprestimo.getLivroId()%>" disabled>
            
                    </div>
                    
                    <div class="form-group">
                        <label>Livro:</label>
                        <input type="text" value="<%=emprestimo.getLivro().getTitulo()%>" disabled>
                       
                    </div>
                    
                    <div class="form-group">  
                        <label>Cliente:</label>
                        <input type="text" value="<%=emprestimo.getCliente().getNome()%>" disabled>
                        
                    </div>
                    
                    <div class="form-group">
                        <label>Data Devolução Realizada:</label>
                        <input type="date" name="txtdataDevolucaoRealizada">
                    </div>

                    
                </div>
                <button type="submit" name="opcao" value="Atualizar" class="btn-cadastrar">Salvar</button>
            </form>
        </div>

        <br>
        <div class="nav-actions">
            <a href="index.jsp" class="nav-item" data-tooltip="Página Inicial">
                <i class="fas fa-home"></i> 🏠
            </a>
        </div>

    </body>
</html>
