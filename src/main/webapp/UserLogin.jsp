<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <link rel="stylesheet" href="style.css">
   <!-- Font Awesome Cdn Link -->
   <link rel="stylesheet" href="css/LoginRegisterStyle.css"/>
</head>
<body>

			<%
				String emailError = (String)request.getAttribute("emailError");
				String passwordError = (String)request.getAttribute("passwordError");
				String fullError = (String)request.getAttribute("fullError");
				String productId = request.getParameter("productId");
				
				String buynow = request.getParameter("buynow");
				
				if(productId != null){
					System.out.println("productIdLogin"+productId);
					session.setAttribute("productIdLogin", productId);
				}
			%>

  <div class="wrapper">
    <h1>Online Pantry</h1>
    <p>Login</p>
    <form action="UserLoginValidation" method="post">
    
		<input type="hidden" value="<%=request.getParameter("buynow") %>" name="buynow">
		<input type="hidden" value="<%=productId %>" name="productId">
		
		
      <input type="text" placeholder="Enter Email" name="userEmail" required>
      
			<%
				if(emailError != null){
					out.println(emailError);
				}
			%>
      <input type="password" placeholder="Password" name="userPassword">
      
			<%
				if(passwordError != null){
					out.println(passwordError);
				}
			%>
      <p class="recover">
        <a href="UserForgotPassword.jsp">Forgot Password?</a>
      </p>
    <button>Login</button>
    </form>
    
			<%
				if(fullError != null){
					out.println(fullError);
				}
			%>
    <div class="not-member">
      Not a member? <a href="UserRegister.jsp">Register Now</a>
    </div>
  </div>
</body>
</html>
