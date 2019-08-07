<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> Code Challenge </h1>
<h2> LISTA DE CUENTAS ACCOUNT</h2>
 	<!-- Lista de cuentas en tabla Account. -->
	<ul>
        <c:forEach var="p" items="${cuentas}">
            <li>IBAN: ${p.account_iban} - Nombre:  ${p.name} - Balance: ${p.balance}</li>
        </c:forEach>
    </ul>

    <!-- formulario en el que se recogen los datos para crear una nueva cuenta -->

</body>
</html>