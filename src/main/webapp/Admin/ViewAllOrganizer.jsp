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
	<%
	List<User> list = (List<User>) request.getAttribute("organizerList");
	%>

	<table border="1">
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
			if (list != null && !list.isEmpty()) {
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
			}
			%>
		</tbody>
	</table>
	<a href="<%=request.getContextPath()%>/DashBoard/AdminDashboard.jsp"><button>BACK</button></a>
</body>
</html>