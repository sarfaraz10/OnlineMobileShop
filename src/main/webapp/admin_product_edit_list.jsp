<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="java.util.regex.Pattern,java.util.regex.Matcher,java.util.Base64,java.sql.Connection,,java.io.ByteArrayOutputStream,java.sql.Blob,java.sql.ResultSet,java.sql.Statement,java.awt.image.BufferedImage,java.io.InputStream,javax.imageio.ImageIO,databaseconnection.DataBaseConnection"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Products Updation</title>
</head>
<body>

<a href="admin_home.jsp">Homepage</a><br><br>
		<%
    try
    {
        
        Connection connection = DataBaseConnection.createConnection();
        
        Statement statement = connection.createStatement();
        ResultSet productResultSet = statement.executeQuery("select * from products;");
        
        int i=0;
        while(productResultSet.next())
        {
        	i++;
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
            <div style="width:300px;border:2px solid black;padding: 0px 10px 10px 20px;">
				<p style="color:red;font-size:30px;"><%=productName %></p>
				<br>
				<img src="data:image/jpeg;base64,${imgBase}" alt="Image not Displayed" style="height:150px;"/>
				<br>
				<p>Stock : <b><%=stock %></b></p>
				<p style="font-size:20px;">Rs.<%=price %></p>
				<%if(Integer.parseInt(stock) >= 0) {%>
				
				<a href="admin_delete_product.jsp?productid=<%=productId %>" >
				<button style="padding: 15px;text-align: center;" value="clicked" >Delete</button>
				</a>
				<%
				}else{
					out.println("Out of Stock");
				} %>
			</div>
            <%
            }%>
            <br>
    <%}
    catch(Exception e){
        out.print(e.getMessage());%><br><%
    }
    %>
</body>
</html>