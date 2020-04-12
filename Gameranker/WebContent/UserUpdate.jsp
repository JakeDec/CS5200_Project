<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update a User</title>
</head>
<body>
	<h1>Update User</h1>
	<a href="Index.jsp"><- Back</a><br />
	<form action="userupdate" method="post">
		<p>
			<label for="userid">UserId</label>
			<input id="userid" name="userid" value="0">
		</p>
		
		<p>
			<label for="newusername">New User Name (Optional)</label>
			<input id="newusername" name="newusername" value="">
		</p>
		
		<p>
			<label for="newSteamId">New Steam ID (Optional)</label>
			<input id="newSteamId" name="newSteamId" value="">
		</p>
		
		
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>