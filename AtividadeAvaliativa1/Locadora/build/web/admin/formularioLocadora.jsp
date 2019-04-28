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
            <a href="locadoraController?action=new">Adicione Nova Locadora</a>
            &nbsp;&nbsp;&nbsp;
            <a href="locadoraController?action=lista">Lista de Locadoras</a>
        </h2>
    </center>
    <div align="center">
        <c:if test="${locadora != null}">
            <form action="locadoraController?action=atualizacao" method="post">
            </c:if>
            <c:if test="${locadora == null}">
                <form action="locadoraController?action=insercao" method="post">
                </c:if>
                <table border="1" cellpadding="5">
                    <caption>
                        <h2>
                            <c:if test="${locadora != null}">
                                Edição
                            </c:if>
                            <c:if test="${locadora == null}">
                                Cadastro
                            </c:if>
                        </h2>
                    </caption>
                    <c:if test="${locadora != null}">
                        <input type="hidden" name="cnpj" value="<c:out value='${locadora.cnpj}' />" />
                    </c:if>
                    <tr>
                        <th>CNPJ: </th>
                        <td>
                            <input type="text" name="cnpj" size="45"
                                   value="<c:out value='${locadora.cnpj}' />"/>
                        </td>
                    </tr>
                    <tr>
                        <th>Nome: </th>
                        <td>
                            <input type="text" name="nome" size="45"
                                   value="<c:out value='${locadora.nome}' />"/>
                        </td>
                    </tr>
                    <tr>
                        <th>Email: </th>
                        <td>
                            <input type="text" name="email" size="45"
                                   value="<c:out value='${locadora.email}' />"/>
                        </td>
                    </tr>
                    <tr>
                        <th>Senha: </th>
                        <td>
                            <input type="password" name="senha" size="5"
                                   value="<c:out value='${locadora.senha}' />"/>
                        </td>
                    </tr>
                    
                    <tr>
                        <th>Cidade: </th>
                        <td>
                            <input type="text" name="cidade" size="5"
                                   value="<c:out value='${locadora.cidade}' />"/>
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