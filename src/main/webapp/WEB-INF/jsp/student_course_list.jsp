<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/student-course-list.css">
    <script type="text/javascript" src="<%=basePath%>/js/student_course_list.js"></script>

</head>
<body style="margin: 0 auto;">
    <div class="container">
        <div class="header-title">

            <div class="for-btns">
                <ul>
                    <li>
                        <div class="btn1" onclick="batch_remove_student()">
                            <a style="color: white;">批量删除</a>
                        </div>
                    </li>
                    <li>
                       <div class="btn1" onclick="add_student()">
                           <a style="color: white;">添加新学生</a>
                       </div>
                    </li>
                    <li>
                        <div class="btn1" onclick="import_student()">
                            <a style="color: white;">从已有学生导入</a>
                        </div>
                    </li>
                    <li>
                        <div class="btn1" onclick="batch_add_student()">
                            <a style="color: white;">批量添加学生</a>
                        </div>
                    </li>
                    <li>
                        <div class="btn1" onclick="upload_resource()">
                            <a style="color: white;">上传课件</a>
                        </div>
                    </li>
                    <li>
                        <div class="btn1" onclick="delete_course()">
                            <a style="color: white;">删除该课程</a>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <div class="list-title">
            <div class="li-stu-title" id="li-stu-title" onclick="showContent()">学生列表</div>
            <div class="li-res-title" id="li-res-title" onclick="showContent1()">课件列表</div>
        </div>
        <div class="body-content" id="stu_list" style="margin-bottom: 50px;">
            <table class="altrowstable" id="alternatecolor" style="width: 80%;font-size: 16px;">
                <%--表头--%>
                <tr style="font-weight: bolder;">
                    <th style="width: 10%;">
                        <input type="checkbox" id="selectAll" onclick="checkAllForEnable(this.id,'alternatecolor')"/>
                        <label for="selectAll">
                            <span>全选</span>
                        </label>
                    </th>
                    <th style="width: 45%;">
                        <span>学生姓名</span>
                    </th>
                    <th style="width: 45%;">
                        <span>操作</span>
                    </th>
                </tr>

                <%--数据--%>
                <c:forEach items="${userList}" var="userItem">
                    <tr>
                        <td style="width: 10%;">
                            <input type="checkbox"  name="check_box"  id="userCheck${userItem.userId}" onclick="checkChanged(this.id)"/>
                        </td>
                        <td style="width: 45%;">
                            <span>${userItem.userName}</span>
                        </td>
                        <td style="width: 45%;">
                            <button id="stu${userItem.userId}" onclick="remove_student(this.id)">移除</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="body-content" id="resource_list" style="display: none; margin: 0 auto;margin-bottom: 50px;">
            <table class="altrowstable" id="alternatecolor1" style="margin: 0 auto;width: 80%;">
                <%--表头--%>
                <tr style="font-weight: bolder;">
                    <th style="width: 10%;">
                        <input type="checkbox" id="selectAll1" onclick="checkAllForEnable(this.id,'alternatecolor1')"/>
                        <label for="selectAll1">
                            <span>全选</span>
                        </label>
                    </th>
                    <th style="width: 40%;">
                        <span>文件名</span>
                    </th>
                    <th style="width: 15%;">
                        <span>下载次数</span>
                    </th>
                    <th style="width: 35%;">
                        <span>操作</span>
                    </th>
                </tr>

                <%--数据--%>
                <c:forEach items="${resourceList}" var="resourceItem">
                    <tr>
                        <td style="width: 10%;">
                            <input type="checkbox" name="check_box1" id="resCheck${resourceItem.resourceId}" onclick="checkChanged(this.id)"/>
                        </td>
                        <td style="width: 40%;">
                            <span>${resourceItem.savePath}</span>
                        </td>
                        <td style="width: 15%;">
                            <span id="time${resourceItem.resourceId}">${resourceItem.downloadTimes}</span>
                        </td>
                        <td style="width: 35%;">
                            <button id="resDownload${resourceItem.resourceId}" onclick="download_resource(this.id)">下载</button>
                            <button id="resRemove${resourceItem.resourceId}" onclick="remove_resource(this.id)">移除</button>
                            <button id="resOnline${resourceItem.resourceId}" name="type${resourceItem.resourceType}" onclick="online_pre_read(this.name,this.id)">在线预览</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>
