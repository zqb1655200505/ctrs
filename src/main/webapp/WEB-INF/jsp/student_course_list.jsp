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
                           <a style="color: white;">添加学生</a>
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
        <div class="body-content" id="stu_list">
            <table class="altrowstable" id="alternatecolor">
                <%--表头--%>
                <tr style="font-weight: bolder;">
                    <th>
                        <input type="checkbox" id="selectAll" onclick="checkAllForEnable(this.id,'alternatecolor')"/>
                        <label for="selectAll">
                            <span>全选</span>
                        </label>
                    </th>
                    <th>
                        <span>学生姓名</span>
                    </th>
                    <th>
                        <span>操作</span>
                    </th>
                </tr>

                <%--数据--%>
                <c:forEach items="${userList}" var="userItem">
                    <tr>
                        <td>
                            <input type="checkbox"  name="check_box"  id="userCheck${userItem.userId}" onclick="checkChanged(this.id)"/>
                        </td>
                        <td>
                            <span>${userItem.userName}</span>
                        </td>
                        <td>
                            <button id="stu${userItem.userId}" onclick="remove_student(this.id)">移除</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="body-content" id="resource_list" style="display: none; margin-left: 20%;">
            <table class="altrowstable" id="alternatecolor1">
                <%--表头--%>
                <tr style="font-weight: bolder;">
                    <th>
                        <input type="checkbox" id="selectAll1" onclick="checkAllForEnable(this.id,'alternatecolor1')"/>
                        <label for="selectAll1">
                            <span>全选</span>
                        </label>
                    </th>
                    <th>
                        <span>文件名</span>
                    </th>
                    <th>
                        <span>操作</span>
                    </th>
                </tr>

                <%--数据--%>
                <c:forEach items="${userList}" var="userItem">
                    <tr>
                        <td>
                            <input type="checkbox" name="check_box1" id="resCheck${userItem.userId}" onclick="checkChanged(this.id)"/>
                        </td>
                        <td>
                            <span>${userItem.userName}</span>
                        </td>
                        <td>
                            <button id="res${userItem.userId}" onclick="download_resource(this.id)">下载</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>
