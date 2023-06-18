<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.regex.*,java.sql.*,javax.sql.*,databaseconnection.DataBaseConnection"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Details</title>
      <link rel="stylesheet" href="css/table.css">
</head>
<body>
	<h1>User Details</h1>
	<br><br><br>
	<%
    try
    {
        
        Connection connection = DataBaseConnection.createConnection();
        Statement statement=connection.createStatement();
        String usersQuery = "select * from users";
        ResultSet resultSet=statement.executeQuery(usersQuery);
    %><table border=1 align=center style="text-align:center">
      <thead>
          <tr>
             <th>Sno</th>
             <th>User Name</th>
             <th>Email</th>
             <th>Phone number</th>
          </tr>
      </thead>
      <tbody>
      
        <%
        int iterate=0;
        while(resultSet.next())
        {
        	String userName = resultSet.getString("username");
        	String userEmail = resultSet.getString("useremail");
        	String userPhone = resultSet.getString("userphone");
        	iterate++;
            %>
            <tr>
                <td><%= iterate %></td>
                <td><%= userName %></td>
                <td><%= userEmail %></td>
                <td><%= userPhone%></td>
            </tr>
            <%}%>
           </tbody>
        </table><br>
    <%}
    catch(Exception exception){
        out.print(exception.getMessage());%><br><%
    }
    %>
</body>
</html>