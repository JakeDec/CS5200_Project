<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Publisher</title>
</head>
<body>
	<form action="publisherread" method="post">
		<h1>Search for a Publisher by Publisher Name</h1>
		<p>
			<label for="publishername">Publisher Name</label>
			<input id="publishername" name="publishername" value="${fn:escapeXml(param.publishername)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="publisherCreate"><a href="publishercreate">Create Publisher</a></div>
	<div id="publisherDelete"><a href="publisherdelete">Delete Publisher</a></div>
	<div id="publisherUpdate"><a href="publisherupdate">Update Publisher</a></div>
	
	<br/>
	<h1>Matching Games</h1>
        <table border="1">
            <tr>
                <th>Publisher Id</th>
                <th>Publisher Name</th>
            </tr>
            <c:forEach items="${publishers}" var="publisher" >
                <tr>
                    <td><c:out value="${publisher.getPublisherId()}" /></td>
                    <td><c:out value="${publisher.getPublisherName()}" /></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
