<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>JSP Actions Example</title>
</head>
<body>

<h1>Weather Forecast</h1>
 <form action="<%= request.getContextPath() %>/weather" method="post">
  City: <input type="text" name="city">
  <br> <br>

  <br>
  <input type="submit" value="ok">
 </form>
</body>
</html>