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
            <a href=clienteController?action=new">Adicione Novo Cliente</a>
            &nbsp;&nbsp;&nbsp;
            <a href="clienteController?action=lista">Lista de Clientes</a>
        </h2>
    </center>
    <div align="center">
        <c:if test="${cliente != null}">
            <form action="clienteController?action=atualizacao" method="post">
            </c:if>
            <c:if test="${cliente == null}">
                <form action="clienteController?action=insercao" method="post">
                </c:if>
                <table border="1" cellpadding="5">
                    <caption>
                        <h2>
                            <c:if test="${cliente != null}">
                                Edição
                            </c:if>
                            <c:if test="${cliente == null}">
                                Cadastro
                            </c:if>
                        </h2>
                    </caption>
                    <c:if test="${cliente != null}">
                        <input type="hidden" name="cpf" value="<c:out value='${cliente.cpf}' />" />
                    </c:if>
                    <tr>
                        <th>CPF: </th>
                        <td>
                            <input type="text" name="cpf" size="45"
                                   value="<c:out value='${cliente.cpf}' />"/>
                        </td>
                    </tr>
                    <tr>
                        <th>Nome: </th>
                        <td>
                            <input type="text" name="nome" size="45"
                                   value="<c:out value='${cliente.nome}' />"/>
                        </td>
                    </tr>
                    <tr>
                        <th>Email: </th>
                        <td>
                            <input type="text" name="email" size="45"
                                   value="<c:out value='${cliente.email}' />"/>
                        </td>
                    </tr>
                    <tr>
                        <th>Senha: </th>
                        <td>
                            <input type="password" name="senha" size="5"
                                   value="<c:out value='${cliente.senha}' />"/>
                        </td>
                    </tr>
                    <tr>
                        <th>Telefone: </th>
                        <td>
                            <input type="text" name="telefone" size="5"
                                   value="<c:out value='${cliente.telefone}' />"
                                   />
                        </td>
                    </tr>
                    <tr>
                        <th>Data de Nascimento: </th>
                        <td>
                            <input type="date" name="dataNascimento" size="5"
                                   value="<c:out value='${cliente.dataNascimento}' />"
                                   />
                        </td>
                    </tr>
                    <tr>
                        <th>Sexo: </th>
                        <td>
                            <input type="radio" name="feminino" size="5"
                                   value="<c:out value='${cliente.sexo}' />"
                                   />Feminino
                            <input type="radio" name="masculino" size="5"
                                   value="<c:out value='${cliente.sexo}' />"
                                   />Masculino
                        </td>
                    </tr>
                    
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="Salvar" />
                        </td>
                    </tr>
                </table>
            </form>
    </div>
</body>
</html>