<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a UserReview</title>
</head>
<body>
	<h1>Create UserReview</h1>
	<a href="Index.jsp"><- Back</a><br />
	<form action="userreviewscreate" method="post">
		<p>
			<label for="userId">UserId</label>
			<input id="userId" name="userId" value="0">
		</p>
		<p>
			<label for="gameId">GameId</label>
			<input id="gameId" name="gameId" value="0">
		</p>
		<p>
			<label for="score">Score</label>
			<input id="score" name="score" value="0">
		</p>
		<p>
			<label for="review">Review</label>
			<input id="review" name="review" value="">
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