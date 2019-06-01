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
        <h1>Gerenciamento de Clientes</h1>
        <h2>
            <a href="clienteController?action=cadastro">Adicione Novo Cliente</a>
            &nbsp;&nbsp;&nbsp;
            <a href="clienteController?action=lista">Lista de Clientes</a>
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Lista de Clientes</h2></caption>
            <tr>
                <th>CPF</th>
                <th>Nome</th>
                <th>Email</th>
                <th>Telefone</th>
                <th>Data de Nascimento</th>
                <th>Sexo</th>
                <th>Ativo</th>
                <th>Ações</th>
            </tr>
            <c:forEach var="cliente" items="${listaClientes}">
                <tr>
                    <td><c:out value="${cliente.cpf}" /></td>
                    <td><c:out value="${cliente.nome}" /></td>
                    <td><c:out value="${cliente.email}" /></td>
                    <td><c:out value="${cliente.telefone}" /></td>
                    <td><c:out value="${cliente.dataNascimento}" /></td>
                    <td><c:out value="${cliente.sexo}" /></td>
                    <td><c:out value="${cliente.ativo}" /></td>
                    <td><a href="clienteController?action=edicao&cpf=<c:out value='${cliente.cpf}' />">Edição</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="clienteController?action=remocao&cpf=<c:out value='${cliente.cpf}' />"
                           onclick="return confirm('Tem certeza de que deseja excluir este item?');">
                            Remoção
                        </a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>