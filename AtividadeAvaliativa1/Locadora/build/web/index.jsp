<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
    <body>
        <h2>Bem vindo ao Sistema de Locação de Bicicletas !
        </h2>

        <sec:authorize access="hasRole('ADMIN')">

            Acesse a área de administrador a partir do link abaixo!" <br/><br/>

            <a href="admin/admin.jsp">Área de Administrador</a><br/>
            <a href="logout">Logout</a>
        </sec:authorize>

        <sec:authorize access="hasRole('USER')">
            Acesse a área de cliente a partir do link abaixo! <br/><br/>

            <a href="user/user.jsp">Área de Cliente</a><br/>
            <a href="logout">Logout</a>
        </sec:authorize>
            
        <sec:authorize access="hasRole('LOCADORA')">
            Acesse a área de locadora a partir do link abaixo! <br/><br/>

            <a href="locadora/locadora.jsp">Área de locadora</a><br/>
            <a href="logout">Logout</a>
        </sec:authorize>
            
    </body>
</html>