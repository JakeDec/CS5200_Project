<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update a Publisher Name</title>
</head>
<body>
	<h1>Update Name</h1>
	<a href="Index.jsp"><- Back</a><br />
	<form action="publisherupdate" method="post">
		<p>
			<label for="oldpublishername">Current Publisher Name</label>
			<input id="oldpublishername" name="oldpublishername" value="">
		</p>
		<p>
			<label for="newpublishername">New Publisher Name</label>
			<input id=newpublishername name="newpublishername" value="">
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