<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Organizer DashBoard</title>
</head>
<body>
	<%
	String userName = (String) session.getAttribute("userName");
	int userId = (int) session.getAttribute("userId");
	%>
	<h3>
		Welcome!
		<%=userName%>
	</h3>
	<hr>
	<ul>
		<li><a
			href="<%=request.getContextPath()%>/ViewUserProfile?id=<%=userId%>">View
				My Profile</a></li>

		<li><a
			href="<%=request.getContextPath()%>/Organizer/AddNewEvent.jsp">Create
				a new Event</a></li>
		<li><a href="<%=request.getContextPath()%>/ViewAllEvents">View
				My Events</a></li>
	</ul><br><br>
	<a href="<%=request.getContextPath()%>/Logout"><button>LOGOUT</button></a>
</body>
</html>