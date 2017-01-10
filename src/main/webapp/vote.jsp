<jsp:useBean id="user" scope="request" type="voteforlunch.model.User"/>
<jsp:useBean id="restaurant" scope="request" type="voteforlunch.model.Restaurant"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meal</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h2><a href="index.html">Home</a></h2>
    <hr>
    <h2>Vote menu</h2>
    <h3>You are going to vote for: ${restaurant.name}</h3>
    <h3>You are logged as: ${user.name}</h3>
    <h3>Sorry, vote API not ready yet :(</h3>
</section>
</body>
</html>
