<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reset Password</title>
</head>
<body>
	
			<%
				String passwordError = (String)request.getAttribute("passworderror");
				String fullError = (String)request.getAttribute("fullerror");
			%>
	<h1>Reset Your Password</h1>
	
	<form action="ResetPasswordValidation" method="post">
	
		<br> 
		<label>New Password</label> 
		<input type="password" name="password" required><br>
		<br>
		
		<label>Confirm Password</label> 
		<input type="password" name="cpassword" required><br>
		<br>
		
		<%
				if(passwordError != null){
					out.println(passwordError+ " ");
				}
			%>
			<br> <br>  
		<input type="submit" value="Reset now">	
	</form>
			<%
				if(fullError != null){
					out.println(fullError);
				}
			%>
	
	
</body>
</html>