<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<link rel="stylesheet" href="resources/css/style.css">

<body>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3>Voting for lunch restaurant</h3>
            <div class="view-box">
                <div class="row">
                    <div class="col-sm-7">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <form method="post" action="users">
                                    <b>Select user:</b>
                                    <select name="userId">
                                        <option value="100000">User</option>
                                        <option value="100001">Admin</option>
                                    </select>
                                    <button type="submit" class="btn btn-success">
                                        <span class="glyphicon glyphicon-log-in" aria-hidden="true">
                                        </span></button>
                                </form>
                            </div>
                            <div class="panel-footer text-right">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<hr>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
