<%@page import="in.keen.Entity.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View All Organizer Page</title>
</head>
<body>
	<h3>Organizer List</h3>
	<hr>
	<%
	List<User> list = (List<User>) request.getAttribute("organizerList");
	if (list == null || list.isEmpty()) {
	%>
	<p>No Organizer to display!</p>
	<%
	} else {
	%>
	<form action="<%=request.getContextPath()%>/SearchOrganizerForAdmin">
		<input type="text" name="searchQuery"
			placeholder="Enter Name or Id....">
		<button type="submit">Search</button>
		<button type="submit">Reset</button>
	</form>
	<br>
	<br>
	<table border="1" cellpadding="10">
		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Email</th>
				<th>Bio</th>
				<th>Profile Picture</th>
				<th>EDIT</th>
				<th>DELETE</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (User u : list) {
			%>
			<tr>
				<td><%=u.getUserId()%></td>
				<td><%=u.getUserName()%></td>
				<td><%=u.getUserEmail()%></td>
				<td><%=u.getProfile().getUserBio()%></td>
				<td><img
					src="<%=request.getContextPath()%>/viewProfilePicture?id=<%=u.getUserId()%>"
					width="100" height="100"></td>
				<td><a
					href="<%=request.getContextPath()%>/editOrganizer?id=<%=u.getUserId()%>">
						<button>EDIT</button>
				</a></td>
				<td><a
					href="<%=request.getContextPath()%>/deleteOrganizer?id=<%=u.getUserId()%>">
						<button>DELETE</button>
				</a>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<%
	}
	%><br>
	<br>
	<div>
		<%
		int currentPage = (Integer) request.getAttribute("currentPage");
		int totalPages = (Integer) request.getAttribute("totalPages");

		if (currentPage > 1) {
		%>
		<a
			href="<%=request.getContextPath()%>/ViewAllOrganizer?page=<%=currentPage - 1%>">&laquo;
			Previous</a>
		<%
		}
		%>
		<span><%=currentPage%></span>
		<%
		if (currentPage < totalPages) {
		%>
		<a
			href="<%=request.getContextPath()%>/ViewAllOrganizer?page=<%=currentPage + 1%>">Next
			&raquo;</a>
		<%
		}
		%>

	</div>
	<a href="<%=request.getContextPath()%>/DashBoard/AdminDashboard.jsp"><button>BACK</button></a>
</body>
</html>