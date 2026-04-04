<%@page import="in.keen.Entity.Event"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Event Page</title>
<style>
.event-container {
	display: flex;
	flex-wrap: wrap;
	padding: 20px;
	gap: 20px;
}

.event-card {
	border: 1px solid #ddd;
	border-radius: 10px;
	width: 300px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	overflow: hidden;
	transition: 0.3s;
}

.event-card:hover {
	transform: translateY(-5px);
	box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}

.event-header {
	background-color: #f4f4f4;
	padding: 15px;
	border-bottom: 1px solid #ddd;
}

.event-body {
	padding: 15px;
}

.event-footer {
	padding: 15px;
	text-align: center;
	background: #fafafa;
}

.btn-book {
	background-color: #28a745;
	color: white;
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	width: 100%;
}
</style>
</head>
<body>
<h2> All Active Events </h2>
	<hr>
	<%
	List<Object[]> list = (List<Object[]>) request.getAttribute("eventList");
	if (list == null || list.isEmpty()) {
	%>
	<p>No Active Events Right Now , ComeBack later!</p>
	<%
	}else{
	%>
	<form action="<%= request.getContextPath()%>/SearchEvent" method="get">
	<input type="text" name="searchQuery" placeholder="Enter Event Name...">
	<button type="submit">Search</button>
	<button type="submit">Reset</button>
	</form><br>
	<div class="event-container">
		<%
		for (Object[] row : list) {
			Event e = (Event) row[0];
			Long currentCount = (Long) row[1];
		%>
		<div class="event-card">
			<div class="event-header">
				<h3 style="margin: 0;">
					<%=e.getEventName()%></h3>
				<small><b>Organized By : <%=e.getOrganizer().getUserName()%></b></small>
			</div>
			<div class="event-body">
				<p>Description :<%=e.getEventDescription()%></p>
				<p>Date :<%=e.getEventDate()%></p>
				<p>Time :<%=e.getEventTime()%></p>
				<p>Venue :<%=e.getEventVenue()%></p>
				<p>Max Capacity :<%=e.getMaxCapacity()%></p>
			</div>
			<div class=event-footer></div>
			<% if(currentCount >= e.getMaxCapacity()){ %>
			<button disabled style="background-color: #ccc; cursor: not-allowed;">SOLD OUT</button>
			<%} else { %>
			<form action="<%=request.getContextPath()%>/BookEvent" method="post">
				<input type="hidden" name="eventId" value="<%=e.getEventId()%>">
				<button type="submit" class="btn-book">BOOK MY SPOT</button>
			</form>
			<%} %>
		</div>
		
		<%
		}
		%>
	</div>
	<%} %>
	<br><br>
	<a href="<%=request.getContextPath()%>/DashBoard/AttendeeDashboard.jsp"><button>BACK</button></a>

</body>
</html>