var course_id;
$(document).ready(function () {
    course_id=GetQueryString("courseId");
});
//=====================================
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
//================================================

window.onload=function(){
    altRows('alternatecolor');
};
function altRows(id){
    if(document.getElementsByTagName){
        var table = document.getElementById(id);
        var rows = table.getElementsByTagName("tr");

        for(i = 0; i < rows.length; i++){
            if(i % 2 == 0){
                rows[i].className = "evenrowcolor";
            }else{
                rows[i].className = "oddrowcolor";
            }
        }
    }
};


//============================================
function checkAllForEnable(chkobj, nodeId)
{
    if ($(chkobj).attr("checked") == "checked") {
        $(nodeId + " input[type=checkbox][disabled!='disabled']").attr("checked", true);
    } else {
        $(nodeId + " input[type=checkbox][disabled!='disabled']").attr("checked", false);
    }
}
function checkChanged(obj) {

    if($("#"+obj).checked)
    {
        $("#"+obj).attr("checked", false);
    }
    else
    {
        $("#"+obj).attr("checked", true);
    }

    //能执行，但是会报attr不是function的错
    // if (obj.checked) {
    //     obj.attr("checked", false);
    // }
    // else {
    //     obj.attr("checked", true);
    // }
}

function remove_student(obj) {

    var id=obj.substr(3);
    //alert(id);
    $.ajax({
        type:"post",
        url:"/ctrs/removeStudent",
        data:{"action":"remove_student_single","user_id":id,"course_id":course_id},
        success:function (data) {
            if(data!=null)
            {
                var res=eval(data);
                alert(res["msg"]);
                window.location.reload();
            }
            else
            {
                alert("接收返回数据出错");
            }

        },
        error:function () {
            alert("请求数据出错");
        }
    });
}
function batch_remove_student() 
{
    var courseId=GetQueryString("courseId");
    $.ajax({
        type:"post",
        url:"/ctrs/removeStudent",
        data:{"action":"remove_student_batch","user_id":id,"courseId":courseId},
        success:function (data) {

        },
        error:function () {
            alert("请求数据出错");
        }
    });
}

//获取url参数
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return (r[2]); return null;
}

function add_student() {
    var d=dialog({
        title: '添加学生',
        width: 450,
        zIndex:9999,
        content: '<p>学生姓名：<input type="text" id="user_name" style="height: 40px;"/></p>',
        okValue: '确定',
        ok: function () {
            var name=$("#user_name").val();
            //alert(name);
            $.ajax({
               type:"post",
                url:"/ctrs/addStudent",
                data:{"username":name,"courseId":course_id},
                success:function (data) {
                    if(data!=null)
                    {
                        var res=eval(data);
                        alert(res["msg"]);
                        window.location.reload();
                    }
                    else
                    {
                        alert("返回数据出错");
                    }
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

function batch_add_student() {
    var d=dialog({
        title: '批量导入学生',
        width: 450,
        zIndex:9999,
        content: '<form id="upload" enctype="multipart/form-data"><p>从Excel导入：' +
        '<input type="file" name="upload_file" id="import_from_excel">' +
        '</p></form>',
        okValue: '确定',
        ok: function () {
            var formDate = new FormData($("#upload")[0]);
            $.ajax({
                type:"post",
                url:"/ctrs/batchAddStudent",
                data:formDate,
                contentType:false,//避免提交时被jQuery修改
                processData:false,
                success:function (data) {
                    var res=eval(data);
                    alert(res["msg"]);
                },
                error:function () {
                    alert("请求出错");
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

function delete_course() {
    var d=dialog({
        title: '操作确认',
        width: 450,
        zIndex:9999,
        content: '<p>确认删除该课程？</p>',
        okValue: '确定',
        ok: function () {
            $.ajax({
               type:"post",
                url:"/ctrs/deleteCourse",
                data:{"courseId":course_id},
                success:function (data) {
                    if(data!=null)
                    {
                        var res=eval(data);
                        alert(res["msg"]);
                        window.location.href="/ctrs/studentManage"
                    }
                    else
                    {
                        alert("删除出错");
                    }
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

function upload_resource() {
    var d=dialog({
        title: '上传课件',
        width: 450,
        zIndex:9999,
        content: '<form id="upload" enctype="multipart/form-data"><p>从Excel导入：' +
        '<input type="file" name="upload_resource" id="upload_resource">' +
        '</p></form>',
        okValue: '确定',
        ok: function () {
            var formDate = new FormData($("#upload")[0]);
            $.ajax({
                type:"post",
                url:"/ctrs/uploadResource",
                data:formDate,
                contentType:false,//避免提交时被jQuery修改
                processData:false,
                success:function (data) {
                    var res=eval(data);
                    alert(res["msg"]);
                },
                error:function () {
                    alert("请求出错");
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
