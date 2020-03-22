<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a UserReview</title>
</head>
<body>
	<form action="userreviewsread" method="post">
		<h1>Search for User Reviews by User Id</h1>
		<p>
			<label for="userid">User Id</label>
			<input id="userid" name="userid" value="${fn:escapeXml(param.userid)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="UserReviewsCreate"><a href="userreviewscreate">Create a User Review</a></div>
	<div id="UserReviewsDelete"><a href="userreviewsdelete">Delete a User Review</a></div>
	<div id="UserReviewsUpdate"><a href="userreviewsupdate">Update a User Review</a></div>
	
	<br/>
	<h1>Matching Reviews</h1>
        <table border="1">
            <tr>
                <th>User Id</th>
                <th>Username</th>
                <th>User Review</th>
            </tr>
            <c:forEach items="${reviews}" var="reviews" >
                <tr>
                    <td><c:out value="${reviews.getUser().getUserId()}" /></td>
                    <td><c:out value="${reviews.getUser().getUserName()}" /></td>
                    <td><c:out value="${reviews.getReview().getReview()}" /></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
