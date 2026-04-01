<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Attendee DashBoard</title>
</head>
<body>
<% String userName = (String) session.getAttribute("userId");
	int userId = (int) session.getAttribute("userId"); %>
<h3>Welcome! <%= userName %></h3>
<li><a href="<%= request.getContextPath()%>/ViewUserProfile?id=<%= userId%>">View My Profile</a></li>

<a href="<%= request.getContextPath()%>/Logout"><button>LOGOUT</button></a>
</body>
</html>