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
	<h3>All Attendees Table</h3>
	<hr>
	<form action="<%=request.getContextPath()%>/SearchAttendeeForAdmin">
		Search : <input type="text" name="searchQuery"
			placeholder="Enter Search Keyword...">
		<button type="submit">Search</button>
		<button type="submit">Reset</button>
	</form>
	<br>
	<%
	List<User> attendeeList = (List<User>) request.getAttribute("attendeeList");
	if (attendeeList == null || attendeeList.isEmpty()) {
	%>
	<p>NO Attendee to display</p>
	<%
	} else {
	%>
	<table border="1" cellpadding="10">
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
		<%
		for (User u : attendeeList) {
		%>
		<tbody>
			<tr>
				<td><%=u.getUserId()%></td>
				<td><%=u.getUserName()%></td>
				<td><%=u.getUserEmail()%></td>
				<td><%=u.getProfile().getUserBio()%></td>
				<td><img
					src="<%=request.getContextPath()%>/viewProfilePicture?id=<%=u.getUserId()%>"
					width="100" height="100"></td>
				<td><a
					href="<%=request.getContextPath()%>/editAttendee?id=<%=u.getUserId()%>"><button>EDIT</button></a></td>
				<td><a
					href="<%=request.getContextPath()%>/deleteAttendee?id=<%=u.getUserId()%>"><button>DELETE</button></a></td>
			</tr>
		</tbody>
		<%
		}
		}
		%>
	</table>
	<br>
	<%
	int currentPage = (Integer) request.getAttribute("currentPage");
	int totalPages = (Integer) request.getAttribute("totalPages");
	String currentSearch = (String) request.getAttribute("searchQuery");
    String searchParam = (currentSearch != null) ? "&searchQuery=" + currentSearch : "";
    String servletPath = (currentSearch != null) ? "SearchAttendeeForAdmin" : "ViewAllAttendee";

	if (currentPage > 1) {
	%>
	<a
		href="<%=request.getContextPath()%>/<%= servletPath %>?page=<%=currentPage - 1%><%= searchParam%>">&laquo;
		Previous</a>
	<%
	}
	%>
	<span><%=currentPage%></span>
	<%
	if (currentPage < totalPages) {
	%>
	<a
		href="<%=request.getContextPath()%>/<%= servletPath %>?page=<%=currentPage + 1%><%= searchParam%>">Next
		&raquo;</a>
	<%
	}
	%>
	<a href="<%=request.getContextPath()%>/DashBoard/AdminDashboard.jsp"><button>Back</button></a>

</body>
</html>