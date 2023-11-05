<%--
  Created by IntelliJ IDEA.
  User: 蒋洪成
  Date: 2023/11/1 0001
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <base href="http://${header.host}${pageContext.request.contextPath}/customer/">
    <title>新顾客注册</title>
</head>
<body>
<h1>新顾客注册</h1>
<form action="reg" method="post">
    用户名：<input type="text" name="name"><br>
    密码：<input type="password" name="password"><br>
    <button type="submit">确 定</button>
</form>
<div>
    <span><a href="book/list">首页</a></span>
    <span><a href="login.jsp">顾客登录</a></span>
    <span><a href="../admin/login.jsp">管理员登录</a></span>
</div>
</body>
</html>
