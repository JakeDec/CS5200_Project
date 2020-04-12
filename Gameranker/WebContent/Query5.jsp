<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Most Popular Games in a Genre</title>
</head>
<body>
	<h1>Most Popular Games in a Genres</h1>
	<a href="Index.jsp"><- Back</a><br />
	<form action="query5" method="post">
		<p>
			<label for="genre">Genre</label>
			<input id="genre" name="genre" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
	<h1>Results</h1>
        <table border="1">
            <tr>
                <th>Game Name</th>
                <th>Genre</th>
                <th>Combination Score</th>
            </tr>
            <c:forEach items="${results}" var="result" >
                <tr>
                    <td><c:out value="${result.getResult1()}" /></td>
                    <td><c:out value="${result.getResult2()}" /></td>
                    <td><c:out value="${result.getResult3()}" /></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>