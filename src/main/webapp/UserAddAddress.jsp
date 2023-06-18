<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.regex.*,java.sql.*,javax.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add New Address</title>
   <link rel="stylesheet" href="css/LoginRegisterStyle.css"/>
   <link rel="stylesheet" href="css/background.css"/>
</head>
<body background="images/banner-bg.png">
	<%
		String fullError = (String)request.getAttribute("fullerror");
	%>
	<br><br>
            <a href="index.jsp" style="margin:40px">Return to Home</a>
            <br>
            <a href="UserAddressDetails.jsp" style="margin:40px">Go Back</a>
	<div class="wrapper" style="border: 2px solid black;">
    <h1>Add new Address</h1>
    <br><br>
    <form action="AddressCreation" method="post">
      <input type="text" placeholder="Enter Receiver Name" name="name" required>
      
      <input type="text" placeholder="Enter Phone Number" name="phonenumber" required>
		
      <input type="text" placeholder="Address Line 1" name="address1" required>
      <input type="text" placeholder="Address Line 2" name="address2" required>
      <input type="text" placeholder="Landmark" name="landmark" required>
      <input type="text" placeholder="Pincode" name="pincode" required>
      <input type="text" placeholder="City" name="city" required>
	
		<input type="submit" style="border: 2px solid black;" value="Add now">
	</form>
	
	<%
		if(fullError != null){
			out.println(fullError);
		}
	%>

	
</body>
</html>