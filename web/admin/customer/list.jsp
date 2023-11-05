<%--
  Created by IntelliJ IDEA.
  User: 蒋洪成
  Date: 2023/11/1 0001
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="cdu.jhc.model.UserStatus" %>
<%@ page import="cdu.jhc.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="cdu.jhc.model.Customer" %>
<%@ page import="cdu.jhc.util.ParseUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <base href="http://${header.host}${pageContext.request.contextPath}/admin/">
    <title>顾客管理</title>
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

<h2>顾客列表</h2>
<form action="customer/query" method="post">
    管理员用户名 ：<input type="text" name="name">
    状态 ：<select name="statusName">
    <option value="" selected>全部</option>
    <option value="冻结">冻结</option>
    <option value="正常">正常</option>
    <option value="异常">异常</option>
</select>
    <button type="submit">查 询</button>
</form>
<table>
    <tr>
        <th>id</th>
        <th>用户名</th>
        <th>密码</th>
        <th>创建时间</th>
        <th>最后一次访问时间</th>
        <th>密码重置</th>
        <th>帐户状态</th>
    </tr>
    <%
        List<Customer> customers = (List<Customer>) request.getAttribute("customers");
        for (User customer : customers) {
    %>
    <tr>
        <td><%=customer.getId() %>
        </td>
        <td><%=customer.getName() %>
        </td>
        <td><%=customer.getPassword() %>
        </td>
        <td><%=ParseUtil.formatDateTime(customer.getCreateTime()) %>
        </td>
        <td><%=ParseUtil.formatDateTime(customer.getLastAccessTime()) %>
        </td>
        <td>
            <a href="customer/reset?id=<%=customer.getId() %>"
            >重置密码</a>
        <td>
            <%=customer.getStatus().getName() %>
            <%=customer.getStatus() == UserStatus.NORMAL
                    ? "<a href='customer/freeze?id=" + customer.getId() + "'>冻结</a>"
                    : "<a href='customer/active?id=" + customer.getId() + "'>解冻</a>" %>
        </td>
    </tr>
    <%
        }
    %>
</table>

<%-- 分页导航--%>
<%
    int p = (int) request.getAttribute("p");
    int pCount = (int) request.getAttribute("pCount");
    if (p > 1) {
        out.print("<a href='customer/list?p=" + (p - 1) + "'>上一页</a>");
    }
    if (p < pCount) {
        out.print("<a href='customer/list?p=" + (p + 1) + "'>下一页</a>");
    }
%>

</body>
</html>
