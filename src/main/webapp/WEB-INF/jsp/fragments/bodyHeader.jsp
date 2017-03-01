<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<header>
<div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <h3 href="meals" class="navbar-brand"><a href="${pageContext.request.contextPath}/"><fmt:message key="app.home"/></a>&nbsp;|&nbsp;
            <a href="restaurants"><fmt:message key="app.title"/></a><spring:message code="app.title"/></h3>

        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
            </ul>
        </div>
    </div>
</div>
</header>