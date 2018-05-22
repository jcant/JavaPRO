<%@page import="org.springframework.http.HttpStatus"%>
<%@page import="org.springframework.http.ResponseEntity"%>
<%@page import="org.springframework.http.MediaType"%>
<%@page import="org.springframework.http.HttpHeaders"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>Prog.kiev.ua</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
</head>
<body>
	
	<% Set<Long> ids = (Set<Long>) request.getAttribute("photos_id");%>
	    <% for (Long id : ids) { %>
	<form class="form-inline" action="./delete" method="POST">
	<div class="row align-middle" style="margin:10px; border-bottom:1px solid gray;">
    	<div class="col-sm-1" style="padding: 10px;">
    		<input type="checkbox" name = "photo_to_delete[]" value="<%=id %>">
    		   		
    	</div>
	    <div class="col-sm-2">
    		<img src="./thumb/<%=id %>" >
	    </div>
    	<div class="col-sm-2">
    		<a href="./photo/<%=id %>">
    			<%=id %>
    		</a>
    	</div>
  	</div>
  	<% } %>
  	
  	<%if(!ids.isEmpty()){ %>
  	<div class="row" style="margin:10px;">
  		<div class="col-sm-1">
  			<button type="submit" class="btn btn-default">Delete selected</button>
  		</div>
  	</div>
  	<%} %>
  	</form>

	<div class="row">

		<div class="col-sm-8">
			<div class="container">
  				<form class="form-inline" action="./add_photo" enctype="multipart/form-data" method="POST">
    				<div class="form-group">
      					<label for="photo">Photo:</label>
      					<input type="file" class="form-control" id="photo" name="photo" placeholder="Select file" style="height: auto;">
    				</div>
    				<button type="submit" class="btn btn-default">Send</button>
  				</form>
			</div>			
		</div>
		<div class="col-sm-2"></div>
	</div>
</body>
</html>
