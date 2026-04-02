<%@page import="in.keen.Entity.Booking"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Attendee Booked in the Event Page</title>
</head>
<body>
<h3>Booked Attendee Table</h3><hr>
	<%
	List<Booking> list = (List<Booking>) request.getAttribute("bookingAttendeeList");
	if (list == null || list.isEmpty()) {
	%>
	<p>No Attendee Booked in this Event!</p>
	<%
	} else {
	%>
	<table border="1" cellpadding="10">
	<thead>
	<tr>
	<th>Booking Id</th>
	<th>Name </th>
	<th>Email </th>
	<th>Booking Time </th>
	<th>Booking Status </th>
	</tr>
	</thead>
	<tbody>
	<% for(Booking b : list){ %>
	<tr>
	<td><%= b.getBookingId() %></td>
	<td><%= b.getAttendee().getUserName() %></td>
	<td><%= b.getAttendee().getUserEmail() %></td>
	<td><%= b.getBookingDate() %></td>
	<td><%= b.getStatus() %></td>
	</tr>
	<%} %>
	</tbody>
	</table><br><br>
	<%
	}
	%>
	<a href="<%= request.getContextPath()%>/ViewAllEvents"><button>BACK</button></a>
</body>
</html>