<%@page import="in.keen.Entity.Event"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Event Page</title>
</head>
<body>
	<%
	Event event = (Event) request.getAttribute("eventDetails");
	%>

	<h3>Edit Event Form</h3>
	<hr>
	<form action="<%=request.getContextPath()%>/updateEvent" method="post">
		<input type="hidden" name="eventId" value="<%=event.getEventId()%>"><br>
		<br> Event Name : <input type="text" name="eventName"
			value="<%=event.getEventName()%>"><br>
		<br> Event Description :
		<textarea name="eventDesp"><%=event.getEventDescription()%></textarea>
		<br>
		<br> Event Date : <input type="date" name="eventDate"
			value="<%=event.getEventDate()%>"><br>
		<br> Event Time : <input type="time" name="eventTime"
			value="<%=event.getEventTime()%>"><br>
		<br> Event Venue : <input type="text" name="eventVenue"
			value="<%=event.getEventVenue()%>"><br>
		<br> Event Capacity : <input type="number" name="eventCapacity"
			value="<%=event.getMaxCapacity()%>"> <br>
		<br>

		<button type="submit">UPDATE</button>
	</form><br><br>
	<a href="<%= request.getContextPath()%>/ViewAllEvents"><button>BACK</button></a>

</body>
</html>