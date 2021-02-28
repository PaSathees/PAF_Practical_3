<%@ page import="com.Item" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//trying to test insert function--------------
	if(request.getParameter("itemCode") != null){
		String stsMsg = "";		
		if(request.getParameter("Action").equalsIgnoreCase("insert")){
			//insert item			
			Item itemObj = new Item();
			stsMsg = itemObj.insertItem(request.getParameter("itemCode"), 
					request.getParameter("itemName"), 
					request.getParameter("itemPrice"), 
					request.getParameter("itemDesc"));		
			
		} else if(request.getParameter("Action").equalsIgnoreCase("update")) {
			//updating item
			Item itemObj = new Item();
			stsMsg = itemObj.updateItem(request.getParameter("itemID"), 
					request.getParameter("itemCode"), 
					request.getParameter("itemName"), 
					request.getParameter("itemPrice"), 
					request.getParameter("itemDesc"));
			
		} else if(request.getParameter("Action").equalsIgnoreCase("delete")) {
			
		}
		session.setAttribute("statusMsg", stsMsg);			
	} 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Items Management</title>
</head>
<body>
	<h1>Items Management</h1>
	<form method="post" action="item.jsp">
		Item code: <input name="itemCode" type="text"><br>
		Item name: <input name="itemName" type="text"><br>
		Item price: <input name="itemPrice" type="text"><br>
		Item description: <input name="itemDesc" type="text"><br>
		<input name="Action" type="hidden" value="insert">
		<input type="submit" name="btnSubmit" value="save">		
	</form>

	<% out.print(session.getAttribute("statusMsg")); %>
	<br>
	
	<%
		Item itemObj = new Item();
		out.print(itemObj.readItems());
	%>
	
</body>
</html>