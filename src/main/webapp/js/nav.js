$(document).ready(function () {
    $.ajax({
        type:"post",
        url:"/ctrs/checkUserType",
        success: function (data) {
            var res=eval(data);
            if(res["code"]==500)
            {
                window.location.href="/ctrs/login";
            }
            else
            {
                if(res["msg"]=="true")
                {
                    document.getElementById('publishNotice').style.display = "";
                    document.getElementById('studentManage').style.display = "";
                    document.getElementById('learning').style.display = "none";
                    document.getElementById("username").innerHTML=res["username"];
                }
                else if(res["msg"]=="false")
                {
                    document.getElementById('publishNotice').style.display = "none";
                    document.getElementById('studentManage').style.display = "none";
                    document.getElementById('learning').style.display = "";
                    document.getElementById("username").innerHTML=res["username"];
                }
            }
        },
        error: function () {
            alert("请求数据出错");
        }
    });
});


function sign_out() {
    $.ajax({
        type:"post",
        url:"/ctrs/signOut",
        success: function (data) {
            var res=eval(data);
            if(res["code"]==200)
            {
                window.location.href="/ctrs/login";
            }
        },
        error:function () {
            alert("请求数据出错");
        }
    });
}

//=======================================弹框背景黑色半透明


