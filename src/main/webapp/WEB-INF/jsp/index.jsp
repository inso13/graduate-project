<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<link rel="stylesheet" href="resources/css/style.css">
<body>
<h3>Voting for lunch restaurant</h3>
<hr>
<form method="post" action="users">
    <b>Select user:</b>
    <select name="userId">
        <option value="100000">User</option>
        <option value="100001">Admin</option>
    </select>
    <button type="submit">Select</button>
</form>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
