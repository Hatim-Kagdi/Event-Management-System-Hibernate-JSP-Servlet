<%@page import="in.keen.Entity.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Attendees Data</title>
</head>
<body>
<% List<User> attendeeList = (List<User>) request.getAttribute("attendeeList"); %>
<h3>All Attendees Table</h3>
<table border="1">
<thead>
<tr>
<th>Id</th>
<th>Name</th>
<th>Email</th>
<th>Bio</th>
<th>Profile Picture</th>
<th>Edit</th>
<th>Delete</th>
</tr>
</thead>
<% if(attendeeList != null){
for(User u : attendeeList){ %>
<tbody>
<tr>
<td><%= u.getUserId() %></td>
<td><%= u.getUserName() %></td>
<td><%= u.getUserEmail() %></td>
<td><%= u.getProfile().getUserBio() %></td>
<td><img src="viewProfilePicture" width="100" hieght="100"></td>
<td><a href="<%= request.getContextPath()%>/editAttendee?id=<%= u.getUserId()%>"><button>EDIT</button></a></td>
<td><a href="<%= request.getContextPath()%>/deleteAttendee"><button>DELETE</button></a></td>
</tr>
</tbody>
<% } }%>
</table><br>
<a href="<%= request.getContextPath()%>/DashBoard/AdminDashboard.jsp"><button>Back</button></a>

</body>
</html>