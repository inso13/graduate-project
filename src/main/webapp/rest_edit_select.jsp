<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Restaurant list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Restaurant edit list</h2>

<%--@elvariable id="restaurants" type="voteforlunch.model.Restaurant"--%>


<form method="post" action="dishes?action=get">
    <b>Select restaurant to watch menu:</b>
    <select name="restId">
        <c:forEach items="${restaurants}" var="rest">
            <option value=${rest.id}>${rest.name}</option>
        </c:forEach>
    </select>
    <button type="submit">Select</button>
</form>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Name</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${restaurants}" var="rest">
        <tr>
            <td>${rest.name}</td>
            <td><a href="restaurants?action=update&id=${rest.id}">Update</a></td>
            <td><a href="restaurants?action=delete&id=${rest.id}">Delete</a></td>
        </tr>
    </c:forEach>
    <hr>
    <a href="restaurants?action=create">Add Restaurant</a>
    <hr>
</table>
</body>
</html>
