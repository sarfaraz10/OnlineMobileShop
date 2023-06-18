<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.regex.*,java.util.*,java.sql.*,javax.sql.*,java.awt.image.BufferedImage,java.io.*,javax.imageio.ImageIO,databaseconnection.DataBaseConnection"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
</head>
<body>
<%
	String productId = (String)request.getParameter("productid");
	String fullProducts = (String)session.getAttribute("fullproducts");

	if(fullProducts == null){
		fullProducts = "";
	}
	
	if(fullProducts.contains(productId+" ") == false){
		fullProducts += productId+" ";
		System.out.println(productId);
		session.setAttribute("fullproducts",fullProducts);	
	}
	response.sendRedirect("index.jsp");
%>
</body>
</html>