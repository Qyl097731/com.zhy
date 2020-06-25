<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/6/24
  Time: 20:57
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
    <script>
        function checkInsertInfo(form) {
            var goodsName = form.goodsName.value;
            var goodsPrice = form.goodsPrice.value;
            var goodsNum = form.goodsNum.value;
            if (goodsNum == null || goodsNum == "" || goodsPrice == null || goodsPrice == "" || goodsName == null || goodsName == "") {
                alert("信息不全");
                return false;
            } else {
                return true;
            }
        }

        function searchCheck(form) {
            var id = form.id.value;
            if (id == null || id == "") {
                alert("信息不全");
                return false;
            } else {
                return true;
            }
        }
        $(document).ready(function () {
            $(".fun-1 span").click(function () {
                location.href=("GoodsServlet?op=search");
            })
        })
    </script>
<body>
<div class="content" style="width: 2000px;display: flex;">
    <div class="fun">
        <div class="fun-1">
            <span>下架商品</span>
        </div>
        <div class="fun-2">
            <span>按id查询商品</span>
            <form action="GoodsServlet" onsubmit="return searchCheck(this)" method="post">
                商品Id:<input type="text" name="id">
                <input type="text" name="op" value="search-id" hidden>
                <input type="submit" name="submit" value="查询商品">
            </form>
        </div>
        <div class="fun-3">
            <a href="GoodsServlet?op=insert-b">批量插入</a>
        </div>
        <div class="fun-4">
            <span>插入商品</span>
            <form action="GoodsServlet" onsubmit="return checkInsertInfo(this)">
                商品名称:<input type="text" name="goodsName">
                商品价格:<input type="text" name="goodsPrice">
                商品数量:<input type="text" name="goodsNum">
                <input type="text" name="op" value="insert" hidden>
                <input type="submit" name="submit" value="插入商品">
            </form>
        </div>
    </div>
    <c:if test="${not empty info}">
        <div class="result">
            <table style="margin:0 auto;width:800px;border-collapse: collapse; text-align: center ;" border="1px" ;
                   cellspacing="20px" ;padding="16px"
                   cellpadding="10px">
                <tr>
                    <th>序号</th>
                    <th>零食名称</th>
                    <th>价格</th>
                    <th>库存</th>
                    <th>下架</th>
                </tr>
                <c:forEach items="${info.list}" var="goods" varStatus="vs">
                    <tr>
                        <td>${vs.index+1}</td>
                        <td>${goods.goodsName}</td>
                        <td>${goods.goodsPrice}</td>
                        <td>${goods.goodsNum}</td>
                        <td><a href="GoodsServlet?op=delete&id=${goods.id}&pageNum=${info.pageNum}">下架</a></td>
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
    </c:if>
</div>
</body>
</html>
