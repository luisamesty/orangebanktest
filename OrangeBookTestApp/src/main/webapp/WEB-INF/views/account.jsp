<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Account Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h1>
	Add an Account
</h1>

<c:url var="addAction1" value="/account/add" ></c:url>

<form:form action="${addAction1}" commandName="account">
<table>
	<c:if test="${!empty account.name}">
	<tr>
		<td>
			<form:label path="id">
				<spring:message text="ID"/>
			</form:label>
		</td>
		<td>
			<form:input path="id" readonly="true" size="8"  disabled="true" />
			<form:hidden path="id" />
		</td> 
	</tr>
	</c:if>
	<tr>
		<td>
			<form:label path="name">
				<spring:message text="Name"/>
			</form:label>
		</td>
		<td>
			<form:input path="name" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="account_iban">
				<spring:message text="IBAN"/>
			</form:label>
		</td>
		<td>
			<form:input path="account_iban" />
		</td>
	</tr>
		<tr>
		<td>
			<form:label path="balance">
				<spring:message text="Balance"/>
			</form:label>
		</td>
		<td>
			<form:input path="balance" />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<c:if test="${!empty account.name}">
				<input type="submit"
					value="<spring:message text="Edit Account"/>" />
			</c:if>
			<c:if test="${empty account.name}">
				<input type="submit"
					value="<spring:message text="Add Account"/>" />
			</c:if>
		</td>
	</tr>
</table>	
</form:form>
<br>
<h3>Accounts List</h3>
<c:if test="${!empty listAccounts}">
	<table class="tg">
	<tr>
		<th width="80">Account ID</th>
		<th width="120">Account Name</th>
		<th width="120">Account IBAN</th>
		<th width="100">Initial Balance</th>
		<th width="100">Current Balance</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listAccounts}" var="account">
		<tr>
			<td>${account.id}</td>
			<td>${account.name}</td>
			<td>${account.account_iban}</td>
			<td>${account.initbalance}</td>
			<td>${account.balance}</td>
			<td><a href="<c:url value='/account/edit/${account.id}' />" >Edit</a></td>
			<td><a href="<c:url value='/account/remove/${account.id}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>



</body>
</html>
