<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Maven_SSM</title>
</head>
<body>

<a href="account/findAll">测试查询</a>

<h3>测试包</h3>

<form action="/account/saveAccount" method="post">
    姓名：<input type="text" name="name"><br>
    金额：<input type="text" name="money"><br>
    <input type="submit" value="保存">
</form>
</body>
</html>