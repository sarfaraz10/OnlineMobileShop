<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="java.util.*,java.sql.*,java.io.*,databaseconnection.DataBaseConnection" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Proceed to Buy</title>

      <link rel="stylesheet" href="css/proceedFinal.css">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/background.css">
</head>
<body>

<div>
<center><h1>Proceed</h1></center>
<a href="index.jsp">Return Home</a><br>
<br>
<%
	String userId = (String)session.getAttribute("userid");
	HashMap<String,String> productMap = (HashMap<String,String>)session.getAttribute("productMap");
	HashSet<String> productList = (HashSet<String>)session.getAttribute("productList");
	Connection connection=null;
	System.out.println("Inside proceed "+productMap);
	if(productList == null || productList.isEmpty()==true){
		out.println("Your Cart is Empty");
	}
	else{
		int total = 0;
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
					
		            String productName = productResult.getString("productname");;
		        	String stock = productResult.getString("stock");
		        	String price = productResult.getString("price");
		        	int quantity = Integer.parseInt(productMap.get(productId));
		        	int subTotal = quantity*Integer.parseInt(price);
		        	total += subTotal;
		        	%>
		        	<div class="card" style="margin-bottom: 20px;">
					  <div class="image">
					    <img src="data:image/jpeg;base64,${imgBase}" alt="img">
					  </div>
					  <div class="subscribe">
					    <h2><%=productName %></h2>
					    <br>
					    <Label>Subtotal : <%=quantity %> x <%=price %> = </Label><Label><%=subTotal %></Label>
					    
					  </div>
					</div>
		        	
		        	<%
				}
			}
		}
		catch(Exception exception){
			out.println(exception.toString());
		}
	}

%>
<form method="post" action="ProceedBuyProducts">
<br><h2>Select Address</h2><br>
	<%
    try
    {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from address where userid = "+userId);
		System.out.println("Inside address User id is "+userId);
        int radioRequiredSetter=0;
        
        while(resultSet.next())
        {
        	String addressId = resultSet.getString("addressid");
        	System.out.println("address ID "+addressId);
            String addressLine1 = resultSet.getString("addressline1");
            String addressLine2 = resultSet.getString("addressline2");
        	String landmark = resultSet.getString("landmark");
        	String pincode = resultSet.getString("pincode");
        	String phoneNumber = resultSet.getString("phonenumber");
        	String city = resultSet.getString("city");
        	String receiverName = resultSet.getString("receivername");
        	System.out.println("This is "+addressId);
            %>
			<div style="width:100%;border:1px solid black;padding: 10px 10px 10px 20px;">
	            <input type="radio" value="<%=addressId %>" name="address" <% if(radioRequiredSetter==0){ out.println("required"); radioRequiredSetter ++; }%>>
	            <p><%=receiverName %></p><br>
	            <p><%=phoneNumber %></p><br>
	            <p><%=addressLine1 %>, <%=addressLine2 %></p>
	            <p><%=landmark %></p>
	            <p><%=city %> - <%=pincode %></p><br>
            </div>
            <!--  
            
            -->
	<%}
		%>
		<p class="recover">
		<br>
        	<a style="color:red;padding-top:10px" href="UserAddressDetails.jsp">Create or Edit Address</a>
      	</p>
      	<br><br><br>
      	<%
    }
    catch(Exception exception){
        out.print(exception.getMessage());%><br><%
    }
    %>
    <span style="margin-bottom:20px;font-weight:bold">Payment Method :</span><br><br>
    <input type="radio" name="payment" value="cc" style="margin-right:20px;margin-bottom:20px" ><label>Credit Card</label>
    <br>
    <input type="radio" name="payment" value="cod" style="margin-right:20px;margin-bottom:20px"><label>Net Banking</label>
    <br>
    <input type="radio" name="payment" value="cod" style="margin-right:20px;margin-bottom:20px" checked><label>Cash on Delivery</label>
	<br><br>
	<input type="submit" class="curve" value="Proceed to Buy">
	</div>
	</form>

</div>
</body>
</html>