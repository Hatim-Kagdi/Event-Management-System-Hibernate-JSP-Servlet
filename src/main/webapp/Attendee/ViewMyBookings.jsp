<%@page import="in.keen.Entity.Booking"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Bookings</title>
</head>
<body>
	<%
	List<Booking> list = (List<Booking>) request.getAttribute("bookingList");
	if (list == null || list.isEmpty()) {
	%>
	<p>You have no bookings!</p>
	<a href="<%=request.getContextPath()%>/ViewAllPublicEvents">Click
		here to book an event!</a>
	<%
	} else {
	%>
	<table border="1" cellpadding="10">
		<thead>
			<tr>
				<th>Booking Id</th>
				<th>Event Name</th>
				<th>Event Venue</th>
				<th>Event Time</th>
				<th>Event Date</th>
				<th>Organized By</th>
				<th>Status</th>
				<th>Cancel</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (Booking b : list) {
			%>

			<tr>
				<td><%=b.getBookingId()%></td>
				<td><%=b.getEvent().getEventName()%></td>
				<td><%=b.getEvent().getEventVenue()%></td>
				<td><%=b.getEvent().getEventTime()%></td>
				<td><%=b.getEvent().getEventDate()%></td>
				<td><%=b.getEvent().getOrganizer().getUserName()%></td>
				<td><%=b.getStatus()%></td>
				<td><a href="<%=request.getContextPath()%>/CancelBooking?id=<%= b.getBookingId() %>"><button>CANCEL</button></a></td>
			</tr>
			<%
			}
			%>
		</tbody>
		<%
		}
		%>
	</table>
	<br>
	<a href="<%=request.getContextPath()%>/DashBoard/AttendeeDashboard.jsp">Back
		to Dashboard</a>
</body>
</html>