<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User login</title>
</head>
<body>
<form action="login" method="post"/>
User Name<input type="text" name="email"/>
Password<input type="password" name="password"/>
<input type="submit" value="login"/>
</form>
${msg}
</body>
</html>