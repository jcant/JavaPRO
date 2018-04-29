<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<html>
 	<head>
      	<title>Prog.kiev.ua</title>
   	</head>
  	<body>
  		<h1>Current date is:</h1>
    	<% Date d = new Date(); %>
    	<h2><%= d.toString() %></h2>
	</body>
</html>
