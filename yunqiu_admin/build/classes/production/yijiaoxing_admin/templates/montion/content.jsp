<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="renderer" content="webkit"/>
    <meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
    <title>云球 -队服颜色</title>

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <link rel="shortcut icon" href="favicon.ico"/>
    <link href="/css/bootstrap.min.css?v=3.3.6" rel="stylesheet"/>
    <link href="/css/font-awesome.min.css?v=4.4.0" rel="stylesheet"/>
    <link href="/css/animate.css" rel="stylesheet"/>
    <link href="/css/style.css?v=4.1.0" rel="stylesheet"/>
    <!-- jqgrid-->
    <link href="/hplus/css/plugins/jqgrid/ui.jqgridffe4.css?0820" rel="stylesheet"/>

    <!-- 全局js -->
    <script src="/js/jquery.min.js?v=2.1.4"></script>
    <script src="/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="/js/plugins/layer/layer.min.js"></script>
    <script src="/js/jquery-form.js"></script>
    <!-- 自定义js -->
    <script src="/js/hplus.js?v=4.1.0"></script>
    <script type="text/javascript" src="/js/contabs.js"></script>
    <!-- 第三方插件 -->
    <script src="/js/plugins/pace/pace.min.js"></script>
    <!-- jqgrid-->
    <script src="/hplus/js/plugins/jqgrid/i18n/grid.locale-cnffe4.js?0820"></script>
    <script src="/hplus/js/plugins/jqgrid/jquery.jqGrid.minffe4.js?0820"></script>
    <!-- sweetalert-->
    <script src="/hplus/js/plugins/sweetalert/sweetalert.min.js"></script>
    <link href="/hplus/css/plugins/sweetalert/sweetalert.css" rel="stylesheet"/>

    <!-- 编辑器-->
    <link rel="stylesheet" type="text/css" href="/templates/ueditor/themes/default/css/ueditor.css"/>
    <script type="text/javascript" src="/templates/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="/templates/ueditor/ueditor.all.js"></script>

</head>

<body>
<script>
    var ue = UE.getEditor('editor',{
        toolbars: [
            [
                'undo', //撤销
                'bold', //加粗
                'italic', //斜体
                'underline', //下划线
                'strikethrough', //删除线
                'formatmatch', //格式刷
                'selectall', //全选
                'print', //打印
                'link', //超链接
                'unlink', //取消链接
                'fontfamily', //字体
                'fontsize', //字号
                'simpleupload', //单图上传
                'insertimage', //多图上传
                'help', //帮助
                'justifyleft', //居左对齐
                'justifyright', //居右对齐
                'justifycenter', //居中对齐
                'justifyjustify', //两端对齐
                'forecolor', //字体颜色
                'backcolor', //背景色
                'touppercase', //字母大写
                'tolowercase', //字母小写
            ]
        ],
        initialFrameWidth:700,//宽度
        initialFrameHeight :320,//高度

    });
    $(document).ready(function(){
        $.jgrid.defaults.styleUI="Bootstrap";
        $("#list4").jqGrid({
            url:'/content/select',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            height:$(window).height()-239,
            autowidth:true,//自动宽
            shrinkToFit:true,
            colModel:[
                {label:"Id",name:'content_id',index:'content_id', width:'15%',align:'center',hidden: true},
                {label:"图片",name:'img_url',index:'img_url', width:'15%',align:'center',hidden: true},
                {label:"文章标题",name:'content_title',index:'content_title', width:'35%',align:'center'},
                {label:"图片",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(rowObject.img_url==undefined){
                            return "";
                        }else{
                            return '<img  style="width: 88px;height: 81px;" src="http://picture-services.b0.upaiyun.com/'+rowObject.img_url+'"/>';
                        }
                    }
                },
                {label:"创建时间",name:'create_time',index:'create_time', width:'35%',align:'center',formatter:"date",
                    formatter:function(cellvalue, options, rowObject){
                        if (typeof(cellvalue)=="undefined" || typeof(cellvalue)=="" || cellvalue==0){
                            return "";
                        }
                        var date = new Date(cellvalue);

                        return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
                    }
                },
                {label:"创建人",name:'userName',index:'userName', width:'35%',align:'center'},
                {label:"顺序",name:'sort',index:'sort', width:'35%',align:'center'},
                {label:"是否显示",name:'is_show',index:'is_show', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){
                            return "是";
                        }else if(cellvalue==2){
                            return "否";
                        }else{
                            return "";
                        }
                    }
                }
            ],
            pager:'#gridPager',
            viewrecords: true,//是否在浏览导航栏显示记录总数
            rowNum:10, //每页显示记录数
            rownumbers: true,
            sortname: "sort",
            sortorder: "asc",
            rowList:[10,30,50],//用于改变显示行数的下拉列表框的元素数组。
            jsonReader:{
                id: "content_id",//设置返回参数中，表格ID的名字为blackId
                root: "list", // json中代表实际模型数据的入口
                page: "page", // json中代表当前页码的数据
                total: "total", // json中代表页码总数的数据
                records: "records", // json中代表数据行总数的数据
                repeatitems : false
            },
            add:false,
            edit:false,
        });
        $("#list4").jqGrid("navGrid","#gridPager",{edit:false,add:false,del:false,search:false},{reloadAfterSubmit:true,});

        $(window).bind("resize",function(){
            var width=$("#divWidth").width();
            $("#list4").setGridWidth(width);
        });

        //按钮权限
        $.ajax({
            url: "/perm/power",
            async : false,
            datatype:"json",
            type:"get",
            success: function (data) {
                $("#user_button").empty();
                var powerList = "";
                if (data.length!=0) {
                    powerList += '<div class="ibox-title">';

                    $.each(eval(data), function (idx, obj) {
                        powerList += '<a data-toggle="modal" style="margin-right: 10px;" class="btn btn-primary" id="'+obj.url+'">'+obj.name+'</a>';
                    });
                    powerList += '</div>';
                    $("#user_button").append(powerList);
                }
            }
        });

        //添加窗口
        $("#btn_add").bind("click",function(){
            $("#add_winfrom").modal('show');
            $("#add").unbind("click");

            $("#add").bind("click",function(){
                //开始ajax操作
                content_title = $("#content_title").val();
                file = $("#file").val();
                sort = $("#sort").val();
                content = UE.getEditor('editor').getContent();

                if(!content_title.length>0){
                    $("#va_content_title").html("请输入文章标题");
                    return;
                }

                if(!file.length>0){
                    $("#va_file").html("请选择图片");
                    return;
                }

                if(!sort.length>0){
                    $("#va_sort").html("请输入排序");
                    return;
                }

                $("#content").val(content);

                if(file.length>0) {
                    var options = {
                        url: "/upload/upload",
                        type: "post",
                        async:false,
                        success: function (data) {
                            $("#img_url").val(data);
                        },
                        error: function (data) { swal("添加失败","网络异常！","error"); return;},//请求失败的函数
                    }
                    $("#addForm").ajaxSubmit(options); //xx 你form表单的id,或者直接$("form").ajaxSubmit(options);
                }


                $("#addForm").ajaxSubmit({
                    type: "POST",//提交类型
                    dataType: "json",//返回结果格式
                    url: "/content/addContent",//请求地址
                    data:$("#addForm").serialize(),
                    success: function (data) {//请求成功后的函数
                        if (data["error_code"] == 1500){
                            swal("系统错误!", data["msg"], "error");
                        }else if (data["error_code"] != 1200){
                            swal("操作失败!",data["msg"],"warning");
                        }else{
                            $("#add_winfrom").modal('hide');
                            swal("添加","添加成功！");
                            $("#list4").trigger("reloadGrid");
                        }
                    },
                    error: function (data) { swal("添加失败","网络异常！","error"); },//请求失败的函数
                    async: false
                });
            });
        });

        //修改窗口
        $("#btn_edit").bind("click",function(){

            $("#update").unbind("click");

            var selectedId = $("#list4").jqGrid("getGridParam", "selrow");
            var select= jQuery("#list4").jqGrid('getRowData', selectedId);
            if (selectedId == null){
                swal("修改","请选择需要编辑的信息！","info");
            }else{
                $("#update_winfrom").modal('show');

                $("#up_content_title").val(select.content_title);
                $("#up_content_id").val(select.content_id);
                $("#up_img_url").val(select.img_url);
                $("#up_sort").val(select.sort);
                $("#up_is_show option:contains('"+select.is_show+"')").prop("selected", true);

                $("#update").bind("click",function(){

                    content_title = $("#up_content_title").val();
                    file = $("#up_file").val();
                    sort = $("#up_sort").val();
                    //content = UE.getEditor('editor').getContent();

                    if(!content_title.length>0){
                        $("#va_content_title").html("请输入文章标题");
                        return;
                    }

                    if(!sort.length>0){
                        $("#va_sort").html("请输入排序");
                        return;
                    }

                    if(file.length>0) {
                        var options = {
                            url: "/upload/upload",
                            type: "post",
                            async:false,
                            success: function (data) {
                                $("#up_img_url").val(data);
                            },
                            error: function (data) { swal("添加失败","网络异常！","error"); return;},//请求失败的函数
                        }
                        $("#updateForm").ajaxSubmit(options); //xx 你form表单的id,或者直接$("form").ajaxSubmit(options);
                    }

                    //开始ajax操作
                    $("#updateForm").ajaxSubmit({
                        type: "POST",//提交类型
                        dataType: "json",//返回结果格式
                        url: "/content/upContent",//请求地址
                        data:$("#updateForm").serialize(),
                        success: function (data) {//请求成功后的函数
                            if (data["error_code"] == 1500){
                                swal("系统错误!", data["msg"], "error");
                            }else if (data["error_code"] != 1200){
                                swal("操作失败!",data["msg"],"warning");
                            }else{
                                $("#update_winfrom").modal('hide');
                                swal("修改","修改成功！");
                                $("#list4").trigger("reloadGrid");
                            }
                        },
                        error: function (data) { swal("添加失败","网络异常！","error"); },//请求失败的函数
                        async: false
                    });
                });
            }
        });

        //删除窗口
        $("#btn_delete").bind("click",function(){
            var selectedId = $("#list4").jqGrid("getGridParam", "selrow");
            var select= jQuery("#list4").jqGrid('getRowData', selectedId);
            if (selectedId == null){
                swal("球队标签","请选择需要删除的信息！","info");
            }else{
                $("#delete_winfrom").modal('show');
                $("#delete").bind("click",function(){
                    $.ajax({
                        url:"/teamcolour/delTeamColour",
                        data:{
                            teamcolorId:select.teamcolorId
                        },
                        success:function(data){
                            if (data["error_code"] == 1500){
                                swal("系统错误!", data["msg"], "error");
                            }else if (data["error_code"] != 1200){
                                swal("操作失败!",data["msg"],"warning");
                            }else{
                                $("#delete_winfrom").modal('hide');
                                /*swal("删除","删除成功！");*/
                                $("#list4").trigger("reloadGrid");
                            }
                        },error:function(){
                            swal("删除失败","网络异常！");
                        }
                    });
                });
            }
        });

    });


</script>

<div  id="user_button">

</div>




<div class="wrapper wrapper-content animated">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox ">

                <div class="wrapper wrapper-content animated">
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="ibox ">
                                <div class="ibox-content">
                                    <div class="jqGrid_wrapper" id="divWidth">
                                        <table id="list4"></table>
                                        <div id="gridPager"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
        </div>
    </div>
</div>



<form id="addForm"  name="uform" enctype="multipart/form-data" type="post">
    <div id="add_winfrom" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width: 1000px;">
            <div class="modal-content">
                <div class="modal-header">
                    <h2 class="modal-title" id="myModalLabel">文章添加</h2>
                    <input type="hidden" id="img_url" name="img_url" class="form-control"/>
                    <input type="hidden" id="content" name="content" class="form-control"/>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-5 control-label">文章标题<span class="validt" id="va_content_title"></span></label>
                        <input type="text" id="content_title" name="content_title" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">文章图片<span class="validt" id="va_img_url"></span></label>
                        <input type="file" id="file" name="file" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">排序<span class="validt" id="va_sort"></span></label>
                        <input type="text" id="sort" name="sort" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">是否显示<span class="validt" id="va_is_show"></span></label>
                        <select id="is_show" name="is_show" class="form-control">
                            <option value="1">是</option>
                            <option value="2">否</option>
                        </select>
                    </div>

                    <script type="text/plain" id="editor" name="content" style="width:900px;height:500px"></script>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary" id="add">添加</button>
                </div>
            </div>
        </div>
    </div>
</form>

<form id="updateForm">
    <div id="update_winfrom" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h2 class="modal-title">文章修改</h2>
                    <input type="hidden" id="up_content_id" name="content_id"/>
                    <input type="hidden" id="up_img_url" name="img_url"/>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-5 control-label">文章标题<span class="validt" id="vu_content_title"></span></label>
                        <input type="text" id="up_content_title" name="content_title" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">文章图片<span class="validt" id="vu_img_url"></span></label>
                        <input type="file" id="up_file" name="file" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">排序<span class="validt" id="vu_sort"></span></label>
                        <input type="text" id="up_sort" name="sort" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">是否显示<span class="validt" id="vu_is_show"></span></label>
                        <select id="up_is_show" name="is_show" class="form-control">
                            <option value="1">是</option>
                            <option value="2">否</option>
                        </select>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="update">修改</button>
                </div>
            </div>
        </div>
    </div>
</form>

<div id="delete_winfrom" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title">确定要删除吗？此操作不可被撤销</h2>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="delete">确定</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>