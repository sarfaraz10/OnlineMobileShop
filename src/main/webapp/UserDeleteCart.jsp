<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Item from Cart</title>
</head>
<body>
<%
	String productId = request.getParameter("productId");
	HashSet<String> productList = (HashSet<String>)session.getAttribute("productList");
	if(productId != null){
		productList.remove(productId);
		session.setAttribute("productList",productList);
		response.sendRedirect("UserCartOrders.jsp");
	}
%>
</body>
</html>