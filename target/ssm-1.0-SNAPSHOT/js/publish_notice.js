function confer_publish() {
    var content=UE.getEditor('editor').getContent();
    var title=$("#title").val();
    if(title=="")
    {
        alert("标题不能为空");
        return;
    }
    if(title.length>100)
    {
        alert("标题最多只能有100个字符");
        return;
    }
    if(content=="")
    {
        alert("公告内容不能为空");
        return;
    }
    //alert(title);
    //alert(content);
    $.ajax({
        type:"post",
        url:"/ctrs/publish",
        data:{"title":title,"content":content},
        success: function (data) {
            var res=eval(data);
            if(res["code"]==200)
            {
                alert(res["msg"]);
                window.location.href="/ctrs/index";
            }
            else
            {
                alert(res["msg"]);
                window.location.reload();
            }
        },
        error: function () {
            alert("请求数据出错");
        }
    });
}