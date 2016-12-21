function add_course() {
    var d = dialog({
        title: '新建课程',
        width: 450,
        zIndex:9999,
        content: '<p style="color: black;font-size: 18px;font-weight: bolder;">课程名称：<input type="text" id="course_name" style="width: 320px;height: 35px;"/></p>' +
                 '<p style="color: black;font-size: 18px;font-weight: bolder;">课程描述：<textarea style="width: 320px;height: 250px;columns: 30;" id="remark"/></p>',
        okValue: '确定',
        ok: function () {
            var course_name=$("#course_name").val();
            var remark=$("#remark").val();
            $.ajax({
                type:"post",
                url:"/ctrs/addCourse",
                data:{"course_name":course_name,"remark":remark},
                success: function (data) {
                    if(data!=null)
                    {
                        //var res=eval(data);
                        alert(data);
                    }
                    else
                    {
                        alert("添加课程失败");
                    }
                    window.location.href="/ctrs/studentManage";
                },

                error:function () {
                    alert("请求数据出错");
                }
            });
            $("#mask").remove();
        },
        cancelValue: '取消',
        cancel: function () {
            $("#mask").remove();
        }
    });
    d.show();
    mask();
}

$(document).ready(function () {
    var search_text=$("#search").val();
    $.ajax({
       type:"post",
       url:"/ctrs/getCourseList",
       data:{"action":"getAll","keyWord":search_text},
       success:function (data) {
           if(data!=null)
           {
               var res=eval(data);
               //alert(res.length);
               //alert(res[0]);
               for(var i=0;i<res.length;i++)
               {
                   var li="";
                   li+='<li class="course-item">';
                   li+='<div class="course-title">';
                   li+='<a href="/ctrs/courseStudent?courseId='+res[i]["courseId"]+'">';
                   li+=res[i]["courseName"];
                   li+='</a>';
                   li+='</div>';
                   li+='<div class="course-content">';
                   li+='<div class="course-teacher">';
                   li+='<a href="/ctrs/teacher" id="'+res[i]["userId"]+'">';
                   li+=res[i]["userName"];
                   li+='</a>';
                   li+='</div>';
                   li+='<div class="course-remark">';
                   li+='<p>';
                   li+=res[i]["remark"];
                   li+='</p>';
                   li+='</div>';
                   li+='</div>';
                   li+='</li>';
                   //alert(li);
                   $("#course-list").append(li);

               }
           }

       },
       error:function () {
           alert("请求数据出错");
       }
   }) ;
});

function brn_search() {
    var search_text=$("#search").val();
    $.ajax({
        type:"post",
        url:"/ctrs/getCourseList",
        data:{"action":"search","keyWord":search_text},
        success:function (data) {
            if(data!=null)
            {
                var res=eval(data);
                $('#course-list li').remove();
                for(var i=0;i<res.length;i++)
                {
                    var li="";
                    li+='<li class="course-item">';
                    li+='<div class="course-title">';
                    li+='<a href="/ctrs/courseStudent?courseId='+res[i]["courseId"]+'">';
                    li+=res[i]["courseName"];
                    li+='</a>';
                    li+='</div>';
                    li+='<div class="course-content">';
                    li+='<div class="course-teacher">';
                    li+='<a href="/ctrs/teacher" id="'+res[i]["userId"]+'">';
                    li+=res[i]["userName"];
                    li+='</a>';
                    li+='</div>';
                    li+='<div class="course-remark">';
                    li+='<p>';
                    li+=res[i]["remark"];
                    li+='</p>';
                    li+='</div>';
                    li+='</div>';
                    li+='</li>';
                    //alert(li);
                    $("#course-list").append(li);
                }
            }
        },
        error:function () {
            alert("请求数据出错");
        }
    }) ;
}

$(window).on("resize", function () {
    if ($("#mask").length) {
        $("#mask").css({
            width: $(window).width(),
            height: $(window).height(),
            opacity: 0
        });
    }
});

var mask = function () {
    $('<div id="mask" style="width: ' + $(window).width() + 'px; '
        + 'height: ' + $(window).height() + 'px;"></div>').appendTo("body");
};