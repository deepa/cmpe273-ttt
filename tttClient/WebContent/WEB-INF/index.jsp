<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tic Tac Toe</title>
</head>
<body>
<form name="mainform" method="POST" action="Entry" onsubmit="return validate()" >
Enter your name: <input type="text" name = "playername"> <br> <br>
<input type="submit" value="submit">
</form>

</body>
</html>