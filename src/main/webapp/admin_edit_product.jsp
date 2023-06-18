<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.regex.*,java.util.*,java.sql.*,javax.sql.*,java.awt.image.BufferedImage,java.io.*,javax.imageio.ImageIO,databaseconnection.DataBaseConnection"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String productid = (String)request.getParameter("productid");
	String fullerror = (String)session.getAttribute("fullerror");
	session.setAttribute("productid", productid);
%>
<h1>Edit Product</h1>
	<br><br>
			<%
				
				InputStream inputStream = null;
				int errorsender = 0;
				String productName = null;
				String stock = null;
				String price = null;
				        try{
					
				        	Connection connection = DataBaseConnection.createConnection();
				        	
					 Statement st=connection.createStatement();
				        ResultSet rs=st.executeQuery("select * from products where productid="+productid);
				     
				  
				    
				    
				        while(rs.next())
				        {
				        	//rs.getBlob("productimage")
				        	productName = rs.getString("productname");
				             stock = rs.getString("stock");
				            price = rs.getString("price");
				            
				        		if(productName != null && stock != null && price != null){
			%>
			        	<form action="EditProductValidation" method="post" enctype="multipart/form-data">
		
		
		<label>Product Name</label>
		<input type="text" name="productname" value="<%=productName%>"required>
		<br><br>
		
		<label>Product Picture</label>
		<input type="file" accept="image/*" name="productimage" >
		<br><br>
		
		<label>Stock level</label>
		<input type="number" name="stock" value="<%=stock%>" required>
		<br><br>
		                                              
		<label>Price per Product</label>
		<input type="text" name="price" value="<%=price%>"  required>
		<br><br>
		
		<input type="submit" value="Update Now">
		
	</form>

	<br><br>
	<a href="admin_home.jsp">Back to home</a>
	<br><br>
	<%} } 
	        }
	        catch(Exception e) {
				out.println(e.toString());
	        }
				
			%>

	<%
		out.println(fullerror);
	%>
</body>
</html>