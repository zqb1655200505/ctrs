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
    <title>发布公告</title>
    <link rel="stylesheet" href="<%=basePath%>/css/publish-notice.css">
    <jsp:include page="nav.jsp"/>
    <script type="text/javascript" src="<%=basePath%>/js/utf8-jsp/ueditor.config.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/utf8-jsp/ueditor.all.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath%>/js/utf8-jsp/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/jquery-2.0.0.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/publish_notice.js"></script>
</head>
<body>
<div class="container">
    <div class="for-editor">
        <div class="notice-title">
            <p style="font-weight: bolder;">标题:&nbsp<input type="text" style="width: 400px;" id="title"/>&nbsp<span style="color:#545657;font-weight: 200;">最多100个字符</span></p>
        </div>
        <input type="hidden" id="hidEditorCnt"/>
        <script id="editor" type="text/plain" style="width:1040px;min-height:500px;"></script>
        <script type="text/javascript">
            //实例化编辑器
            var ue = UE.getEditor('editor', {
                autoHeight: false
            });

            if (!ue.isServerConfigLoaded()) {
                ue.loadServerConfig();
            }
        </script>
        <div class="for-submit">
            <div class="btn" onclick="confer_publish()">
                <a>确认发布</a>
            </div>
        </div>
    </div>

</div>
</body>
</html>
