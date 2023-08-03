<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Balance</title>
</head>
<body>
	<h3>
		<%
		session = request.getSession();
		out.println("Balance = " + session.getAttribute("balance"));
		%>
		<a href="BalanceFail.jsp"></a>
	</h3>
</body>
</html>