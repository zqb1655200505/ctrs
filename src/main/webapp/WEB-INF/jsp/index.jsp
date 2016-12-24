<%--
  Created by IntelliJ IDEA.
  User: zqb
  Date: 2016/12/9
  Time: 12:16
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
    <jsp:include page="nav.jsp"/>
    <link rel="stylesheet" href="<%=basePath%>/css/index.css">
    <script type="text/javascript" src="<%=basePath%>/js/jquery-2.0.0.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/index.js"></script>
    <title>首页</title>
</head>
<body>
    <div class="body-container">
        <div style="margin-left: 50px;">
            <h1 style="color: black;">最新公告</h1>
        </div>
        <ul class="notice-list" id="notice_list">
            <li class="notice-item">
                <div class="notice-logo">
                    <img src="<%=basePath%>/image/news_icon.png">
                </div>
                <div class="notice-item-content">
                    <div class="notice-title">
                        <a href="#">操作系统</a>
                    </div>
                    <div class="notice-date" style="color: black;">
                        <span style="margin-right: 30px;">zqb</span>
                        <span>2016/12/22</span>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</body>
</html>
