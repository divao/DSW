<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Locadora</title>
        <link rel="stylesheet" type="text/css" href="estilo.css" />
    </head>
    <body>
        <h1>Lista de Locações</h1><br/>
    <c:if test="${empty requestScope.listaLocacoes}">
        Não há locações!
    </c:if>
    <c:if test="${!empty requestScope.listaLocacoes}">
        <table>
            <tr>
                <th>CPF</th>
                <th>Data</th>
                <th>Horário</th>
            </tr>
            <c:forEach items="${requestScope.listaLocacoes}" var="locacao">
                <tr>
                    <td>${locacao.cpf}</td>
                    <td>${locacao.data}</td>
                    <td>${locacao.horario}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>