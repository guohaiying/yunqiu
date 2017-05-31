<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="renderer" content="webkit"/>
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
    <!-- validation-->
    <script src="/hplus/js/plugins/validate/jquery.validate.min.js"/>
    <script src="/hplus/js/plugins/validate/messages_zh.min.js"></script>
</head>

<body>
<script>
    $(document).ready(function(){
        $.jgrid.defaults.styleUI="Bootstrap";
        $("#list4").jqGrid({
            url:'/banner/select',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            height:$(window).height()-239,
            autowidth:true,//自动宽
            shrinkToFit:true,
            colModel:[
                {label:"Id",name:'banner_id',index:'banner_id', width:'15%',align:'center',hidden: true},
                {label:"图片",name:'img_url',index:'img_url', width:'15%',align:'center',hidden: true},
                {label:"排序",name:'sort',index:'sort', width:'35%',align:'center'},
                {label:"图片",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(rowObject.img_url==undefined){
                            return "";
                        }else{
                            return '<img  style="width: 88px;height: 81px;" src="http://picture-services.b0.upaiyun.com/'+rowObject.img_url+'"/>';
                        }
                    }
                },
                {label:"文章链接",name:'article_url',index:'article_url', width:'35%',align:'center'},
                {label:"文章内容",name:'article_content',index:'article_content	', width:'35%',align:'center'},
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
                id: "banner_id",//设置返回参数中，表格ID的名字为blackId
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
                file = $("#file").val();
                article_url = $("#article_url").val();
                sort = $("#sort").val();

                $("#va_img_url").html("");
                $("#va_article_url").html("");
                $("#va_sort").html("");

                if(!file.length>0){
                    $("#va_img_url").html("请选择banner图片");
                    return;
                }

                if(!article_url.length>0){
                    $("#va_article_url").html("请输入图片超链接");
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
                            $("#img_url").val(data);
                        },
                        error: function (data) { swal("添加失败","网络异常！","error"); return;},//请求失败的函数
                    }
                    $("#addForm").ajaxSubmit(options); //xx 你form表单的id,或者直接$("form").ajaxSubmit(options);
                }

                $("#addForm").ajaxSubmit({
                    type: "POST",//提交类型
                    dataType: "json",//返回结果格式
                    url: "/banner/addBanner",//请求地址
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

                $("#up_img_url").val(select.img_url);
                $("#up_article_url").val(select.article_url);
                $("#up_article_content").val(select.article_content);
                $("#up_sort").val(select.sort);
                $("#up_banner_id").val(select.banner_id);
                $("#up_is_show option:contains('"+select.is_show+"')").prop("selected", true);

                $("#update").bind("click",function(){
                    file = $("#up_file").val();
                    article_url = $("#up_article_url").val();
                    sort = $("#up_sort").val();

                    $("#vu_img_url").html("");
                    $("#vu_article_url").html("");
                    $("#vu_sort").html("");

                    if(!article_url.length>0){
                        $("#vu_article_url").html("请输入图片超链接");
                        return;
                    }

                    if(!sort.length>0){
                        $("#vu_sort").html("请输入排序");
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
                        url: "/banner/upBanner",//请求地址
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
                        url:"/banner/delBanner",
                        data:{
                            banner_id:select.banner_id
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



<form id="addForm">
    <div id="add_winfrom" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h2 class="modal-title" id="myModalLabel">banner添加</h2>
                    <input type="hidden" id="img_url" name="img_url" class="form-control"/>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-5 control-label">banner图片<span class="validt" id="va_img_url"></span></label>
                        <input type="file" id="file" name="file" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">图片超链接<span class="validt" id="va_article_url"></span></label>
                        <input type="text" id="article_url" name="article_url" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">文章内容<span class="validt" id="va_article_content"></span></label>
                        <textarea class="form-control" id="article_content" name="article_content" ></textarea>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">排序<span class="validt" id="va_sort"></span></label>
                        <input type="text" id="sort" name="sort" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">是否显示<span class="validt" id="va_is_show"></span></label>
                        <select id="is_show" name="is_show" class="form-control">
                            <option value="1">显示</option>
                            <option value="2">不显示</option>
                        </select>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="add">添加</button>
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
                    <h2 class="modal-title">banner修改</h2>
                    <input type="hidden" id="up_banner_id" name="banner_id"/>
                    <input type="hidden" id="up_img_url" name="img_url" class="form-control"/>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-5 control-label">banner图片<span class="validt" id="vu_img_url"></span></label>
                        <input type="file" id="up_file" name="file" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">图片超链接<span class="validt" id="vu_article_url"></span></label>
                        <input type="text" id="up_article_url" name="article_url" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">文章内容<span class="validt" id="vu_article_content"></span></label>
                        <textarea class="form-control" id="up_article_content" name="article_content" ></textarea>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">排序<span class="validt" id="vu_sort"></span></label>
                        <input type="text" id="up_sort" name="sort" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">是否显示<span class="validt" id="vu_is_show"></span></label>
                        <select id="up_is_show" name="is_show" class="form-control">
                            <option value="1">显示</option>
                            <option value="2">不显示</option>
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