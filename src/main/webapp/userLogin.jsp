<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/6/23
  Time: 23:48
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
    <base href="<%=basePath%>">
    <title>Title</title>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
    <script src="src/unslider.min.js"></script>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        .autSelect{
            margin-top: 20px;
            font-size: 20px;
            text-align: center;
        }
        .autSelect select{
            font-size: 20px;
        }
        .btnSelect {
            text-align: center;
        }

        .btnSelect button {
            width: 100px;
            border: none;
            outline: 0;
            font-size: 20px;
            margin-top: 20px
        }

        .btnSelect .active {
            background: #666;
            color: white;
        }

        #login, #register {
            display: flex;
            justify-content: space-around;
            justify-items: center;
            width: 320px;
            height: 100px;
            margin: 0 auto;
        }

        label {
            display: block;
            padding: 20px;
        }

        input {
            font-size: 18px;
            letter-spacing: 1px;
        }

        .submitLab {
            width: 300px;
            text-align: center;
        }

        .submitLab input {
            height: 40px;
            width: 150px;
            border: 0px;
            border-radius: 5px;
            background: olive;
        }
    </style>
    <script>
        $(document).ready(function () {
            $(".btnSelect button:eq(1)").click(function () {
                $(this).addClass("active").siblings().removeClass("active");
                $("#login").css("display", "none");
                $("#register").css("display", "block");
            })
            $(".btnSelect button:eq(0)").click(function () {
                $(this).addClass("active").siblings().removeClass("active");
                $("#login").css("display", "block");
                $("#register").css("display", "none");
            })
        })
        function loginCheck(form) {
            var username = form.username.value;
            var password = form.password.value;
            if(username == null || username == "" ){
                $(".usernameError-l").text("　　请输入账号");
                return false;
            }else if(password == null || password == "") {
                $(".passwordError-l").text("　　请输入密码");
                return false;
            }
            return true;
        }
        function registerCheck(form) {
            var username = form.username.value;
            var password = form.password.value;
            if(username == null || username == "" ){
                $(".usernameError-r").text("　　请输入账号");
                return false;
            }else if(password == null || password == "") {
                $(".passwordError-r").text("　　请输入密码");
                return false;
            }
            return true;
        }

    </script>
</head>
<body>
<%@include file="header.html" %>
<div class="content">
    <div class="btnSelect">
        <button class="active">登录</button>
        <button>注册</button>
    </div>
    <div id="login">
        <form action="UserServlet" onsubmit="return loginCheck(this)">
            <input type="text" hidden value="0" name="op">
            <div class="autSelect">
                身份：<select name="autSelect">
                <option value="0" selected="selected">卖家</option>
                <option value="1">买家</option>
            </select>
            </div>
            <label for="username-l">
                账号：<input type="text" name="username" id="username-l" placeholder="账号&nbsp;username">
            </label>
            <span class="usernameError-l"></span>
            <label for="password-l">
                密码：<input type="password" name="password" id="password-l" placeholder="密码&nbsp;password">
            </label>
            <span class="passwordError-l"></span>
            <label for="submit-l" class="submitLab">
                <input type="submit" name="submit" id="submit-l" value="登录"/>
            </label>
        </form>
    </div>
    <div id="register" style="display: none">
        <form action="UserServlet" onsubmit="return registerCheck(this)">
            <input type="text" hidden value="1" name="op">
            <div class="autSelect">
                身份：<select name="autSelect">
                <option value="0" selected="selected">卖家</option>
                <option value="1">买家</option>
            </select>
            </div>
            <label for="username-r">
                账号：<input type="text" name="username" id="username-r" placeholder="账号&nbsp;username">
            </label>
            <span class="usernameError-r"></span>
            <label for="password-r">
                密码：<input type="password" name="password" id="password-r" placeholder="密码&nbsp;password">
            </label>
            <span class="passwordError-r"></span>
            <label for="submit-r" class="submitLab">
                <input type="submit" name="submit" id="submit-r" value="注册"/>
            </label>
        </form>
    </div>
</div>
</body>
</html>
