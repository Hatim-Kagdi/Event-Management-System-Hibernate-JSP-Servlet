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
	String userName = (String) session.getAttribute("userName");
	int userId = (Integer) session.getAttribute("userId");
	%>
	<h2>
		WELCOME!
		<%=userName%></h2>
	<hr>
	<ul>
		<li><a
			href="<%=request.getContextPath()%>/ViewUserProfile?id=<%=userId%>">View
				My Profile</a></li>
		<li><a href="<%=request.getContextPath()%>/ViewAllAttendee">View
				All Attendees </a></li>
		<li><a
			href="<%=request.getContextPath()%>/ViewAllOrganizer">View
				All Organizers </a></li>
	</ul>

	<a href="<%=request.getContextPath()%>/Logout"><button>LOGOUT</button></a>
</body>
</html>