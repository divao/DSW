<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Locadora</title>
    </head>
    <body>
        
        <div align ="center">
            <h1>Locadora de Bicicletas</h1><br>
        
            
            <label>Login: </label><input type="text" name="txtLogin"><br><br
                <label>Senha:</label><input type="password" name="txtSenha"><br><br>
                <input type="button" name="btnLogin" value="Logar"><br><br>
            
        </div>
        
        
        
        
<div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Lista de Locadoras</h2></caption>
            <tr>
                <th>CNPJ</th>
                <th>Nome</th>
                <th>Email</th>
                <th>Cidade</th>
            </tr>
            <c:forEach var="livro" items="${listaLocadoras}">
                <tr>
                    <td><c:out value="${locadora.cnpj}" /></td>
                    <td><c:out value="${locadora.nome}" /></td>
                    <td><c:out value="${locadora.email}" /></td>
                    <td><c:out value="${locadora.cidade}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>

    </body>
</html>
