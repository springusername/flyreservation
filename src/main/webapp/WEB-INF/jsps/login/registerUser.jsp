<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register User</title>
</head>
<body>
<h1>User Registration</h1>
<form action="registerUser" method="Post">
First Name: <input type="text" name="firstName"/><br>
Last Name: <input type="text" name="lastName"/><br>
User Name: <input type="text" name="email"/><br>
Password: <input type="password" name="password"/><br>
Confirm Password: <input type="password" name="confirmPassword"/><br>
<input type="submit" value="Register"/>
</form>
</body>
</html>