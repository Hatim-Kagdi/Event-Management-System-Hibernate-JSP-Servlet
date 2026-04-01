<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add New Event Page</title>
</head>
<body>
<h3>Add New Event Form</h3>
<hr>
<form action="<%= request.getContextPath()%>/createNewEvent" method="post">
Event Name : <input type="text" name="eventName" placeholder="Enter event name.."><br><br>
Event Description : <textarea name="eventDesp" placeholder="Enter event description..."></textarea><br><br>
Event Date : <input type="date" name="eventDate"><br><br>
Event Time : <input type="time" name="eventTime"><br><br>
Event Venue : <input type="text" name="eventVenue" placeholder="Enter event venue..."><br><br>
Event Capacity : <input type="number" name="eventCapacity" placeholder="Enter event Capacty..."><br><br>
<button type="submit">SUBMIT</button>
</form><br><br>
<a href="<%= request.getContextPath()%>/DashBoard/OrganizerDashboard.jsp"><button>BACK</button></a>
</body>
</html>