<%@page import="in.keen.Entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Profile Page</title>
</head>
<body>
	<%
	User user = (User) request.getAttribute("userProfileDetails");
	%>
	<h3>Your Profile</h3>
	<hr>
	Id :
	<%=user.getUserId()%><br> Name :
	<%=user.getUserName()%><br> Email :
	<%=user.getUserEmail()%><br> Password : *********(Encrypted)
	<br> Role :
	<%=user.getUserRole()%><br> Bio :
	<%=user.getProfile().getUserBio()%><br> Profile Picture :
	<img
		src="<%=request.getContextPath()%>/viewProfilePicture?id=<%=user.getUserId()%>"
		width="150" hieght="150">
	<br>
	<a href="<%=request.getContextPath()%>/DashBoard/AdminDashboard.jsp"><button>BACK</button></a>
</body>
</html>