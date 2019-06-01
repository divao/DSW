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
            <a href="locacaoController?action=new">Adicione Nova Locação</a>
            &nbsp;&nbsp;&nbsp;
            <a href="locacaoController?action=lista">Lista de Locação</a>
        </h2>
    </center>
    <div align="center">
        <c:if test="${locacao != null}">
            <form action="locacaoController?action=atualizacao" method="post">
            </c:if>
            <c:if test="${locacao == null}">
                <form action="locacaoController?action=insercao" method="post">
                </c:if>
                <table border="1" cellpadding="5">
                    <caption>
                        <h2>
                            <c:if test="${locacao != null}">
                                Edição
                            </c:if>
                            <c:if test="${locacao == null}">
                                Cadastro
                            </c:if>
                        </h2>
                    </caption>
                    <c:if test="${locacao != null}">
                        <input type="hidden" name="cpf" value="<c:out value='${locacao.cpf}' />" />
                    </c:if>
                        
                    <tr>
                        <th>CPF: </th>
                        <td>
                            <input type="text" name="cpf" size="45"
                                   value="<c:out value='${locacao.cpf}' />"/>
                        </td>
                    </tr>    
                    
                    <tr>
                        <th>CNPJ: </th>
                        <td>
                            <input type="text" name="cnpj" size="45"
                                   value="<c:out value='${locacao.cnpj}' />"/>
                        </td>
                    </tr>
                    
                    <tr>
                        <th>Data: </th>
                        <td>
                            <input type="date" name="data" size="45"
                                   value="<c:out value='${locacao.data}' />"/>
                        </td>
                    </tr>
                    <tr>
                        <th>Horario: </th>
                        <td>
                            <input type="number" name="horario" size="45"
                                   value="<c:out value='${locacao.horario}' />"/>
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