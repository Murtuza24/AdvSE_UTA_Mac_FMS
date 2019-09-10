<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<title>Admin - UTA Mac FMS</title>
</head>
<body>
	<div class="button-box col-lg-12 offset-md-1">
		<h1>Hello, ${homePage.lastname} , ${homePage.firstname}! <a class="btn btn-danger offset-md-1"
			href="/UTA_Mac_FMS/LogoutController">Logout</a></h1><br> <a
			class="btn btn-primary col-md-4" href="/UTA_Mac_FMS/profilePage.jsp"
			role="button">My Profile</a> <br> <br> <a
			class="btn btn-primary col-md-4" href="/UTA_Mac_FMS/register.jsp"
			role="button">Search for a User</a> <br> <br> <a
			class="btn btn-primary col-md-4" href="/UTA_Mac_FMS/register.jsp"
			role="button">Edit User Details</a> <br> <br> <a
			class="btn btn-primary col-md-4" href="/UTA_Mac_FMS/register.jsp"
			role="button">Change User Role</a> <br> <br>

	</div>
</body>
</html>