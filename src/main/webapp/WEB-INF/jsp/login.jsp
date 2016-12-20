<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path ;

%>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/default.css">
    <%--<link rel='stylesheet prefetch' href='http://fonts.useso.com/css?family=Open+Sans:600'>--%>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/styles.css">
    <link href="<%=basePath%>/css/login.css" rel="stylesheet">
    <script type="text/javascript" src="<%=basePath%>/js/jquery-2.0.0.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/loginPage.js"></script>
</head>
<body onkeydown="keyLogin()">
<div class="body-container">
    <div class="header-title">
        <h1>课程教学资源管理系统</h1>
    </div>
    <div class="login-wrap">
        <div class="login-html">
            <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
            <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
            <div class="login-form">
                <%--<form id="login" action="/ctrs/login_check" method="post">--%>
                    <div class="sign-in-htm">
                        <div class="group">
                            <label for="user_login" class="label" style="font-family: 'Microsoft YaHei',sans-serif;font-size: 11px;">Username</label>
                            <input id="user_login" name="user_login" type="text" class="input">
                        </div>
                        <div class="group">
                            <label for="pass_login" class="label" style="font-family: 'Microsoft YaHei',sans-serif;font-size: 11px;">Password</label>
                            <input id="pass_login" name="pass_login" type="password" class="input" data-type="password">
                        </div>
                        <div class="group">
                            <input id="check" type="checkbox" class="check" checked>
                            <label for="check" style="font-family: 'Microsoft YaHei',sans-serif;font-size: 14px;"><span class="icon"></span> Keep me Signed in</label>
                        </div>
                        <div class="group" style="font-size: 18px;">
                            <input type="button" class="button" value="Sign In" name="submit_login" onclick="login()">
                        </div>
                        <div class="hr"></div>
                        <div class="foot-lnk">
                            <a href="#">Forgot Password?</a>
                        </div>
                    </div>
                <%--</form>--%>
                <%--<form id="register" action="/ctrs/register_check" method="post">--%>
                    <div class="sign-up-htm">
                        <div class="group">
                            <label for="user_register" class="label" style="font-family: 'Microsoft YaHei',sans-serif;font-size: 11px;">Username</label>
                            <input id="user_register" name="user_register" type="text" class="input">
                        </div>
                        <div class="group">
                            <label for="pass_register" class="label" style="font-family: 'Microsoft YaHei',sans-serif;font-size: 11px;">Password</label>
                            <input id="pass_register" name="pass_register" type="password" class="input" data-type="password">
                        </div>
                        <div class="group">
                            <label for="pass_confer" class="label" style="font-family: 'Microsoft YaHei',sans-serif;font-size: 11px;">Repeat Password</label>
                            <input id="pass_confer" name="pass_confer" type="password" class="input" data-type="password">
                        </div>

                        <div class="group" style="font-size: 18px;">
                            <input type="button" class="button" value="Sign Up" name="submit_register" onclick="register()">
                        </div>
                        <div class="hr"></div>
                    </div>
                <%--</form>--%>
            </div>
        </div>
    </div>
</div>
</body>
</html>
