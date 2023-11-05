<%@ page import="cdu.jhc.model.Book" %>
<%@ page import="cdu.jhc.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <base href="http://${header.host}${pageContext.request.contextPath}/customer/">
    <title>购书网站</title>
</head>
<body>
<div>
    <h1>购书网站</h1>
    <a href="book/list">首页</a>
    <a href="../admin/login.jsp">后台管理入口</a>
</div>
<div>
    <%
        User customer = (User) session.getAttribute("customer");
        if (customer == null) {
    %>
    <%-- 顾客未登录 --%>
    <a href="login.jsp">登录</a>
    <a href="register.jsp">新顾客注册</a>
    <%
    } else {
    %>
    <%-- 顾客已登录 --%>
    <span>你好，<%=customer.getName() %></span>
    <a href="reset?id=<%=customer.getId() %>">重置密码</a>
    <a href="logout">退出</a>
    <%
        }
    %>
</div>
<hr>
<h2>图书详情</h2>

<%
    Book book = (Book) request.getAttribute("book");
%>
<img src="<%=book.getCoverUrl() %>" alt="图书封面" height="200px">
<h3>书名：<%=book.getTitle() %>
</h3>
<p>作者：<%=book.getAuthor() %>
    <br>出版社：<%=book.getPress() %>
    <br>出版日期：<%=book.getPublishDate() %>
    <br>上架日期：<%=book.getMarketDate() %>
    <br>折扣：<%=book.getSale() %>
    <br>价格：<%=book.getPrice() %>
    <%
        if (book.getStock() > 0) {
    %>
    <a href="cart/in?id=<%=book.getId() %>">加入购物车</a>
    <%
        }
    %>
    <br>
    <%=book.getInfo() %>
</p>

</body>
<html>