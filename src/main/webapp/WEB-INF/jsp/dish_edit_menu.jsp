<jsp:useBean id="restId" scope="request" type="java.lang.Integer"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<link rel="stylesheet" href="resources/css/style.css">
<section>
    <h2>${param.action == 'create' ? 'Create dish' : 'Edit dish'}</h2>
    <hr>
    <jsp:useBean id="dish" type="voteforlunch.model.Dish" scope="request"/>
    <form method="post" action="dishes?restId=${restId}">
        <input type="hidden" name="id" value="${dish.id}">
        <dl>
            <dt>Description:</dt>
            <dd><input type="text" value="${dish.description}" size=40 name="description"></dd>
        </dl>
        <dl>
            <dt>Price:</dt>
            <dd><input type="text" value="${dish.price}" size=40 name="price"></dd>
        </dl>
        <input type="hidden" name="restId" value="${restId}">
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
