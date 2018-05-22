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
	

	<div class="row">

		<div class="col-sm-8">
			<div class="container">
  				<form class="form-inline" action="./get_zip" enctype="multipart/form-data" method="POST">
    				<div class="form-group">
      					<label for="user_file">File:</label>
      					<input type="file" class="form-control" id="userFile" name="userFile" placeholder="Select file" style="height: auto;">
    				</div>
    				<button type="submit" class="btn btn-default">Get Zip</button>
  				</form>
			</div>			
		</div>
		<div class="col-sm-2"></div>
	</div>
</body>
</html>
