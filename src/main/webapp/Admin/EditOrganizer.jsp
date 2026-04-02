<%@page import="in.keen.Entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Organizer Page</title>
</head>
<body>
	<%
	User user = (User) request.getAttribute("organizerDetails");
	%>

	<form action="<%=request.getContextPath()%>/updateOrganizer"
		method="post" enctype="multipart/form-data">
		<input type="hidden" name="userId" value="<%=user.getUserId()%>"><br>
		<br> Name : <input type="text" name="userName"
			value="<%=user.getUserName()%>"><br> <br> Email : <input
			type="email" name="userEmail" value="<%=user.getUserEmail()%>"><br>
		<br> Bio :
		<textarea name="userBio"><%= user.getProfile().getUserBio()%></textarea><br><br>
		Profile Picture : <input type="file" name="userProfilePicture"><br>
		<br> <img
			src="<%=request.getContextPath()%>/viewProfilePicture?id=<%=user.getUserId()%>"
			width="100" height="100"><br> <br>
		<button type="submit">UPDATE</button>
	</form>
	<br>
	<br>

	<a href="<%=request.getContextPath()%>/ViewAllOrganizer"><button>BACK</button></a>

</body>
</html>