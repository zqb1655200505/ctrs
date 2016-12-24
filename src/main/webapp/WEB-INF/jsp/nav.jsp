<%--
  Created by IntelliJ IDEA.
  User: zqb
  Date: 2016/12/19
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path ;

%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/normalize1.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/htmleaf-demo.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style1.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/nav.css">
    <script type="text/javascript" src="<%=basePath%>/js/jquery-2.0.0.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/nav.js"></script>
</head>
<body>

    <div class="header">
        <nav class="navigation">
            <div class="wrapper">
                <ul class="navigation-list ul-reset">

                    <li class="navigation-item ib">
                        <a href="/ctrs/index" class="navigation-link">
                            主页
                        </a>
                    </li>

                    <li class="navigation-item ib" id="learning">
                        <a href="/ctrs/courseRelate" class="navigation-link">
                            乐学
                        </a>
                    </li>

                    <%--教师专有--%>
                    <li class="navigation-item ib" style="display: none;" id="publishNotice">
                        <a href="/ctrs/publishNotice" class="navigation-link">
                            发布公告
                        </a>
                    </li>

                    <%--教师专有--%>
                    <li class="navigation-item ib" style="display: none;" id="studentManage">
                        <a href="/ctrs/studentManage" class="navigation-link">
                            学生管理
                        </a>
                    </li>

                    <li class="navigation-item ib">
                        <a href="/ctrs/onlineDiscussion" class="navigation-link">
                            在线答疑
                        </a>
                    </li>

                    <li class="navigation-item ib">
                        <a href="/ctrs/centralPerson" class="navigation-link">
                            个人中心
                        </a>
                    </li>
                </ul>
            </div>
            <div class="for-username">
                <span id="username" style="color: #40bcff;"></span>
            </div>
            <div class="btn"  onclick="sign_out()">
                <a  style="color: white;">注销</a>
            </div>
        </nav>
    </div>

</body>
</html>
