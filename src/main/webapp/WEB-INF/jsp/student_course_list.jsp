<%--
  Created by IntelliJ IDEA.
  User: zqb
  Date: 2016/12/20
  Time: 16:45
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
    <title>课程学生列表</title>
    <jsp:include page="nav.jsp"/>
    <script type="text/javascript" src="<%=basePath%>/js/dialog-min.js"></script>
    <link rel="stylesheet" href="<%=basePath%>/css/ui-dialog.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/student-manage.css">
</head>
<body>

</body>
</html>
