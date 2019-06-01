<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:remove scope="session" var="novaAposta" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Locadora</title>
        <link rel="stylesheet" type="text/css" href="estilo.css" />
    </head>
    <body>
        <h1>Locadora de Bicicletas!</h1>
        <br>
        <c:if test="${!empty mensagem}">
            ${mensagem}
            <br>
        </c:if>

        <p>Escolha o que deseja fazer:</p>
        <a href="#">Listar clientes</a><br/>
        <a href="#">Listar locadoras</a><br/>
        <a href="#">Listar Locações</a><br/><!-- Cliente-->
        <a href="#">Listar Locações</a><br/><!-- Locadora-->
        
        

    </body>
</html>
