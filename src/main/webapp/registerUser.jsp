<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Registration Page</title>
</head>
<body>
	<h1>Welcome to Event Management System</h1>
	<h3>Register for Events</h3>
	<form action="<%=request.getContextPath()%>/register"
		method="post" enctype="multipart/form-data">
		Name : <input type="text" name="userName"
			placeholder="Enter your full name..." required><br>
		<br> Email : <input type="email" name="userEmail"
			placeholder="Enter your email id..." required><br>
		<br> Password : <input type="password" name="userPassword"
			placeholder="Enter passsword..." required><br>
		<br> Role : <select name="userRole">
			<option value="" selected disabled >--Select a Role--</option>
			<option value="ATTENDEE">Attendee</option>
			<option value="ORGANIZER">Organizer</option>
		</select><br>
		<br> Bio :
		<textarea name="userBio" placeholder="Tell us about yourself...."></textarea>
		<br>
		<br> Profile Picture : <input type="file" name="profilePic"
			accept="image/*"><br>
		<br>

		<button type="submit">Register</button>
	</form><br><br>
	<a href="<%= request.getContextPath()%>/login.jsp"><button>LOGIN</button></a>
</body>
</html>