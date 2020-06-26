
<%@page contentType="text/html;charset=UTF-8" language="java" isErrorPage="false" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getLocalPort() + path + "/";
%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%--    <base href="<%=basePath%>">--%>
    <title>Title</title>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
    <script src="src/unslider.min.js"></script>
    <style>
        * {
            margin: 0;
            padding: 0
        }
    </style>
<body>
<header>
    <c:if test="${empty authority}">
        <a href="userLogin.jsp">登录</a>
    </c:if>
    <c:if test="${not empty authority}">
        ${username}　
        <a href="logout.jsp">退出</a>
    </c:if>
    <p>当前在线<%=application.getAttribute("count")%></p>
</header>
<div class="content">
    <table style="margin:0 auto;width:800px;border-collapse: collapse; text-align: center ;" border="1px" ;
           cellspacing="20px" ;padding="16px"
           cellpadding="10px">
        <tr>
            <th>序号</th>
            <th>零食名称</th>
            <th>价格</th>
            <th>库存</th>
        </tr>
        <c:forEach items="${goodsList}" var="goods" varStatus="vs">
            <tr>
                <td>${vs.index+1}</td>
                <td>${goods.goodsName}</td>
                <td>${goods.goodsPrice}</td>
                <td>${goods.goodsNum}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</div>
</body>
</html>
