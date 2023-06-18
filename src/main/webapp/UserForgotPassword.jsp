<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forgot Password</title>
</head>
<body>
			<%
				String emailError = (String)request.getAttribute("emailerror");
				String phoneError = (String)request.getAttribute("phoneerror");
				String fullError = (String)request.getAttribute("fullerror");
				String userEmail = (String)request.getAttribute("usermail");
				session.setAttribute("usermail", userEmail);
			%>
			
	<h1>Forgot Password</h1>
	
	<form action="ForgotPasswordValidation" method="post">
	
		<br> 
		<label>Email</label> 
		<input type="email" name="email"
			required>
			<br> 
			
			<%
				if(emailError != null){
					out.println(emailError);
				}
			%>
			<br> <br> 
		<label>Phone Number</label> 
		<input type="tel" name="userphone" required><br>
		
			<%
				if(phoneError != null){
					out.println(phoneError);
				}
			%>
		<br> <br> 
		<input type="submit" value="Reset Password">	
	</form>
	
	<br> 
			<%
				if(fullError != null){
					out.println(fullError);
				}
			%>
</body>
</html>