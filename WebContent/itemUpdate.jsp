<%@ page import="com.Item" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//get the item and 
	if(request.getParameter("itemID") != null){
		Item itemObj = new Item();
		String formMsg = itemObj.getItemForUpdate(request.getParameter("itemID"));
		
		session.setAttribute("formMsg", formMsg);
	}
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Item management</title>
</head>
<body>
	<h1>Update Item</h1>	
	<% out.print(session.getAttribute("formMsg")); %>
</body>
</html>