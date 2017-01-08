<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Restaurant list</h2>

<%--@elvariable id="restaurants" type="voteforlunch.model.Restaurant"--%>

<form method="post" action="restaurant">
    <b>Select restaurant:</b>
    <select name="restId">
    <c:forEach items="${restaurants}" var="rest">
        <option value=${rest.id}>${rest.name}</option>
    </c:forEach>
    </select>
    <button type="submit">Select</button>
</body>
</html>