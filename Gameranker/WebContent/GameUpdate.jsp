<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update a Game</title>
</head>
<body>
	<h1>Update Game</h1>
	<a href="Index.jsp"><- Back</a><br />
	<form action="gameupdate" method="post">
		<p>
			<label for="gameid">GameId</label>
			<input id="gameid" name="gameid" value="">
		</p>
		
		<p>
			<label for="newpublisher">NewPublisher</label>
			<input id="newpublisher" name="newpublisher" value="">
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