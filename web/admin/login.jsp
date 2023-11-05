<%--
  Created by IntelliJ IDEA.
  User: 蒋洪成
  Date: 2023/11/1 0001
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <base href="http://${header.host}${pageContext.request.contextPath}/admin/">
    <title>管理员登录</title>
</head>
<body>
<h1>管理员登录</h1>
<form action="login" method="post">
    用户名：<input type="text" name="name"><br>
    密码：<input type="password" name="password"><br>
    验证码：<input type="text" name="inputCode">
    <img src="validCode" id="vCode" onclick="refreshCode()"><br>
    <button type="submit">登 录</button>
</form>
<div>
    <span><a href="../customer/book/list">首页(图书列表) </a></span>
    <span><a href="../customer/login.jsp"> 顾客登录</a></span>
</div>
</body>
</html>
<script>
    function refreshCode() {
        document.getElementById("vCode").src = "validCode?r=" + Math.random();
    }
</script>
