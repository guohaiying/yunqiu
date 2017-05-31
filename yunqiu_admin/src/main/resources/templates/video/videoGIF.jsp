<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="renderer" content="webkit"/>
    <title>云球 - 视频</title>

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
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
    <script src="/hplus/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="/hplus/js/content.min.js?v=1.0.0"></script>
    <script src="/hplus/js/demo/form-validate-demo.min.js"></script>
    <link href="/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet"/>
    <link href="/hplus/css/animate.min.css" rel="stylesheet"/>


</head>

<body>
<script>
    $(document).ready(function () {
        $.jgrid.defaults.styleUI = "Bootstrap";
        $("#list4").jqGrid({
            url: '/video/select',
            datatype: "json", //数据来源，本地数据
            mtype: "GET",//提交方式
            postData: {'videoType': 2},
            height: $(window).height() - 239,
            autowidth: true,//自动宽
            shrinkToFit: true,
            colModel: [
                {label: "视频Id", name: 'videoId', index: 'videoId', width: '15%', align: 'center', hidden: true},
                {label: "地址", name: 'videoAddressHigh', index: 'videoAddressHigh', width: '15%', align: 'center', hidden: true},
                {label: "创建时间", name: 'createTime', index: 'createTime', width: '35%', align: 'center'},
                {label: "创建人", name: 'userName', index: 'userName', width: '35%', align: 'center'},
                {label: "GIF名称", name: 'videoName', index: 'videoName', width: '35%', align: 'center'},
                {label: "GIF", name: '', index: '', width: '35%', align: 'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(rowObject.videoAddressHigh==undefined){
                            return "";
                        }else{
                            return '<img  style="width: 88px;height: 81px;" src="http://picture-services.b0.upaiyun.com/'+rowObject.videoAddressHigh+'"/>';
                        }
                    }
                },
                {label: "是否显示", name: 'ifShow', index: 'ifShow', width: '35%', align: 'center',
                    formatter: function (cellvalue, options, rowObject) {
                        if (cellvalue == 1) {
                            return "是";
                        } else if (cellvalue == 2) {
                            return "否";
                        } else {
                            return "";
                        }
                    }
                }
            ],
            pager: '#gridPager',
            viewrecords: true,//是否在浏览导航栏显示记录总数
            rowNum: 10, //每页显示记录数
            rownumbers: true,
            sortname: "createTime",
            sortorder: "desc",
            rowList: [10, 30, 50],//用于改变显示行数的下拉列表框的元素数组。
            jsonReader: {
                id: "videoId",//设置返回参数中，表格ID的名字为blackId
                root: "list", // json中代表实际模型数据的入口
                page: "page", // json中代表当前页码的数据
                total: "total", // json中代表页码总数的数据
                records: "records", // json中代表数据行总数的数据
                repeatitems: false
            },
            add: false,
            edit: false,
        });
        $("#list4").jqGrid("navGrid", "#gridPager", {
            edit: false,
            add: false,
            del: false,
            search: false
        }, {reloadAfterSubmit: true,});

        $(window).bind("resize", function () {
            var width = $("#divWidth").width();
            $("#list4").setGridWidth(width);
        });

        //按钮权限
        $.ajax({
            url: "/perm/power",
            async: false,
            datatype: "json",
            type: "get",
            success: function (data) {
                $("#user_button").empty();
                var powerList = "";
                if (data.length != 0) {
                    powerList += '<div class="ibox-title">';

                    $.each(eval(data), function (idx, obj) {
                        powerList += '<a data-toggle="modal" style="margin-right: 10px;" class="btn btn-primary" id="' + obj.url + '">' + obj.name + '</a>';
                    });
                    powerList += '</div>';
                    $("#user_button").append(powerList);
                }
            }
        });

        //查询条件传值
        $("#serc").click(function () {
            var userName = $("#s_userName").val();
            var classify = $("#s_classify").find("option:selected").val();
            var ifShow = $("#s_ifShow").find("option:selected").val();
            var videoTag = "";
            $("#s_videoTag option:selected").each(function () {
                //alert($(this).val()); //这里得到的就是
                if ($(this).val() != 0) {
                    videoTag += $(this).val() + ",";
                }
            })

            $("#list4").jqGrid('setGridParam', {
                datatype: 'json',
                url: '/video/select',
                page: 1,
                postData: {
                    'userName': userName,
                    'classify': classify,
                    'videoTag': videoTag,
                    'ifShow': ifShow
                }
            }).trigger("reloadGrid"); //重新载入
        });


        //添加窗口
        $("#btn_add").bind("click", function () {
            $("#add_winfrom").modal('show');
            $("#add").unbind("click");
            $("#add").bind("click", function () {

                var videoName = $("#a_videoName").val();//视频名称
                var videoAddressHigh = $("#a_videoAddressHigh").val();//高清地址
                var ifShow = $("#a_ifShow").find("option:selected").val();//是否显示

                $("#va_videoName").html("");
                $("#va_va_").html("");
                $("#va_ifShow").html("");

                if (!videoName.length > 0) {
                    $("#va_videoName").html("请输入GIF名称");
                    return;
                }
                if (!videoAddressHigh.length > 0) {
                    $("#va_videoHOS").html("请选择GIF");
                    return;
                }
                if (!ifShow.length > 0) {
                    $("#va_ifShow").html("请选择是否显示");
                    return;
                }

                //上传图片
                var options={
                    url: "/upload/upload",
                    type:"post",
                    success: function (data) {

                        $("#add_videoAddressHigh").val(data);

                        $("#addForm").ajaxSubmit({
                            type: "POST",//提交类型
                            dataType: "json",//返回结果格式
                            url: "/video/addVideo",//请求地址
                            data: $("#addForm").serialize(),
                            success: function (data) {//请求成功后的函数
                                if (data["error_code"] == 1500) {
                                    swal("系统错误!", data["msg"], "error");
                                } else if (data["error_code"] != 1200) {
                                    swal("操作失败!", data["msg"], "warning");
                                } else {
                                    $("#add_winfrom").modal('hide');
                                    swal("添加", "添加成功！");
                                    $("#list4").trigger("reloadGrid");
                                }
                            },
                            error: function (data) {
                                swal("添加失败", "网络异常！", "error");
                            },//请求失败的函数
                            async: false
                        });
                    }
                }
                $("#addForm").ajaxSubmit(options); //xx 你form表单的id,或者直接$("form").ajaxSubmit(options);

            });
        });

        //修改窗口
        $("#btn_edit").bind("click", function () {

            $("#update").unbind("click");

            var selectedId = $("#list4").jqGrid("getGridParam", "selrow");
            var select = jQuery("#list4").jqGrid('getRowData', selectedId);
            if (selectedId == null) {
                swal("修改", "请选择需要编辑的信息！", "info");
            } else {

                $("#up_videoId").val(select.videoId);
                $("#up_videoName").val(select.videoName);
                $("#update_videoAddressHigh").val(select.videoAddressHigh);
                $("#up_ifShow option:contains('" + select.ifShow + "')").prop("selected", true);

                $("#update_winfrom").modal('show');

                $("#update").bind("click", function () {

                    var videoName = $("#up_videoName").val();//视频名称
                    var videoAddressHigh = $("#up_videoAddressHigh").val();//高清地址
                    var ifShow = $("#up_ifShow").find("option:selected").val();//是否显示

                    $("#vu_videoName").html("");
                    $("#vu_videoAddressHigh").html("");
                    $("#vu_ifShow").html("");

                    if (!videoName.length > 0) {
                        $("#vu_videoName").html("请输入GIF名称");
                        return;
                    }
                    if (!ifShow.length > 0) {
                        $("#vu_ifShow").html("请选择是否显示");
                        return;
                    }

                    if(!videoAddressHigh.length > 0){
                        //开始ajax操作
                        $("#updateForm").ajaxSubmit({
                            type: "POST",//提交类型
                            dataType: "json",//返回结果格式
                            url: "/video/upVideo",//请求地址
                            data: $("#updateForm").serialize(),
                            success: function (data) {//请求成功后的函数
                                if (data["error_code"] == 1500) {
                                    swal("系统错误!", data["msg"], "error");
                                } else if (data["error_code"] != 1200) {
                                    swal("操作失败!", data["msg"], "warning");
                                } else {
                                    $("#update_winfrom").modal('hide');
                                    swal("修改", "修改成功！");
                                    $("#list4").trigger("reloadGrid");
                                }
                            },
                            error: function (data) {
                                swal("添加失败", "网络异常！", "error");
                            },//请求失败的函数
                            async: false
                        });
                    }else{
                        //上传图片
                        var options={
                            url: "/upload/upload",
                            type:"post",
                            success: function (data) {
                                $("#update_videoAddressHigh").val(data);

                                //开始ajax操作
                                $("#updateForm").ajaxSubmit({
                                    type: "POST",//提交类型
                                    dataType: "json",//返回结果格式
                                    url: "/video/upVideo",//请求地址
                                    data: $("#updateForm").serialize(),
                                    success: function (data) {//请求成功后的函数
                                        if (data["error_code"] == 1500) {
                                            swal("系统错误!", data["msg"], "error");
                                        } else if (data["error_code"] != 1200) {
                                            swal("操作失败!", data["msg"], "warning");
                                        } else {
                                            $("#update_winfrom").modal('hide');
                                            swal("修改", "修改成功！");
                                            $("#list4").trigger("reloadGrid");
                                        }
                                    },
                                    error: function (data) {
                                        swal("添加失败", "网络异常！", "error");
                                    },//请求失败的函数
                                    async: false
                                });
                            }
                        }
                        $("#updateForm").ajaxSubmit(options); //xx 你form表单的id,或者直接$("form").ajaxSubmit(options);
                    }
                });
            }
        });

        //删除窗口
        $("#btn_delete").bind("click", function () {
            var selectedId = $("#list4").jqGrid("getGridParam", "selrow");
            var select = jQuery("#list4").jqGrid('getRowData', selectedId);
            if (selectedId == null) {
                swal("用户", "请选择需要删除的信息！", "info");
            } else {
                $("#delete_winfrom").modal('show');
                $("#delete").bind("click", function () {
                    var username = $("#username").val();
                    var password = $("#password").val();
                    $.ajax({
                        url: "/video/delVideo",
                        data: {
                            videoId: select.videoId,
                            username: username,
                            password: password
                        },
                        success: function (data) {
                            if (data["error_code"] == 1500) {
                                swal("系统错误!", data["msg"], "error");
                            } else if (data["error_code"] != 1200) {
                                swal("操作失败!", data["msg"], "warning");
                            } else {
                                $("#delete_winfrom").modal('hide');
                                /*swal("删除","删除成功！");*/
                                $("#list4").trigger("reloadGrid");
                            }
                        }, error: function () {
                            swal("删除失败", "网络异常！");
                        }
                    });
                });
            }
        });
    });

</script>

<div id="user_button">

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


<form id="addForm" enctype="multipart/form-data">
    <div id="add_winfrom" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h2 class="modal-title" id="myModalLabel">GIF添加</h2>
                    <input type="hidden" name="videoType" class="form-control" value="2"/>
                    <input type="hidden" name="videoAddressHigh" id="add_videoAddressHigh" class="form-control"/>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label class="col-sm-5 control-label">GIF名称<span class="validt" id="va_videoName"></span></label>
                        <input type="text" name="videoName" class="form-control" id="a_videoName"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">上传GIF<span class="validt" id="va_videoAddressHigh"></span></label>
                        <input type="file" name="file" class="form-control" id="a_videoAddressHigh"/>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-5 control-label">是否显示<span class="validt" id="va_ifShow"></span></label>
                        <select class="form-control" name="ifShow" id="a_ifShow">
                            <option value="1">是</option>
                            <option value="2">否</option>
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
                    <h2 class="modal-title">GIF修改</h2>
                    <input type="hidden" id="up_videoId" name="videoId"/>
                    <input type="hidden" name="videoType" class="form-control" value="2"/>
                    <input type="hidden" name="videoAddressHigh" id="update_videoAddressHigh" class="form-control"/>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label class="col-sm-5 control-label">GIF名称<span class="validt"  id="vu_videoName"></span></label>
                        <input type="text" name="videoName" class="form-control" id="up_videoName"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">上传GIF<span class="validt" id="vu_videoAddressHigh"></span></label>
                        <input type="file" name="file" class="form-control" id="up_videoAddressHigh"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">是否显示<span class="validt" id="vu_ifShow"></span></label>
                        <select class="form-control" name="ifShow" id="up_ifShow">
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
                <h2 class="modal-title">你确定要删除此视频？此操作无法撤销</h2>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label class="col-sm-5 control-label">管理员帐号</label>
                    <input type="text" name="username" class="form-control" id="username"/>
                </div>

                <div class="form-group">
                    <label class="col-sm-5 control-label">管理员密码</label>
                    <input type="password" name="password" class="form-control" id="password"/>
                </div>
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