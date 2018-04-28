<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <script type="text/javascript" src=" https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.name},欢迎光临网站~~
<c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.keys.contains('btn')}">
<button onclick="foo()">点击有惊喜</button>
</c:if>
<script>
    function foo() {
        $.get("/btn/testBtn").then(function (r) {
            console.log(r);
            alert(r.msg);
        })
    }
</script>
</body>
</html>
