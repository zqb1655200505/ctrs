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
    altRows('alternatecolor1');
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
    if (document.getElementById(chkobj).checked) {
        //attr属性由于jquery版本问题，第二次勾选时会出错
        //对于HTML元素本身就带有的固有属性，在处理时，使用prop方法。
        //对于HTML元素我们自己自定义的DOM属性，在处理时，使用attr方法。
        //$("#"+nodeId + " input[type=checkbox][disabled!='disabled']").attr("checked", true);
        //$("[name = check_box]:checkbox").attr("checked", true);
        $("#"+nodeId + " input[type=checkbox][disabled!='disabled']").prop("checked","checked")
    }
    else
    {
        //$("#"+nodeId + " input[type=checkbox][disabled!='disabled']").attr("checked", false);
        //$("[name = check_box]:checkbox").attr("checked", false);
        $("#"+nodeId+ " input[type=checkbox][disabled!='disabled']").removeAttr("checked");
    }
}
function checkChanged(obj) {
    //alert(document.getElementById(obj).checked);
    if(document.getElementById(obj).checked)
    {
        $("#"+obj).prop("checked", true);
    }
    else
    {
        $("#"+obj).prop("checked", false);
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
    if(confirm("确定要移除该学生？"))
    {
        var id=obj.substr(3);
        $.ajax({
            type:"post",
            url:"/ctrs/removeStudent",
            data:{"action":"remove_student_single","stu_ids":id,"course_id":course_id},
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
}
function batch_remove_student() 
{
    if(confirm("移除所有选中学生？"))
    {
        var ids=getCheckd("check_box");
        var courseId=GetQueryString("courseId");
        for(var i=0;i<ids.length;i++)
        {
            ids[i]=ids[i].substr(9);
        }
        //alert(ids);
        ids=ids.toString();
        $.ajax({
            type:"post",
            url:"/ctrs/removeStudent",
            data:{"action":"remove_student_batch","stu_ids":ids,"course_id":course_id},
            success:function (data) {

                var res=eval(data);
                alert(res["msg"]);
                window.location.reload();
            },
            error:function () {
                alert("请求数据出错");
            }
        });
    }

}

//封装判断数组是否包含指定元素
Array.prototype.contains = function ( needle ) {
    for (i in this) {
        if (this[i] == needle) return true;
    }
    return false;
};
//从页面中获取当前课程所有已有学生
function getCurrentStudentList(objName){
    var obj = document.getElementsByName(objName);
    var check_val = new Array(obj.length);
    for(var i=0;i<obj.length;i++)
    {
        check_val[i]=obj[i].id;
    }
    return check_val;
}
//从已有学生列表导入
function import_student() {
    var list="";
    var cur_list=getCurrentStudentList("check_box");

    for(var i=0;i<cur_list.length;i++)
    {
        cur_list[i]=cur_list[i].substr(9);
    }
    $.ajax({
        type:"post",
        url:"/ctrs/getAllStudent",
        success:function (data) {
            var res=eval(data);
            var stu_list=res["msg"];
            for(var i=0;i<stu_list.length;i++)
            {
                var item=eval(stu_list[i]);
                if(cur_list.contains(item["userId"]))
                {
                    continue;
                }
                list+='<li>';
                list+='<input name="stu_list_check" style="list-style-type: none;" type="checkbox" id="student'+item["userId"]+'">';
                list+='<label for="student'+item["userId"]+'">'+item["userName"]+'</label>';
                list+='</li>';
            }
            //alert(list);
            var d=dialog({
                title: '添加学生',
                width: 450,
                zIndex:9999,
                content: '<div><ul style="list-style: none;font-size: 18px;" id="student_list">'+list+'</ul></div>',
                okValue: '确定',
                ok: function () {
                    var ids=getCheckd("stu_list_check");
                    for(var i=0;i<ids.length;i++)
                    {
                        ids[i]=ids[i].substr(7);
                    }
                    var stu_ids=ids.toString();
                    if(stu_ids.length>0)
                    {
                        $.ajax({
                            type:"post" ,
                            url:"/ctrs/addStudentFromExist",
                            data:{"stu_ids":stu_ids,"course_id":course_id},
                            success:function (data) {
                                var res=eval(data);
                                alert(res["msg"]);
                                window.location.reload();
                            },
                            error:function () {
                                alert("请求数据出错");
                            }
                        });
                    }
                    else
                    {
                        alert("请先选择学生");
                    }
                    $("#mask").remove();
                },
                cancelValue: '取消',
                cancel: function () {
                    $("#mask").remove();
                }
            });
            d.show();
            mask();
        },
        error:function () {
            alert("请求数据出错");
        }
    });

}
function batch_download_resource() {
    if(confirm("确定下载选中资源？"))
    {
        var ids=getCheckd("check_box1");
        for(var i=0;i<ids.length;i++)
        {
            ids[i]=ids[i].substr(8);
        }
    }
}


//获取选中的checkbox
function getCheckd(objName){
    var obj = document.getElementsByName(objName);
    var check_val = [];
    for(var k in obj){
        if(obj[k].checked)
            check_val.push(obj[k].id);
    }
    return check_val;
}


//获取url参数
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return (r[2]); return null;
}
var flag=0;
function showContent1()
{
    flag=0;
    document.getElementById("stu_list").style.display="none";
    document.getElementById("resource_list").style.display="";
    document.getElementById("li-stu-title").style.backgroundColor="#94fff8";
    document.getElementById("li-res-title").style.backgroundColor="#8ee3ff";

}
function showContent()
{
    flag=1;
    document.getElementById("resource_list").style.display="none";
    document.getElementById("stu_list").style.display="";
    document.getElementById("li-stu-title").style.backgroundColor="#8ee3ff";
    document.getElementById("li-res-title").style.backgroundColor="#94fff8";
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
    var d_stu=dialog({
        title: '批量导入学生',
        width: 450,
        zIndex:9999,
        content: '<p>暂无模板? <button class="button" onclick="downloadSample()">下载</button></p><br/>'+
        '<form id="upload" enctype="multipart/form-data"><p>从Excel导入：' +
        '<input type="file" name="upload_file" id="import_from_excel">' +
        '<input type="hidden" name="courseId" value="'+course_id+'">' +
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
                    window.location.reload();
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
    d_stu.show();
    mask();
}

//法一：
    //ajax实现下载：
    //JQuery的ajax函数的返回类型只有xml、text、json、html等类型，没有“流”类型，所以我们要实现ajax下载，
    // 不能够使用相应的ajax函数进行文件下载。但可以用js生成一个form，用这个form提交参数，并返回“流”类型的数据。
    // 在实现过程中，页面也没有进行刷新。
//法二
    //通过a标签
    // <a href="/ctrs/downloadResource">下载</a>
function downloadSample() {
    var form=$("<form>");//定义一个form表单
    form.attr("style","display:none");
    form.attr("target","");
    form.attr("method","post");
    form.attr("action","/ctrs/downloadResource");
    //通过input标签的name属性和value属性来传递参数
    var input1=newInput("path","WEB-INF/res/excel/StudentSample.xls");
    var input2=newInput("type","sample");
    $("body").append(form);//将表单放置在web中
    form.append(input1);
    form.append(input2);
    form.submit();//表单提交
    $(".ui-dialog-close").click();
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
    //文件上传的表单提交方式必须是POST方式，
    //编码类型：enctype="multipart/form-data"，默认是 application/x-www-form-urlencoded
    //比如：<form action="FileUpLoad" enctype="multipart/form-data" method="post">
    var d=dialog({
        title: '上传课件',
        width: 450,
        zIndex:9999,
        content: '<form id="upload" enctype="multipart/form-data">' +
        '<p><input type="file" name="upload_resource"></p>' +
        '<p><input type="hidden" name="courseId" value="'+course_id+'"><p>' +
        '</form>'+
        '<div class="add_btn" onclick="add_file()"><img src="../image/add.png"></div>',
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
                    window.location.reload();
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
function add_file() {
    $("#upload").append('<p><input type="file" name="upload_resource"></p>');
}

function download_resource(res_id)
{
    res_id=res_id.substr(11);
    var form=$("<form>");//定义一个form表单
    form.attr("style","display:none");
    form.attr("target","");
    form.attr("method","post");
    form.attr("action","/ctrs/downloadResource");
    //通过input标签的name属性和value属性来传递参数
    var input1=newInput("resId",res_id);
    var input2=newInput("type","resource");
    $("body").append(form);//将表单放置在web中
    form.append(input1);
    form.append(input2);
    form.submit();//表单提交
    //window.location.reload();
    $("#time"+res_id).text(Number($("#time"+res_id).text())+1);
}

function remove_resource(obj)
{
    obj=obj.substr(9);
    var d=dialog({
        title: '操作确认',
        width: 450,
        zIndex:9999,
        content: '<p>确认移除该资源文件？</p>',
        okValue: '确定',
        ok: function () {
            $.ajax({
                type:"post",
                url:"/ctrs/removeResource",
                data:{"resId":obj},
                success:function (data) {
                    if(data!=null)
                    {
                        var res=eval(data);
                        alert(res["msg"]);
                        window.location.reload();
                        $("#li-res-title").click();
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

function online_pre_read(objName,objId) {
    objName=objName.substr(4);
    if(objName==3)//不支持zip文件预览
    {
        alert("暂不支持改格式文件预览");
        return;
    }
    objId=objId.substr(9);
    var form=$("<form>");//定义一个form表单
    form.attr("style","display:none");
    form.attr("target","_blank");
    form.attr("method","post");
    form.attr("action","/ctrs/onlineRead");
    //通过input标签的name属性和value属性来传递参数
    var input1=newInput("resId",objId);
    $("body").append(form);//将表单放置在web中
    form.append(input1);
    form.submit();//表单提交

}

function newInput(name,value) {
    var input=$("<input>");
    input.attr("type","hidden");
    input.attr("name",name);
    input.attr("value",value);
    return input;
}