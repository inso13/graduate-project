<%--@elvariable id="restId" type="java.lang.Integer"--%>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<link rel="stylesheet" href="resources/css/style.css">
<body>
<section>
    <hr>
    <h3>You have already voted today!</h3>
    <h3><a href="dishes/get?restId=${restId}">Press to go back to restaurants list</a></h3>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>

