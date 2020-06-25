<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/6/24
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
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
        <c:forEach items="${info.list}" var="goods" varStatus="vs">
            <tr>
                <td>${vs.index+1}</td>
                <td>${goods.goodsName}</td>
                <td>${goods.goodsPrice}</td>
                <td>${goods.goodsNum}</td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="10">
                <a href="GoodsServlet?pageNum=1&op=search">首页</a><a
                    href="GoodsServlet?pageNum=${info.prePage}&op=search"
                    contenteditable="${info.pageNum==1}"
                    style="outline: none">上一页</a>
                <c:forEach items="${nums}" var="num">

                    <a href="GoodsServlet?pageNum=${info.prePage}&op=search"
                       style="text-decoration:${num==info.pageNum?'none':'underline'}"
                       contenteditable="${num==info.pageNum}" style="outline: none">${num}</a>
                </c:forEach>
                <a href="GoodsServlet?pageNum=${info.prePage}&op=search"
                   contenteditable="${info.pageNum==info.pages}" style="outline: none">下一页</a><a
                    href="GoodsServlet?pageNum=${info.prePage}&op=search">末页</a>
            </td>
        </tr>
    </table>
</div>
</div>
</body>
</html>
