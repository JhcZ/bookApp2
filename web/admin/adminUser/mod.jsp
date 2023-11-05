<%--
  Created by IntelliJ IDEA.
  User: 蒋洪成
  Date: 2023/11/1 0001
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="cdu.jhc.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <base href="http://${header.host}${pageContext.request.contextPath}/admin/">
    <title>管理员用户管理</title>
</head>
<body>
<div>
    <h1>购书网站后台管理平台</h1>
    <a href="../customer/book/list">前台首页</a>
    <a href="book/list">图书列表</a>
    <a href="book/add.jsp">添加图书</a>
    <a href="customer/list">顾客列表</a>
    <a href="adminUser/list">管理员列表</a>
    <a href="adminUser/add.jsp">添加管理员</a>
</div>
<div>
    <%
        User admin = (User) session.getAttribute("admin");
        if (admin == null) {
            //管理员未登录
            response.sendRedirect(request.getContextPath()+"/admin/login.jsp");
        } else {
    %>
    <%-- 管理员已登录 --%>
    <a href="reset?id=<%=admin.getId() %>">重置密码</a>
    <a href="logout">退出</a>
    <%
        }
    %>
</div>
<hr>

<h1>修改管理员</h1>
<%
        User oldAdminUser = (User) request.getAttribute("admin");
        if (oldAdminUser == null) {
            out.print("获取管理员信息失败");
            return;
        }
%>

<form action="adminUser/mod" method="post">
    管理员id: <input type="text" name="id" readonly value="<%=oldAdminUser.getId() %>">
    管理员用户名:<input type="text" name="name" value="<%=oldAdminUser.getName() %>"><br>
    <input type="text" name="status" value="<%=oldAdminUser.getStatus() %>" hidden="hidden">
    <input type="text" name="createTime" value="<%=oldAdminUser.getCreateTime() %>" hidden="hidden">
    <input type="text" name="lastAccessTime" value="<%=oldAdminUser.getLastAccessTime() %>" hidden="hidden">
    <button type="submit">提 交</button>
    </div>
</form>

</body>
</html>
