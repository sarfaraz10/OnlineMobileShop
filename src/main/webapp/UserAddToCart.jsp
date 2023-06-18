<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add to Cart</title>
</head>
<body>
<%
	String productId = request.getParameter("productId");
	String buynow = request.getParameter("buynow");
	HashSet<String> productList = (HashSet<String>)session.getAttribute("productList");
	
	System.out.println("This is buynow "+buynow+" "+productId);
	
	if(productList == null){
		System.out.println("Inside add to cart ra");
		productList = new HashSet<String>();
	}
	
	if(productId != null){
		productList.add(productId);
		session.setAttribute("productList",productList);
		
		if(buynow != null){
			response.sendRedirect("UserCartOrders.jsp");
		}
		else{
			response.sendRedirect("index.jsp");
		}
	}
	else{
		System.out.println("Ouside ra"+productId);
	}
	
%>
</body>
</html>