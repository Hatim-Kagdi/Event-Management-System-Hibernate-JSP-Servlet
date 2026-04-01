<%@page import="in.keen.Entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User DashBoard</title>
</head>
<body>
	<%
	User user = (User) session.getAttribute("session_user");
	if(user == null){
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}
	%>
	<h2>
		WELCOME!
		<%=user.getUserName()%></h2>
	<hr>
	<ul>
		<li><a
			href="<%=request.getContextPath()%>/ViewUserProfile?id=<%=user.getUserId()%>">View
				My Profile</a></li>
		<li><a href="<%=request.getContextPath()%>/ViewAllAttendee">View
				All Attendees </a></li>
		<li><a
			href="<%=request.getContextPath()%>/ViewAllOrganizer?id=<%=user.getUserId()%>">View
				All Organizers </a></li>
	</ul>

	<a href="<%=request.getContextPath()%>/Logout"><button>LOGOUT</button></a>
</body>
</html>