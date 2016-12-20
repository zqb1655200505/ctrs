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
    <title>首页</title>
</head>
<body>
    <div class="body-container">
        首页
    </div>
</body>
</html>
