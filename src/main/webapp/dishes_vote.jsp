<%--@elvariable id="votes" type="java.Integer"--%>
<%--@elvariable id="restId" type="java.lang.Integer"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<head>
    <title>Meal list</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h2><a href="index.html">Home</a></h2>
    <h2>Dish list</h2>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Description</th>
            <th>Price</th>
        </tr>
        </thead>
        <%--@elvariable id="dishes" type="voteforlunch.model.dish"--%>
        <c:forEach items="${dishes}" var="dish">
            <jsp:useBean id="dish" scope="page" type="voteforlunch.model.Dish"/>
            <tr>
                <td>${dish.description}</td>
                <td>${dish.price}</td>
            </tr>
        </c:forEach>
        <hr>
        <a href="restaurants?action=vote&restId=${restId}">Vote for this restaurant</a>
        <h2>Today votes: ${votes}</h2>
        <hr>
    </table>
</section>
</body>
</html>