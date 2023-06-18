<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page
	import="java.sql.Connection,java.sql.ResultSet,java.sql.Statement,java.sql.Blob,java.io.ByteArrayOutputStream,java.util.Base64,java.io.InputStream,java.awt.image.BufferedImage,javax.imageio.ImageIO,databaseconnection.DataBaseConnection"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Address</title>
</head>
<body>

<h1>Delete Address</h1>

<% 
	String addressid = (String)request.getParameter("addressid");
	try{
		Connection connection = DataBaseConnection.createConnection();
		
		Statement statement=connection.createStatement();
		String deleteQuery = "delete from address where addressid = "+addressid;
        int deleteAddress=statement.executeUpdate(deleteQuery);
        response.sendRedirect("UserAddressDetails.jsp");
	}
	catch(Exception e){
		out.println(e.toString());
	}
%>


</body>
</html>