<%@page contentType="text/html;charset=UTF-8" language="java" isErrorPage="false" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getLocalPort() + path + "/";
%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            $(".fun-2 span").click(function () {
                $(".fun form").hide();
                $(".fun-2 form").show();
            })
            $(".fun-4 span").click(function () {
                $(".fun form").hide();
                $(".fun-4 form").show();
            })
            $(".fun").click(function () {
                if(${empty authority}){
                    alert("请登录");
                    location.href=("userLogin.jsp");
                }
            })
        })
    </script>
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
<div class="content" style="width: 2000px;display: flex;justify-items: center;justify-content: space-around">
    <div class="fun" style="width: 800px;display: flex;justify-items: center;justify-content: space-around">
        <div class="fun-1">
            <span>下架商品</span>
        </div>
        <div class="fun-2">
            <span>按id查询商品</span>
            <form action="GoodsServlet" onsubmit="return searchCheck(this)" method="post" hidden>
                商品Id:<input type="text" name="id">
                <br>
                <input type="text" name="op" value="search-id" hidden>
                <input type="submit" name="submit" value="查询商品">
                <br>
            </form>
        </div>
        <div class="fun-3">
            <a href="GoodsServlet?op=insert-b">批量插入</a>
        </div>
        <div class="fun-4">
            <span>插入商品</span>
            <form action="GoodsServlet" onsubmit="return checkInsertInfo(this)" hidden>
                商品名称:<input type="text" name="goodsName">
                <br>
                商品价格:<input type="text" name="goodsPrice">
                <br>
                商品数量:<input type="text" name="goodsNum">
                <br>
                <input type="text" name="op" value="insert" hidden>
                <input type="submit" name="submit" value="插入商品">
            </form>
        </div>
    </div>
    <c:if test="${not empty goodsList}">
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
                <c:forEach items="${goodsList}" var="goods" varStatus="vs">
                    <tr>
                        <td>${vs.index+1}</td>
                        <td>${goods.goodsName}</td>
                        <td>${goods.goodsPrice}</td>
                        <td>${goods.goodsNum}</td>
                        <td><a href="GoodsServlet?op=delete&id=${goods.id}">下架</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
</div>
</body>
</html>
