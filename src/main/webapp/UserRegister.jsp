<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Register</title>
  <link rel="stylesheet" href="style.css">
   <!-- Font Awesome Cdn Link -->
   <link rel="stylesheet" href="css/LoginRegisterStyle.css"/>
</head>
<body>

			<%
			String emailError = (String)request.getAttribute("emailError");
			String nameError = (String)request.getAttribute("nameError");
			String phoneError = (String)request.getAttribute("phoneError");
			String passwordError = (String)request.getAttribute("passwordError");
			String fullError = (String)request.getAttribute("fullError");
			%>


  <div class="wrapper">
    <h1>Online Pantry</h1>
    <p>Register</p>
    <form action="UserRegisterValidation" method="post">
      <input type="text" placeholder="Enter Name" name="userName" required>
      <%
				if(nameError != null){
					out.println(nameError);
				}
			%>
      <input type="text" placeholder="Enter Email" name="userEmail" required>
      <%
				if(emailError != null){
					out.println(emailError);
				}
			%>
      <input type="text" placeholder="Enter Phone Number" name="userPhoneNumber" required>      
			<%
				if(phoneError != null){
					out.println(phoneError);
				}
			%>
      <input type="password" placeholder="Password" name="userPassword">
      
      <input type="password" placeholder="Confirm Password" name="userConfirmPassword">
			<%
				if(passwordError != null){
					out.println(passwordError);
				}
			%>
    <button>Register</button>
    </form>
    
			<%
				if(fullError != null){
					out.println(fullError);
				}
			%>
    <div class="not-member">
      Already a member? <a href="UserLogin.jsp">Login Now</a>
    </div>
  </div>
</body>
</html>
