<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<link rel="stylesheet" href="resources/css/style.css">
<body>
<h2>Restaurant list</h2>

<%--@elvariable getId="restaurants" type="voteforlunch.model.Restaurant"--%>

<form method="post" action="dishes/get">
    <b>Select restaurant:</b>
    <select name="restId">
    <c:forEach items="${restaurants}" var="rest">
        <option value=${rest.id}>${rest.name}</option>
    </c:forEach>
    </select>
    <button type="submit">Select</button>
</form>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
