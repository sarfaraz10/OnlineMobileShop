<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page
	import="java.sql.Connection,java.sql.ResultSet,java.sql.Statement,java.sql.Blob,java.io.ByteArrayOutputStream,java.util.Base64,java.io.InputStream,java.awt.image.BufferedImage,javax.imageio.ImageIO,databaseconnection.DataBaseConnection"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Approve Order</title>
</head>
<body>
<%

	String orderId = request.getParameter("orderid");
	if(orderId != null){
		try{
			Connection connection = DataBaseConnection.createConnection();
			String updateQuery = "update orders set status='reject' where orderid = "+orderId;
			
			Statement statement = connection.createStatement();

			int updateOrder=statement.executeUpdate(updateQuery);
	        response.sendRedirect("admin_orders.jsp");
		}
		catch(Exception exception){
			System.out.println(exception.toString());
		}
	}

%>
</body>
</html>