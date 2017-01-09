<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Dish</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h2><a href="index.html">Home</a></h2>
    <h2>${param.action == 'create' ? 'Create dish' : 'Edit dish'}</h2>
    <hr>
    <jsp:useBean id="dish" type="voteforlunch.model.Dish" scope="request"/>
    <form method="post" action="dishes?action=create">
        <input type="hidden" name="id" value="${dish.id}">
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" value="${dish.description}" size=40 name="name"></dd>
        </dl>
        <dl>
            <dt>Price:</dt>
            <dd><input type="text" value="${dish.price}" size=40 name="price"></dd>
        </dl>
        <input type="hidden" name="restId" value="${dish.restId}">
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>
</body>
</html>
