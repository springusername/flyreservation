<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Find flights</title>
</head>
<body>
<h1>Find flights</h1>
<form action="findFlights" method="post">
From<input type="text" name="from">
To:<input type="text" name="to">
Departure Date:<input type="text" name="departureDate">
<input type="submit" value="Search">
</form>
</body>
</html>