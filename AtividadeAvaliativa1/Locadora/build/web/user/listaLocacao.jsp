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
        <h1>Gerenciamento de Locações</h1>
        <h2>
            <a href="locacaoController?action=cadastro">Adicione Nova Locação</a>
            &nbsp;&nbsp;&nbsp;
            <a href="locacaoController?action=lista">Lista de Locações</a>
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Lista de Locações</h2></caption>
            <tr>
                <th>CPF</th>
                <th>CNPJ</th>
                <th>Data</th>
                <th>Horario</th>
                <th>Ações</th>
            </tr>
            <c:forEach var="locacao" items="${listaLocacoes}">
                <tr>
                    <td><c:out value="${locacao.cpf}" /></td>
                    <td><c:out value="${locacao.cnpj}" /></td>
                    <td><c:out value="${locacao.data}" /></td>
                    <td><c:out value="${locacao.horario}" /></td>
                    <td><a href="locacaoController?cpf=<c:out value='${locacao.cpf}' />&cnpj=<c:out value='${locacao.cnpj}' />&data=<c:out value='${locacao.data}' />&horario=<c:out value='${locacao.horario}' />&action=edicao">Edição</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="locacaoController?cpf=<c:out value='${locacao.cpf}' />&cnpj=<c:out value='${locacao.cnpj}' />&data=<c:out value='${locacao.data}' />&horario=<c:out value='${locacao.horario}' />&action=remocao"
                           onclick="return confirm('Tem certeza de que deseja excluir este item?');">
                            Remoção
                        </a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>