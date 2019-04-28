<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
    <body>
        <h2>Bem vindo ao Sistema de Loca��o de Bicicletas !
        </h2>

        <sec:authorize access="hasRole('ADMIN')">

            Acesse a �rea de administrador a partir do link abaixo!" <br/><br/>

            <a href="admin/admin.jsp">�rea de Administrador</a><br/>
            <a href="logout">Logout</a>
        </sec:authorize>

        <sec:authorize access="hasRole('USER')">
            Acesse a �rea de cliente a partir do link abaixo! <br/><br/>

            <a href="user/user.jsp">�rea de Cliente</a><br/>
            <a href="logout">Logout</a>
        </sec:authorize>
            
        <sec:authorize access="hasRole('LOCADORA')">
            Acesse a �rea de locadora a partir do link abaixo! <br/><br/>

            <a href="locadora/locadora.jsp">�rea de locadora</a><br/>
            <a href="logout">Logout</a>
        </sec:authorize>
            
    </body>
</html>