<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Game</title>
</head>
<body>
		<h1>Search for a Game by GameName</h1>
	<a href="Index.jsp"><- Back</a><br />
	<form action="gameread" method="post">
		<p>
			<label for="gamename">GameName</label>
			<input id="gamename" name="gamename" value="${fn:escapeXml(param.gamename)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="gameCreate"><a href="gamecreate">Create Game</a></div>
	<div id="gameDelete"><a href="gamedelete">Delete Game</a></div>
	<div id="gameUpdate"><a href="gameupdate">Update Game</a></div>
	
	<br/>
	<h1>Matching Games</h1>
        <table border="1">
            <tr>
                <th>GameName</th>
                <th>Publisher</th>
                <th>ReleaseYear</th>
            </tr>
            <c:forEach items="${games}" var="game" >
                <tr>
                    <td><c:out value="${game.getGameName()}" /></td>
                    <td><c:out value="${game.getPublisher().getPublisherName()}" /></td>
                    <td><c:out value="${game.getReleaseYear()}" /></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
