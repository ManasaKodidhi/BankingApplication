<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <%
    session=request.getSession();
    int s1=(int)session.getAttribute("lid");
    String s2=(String)session.getAttribute("l_type");
    int s3=(int)session.getAttribute("tenure");
    float s4=(float)session.getAttribute("interest");
    String s5=(String)session.getAttribute("description");
    out.println(s1);
    out.println(s2);
    out.println(s3);
    out.println(s4);
    out.println(s5);

    



    
    
    %>

</body>
</html>