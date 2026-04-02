<%@page import="in.keen.Entity.Event"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Events</title>
</head>
<body>
	<h3>Events Table</h3>
	<hr>
	<%
	List<Object[]> data = (List<Object[]>) request.getAttribute("eventData");
	if (data == null) {
	%>
	<p>No Event Found!</p>
	<a href="<%=request.getContextPath()%>/Organizer/AddNewEvent.jsp">Create
		one Now!</a>
	<br>
	<br>
	<%
	} else {
	%>
	<table border="1" cellpadding="10">
		<thead>
			<tr>
				<th>Event Id</th>
				<th>Name</th>
				<th>Description</th>
				<th>Date</th>
				<th>Time</th>
				<th>Venue</th>
				<th>Capacity</th>
				<th>Bookings</th>
				<th>View Bookings</th>
				<th>EDIT</th>
				<th>DELETE</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (Object[] obj : data) {
				Event e = (Event) obj[0];
				Long count =(Long) obj[1];
			%>
			<tr>
				<td><%=e.getEventId()%></td>
				<td><%=e.getEventName()%></td>
				<td><%=e.getEventDescription()%></td>
				<td><%=e.getEventDate()%></td>
				<td><%=e.getEventTime()%></td>
				<td><%=e.getEventVenue()%></td>
				<td><%=e.getMaxCapacity()%></td>
				<td><%= count %></td>
				<td><a href="<%= request.getContextPath()%>/ViewBookedAttendees?id=<%= e.getEventId() %>"> View </a>
				</td>
				<td><a
					href="<%=request.getContextPath()%>/editEvent?id=<%=e.getEventId()%>"><button>EDIT</button></a>
				<td><a
					href="<%=request.getContextPath()%>/deleteEvent?id=<%=e.getEventId()%>"><button>DELETE</button></a>
			</tr>
		</tbody>

		<%
		}
		}
		%>
	</table>
	<br>
	<br>


	<a
		href="<%=request.getContextPath()%>/DashBoard/OrganizerDashboard.jsp"><button>BACK</button></a>
</body>
</html>