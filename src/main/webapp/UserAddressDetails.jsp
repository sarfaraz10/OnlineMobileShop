<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.regex.*,java.util.*,java.sql.*,javax.sql.*,java.awt.image.BufferedImage,java.io.*,javax.imageio.ImageIO,databaseconnection.DataBaseConnection"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Address Details</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<h1>Address Details</h1>
	
            <br>
            <a href="index.jsp">Return to Home</a>
            <br><br>
            <a href="UserProceedToBuy.jsp">Back to Proceed to Buy</a>
	<%
		String userId = (String)session.getAttribute("userid");
    try
    {
        Connection connection = DataBaseConnection.createConnection(); 
        
        Statement statement = connection.createStatement();
        String userIdQuery = "select * from address where userid='"+userId+"'";
        ResultSet userResultSet = statement.executeQuery(userIdQuery);
        
        while(userResultSet.next())
        {
        	int addressid = userResultSet.getInt("addressid");
        	String address1 = userResultSet.getString("addressline1");
        	String address2 = userResultSet.getString("addressline2");
        	String pincode = userResultSet.getString("pincode");
        	String city = userResultSet.getString("city");
        	String phonenumber = userResultSet.getString("phonenumber");
        	String landmark = userResultSet.getString("landmark");
        	String receivername = userResultSet.getString("receivername");
            %>
            <br>
            <div style="width:300px;border:2px solid black;padding: 0px 10px 10px 20px;">
				<p style="color:red;font-size:30px;"><%=receivername %></p>
				<p><%=address1 %></p>
				<p><%=address2 %></p>
				<p><%=landmark %></p>
				<p><%=city %></p>
				<p><%=pincode %></p>
				<p style="font-size:20px;"><%=phonenumber %></p>
				
				<a href="UserEditAddress.jsp?addressid=<%=userResultSet.getString("addressid") %>" >
				<button style="padding: 15px;text-align: center;"  name="edit" class="curve">Edit</button>
				</a>
				<a href="UserDeleteAddress.jsp?addressid=<%=userResultSet.getString("addressid") %>" >
				<button style="padding: 15px;text-align: center;"  name="delete" class="curve">Delete</button>
				</a>
			</div>
            <%
            }%>
            
           </tbody>
        </table><br>
    <%}
    catch(Exception e){
        out.print(e.getMessage());%><br><%
    }
    %>
	
	
	<a href="UserAddAddress.jsp" style="color:red;padding-top:10px">Add New Address</a>
</body>
</html>