<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page
	import="java.sql.Connection,java.sql.ResultSet,java.sql.Statement,java.sql.Blob,java.io.ByteArrayOutputStream,java.util.Base64,java.io.InputStream,java.awt.image.BufferedImage,javax.imageio.ImageIO,databaseconnection.DataBaseConnection"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Address</title>
</head>
<body>

<h1>Edit Address</h1>

<% 
	String addressId = (String)request.getParameter("addressid");
	String fullError = (String)session.getAttribute("fullerror");
	session.setAttribute("addressid",addressId);
%>

<%
	if(addressId != null){
		try
	    {
	        Connection connection = DataBaseConnection.createConnection(); 
	        
	        Statement statement = connection.createStatement();
	        String addressIdQuery = "select * from address where addressid='"+addressId+"'";
	        ResultSet addressResultSet = statement.executeQuery(addressIdQuery);
	        
	        if(addressResultSet.next())
	        {
	        	String addressLine1 = addressResultSet.getString("addressline1");
	        	String addressLine2 = addressResultSet.getString("addressline2");
	        	String pincode = addressResultSet.getString("pincode");
	        	String city = addressResultSet.getString("city");
	        	String phoneNumber = addressResultSet.getString("phonenumber");
	        	String landmark = addressResultSet.getString("landmark");
	        	String receiverName = addressResultSet.getString("receivername");
	            %>
	            <form action="AddressUpdation" method="post">
					<label>Name  </label><input type="text" value="<%=receiverName %>" name="receivername">
					<br><br>
					<label>Phone number  </label><input type="text" value="<%=phoneNumber %>" name="phonenumber">
					<br><br>
					<label>Address line 1  </label><input type="text" value="<%=addressLine1 %>" name="address1">
					<br><br>
					<label>Address line 2  </label><input type="text" value="<%=addressLine2 %>" name="address2">
					<br><br>
					<label>Landmark  </label><input type="text" value="<%=landmark %>" name="landmark">
					<br><br>
					<label>City  </label><input type="text" value="<%=city %>" name="city">
					<br><br>
					<label>Pincode  </label><input type="text" value="<%=pincode %>" name="pincode">
					<br><br>
					
					<input type="submit" name="submit" value="Update Now">
					</form>
	            <%
	            }%>
	            
	           </tbody>
	        </table><br>
	    <%}
	    catch(Exception e){
	        out.print(e.getMessage());%><br><%
	    }
	}
%>

<%

if(fullError != null){
	out.println(fullError);
}
 
%>
</body>
</html>