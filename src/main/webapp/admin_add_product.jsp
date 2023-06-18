<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add New Product</title>
</head>
<body>
	
	
	<h1>Add new Product</h1>
	<br><br>
			<%
				String fullError = (String)request.getAttribute("fullerror");
			%>
	<form action="AddProductValidation" method="post" enctype="multipart/form-data">
		
		
		<label>Product Name</label>
		<input type="text" name="productname" required>
		<br><br>
		
		<label>Product Picture</label>
		<input type="file" accept="image/*" name="productimage" required>
		<br><br>
		
		<label>Stock level</label>
		<input type="number" name="stock" required>
		<br><br>
		                                              
		<label>Price per Product</label>
		<input type="text" name="price" required>
		<br><br>
		
		<input type="submit" value="Add Now">
		
	</form>
	<%
		if(fullError != null){
			out.println(fullError);
		}
	%>
	<br><br>
	<a href="admin_home.jsp">Back to home</a>
	<br><br>
	
	
</body>
</html>