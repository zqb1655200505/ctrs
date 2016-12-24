$(document).ready(function () {
    $.ajax({
       type:"post",
        url:"/ctrs/getNotice",
        success:function (data) {
            //alert(data);
            if(data!=null)
            {
                var res=eval(data);
                $("#notice_list li").remove();
                for(var i=0;i<res.length;i++)
                {
                    var noticeId=res[i]["noticeId"];
                    var title=res[i]["title"];
                    var publishTime=res[i]["publishTime"];
                    $.ajax({
                        type:"post",
                        url:"/ctrs/getUserName",
                        data:{"userId":res[i]["userId"]},
                        success:function (data1) {
                            if(data1!=null)
                            {
                                var res1=eval(data1);
                                if(res1["code"]==200)
                                {
                                    var li="";
                                    li+='<li class="notice-item">';
                                    li+='<div class="notice-logo">';
                                    li+='<img src="/image/news_icon.png">';
                                    li+='</div>';
                                    li+='<div class="notice-item-content">';
                                    li+='<div class="notice-title">';
                                    li+='<a target="_blank" href="/notice.jsp?noticeId='+noticeId+'">';
                                    li+=title;
                                    li+='</a>';
                                    li+='</div>';
                                    li+='<div class="notice-date" style="color: black;">';
                                    li+='<span style="margin-right: 30px;">';
                                    li+=res1["msg"];
                                    li+='</span>';
                                    li+='<span>';
                                    li+=publishTime;
                                    li+='</span>';
                                    li+='</div>';
                                    li+='</div>';
                                    li+='</li>';
                                    //alert(li);
                                    $("#notice_list").append(li);
                                }
                                else
                                {
                                    alert(res1["msg"]);
                                }
                            }

                        },
                        error:function () {
                            alert("请求数据出错");
                        }
                    });


                }
            }
        },
        error:function () {
            alert("请求数据出错");
        }
    });
});