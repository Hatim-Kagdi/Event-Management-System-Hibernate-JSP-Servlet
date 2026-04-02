<%@page import="in.keen.Entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>This is Organizer Profile</title>
</head>
<body>
	<h2>Organizer Profile</h2>
	<%
	User user = (User) request.getAttribute("userProfileDetails");
	%>
	
	ID : <%= user.getUserId() %><br><br>
	Name : <%= user.getUserName() %><br><br>
	Email : <%= user.getUserEmail() %><br><br>
	Password : ******** (Encrypted)<br><br>
	Bio : <%= user.getProfile().getUserBio() %><br><br>
	Profile Picture : <img src="<%= request.getContextPath()%>/viewProfilePicture?id=<%= user.getUserId() %>" width="150" height="150"><br><br>
	<a href="<%= request.getContextPath()%>/DashBoard/OrganizerDashboard.jsp"><button>BACK</button> </a>
	
</body>
</html>