<jsp:useBean id="user" scope="request" type="voteforlunch.model.User"/>
<jsp:useBean id="restaurant" scope="request" type="voteforlunch.model.Restaurant"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<link rel="stylesheet" href="resources/css/style.css">
<body>
<section>
    <h2>Vote menu</h2>
    <h3>You are going to vote for: ${restaurant.name}</h3>
    <h3>You are logged as: ${user.name}</h3>
</section>
<form method="post" action="votes/confirm">
    <input type="hidden" name="restId" value="${restaurant.id}">
    <input type="hidden" name="userId" value="${user.id}">
    <button type="submit">Confirm</button>
    <button onclick="window.history.back()">Cancel</button>
</form>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
