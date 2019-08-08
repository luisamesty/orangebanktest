<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Transaction Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h1>
	Search Transaction By REFERENCE
</h1>

<c:url var="addAction4" value="/transaction/status" ></c:url>

<form:form action="${addAction4}" commandName="transactionref">
  <table>
	<tr>
		<td>
			<form:label path="treference">
				<spring:message text="Reference"/>
			</form:label>
		</td>
		<td>
			<form:input path="treference" />
		</td> 
	</tr>
<!-- 	<tr> -->
<!-- 		<td> -->
<%-- 			<form:label path="account_iban"> --%>
<%-- 				<spring:message text="IBAN"/> --%>
<%-- 			</form:label> --%>
<!-- 		</td> -->
<!-- 		<td> -->
<%-- 			<form:input path="account_iban" /> --%>
<!-- 		</td> -->
<!-- 	</tr> -->


	<tr>
		<td colspan="2">
			<input type="submit"
				value="<spring:message text="Search Transactions"/>" />
		</td>
	</tr>
  </table>	
</form:form>
<br>
<h3>Transaction List by REFERENCE</h3>
<c:if test="${!empty listTransactionsByREF}">
	<table class="tg">
	<tr>
		<th width="120">Account IBAN</th>
		<th width="100">Reference</th>
		<th width="60">Date</th>
		<th width="60">Amount</th>
		<th width="60">Fee</th>
		<th width="60">TR Status</th>
		<th width="60">CHannel</th>
	</tr>
	<c:forEach items="${listTransactionsByREF}" var="transactionref">
		<tr>
			<td>${transactionref.account_iban}</td>
			<td>${transactionref.treference}</td>
			<td>${transactionref.trfecha}</td>
			<td>${transactionref.tramount}</td>
			<td>${transactionref.trfee}</td>
			<td>${transactionref.trstatus}</td>
			<td>${transactionref.trchannel}</td>
		</tr>
	</c:forEach>
	</table>
</c:if>

</body>
</html>
