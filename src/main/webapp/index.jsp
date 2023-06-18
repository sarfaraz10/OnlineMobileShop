<!--

	Title       		: Online Mobile Shop
	Project Description : Online shopping web application to purchase budget friendly phones
	Author      		: Sarfaraz khan A
	Created at  		: 15/03/2022
	Updated at  		: 09/04/2022 
	Reviewed by 		: Anitha
	Peer Reviewed by	: Swathi
	Reviewed at 		: 09/04/2022

-->


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page
	import="java.sql.Connection,java.sql.ResultSet,java.sql.Statement,java.sql.Blob,java.io.ByteArrayOutputStream,java.util.Base64,java.io.InputStream,java.awt.image.BufferedImage,javax.imageio.ImageIO,databaseconnection.DataBaseConnection,java.util.*"%>


<!DOCTYPE html>
<html lang="en">
   <head>
      <!-- basic -->
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <!-- mobile metas -->
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="viewport" content="initial-scale=1, maximum-scale=1">
      <!-- site metas -->
      <title>Home Page</title>
      <meta name="keywords" content="">
      <meta name="description" content="">
      <meta name="author" content="">
      <!-- bootstrap css -->
      <link rel="stylesheet" href="css/bootstrap.min.css">
      <!-- style css -->
      <link rel="stylesheet" href="stylesk.css">
      <link rel="stylesheet" href="css/HomeStyle.css">
      <!-- Responsive-->
      

   </head>
   <!-- body -->
   <body>
  <%
  
  	HashSet<String> productList = (HashSet<String>)session.getAttribute("productList");
  	
	String fullProducts = (String) session.getAttribute("fullProducts");

	String userId = (String) session.getAttribute("userid");
	%>
<div id="mySidebar" class="sidebar">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
  <a href="index.jsp">Home</a>
  <%
  if (userId != null) {
		out.println("<a href='UserOrders.jsp'>Your Orders</a>");
		out.println("<hr style='height:2px;border-width:0;color:gray;background-color:gray'><a href='Logout.jsp' style='color:#FF5252'>Logout</a>");
	}
  %>
</div>
<div id="main">
      <!-- header section start -->
      <div class="header_section">
        <div class="container-fluid">
         <div class="row">
            <div class="col-sm-4">
               <div class="search_icon"></div>
            </div>
            <div class="col-sm-4">
               <div class="logo"><img src="images/logo.png"></div>
            </div>
            <div class="col-sm-4">
               <div class="togle_3">
                  <div class="left_main">
                     <div class="menu_main">
                     <% if(userId != null) { %>
                        <a href="UserCartOrders.jsp"><i class="fa fa-shopping-cart"></i> Cart</a>
                      <% } else { %>
                        <a href="UserLogin.jsp"><i class="fa fa-fw fa-user"></i> Login</a>
                      <%} %>
                     </div>
                  </div>

                  <div class="right_main">
                     <div class="togle_main"><a class="class="openbtn onclick="openNav()"><img src="images/togle-menu-icon.png" style="max-width: 100%;"></a></div>
                  </div>
               </div>
               </div>
            </div>
         </div>
         
         
     <div class="banner_section layout_padding">
    
	<br>

		<%
		try {

			Connection connection = DataBaseConnection.createConnection();

			Statement statement = connection.createStatement();
			String productSelection = "select * from products;";
			ResultSet productResultSet = statement.executeQuery(productSelection);

			while (productResultSet.next()) {
				Blob blob = productResultSet.getBlob("productimage");

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

				//rs.getBlob("productimage")
				int productId = productResultSet.getInt("productid");
				String stock = productResultSet.getString("stock");
				String price = productResultSet.getString("price");
				String productName = productResultSet.getString("productname");
		%>
		<div class="box">
			<div>
				<img src="data:image/jpeg;base64,${imgBase}" style="height: 150px;">
			</div>
			<div>
				<h2 ><%=productName%></h2>
				<h3 >
					Only <%=stock%> Left
				</h3>
				<h2>
					Price : Rs.<%=price%></h2>
				<br>
				<%
				if (Integer.parseInt(stock) >= 0) {
				
				if (userId != null) {
				%>
				
					<button style="padding: 15px; text-align: center;" value="clicked" class="round" onclick="window.location.href = 'UserAddToCart.jsp?productId=<%=productId%>';">Add
						to Cart</button>
						
				<%
				} else {
				%>
				
					<button style="padding: 15px; text-align: center;" value="clicked" class="round" onclick="window.location.href = 'UserLogin.jsp?productId=<%=productId%>';">Add
						to Cart</button>

				<%
				}
				if (userId != null) {
				%>
					<button style="padding: 15px; text-align: center;" value="clicked" class="round" onclick="window.location.href = 'UserAddToCart.jsp?productId=<%=productId%>&buynow=yes';">Buy Now</button>
				<%
				} else {
				%>
					<button style="padding: 15px; text-align: center;" value="clicked" class="round" onclick="window.location.href = 'UserLogin.jsp?productId=<%=productId%>&buynow=yes';">Buy Now</button>
						<%} %>
			</div>
		</div>
		<%
		} else {
			out.println("Out of Stock");
		}
		}
		%>
		
		<br>
		
		<%
		}
		catch (Exception exception) {
			out.println(exception.getMessage());
		}
		finally {
			System.out.println("Success Home Page");
		}
		%>
      


   <!-- Testimonial section end -->
   <!-- footer section start -->
   <div class="footer_section layout_padding">
      <div class="container">
        <!--<div class="row">
            <div class="col-sm-4">
               <h2 class="important_text">Important Link</h2>
               <div class="footer_menu">
                  <ul>
                     <li><a href="#">Home</a></li>
                     <li><a href="#">About Us</a></li>
                     <li><a href="#">Our Latest Event</a></li>
                     <li><a href="#">Our Latest Article</a></li>
                     <li><a href="#">Join With Us</a></li>
                  </ul>
               </div>
            </div>
            <div class="col-sm-4">
               <h2 class="important_text">Social Link</h2>
               <div class="footer_menu">
                  <ul>
                     <li><a href="#">Facebook</a></li>
                     <li><a href="#">Linkedin</a></li>
                     <li><a href="#">Twitter</a></li>
                     <li><a href="#">Youtube</a></li>
                     <li><a href="#">Pinterest</a></li>
                  </ul>
               </div>
            </div>
            <div class="col-sm-4">
               <h2 class="important_text">Subscribe With Us</h2>
               <p class="footer_lorem_text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Fuga</p>
               <div class="input-group mb-3">
                  <input type="text" class="form-control" placeholder="text" aria-label="Recipient's username" aria-describedby=" basic-addon2">
                  <div class="input-group-append">
                     <span class="input-group-text" id="basic-addon2">Subscribe</span>
                  </div>
               </div>
            </div>
         </div>   --> 
      </div>
   </div>
   <!-- footer section start -->
   <!-- copyright section start -->

   </div>



      <script>
function openNav() {
  document.getElementById("mySidebar").style.width = "250px";
  document.getElementById("main").style.marginLeft = "250px";
}

function closeNav() {
  document.getElementById("mySidebar").style.width = "0";
  document.getElementById("main").style.marginLeft= "0";
}
</script>

   </body>
   </html>