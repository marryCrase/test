<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/5/8 0008
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Title</title>
</head>
<body>

    <h3>所有账户信息</h3>

    <c:forEach items="${list}" var="account">
        ${account.name}
    </c:forEach>

</body>
</html>
