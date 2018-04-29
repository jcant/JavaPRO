<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Calendar" %>
<html>
 	<head>
      	<title>Prog.kiev.ua</title>
   	</head>
  	<body>
  		<%
  			Calendar cal = Calendar.getInstance();
  			int day = cal.get(Calendar.DAY_OF_WEEK);
  			int hour = cal.get(Calendar.HOUR_OF_DAY);
  			boolean opened = ((day == Calendar.MONDAY) && (hour >= 10) && (hour <= 18));
  		%>
  		
  		<h1>Today we are:</h1>
  		<% if (opened) { %>
  			<h2>Opened</h2>
  		<% } else { %>
  			<h2>Closed</h2>
  		<% } %>
	</body>
</html>
