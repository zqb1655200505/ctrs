function register()
{
    var username=$("#user_register").val();
    var password=$("#pass_register").val();
    var pass_confer=$("#pass_confer").val();
    if(username==null||username=="")
    {
        alert("用户名不能为空！");
        return;
    }
    if(password==null||password=="")
    {
        alert("密码不能为空！");
        return;
    }
    if(pass_confer==null||pass_confer=="")
    {
        alert("确认密码不能为空！");
        return;
    }
    if(pass_confer!=password)
    {
        alert("密码前后不一致！");
        return;
    }
    //alert(username);
    $.ajax({
        type: "post",
        url: "/ctrs/register_check",
        data: {"user_register":username,"pass_register":password,"pass_confer":pass_confer},
        success: function (data) {
            var res=eval(data);
            alert(res["result"]);
            window.location.href="/ctrs/login";
        },
        error:function () {
            alert("请求数据出错");
        }
    });
}

function login()
{
    var username=$("#user_login").val();
    var password=$("#pass_login").val();
    if(username==null||username=="")
    {
        alert("用户名不能为空！");
        return;
    }
    if(password==null||password=="")
    {
        alert("密码不能为空！");
        return;
    }
    $.ajax({
        type: "post",
        url: "/ctrs/login_check",
        data: {"user_login":username,"pass_login":password},
        success: function (data) {
            var res=eval(data);
            if(res["code"]=="error")
            {
                alert(res["msg"]);

                window.location.href="/ctrs/login";
            }
            else
            {
                var checkbox = document.getElementById('check');
                if(checkbox.checked)
                {
                    setcookie("username",$("#user_login").val(),"/");
                    setcookie("password",$("#pass_login").val(),"/");
                }
                else
                {
                    setcookie("username","","/");
                    setcookie("password","","/");
                }
                window.location.href="/ctrs/index";
            }
        },
        error:function () {
            alert("请求数据出错");
        }
    });
}

function keyLogin()
{
    if (event.keyCode==13)  //回车键的键值为13
    {
        login();
    }
}

function setcookie(name,value,path)
{
    var expires = new Date();
    expires.setTime(expires.getTime() + 1000*60*60*24*7);
    path = path == "" ? "" : ";path=" + path;
    expires =  ";expires=" + expires.toUTCString();
    document.cookie = name + "=" + value + expires + path;

}
function getCookie(name){
    var name = escape(name);
    //读cookie属性，这将返回文档的所有cookie
    var allcookies = document.cookie;
    //查找名为name的cookie的开始位置
    name += "=";
    var pos = allcookies.indexOf(name);
    //如果找到了具有该名字的cookie，那么提取并使用它的值
    if (pos != -1)
    {                       //如果pos值为-1则说明搜索"version="失败
        var start = pos + name.length;         //cookie值开始的位置
        var end = allcookies.indexOf(";",start);    //从cookie值开始的位置起搜索第一个";"的位置,即cookie值结尾的位置
        if (end == -1) //如果end值为-1说明cookie列表里只有一个cookie
        {
            end = allcookies.length;
        }
        var value = allcookies.substring(start,end); //提取cookie的值
        return (value);              //对它解码
    }
    else //搜索失败，返回空字符串
    {
        return "";
    }
}

$(document).ready(function () {
    //alert(getCookie("username"));
    var username=getCookie("username");
    var password=getCookie("password");
    $("#user_login").val(username);
    $("#pass_login").val(password);
})