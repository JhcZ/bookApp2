<%@ page import="cdu.jhc.model.Book" %>
<%@ page import="java.util.List" %>
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
<%
    List<Book> books = (List<Book>) request.getAttribute("books");
    for (Book book : books) {
        out.print("<div>");
%>
<a href="book/info?id=<%=book.getId() %>"><img width="64px" src="<%=book.getCoverUrl() %>"></a>
<p>
    <a href="book/info?id=<%=book.getId() %>"><%=book.getTitle() %>
    </a><br>
    作者：<%=book.getAuthor() %><br>
    出版社：<%=book.getPress() %><br>
    价格：<%=book.getPrice() %><br>
    库存：<%=book.getStock() %><br>
</p>
<%
        out.print("</div>");
    }
%>
</body>
</html>