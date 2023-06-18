<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*,java.sql.*,databaseconnection.DataBaseConnection,java.io.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
</head>
<body>
<h1>Cart List</h1>
<a href="index.jsp">Home</a><br><br>
<form name="cart_form" action="UserCartServlet" method="post">
<%
	HashSet<String> productList = (HashSet<String>)session.getAttribute("productList");
	String userId = (String)session.getAttribute("userid");
	Connection connection = null;
	
	if(productList == null || productList.isEmpty()==true){
		out.println("Your Cart is Empty");
	}
	else{
		try{
			connection = DataBaseConnection.createConnection();
			int stockChecker=1;
			for(String productId : productList){
				Statement productStatement = connection.createStatement();
				String productQuery = "select * from products where productid = "+productId;
				ResultSet productResult = productStatement.executeQuery(productQuery);
				if(productResult.next()){
					Blob blob = productResult.getBlob("productimage");

		            InputStream inputStream = blob.getBinaryStream();
		            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		            byte[] buffer = new byte[4096];
		            int bytesRead = -1;

		            while ((bytesRead = inputStream.read(buffer)) != -1) {
		                    outputStream.write(buffer, 0, bytesRead);
		            }

		            byte[] imageBytes = outputStream.toByteArray();

		            String encode = Base64.getEncoder().encodeToString(imageBytes);
		            request.setAttribute("imgBase", encode);

		        	String stock = productResult.getString("stock");
		        	String price = productResult.getString("price");
		        	
		        	%>
		        	
		        	
		        	<div style="width:300px;border:2px solid black;padding: 0px 10px 10px 20px;">
		        	
						<p style="color:red;font-size:30px;"><%=productResult.getString("productname") %></p>
						<br>
						<img src="data:image/jpeg;base64,${imgBase}" alt="Image not Displayed" style="height:150px;"/>
						<br><br>
						<p>Only <b><%=stock %></b> left </p>
						<br>
						<label>Quantity </label><input type="number" id="quantity" name="<%=productId %>" min="1" max="<%=stock %>" required>
						
						<p style="font-size:20px;">Price : Rs.<%=price %></p>										
						
						<button type="button" class="curve" onclick="window.location.href = 'UserDeleteCart.jsp?productId=<%=productId%>';">Delete</button>
					</div>
					
		        	
		        	<%
				}
			}
		}
		catch(Exception exception){
			out.println(exception.toString());
		}
		finally{
			connection.close();
		}
		%>
		
<br><br>

<button type="submit" class="curve" >Proceed to Buy</button>

<br><br><br><br>
		<%
	}
	

%>
</form>


</body>
</html>