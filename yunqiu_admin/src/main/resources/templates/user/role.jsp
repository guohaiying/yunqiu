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
    <!-- zTree-->
    <link rel="stylesheet" href="/js/zTree_v3-master/css/demo.css" type="text/css"/>
    <link rel="stylesheet" href="/js/zTree_v3-master/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
    <script type="text/javascript" src="/js/zTree_v3-master/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="/js/zTree_v3-master/js/jquery.ztree.excheck.js"></script>

</head>

<body>
<script>
    $(document).ready(function(){
        $.jgrid.defaults.styleUI="Bootstrap";
        $("#list4").jqGrid({
            url:'/role/select',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            height:$(window).height()-239,
            autowidth:true,//自动宽
            shrinkToFit:true,
            colModel:[
                {label:"角色Id",name:'roleId',index:'roleId', width:'15%',align:'center',hidden: true},
                {label:"角色名称",name:'roleName',index:'roleName', width:'15%',align:'center'},
                {label:"创建时间",name:'createTime',index:'createTime', width:'35%',align:'center',formatter:"date",
                    formatter:function(cellvalue, options, rowObject){
                        if (typeof(cellvalue)=="undefined" || typeof(cellvalue)=="" || cellvalue==0){
                            return "";
                        }
                        var date = new Date(cellvalue);

                        return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
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
                id: "roleId",//设置返回参数中，表格ID的名字为blackId
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
                $("#addForm").ajaxSubmit({
                    type: "POST",//提交类型
                    dataType: "json",//返回结果格式
                    url: "/role/addRole",//请求地址
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

                $("#up_roleName").val(select.roleName);
                $("#up_remark").val(select.remark);
                $("#up_roleId").val(select.roleId);

                $("#update").bind("click",function(){
                    //开始ajax操作
                    $("#updateForm").ajaxSubmit({
                        type: "POST",//提交类型
                        dataType: "json",//返回结果格式
                        url: "/role/upRole",//请求地址
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
                swal("删除","请选择需要删除的信息！","info");
            }else{
                $("#delete_winfrom").modal('show');
                $("#delete").bind("click",function(){
                    $.ajax({
                        url:"/role/delRole",
                        data:{
                            roleId:select.roleId
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

        //授权
        $("#btn_authorize").bind("click",function(){
            var selectedId = $("#list4").jqGrid("getGridParam", "selrow");
            var select= jQuery("#list4").jqGrid('getRowData', selectedId);
            if (selectedId == null){
                swal("角色","请选择需要授权的信息！","info");
            }else{
                $("#seeMenu").modal('show');
                $.ajax({
                    url:"/role/getMenuRole?roleId="+select.roleId,
                    async : false,
                    datatype:'json',
                    type:'get',
                    success:function(e){
                        var setting = {
                            check: {enable: true},
                            data: {simpleData: {enable: true}},
                            callback:{onCheck:onCheck}
                        };
                        var zNodes = eval("(" + e + ")");
                        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                    },
                    error:function(){
                        swal("角色","服务器繁忙！","info");
                    }
                });
            }

        });
    });

    //获取选中节点值
    function onCheck(e,treeId,treeNode){
        var boolean=false;
        var selectedId = $("#list4").jqGrid("getGridParam", "selrow");
        var select= jQuery("#list4").jqGrid('getRowData', selectedId);
        var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
        nodes=treeObj.getCheckedNodes(true);


            //授权
        $("#empower").bind("click",function(){
            $.ajax({
                type : 'get',
                url : '/role/deleteMenuRole',
                data: {"roleId":select.roleId},
                async : false,
                success:function(e){
                    boolean=true;
                }
            });
             $.each(eval(nodes), function (idx, obj) {
                $.ajax({
                    type : 'get',
                    url : '/role/addMenuRole',
                    data: {"roleId":select.roleId,"menuId":obj.id},
                    async : false,
                    success:function(data){
                        boolean=true;
                    }
                });
            });
            if(boolean){
                $("#seeMenu").modal('hide');
                swal("角色授权!", "授权成功", "success");
            }
        });
    }


</script>

<div  id="user_button">

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

<form id="addForm">
    <div id="add_winfrom" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h2 class="modal-title" id="myModalLabel">角色添加</h2>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-5 control-label">角色名</label>
                        <input type="text" id="roleName" name="roleName" />
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">备注</label>
                        <textarea id="remark" name="remark" rows="5" cols="21"></textarea>
                    </div>
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
                    <h2 class="modal-title">角色修改</h2>
                    <input type="hidden" id="up_roleId" name="roleId"/>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-5 control-label">角色名</label>
                        <input type="text" id="up_roleName" name="roleName"/>
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
                <h2 class="modal-title">确定删除角色？</h2>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="delete">确定</button>
            </div>
        </div>
    </div>
</div>

<!-- 权限列表开始 -->
<div id="seeMenu" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title" id="menu_myModalLabel">查看权限</h2>
            </div>
            <div class="modal-body"><ul id="treeDemo" class="ztree"></ul></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="empower">授权</button>
            </div>
        </div>
    </div>
</div>
<!-- 权限列表结束 -->




</body>
</html>