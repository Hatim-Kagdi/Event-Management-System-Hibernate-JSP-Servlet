<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Attendee DashBoard</title>
</head>
<body>
	<%
	String userName = (String) session.getAttribute("userName");
	Integer userId = (Integer) session.getAttribute("userId");
	%>
	<h3>
		Welcome!
		<%=userName%></h3>
	<ul>
		<li><a
			href="<%=request.getContextPath()%>/ViewUserProfile?id=<%=userId%>">View
				My Profile</a></li>
		<li><a href="<%=request.getContextPath()%>/ViewAllPublicEvents">View
				All Events</a></li>
		<li><a
			href="<%=request.getContextPath()%>/ViewMyBookings">View
				My Bookings</a></li>
	</ul>
	<a href="<%=request.getContextPath()%>/Logout"><button>LOGOUT</button></a>
</body>
</html>