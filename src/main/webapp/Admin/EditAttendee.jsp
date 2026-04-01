<%@page import="in.keen.Entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Attendee Edit Page</title>
</head>
<body>
	<%
	User user = (User) request.getAttribute("attendeeList");
	if (user == null) {
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}
	%>
	<h3>Edit Form</h3>
	<hr>
	<form action="<%=request.getContextPath()%>/updateAttendee"
		method="post" enctype="multipart/form-data">
		<input type="hidden" name="userId" value="<%=user.getUserId()%>">
		Name : <input type="text" name="userName"
			value="<%=user.getUserName()%>"><br> <br> Email : <input
			type="email" name="userEmail" value="<%=user.getUserEmail()%>"><br>
		<br> Bio :
		<textarea name="userBio"><%=user.getProfile().getUserBio()%></textarea>
		<br> <br> Profile Picture : <input type="file"
			name="userProfilePicture"><br> <img
			src="<%=request.getContextPath()%>/viewProfilePicture?userId=<%=user.getUserId()%>"
			 width="100" height="100"><br> <br>
		<button type="submit">UPDATE</button>
	</form>
	<br>
	<br>
	<a href="<%=request.getContextPath()%>/DashBoard/AdminDashboard.jsp"><button>BACK</button></a>
</body>
</html>