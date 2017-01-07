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
<c:forEach items="${restaurants}" var="rest">
    <h3>Name: ${rest.name}; </h3>
</c:forEach>
<%--@elvariable id="dishes" type="voteforlunch.model.Dish"--%>
<c:forEach items="${dishes}" var="dish">
    <h2>Name: ${dish.description}; ${dish.price};</h2>
</c:forEach>

</body>
</html>
