<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Locadora</title>
        <link rel="stylesheet" type="text/css" href="estilo.css" />
    </head>
    <body>
    <center>
        <h1>Gerenciamento de Locadoras</h1>
        <h2>
            <a href="locadoraController?action=cadastro">Adicione Nova Locadora</a>
            &nbsp;&nbsp;&nbsp;
            <a href="locadoraController?action=lista">Lista de Locadoras</a>
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Lista de Locadoras</h2></caption>
            <tr>
                <th>CNPJ</th>
                <th>Nome</th>
                <th>Email</th>
                <th>Cidade</th>
                <th>Ativo</th>
                <th>Ações</th>
            </tr>
            <c:forEach var="locadora" items="${listaLocadoras}">
                <tr>
                    <td><c:out value="${locadora.cnpj}" /></td>
                    <td><c:out value="${locadora.nome}" /></td>
                    <td><c:out value="${locadora.email}" /></td>
                    <td><c:out value="${locadora.cidade}" /></td>
                    <td><c:out value="${locadora.ativo}" /></td>
                    <td><a href="locadoraController?cnpj=<c:out value='${locadora.cnpj}' />&action=edicao">Edição</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="locadoraController?cnpj=<c:out value='${locadora.cnpj}' />&action=remocao"
                           onclick="return confirm('Tem certeza de que deseja excluir este item?');">
                            Remoção
                        </a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>