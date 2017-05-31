<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="renderer" content="webkit"/>
    <title>云球 - 球队</title>

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

    <script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
</head>

<body>
<script>
    $(document).ready(function(){
        $.jgrid.defaults.styleUI="Bootstrap";
        $("#list4").jqGrid({
            url:'/teamMember/selectAllTeamUser',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            height:$(window).height()-239,
            autowidth:true,//自动宽
            shrinkToFit:true,
            colModel:[
                {label:"主键",name:'memberId',index:'memberId', width:'15%',align:'center',hidden: true},
                {label:"用户Id",name:'userId',index:'userId', width:'15%',align:'center',hidden: true},
                {label:"球员姓名",name:'nickname',index:'nickname', width:'35%',align:'center'},
                {label:"球衣号",name:'jerseyNo',index:'jerseyNo', width:'35%',align:'center'},
                {label:"出勤率",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return "50%";
                    }
                },
                {label:"身份",name:'identity',index:'identity', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){
                            return "队长";
                        }else if(cellvalue==2){
                            return "副队长";
                        }else if(cellvalue==3){
                            return "领队";
                        }else if(cellvalue==4){
                            return "教练";
                        }else if(cellvalue==5){
                            return "队员";
                        }else if(cellvalue==6){
                            return "外援";
                        }else if(cellvalue==7){
                            return "啦啦队";
                        }else{
                            return "";
                        }
                    }
                },
                {label:"位置",name:'place',index:'place', width:'35%',align:'center'},
                {label:"管理者身份",name:'jurisdiction',index:'jurisdiction', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==0){
                            return "无管理权限";
                        }else if(cellvalue==1){
                            return "超级管理员";
                        }else if(cellvalue==2){
                            return "普通管理员";
                        }else{
                            return "";
                        }
                    }
                },
                {label:"状态",name:'status',index:'status', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){
                            return "正常";
                        }else if(cellvalue==2){
                            return "离队";
                        }else{
                            return "";
                        }
                    }
                },
                {label:"操作",name:'ustatus',index:'ustatus', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){
                            var text = "'"+rowObject.memberId+"','"+rowObject.userId+"'";
                            return '<a href="javascript:shenpi('+text+')">待审批</a>';
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
            sortname: "nickname",
            sortorder: "desc",
            rowList:[10,30,50],//用于改变显示行数的下拉列表框的元素数组。
            jsonReader:{
                id: "memberId",//设置返回参数中，表格ID的名字为blackId
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

        //查询条件传值
        $("#serc").click(function(){
            var teamName=$("#s_teamName").val();
            var teamType=$("#s_teamType").val();
            var status=$("#s_status").val();
            var province = $("#s_province").find("option:selected").text();
            var city = $("#s_city").find("option:selected").text();
            var area = $("#s_area").find("option:selected").text();

            $("#list4").jqGrid('setGridParam',{
                datatype:'json',
                url:'/team/select',
                page:1,
                postData:{
                    'teamName':teamName,
                    'teamType':teamType,
                    'status':status,
                    'province':province,
                    'city':city,
                    'area':area
                }
            }).trigger("reloadGrid"); //重新载入
        });

        //获取球队名称
        $.ajax({
            url: "/teamMember/getTeamName",
            success: function (data) {
                if (data["error_code"] == 1500) {
                    swal("系统错误!", data["msg"], "error");
                } else if (data["error_code"] != 1200) {
                    swal("操作失败!", data["msg"], "warning");
                } else {
                    $("#teamName").empty();
                    $("#teamName").text(data["data"]);
                }
            }, error: function () {
                swal("删除失败", "网络异常！");
            }
        });


        //添加窗口
        $("#btn_add").bind("click",function(){
            $("#add_winfrom").modal('show');
            $("#add").unbind("click");

            $("#add").bind("click",function(){

                var jerseyNo=$("#jerseyNo").val();

                $("#va_jerseyNo").html("");

                if(!jerseyNo.length>0){
                    $("#va_jerseyNo").html("请输入球衣号");
                    return;
                }

                //开始ajax操作
                $("#addForm").ajaxSubmit({
                    type: "POST",//提交类型
                    dataType: "json",//返回结果格式
                    url: "/teamMember/addTeamMemberU",//请求地址
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

                $("#up_memberId").val(select.memberId);
                $("#up_jerseyNo").val(select.jerseyNo);
                $("#up_identity option:contains('"+select.identity+"')").prop("selected", true);
                $("#up_jurisdiction option:contains('"+select.jurisdiction+"')").prop("selected", true);

                $("#update").bind("click",function(){

                    var jerseyNo=$("#up_jerseyNo").val();//球队名称

                    $("#vu_jerseyNo").html("");

                    if(!jerseyNo.length>0){
                        $("#vu_jerseyNo").html("请输入球衣号");
                        return;
                    }
                    //开始ajax操作
                    $("#updateForm").ajaxSubmit({
                        type: "POST",//提交类型
                        dataType: "json",//返回结果格式
                        url: "/teamMember/updateTeamMember",//请求地址
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
        $("#btn_delete").bind("click",function() {
            var selectedId = $("#list4").jqGrid("getGridParam", "selrow");
            var select = jQuery("#list4").jqGrid('getRowData', selectedId);
            if (selectedId == null) {
                swal("用户", "请选择需要删除的信息！", "info");
            } else {
                $("#delete_winfrom").modal('show');
                $("#delete").bind("click", function () {
                    $.ajax({
                        url: "/teamMember/updateTeamMemberStatus",
                        data: {
                            status: 2,
                            memberId:select.memberId
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

        //用户
        $.ajax({
            url:"/user/getAllUser",
            success:function(data){
                $("#userId").empty();
                items = data["data"];
                var selectList = "";
                $.each(eval(items), function (idx, obj) {
                    selectList += '<option value="'+obj.userId+'" >'+obj.nickname+'</option>';
                });
                $("#userId").append(selectList);
            },error:function(){
                swal("获取失败","网络异常！");
            }
        });

    });


    function shenpi(memberId,userId){
        $("#join_winfrom").modal('show');
        $("#join").bind("click", function () {
            $.ajax({
                url: "/teamMember/updateShenpi",
                data: {
                    status: 1,
                    memberId:memberId,
                    userId:userId
                },
                success: function (data) {
                    if (data["error_code"] == 1500) {
                        swal("系统错误!", data["msg"], "error");
                    } else if (data["error_code"] != 1200) {
                        swal("操作失败!", data["msg"], "warning");
                    } else {
                        $("#join_winfrom").modal('hide');
                        /*swal("删除","删除成功！");*/
                        $("#list4").trigger("reloadGrid");
                    }
                }, error: function () {
                    swal("删除失败", "网络异常！");
                }
            });
        });
    }


</script>

<div>
    <div class="ibox-title">
        <a data-toggle="modal" style="margin-right: 10px;" class="btn btn-primary" id="btn_add">添加</a>
        <a data-toggle="modal" style="margin-right: 10px;" class="btn btn-primary" id="btn_edit">编辑</a>
        <a data-toggle="modal" style="margin-right: 10px;" class="btn btn-primary" id="btn_delete">移除</a>
    </div>
</div>

<div style="font-size: 25px;margin-left: 38px;margin-top: 39px;" id="teamName">
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
                    <h2 class="modal-title" id="myModalLabel">加入球队</h2>
                    <input type="hidden" name="status" value="2"/>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-5 control-label">球员名称<span class="validt" id="va_userId"></span></label>
                        <select id="userId" name="userId"  class="form-control">

                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">球衣号<span class="validt" id="va_jerseyNo"></span></label>
                        <input id="jerseyNo" name="jerseyNo"  class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">身份<span class="validt" id="va_identity"></span></label>
                        <select id="identity" name="identity"  class="form-control">
                            <option value="1">队长</option>
                            <option value="2">副队长</option>
                            <option value="3">领队</option>
                            <option value="4">教练</option>
                            <option value="5">队员</option>
                            <option value="6">外援</option>
                            <option value="7">啦啦队</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">管理员身份<span class="validt" id="va_jurisdiction"></span></label>
                        <select id="jurisdiction" name="jurisdiction"  class="form-control">
                            <option value="0">无管理权限</option>
                            <option value="1">超级管理员</option>
                            <option value="2">普通管理员</option>
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
                    <h2 class="modal-title">球员修改</h2>
                    <input type="hidden" id="up_memberId" name="memberId"/>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-5 control-label">球衣号<span class="validt" id="vu_jerseyNo"></span></label>
                        <input id="up_jerseyNo" name="jerseyNo"  class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">位置<span class="validt" id="va_place"></span></label>
                        <select tabindex="4" id="up_place" name="place" class="form-control" multiple="multiple">
                            <option value="左边锋">左边锋</option>
                            <option value="前锋">前锋</option>
                            <option value="中锋">中锋</option>
                            <option value="影锋">影锋</option>
                            <option value="右边锋">右边锋</option>
                            <option value="左前卫">左前卫</option>
                            <option value="前腰">前腰</option>
                            <option value="中前卫">中前卫</option>
                            <option value="后腰">后腰</option>
                            <option value="右前卫">右前卫</option>
                            <option value="左翼卫">左翼卫</option>
                            <option value="左后卫">左后卫</option>
                            <option value="中后卫">中后卫</option>
                            <option value="清道夫">清道夫</option>
                            <option value="右后卫">右后卫</option>
                            <option value="右翼卫">右翼卫</option>
                            <option value="门将">门将</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">身份<span class="validt" id="vu_identity"></span></label>
                        <select id="up_identity" name="identity"  class="form-control">
                            <option value="1">队长</option>
                            <option value="2">副队长</option>
                            <option value="3">领队</option>
                            <option value="4">教练</option>
                            <option value="5">队员</option>
                            <option value="6">外援</option>
                            <option value="7">啦啦队</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">管理员身份<span class="validt" id="vu_jurisdiction"></span></label>
                        <select id="up_jurisdiction" name="jurisdiction"  class="form-control">
                            <option value="0">无管理权限</option>
                            <option value="1">超级管理员</option>
                            <option value="2">普通管理员</option>
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
                <h2 class="modal-title">确定要移除此球员吗？此操作不可撤销</h2>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="delete">确定</button>
            </div>
        </div>
    </div>
</div>


<div id="join_winfrom" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title">你确定要此队员加入球队吗？</h2>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="join">确定</button>
            </div>
        </div>
    </div>
</div>


</body>
</html>