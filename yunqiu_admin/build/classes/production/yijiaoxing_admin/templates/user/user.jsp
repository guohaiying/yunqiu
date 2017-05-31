<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="renderer" content="webkit"/>
    <title>云球 - 主页</title>

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
            url:'/perm/select',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            height:$(window).height()-239,
            autowidth:true,//自动宽
            shrinkToFit:true,
            colModel:[
                {label:"用户Id",name:'adminId',index:'adminId', width:'15%',align:'center',hidden: true},
                {label:"角色Id",name:'roleId',index:'roleId', width:'15%',align:'center',hidden: true},
                {label:"用户名",name:'userName',index:'userName', width:'15%',align:'center'},
                {label:"手机号",name:'phone',index:'phone', width:'35%',align:'center'},
                {label:"邮箱",name:'email',index:'email', width:'35%',align:'center'},
                {label:"创建时间",name:'createTime',index:'createTime', width:'35%',align:'center',formatter:"date",
                    formatter:function(cellvalue, options, rowObject){
                        if (typeof(cellvalue)=="undefined" || typeof(cellvalue)=="" || cellvalue==0){
                            return "";
                        }
                        var date = new Date(cellvalue);

                        return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
                    }
                },
                {label:"角色",name:'roleName',index:'roleName', width:'35%',align:'center'},
                {label:"状态",name:'status',index:'status', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){
                            return "正常";
                        }else if(cellvalue==2){
                            return "冻结";
                        }else{
                            return "";
                        }
                    }
                },
                {label:"备注",name:'remark',index:'remark', width:'35%',align:'center'}
            ],
            pager:'#gridPager',
            viewrecords: true,//是否在浏览导航栏显示记录总数
            rowNum:10, //每页显示记录数
            rownumbers: true,
            sortname: "createTime",
            sortorder: "desc",
            rowList:[10,30,50],//用于改变显示行数的下拉列表框的元素数组。
            jsonReader:{
                id: "adminId",//设置返回参数中，表格ID的名字为blackId
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

        //查询所有角色
        $.ajax({
            url: "/role/getRoleList",
            async : false,
            datatype:"json",
            type:"get",
            success: function (data) {
                $("div[name=roleList]").empty();
                items = data["data"];
                var roleList = "";
                $.each(eval(items), function (idx, obj) {
                    roleList += '<p><input type="checkbox" name="role" value="'+obj.roleId+'" />'+obj.roleName+'</p>';
                });
                $("div[name=roleList]").append(roleList);
            }
        });

        //查询列表select
        $.ajax({
            url: "/role/getRoleList",
            async : false,
            datatype:"json",
            type:"get",
            success: function (data) {
                $("#s_role").empty();
                items = data["data"];
                var roleList = "";
                roleList += '<option value="0" selected="selected">全部</option>';
                $.each(eval(items), function (idx, obj) {
                    roleList += '<option name="role" value="'+obj.roleId+'" >'+obj.roleName+'</option>';
                });
                $("#s_role").append(roleList);
            }
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

        //查询条件传值
        $("#serc").click(function(){
            var userName=$("#s_userName").val();
            var phone=$("#s_phone").val();
            var status=$("#s_status").val();
            var role=$("#s_role").val();

            $("#list4").jqGrid('setGridParam',{
                datatype:'json',
                url:'/perm/select',
                page:1,
                postData:{
                    'userName':userName,
                    'phone':phone,
                    'status':status,
                    'role':role
                }
            }).trigger("reloadGrid"); //重新载入
        });


        //添加窗口
        $("#btn_add").bind("click",function(){
            $("#add_winfrom").modal('show');
            $("#add").unbind("click");

            $("#add").bind("click",function(){
                //开始ajax操作
                $("#addForm").ajaxSubmit({
                    type: "POST",//提交类型
                    dataType: "json",//返回结果格式
                    url: "/perm/addUser",//请求地址
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

                $("#up_userName").val(select.userName);
                $("#up_phone").val(select.phone);
                $("#up_email").val(select.email);
                $("#up_remark").val(select.remark);
                $("#up_adminId").val(select.adminId);

                //所有checkbox不选中
                $("input[name='role']").removeAttr("checked");//取消全选

                var role = select.roleId;
                var strs= new Array(); //定义一数组
                strs=role.split(","); //字符分割
                $.each(strs, function (idx, obj) {
                    $("input[name='role'][value="+obj+"]").prop("checked","checked");
                });

                $("#update").bind("click",function(){
                    //开始ajax操作
                    $("#updateForm").ajaxSubmit({
                        type: "POST",//提交类型
                        dataType: "json",//返回结果格式
                        url: "/perm/upUser",//请求地址
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


        //修改密码窗口
        $("#btn_upPassword").bind("click",function(){

            $("#updatePassword").unbind("click");

            var selectedId = $("#list4").jqGrid("getGridParam", "selrow");
            var select= jQuery("#list4").jqGrid('getRowData', selectedId);
            if (selectedId == null){
                swal("修改","请选择需要修改的信息！","info");
            }else{
                $("#updatePassword_winfrom").modal('show');

                $("#upPassword_adminId").val(select.adminId);

                $("#updatePassword").bind("click",function(){

                    var upPassword_password = $("#upPassword_password");
                    var upAgainPassword_password = $("#upAgainPassword_password");

                    if(!upPassword_password.length>0){
                        swal("验证","请输入密码！");
                        return;
                    }

                    if(!upAgainPassword_password.length>0){
                        swal("验证","请再次输入密码！");
                        return;
                    }

                    if(upPassword_password!=upAgainPassword_password){
                        swal("验证","两次输入密码不同 请重新输入！");
                        $("#upPassword_password").val();
                        $("#upPassword_password").val();
                        return;
                    }

                    //开始ajax操作
                    $("#updatePasswordForm").ajaxSubmit({
                        type: "POST",//提交类型
                        dataType: "json",//返回结果格式
                        url: "/perm/upPassword",//请求地址
                        data:$("#updatePasswordForm").serialize(),
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
                swal("用户","请选择需要删除的信息！","info");
            }else{
                $("#delete_winfrom").modal('show');
                $("#delete").bind("click",function(){
                    $.ajax({
                        url:"/perm/delUser",
                        data:{
                            adminId:select.adminId
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


        //冻结窗口
        $("#btn_freeze").bind("click",function(){
            var selectedId = $("#list4").jqGrid("getGridParam", "selrow");
            var select= jQuery("#list4").jqGrid('getRowData', selectedId);
            if (selectedId == null){
                swal("用户","请选择需要冻结的信息！","info");
            }else{
                $("#freeze_winfrom").modal('show');
                var status = 1;
                if(select.status=="正常"){
                    status=2
                }else{
                    status=1
                }

                $("#freeze").bind("click",function(){
                    $.ajax({
                        url:"/perm/freezeUser",
                        data:{
                            adminId:select.adminId,
                            status:status
                        },
                        success:function(data){
                            if (data["error_code"] == 1500){
                                swal("系统错误!", data["msg"], "error");
                            }else if (data["error_code"] != 1200){
                                swal("操作失败!",data["msg"],"warning");
                            }else{
                                $("#freeze_winfrom").modal('hide');
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

<div id="user_button">

</div>


<div class="wrapper wrapper-content animated">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox ">
                <div class="ibox-title">
                    <form class="form-inline">
                        <div class="form-group">
                            <label>用户名</label>
                            <input type="text" id="s_userName" name="userName" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>联系方式</label>
                            <input type="text" id="s_phone" name="phone" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>状态</label>
                            <select class="form-control" id="s_status">
                                <option value="0" selected="selected">全部</option>
                                <option value="1">正常</option>
                                <option value="2">冻结</option>
                            </select>
                        </div>

                        <div class="form-group" >
                            <label>角色</label>
                            <select class="form-control" id="s_role" >
                            </select>
                        </div>

                        <button type="button" class="btn btn-default" id="serc">查询</button>
                    </form>
                </div>

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
                    <h2 class="modal-title" id="myModalLabel">用户添加</h2>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-5 control-label">用户名</label>
                        <input type="text" id="userName" name="userName"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">密码</label>
                        <input type="password" id="password" name="password"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">联系方式</label>
                        <input type="text" id="phone" name="phone"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">邮箱</label>
                        <input type="text" id="email" name="email" />
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">状态</label>
                        <input type="radio" name="status" value="1"/>正常
                        <input type="radio" name="status" value="2"/>冻结
                    </div>

                    <div class="form-group" >
                        <label class="col-sm-5 control-label">角色</label>
                        <div  name="roleList" style="width: 58%;margin-left:auto" >

                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">备注</label>
                        <textarea id="remark" name="remark" rows="5"></textarea>
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
                    <h2 class="modal-title">用户修改</h2>
                    <input type="hidden" id="up_adminId" name="adminId"/>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-5 control-label">用户名</label>
                        <input type="text" id="up_userName" name="userName"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">联系方式</label>
                        <input type="text" id="up_phone" name="phone"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">邮箱</label>
                        <input type="text" id="up_email" name="email" />
                    </div>

                    <div class="form-group" >
                        <label class="col-sm-5 control-label">角色</label>
                        <div  name="roleList" style="width: 58%;margin-left:auto" >

                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">备注</label>
                        <textarea id="up_remark" name="remark" rows="5"></textarea>
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
                <h2 class="modal-title">确定删除用户？</h2>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="delete">确定</button>
            </div>
        </div>
    </div>
</div>

<div id="freeze_winfrom" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title">确定冻结/解冻该用户？</h2>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="freeze">确定</button>
            </div>
        </div>
    </div>
</div>


<form id="updatePasswordForm">
    <div id="updatePassword_winfrom" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h2 class="modal-title">密码修改</h2>
                    <input type="hidden" id="upPassword_adminId" name="adminId"/>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-5 control-label">密码</label>
                        <input type="text" id="upPassword_password" name="password"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">再次输入密码</label>
                        <input type="text" id="upAgainPassword_password"/>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="updatePassword">修改</button>
                </div>
            </div>
        </div>
    </div>
</form>


</body>
</html>