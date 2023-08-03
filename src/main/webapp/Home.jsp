<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home page</title>
 <link rel="stylesheet" href="style.css">
 
</head>
<body>
    <section class="container-jsp"> 

    

	<h1 align="center">Welcome to the Bank</h1>
	           
	<% 
	 session = request.getSession();
	  String s1 = (String)session.getAttribute("cust_name");
	  out.println(s1+"Welcome to your account. \n Please select an operation to perform.");
	  %>

	<a href="CheckBalance">1.CheckBalance</a>
	<a href="ChangePin.html">2.ChangePin</a>
	<a href="Loan.jsp">3.Loan</a>
	<a href="TransferStatus.html">4.TransferStatus</a>
	
	<a href="LogOut">5.LogOut</a>
	
	</form>
	
</body>
</html>