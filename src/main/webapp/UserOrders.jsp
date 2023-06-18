<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%@ page
	import="java.sql.Connection,java.sql.ResultSet,java.sql.Statement,java.sql.Blob,java.io.ByteArrayOutputStream,java.util.Base64,java.io.InputStream,java.awt.image.BufferedImage,javax.imageio.ImageIO,databaseconnection.DataBaseConnection"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Your Orders</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<a href="index.jsp" >Return to Home</a>
<h1>Your Orders</h1>

<%
	String userId = (String)session.getAttribute("userid");

	try{
		Connection connection = DataBaseConnection.createConnection();
		String ordersQuery = "select * from orders where userid = "+userId;
		Statement statement = connection.createStatement();
		ResultSet orderResultSet = statement.executeQuery(ordersQuery);
		
		while(orderResultSet.next()){
			
			String productId = String.valueOf(orderResultSet.getInt("productid"));
			String addressLine1 = orderResultSet.getString("addressline1");
			String addressLine2 = orderResultSet.getString("addressline2");
			String pincode = orderResultSet.getString("pincode");
			String phoneNumber = orderResultSet.getString("phonenumber");
			String city = orderResultSet.getString("city");
			String landmark = orderResultSet.getString("landmark");
			String receiverName = orderResultSet.getString("receivername");
			String quantity = orderResultSet.getString("quantity");
			String total = orderResultSet.getString("total");
			String price = orderResultSet.getString("price");
			String productName = orderResultSet.getString("productname");
			Blob productImage = orderResultSet.getBlob("productimage");
			String status = orderResultSet.getString("status");
			
			String productQuery = "select * from products where productid = "+productId;
			Statement productStatement = connection.createStatement();
			ResultSet productResult = productStatement.executeQuery(productQuery);
			
			InputStream inputStream = productImage.getBinaryStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			byte[] imageBytes = outputStream.toByteArray();

			String encode = Base64.getEncoder().encodeToString(imageBytes);
			request.setAttribute("imgBase", encode);

			
			%>
			
				<div class="box">
			
			<%
			
			if(productResult.next() ==  false){
				%>
					<p style="color:red">Deleted Product</p>				
				<%
			}
			%>
			
			<div>
				<img src="data:image/jpeg;base64,${imgBase}" style="height: 150px;">
			</div>
			<div>
				<h2 ><%=productName%></h2>
				<h3>
					<%=quantity%>X Rs.<%=price%> = <%=total%></h3>
				<br>
			</div>
			<div>
				<label style="font-size:20px"><%=receiverName%></label>
				<br>
				<label><%=addressLine1%>, <%=addressLine2%></label>
				<br>
				<label><%=landmark%></label><br>
				<label><%=city%></label><br>
				<label><%=pincode%></label><br>
				<label style="font-size:20px"><%=phoneNumber%></label>
			</div>
			<br><br>
			<div class="status">
			<%
				if(status.equals("waiting")){
					out.println("<Label style='color:orange;font-size:20px'>Product needs approval from Admin</Label>");
				}
				else if(status.equals("approved")){
					out.println("<Label style='color:green;font-size:20px'>Your product has been approved. It will be arriving soon</Label>");
				}
				else if(status.equals("delivered")){
					out.println("<Label style='color:#E56501;font-size:20px'>This product has been delivered</Label>");
				}
				else{
					out.println("<Label style='color:red;font-size:20px'>This product has been Rejected by Admin</Label>");
				}
			%>
			</div>
			</div>
			<%
			
		}
		
	}
	catch(Exception orderException){
		out.println(orderException.toString());
		System.out.println(orderException.getMessage());
	}
	
%>

</body>
</html>