<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login User Page</title>
</head>
<body>
<h3>Login Page</h3>
<form action="<%= request.getContextPath()%>/loginUser" method="post">
Email : <input type="email" name="loginEmail" placeholder="Enter your email.."><br><br>
PassWord : <input type="text" placeholder="Enter your password" name="loginPassword"><br><br>
<button type="submit">LOGIN</button>
</form>
<br>
<a href="<%= request.getContextPath()%>/registerUser.jsp"><button>REGISTER</button></a>
</body>
</html>