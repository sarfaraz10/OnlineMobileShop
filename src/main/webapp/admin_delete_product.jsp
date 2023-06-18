<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.regex.*,java.util.*,java.sql.*,javax.sql.*,java.awt.image.BufferedImage,java.io.*,javax.imageio.ImageIO,databaseconnection.DataBaseConnection"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Product</title>
</head>
<body>
<% 
	String productId = (String)request.getParameter("productid");
	try{
		Connection connection = DataBaseConnection.createConnection();
		
		Statement statement=connection.createStatement();
		String deleteQuery = "delete from products where productid = "+productId;
        int executeQuery = statement.executeUpdate(deleteQuery);
        response.sendRedirect("admin_product_edit_list.jsp");
	}
	catch(Exception exception){
		out.println(exception.toString());
	}
%>
</body>
</html>