<%--
  Created by IntelliJ IDEA.
  User: zqb
  Date: 2016/12/22
  Time: 4:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>公告</title>
</head>
<script type="text/javascript" src="/js/jquery-2.0.0.min.js"></script>
<script>
    //获取url参数
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return (r[2]); return null;
    }
    $(document).ready(function () {
        var noticeId=GetQueryString("noticeId");
        $.ajax({
           type:"post",
            url:"/ctrs/getNoticeInfo",
            data:{"noticeId":noticeId},
            success: function (data) {
                if(data!=null)
                {

                    var res=eval(data);
                    $("#title").append("<h1>标题："+res["title"]+"</h1>");
                    $("#content").append(res["content"]);

                }

            },
            error:function () {
                alert("请求数据出错");
            }
        });
    });
</script>
<body>
    <div style="width: 1000px;margin: 0 auto;" id="container">
        <div style="width: 60%;text-align: center;margin: 0 auto;margin-top: 30px;" id="title">

        </div>
        <div style="margin-top: 25px;margin: 0 auto;padding:20px;font-size: 20px;" id="content">

        </div>
    </div>
</body>
</html>
