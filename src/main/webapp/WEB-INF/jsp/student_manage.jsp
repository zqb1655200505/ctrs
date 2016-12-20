<%--
  Created by IntelliJ IDEA.
  User: zqb
  Date: 2016/12/10
  Time: 23:39
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
    <title>学生管理</title>
    <jsp:include page="nav.jsp"/>
    <script type="text/javascript" src="<%=basePath%>/js/dialog-min.js"></script>
    <link rel="stylesheet" href="<%=basePath%>/css/ui-dialog.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/student-manage.css">
    <script type="text/javascript" src="<%=basePath%>/js/student_manage.js"></script>
</head>
<body style="margin: 0 auto;">
<div class="container">
    <div class="top-title">
        <div class="course-list-title">
            <p style="font-size: 22px;">课程列表</p>
        </div>
        <div class="for-add-course">
            <div class="add-course" onclick="add_course()">
                <p>新建课程</p>
            </div>
        </div>
        <div class="for-search">
            <div class="for-input">
                <input style="width: 100%;height: 100%;" type="text" id="search"/>
            </div>
            <div class="for-icon">
                <img src="<%=basePath%>/image/search.png" class="icon-search" onclick="brn_search()">
            </div>
        </div>
    </div>
    <div class="course-list">
        <ul id="course-list">
            <%--<li class="course-item">--%>

                    <%--<div class="course-title">--%>
                        <%--<a href="#">操作系统</a>--%>
                    <%--</div>--%>
                    <%--<div class="course-content">--%>
                        <%--<div class="course-teacher">--%>
                            <%--<a href="#">马锐</a>--%>
                        <%--</div>--%>
                        <%--<div class="course-remark">--%>
                            <%--<p>操作系统（软件学院）-2016<br/>--%>
                                <%--软件工程基础实习-2014级<br/>--%>
                                <%--分布式系统-2013-软件工程（工学硕士）</p>--%>
                        <%--</div>--%>
                    <%--</div>--%>

            <%--</li>--%>

            <%--<li class="course-item">--%>
                <%--<div class="course-title">--%>
                    <%--<a href="#">操作系统</a>--%>
                <%--</div>--%>
                <%--<div class="course-content">--%>
                    <%--<div class="course-teacher">--%>
                        <%--<a href="#">马锐</a>--%>
                    <%--</div>--%>
                    <%--<div class="course-remark">--%>
                        <%--<p>操作系统（软件学院）-2016<br/>--%>
                            <%--软件工程基础实习-2014级<br/>--%>
                            <%--分布式系统-2013-软件工程（工学硕士）</p>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</li>--%>
        </ul>
    </div>

</div>
</body>
</html>
