<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="renderer" content="webkit"/>
    <title> </title>

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
            url:'/joinleague/select',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            height:$(window).height()-239,
            autowidth:true,//自动宽
            shrinkToFit:true,
            colModel:[
                {label:"Id",name:'joinId',index:'joinId', width:'15%',align:'center',hidden:true},
                {label:"赛事Id",name:'leagueId',index:'leagueId', width:'15%',align:'center',hidden:true},
                {label:"球队Id",name:'teamId',index:'teamId', width:'15%',align:'center'},
                {label:"球队名称",name:'teamName',index:'teamName', width:'35%',align:'center'},
                {label:"队徽",name:'badge',index:'badge', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==undefined){
                            return "";
                        }else{
                            return '<img  style="width: 88px;height: 81px;" src="http://picture-services.b0.upaiyun.com/'+cellvalue+'"/>';
                        }
                    }
                },
                {label:"状态",name:'auditStatus',index:'auditStatus', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){
                            return "待审核";
                        }else if(cellvalue==2){
                            return "通过";
                        }else if(cellvalue==3){
                            return "拒绝";
                        }else{
                            return "";
                        }
                    }
                },
                {label:"操作",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        var text = "'"+rowObject.joinId+"'";
                        return '<a href="javascript:editInfo('+text+')">编辑</a>   <a href="javascript:deleteInfo('+text+')">删除</a>';
                    }
                }
            ],
            pager:'#gridPager',
            viewrecords: true,//是否在浏览导航栏显示记录总数
            rowNum:10, //每页显示记录数
            rownumbers: true,
            sortname: "joinTime",
            sortorder: "desc",
            rowList:[10,30,50],//用于改变显示行数的下拉列表框的元素数组。
            jsonReader:{
                id: "teamId",//设置返回参数中，表格ID的名字为blackId
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

        //获取所有球队
        $.ajax({
            url: "/team/getTeamList",
            async : false,
            datatype:"json",
            type:"get",
            success: function (data) {
                $("#teamName").empty();
                items = data["data"];
                var selectList = "";
                $.each(eval(items), function (idx, obj) {
                    selectList += '<option value="'+obj.teamId+'" >'+obj.teamName+'</option>';
                });
                $("#teamName").append(selectList);
            }
        });


        //获取当前参赛球队名称
        $.ajax({
            url: "/joinleague/getJoinLeagueName",
            async : false,
            datatype:"json",
            type:"get",
            success: function (data) {
                $("#leagueNameInfo").text("参赛球队："+data);
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
                    url: "/joinleague/addjoinLeague",//请求地址
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
    });
    
    //修改窗口
    function editInfo(joinId) {
        $("#update").unbind("click");

        $("#update_winfrom").modal('show');

        $("#joinId").val(joinId);

        $("#update").bind("click",function(){
            //开始ajax操作
            $("#updateForm").ajaxSubmit({
                type: "POST",//提交类型
                dataType: "json",//返回结果格式
                url: "/joinleague/upjoinLeague",//请求地址
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

    //删除窗口
    function deleteInfo(joinId) {
        $("#delete_winfrom").modal('show');

        $("#delete").bind("click",function(){
            $.ajax({
                url:"/joinleague/deljoinLeague",
                data:{
                    joinId:joinId
                },
                success:function(data){
                    if (data["error_code"] == 1500){
                        swal("系统错误!", data["msg"], "error");
                    }else if (data["error_code"] != 1200){
                        swal("操作失败!",data["msg"],"warning");
                    }else{
                        $("#delete_winfrom").modal('hide');
                        swal("删除","删除成功！");
                        $("#list4").trigger("reloadGrid");
                    }
                },error:function(){
                    swal("删除失败","网络异常！");
                }
            });
        });
    }


</script>


<div  id="user_button" style="margin-left: 24px;">
    <div class="ibox-title">
        <a data-toggle="modal" style="margin-right: 10px;" class="btn btn-primary" id="btn_add">添加</a>
     </div>
</div>

<div id="leagueNameInfo" style="font-size: 25px;margin-left: 38px;">

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

                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-5 control-label">球队名称</label>
                        <select id="teamName" name="teamId" class="form-control">

                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">状态</label>
                        <select name="auditStatus"  class="form-control">
                            <option value="2">通过</option>
                            <option value="3">拒绝</option>
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
                <div class="modal-body">
                    <input type="hidden" id="joinId"  name="joinId"/>
                    <div class="form-group">
                        <label class="col-sm-5 control-label">状态</label>
                        <select name="auditStatus" id="auditStatus" class="form-control">
                            <option value="2">通过</option>
                            <option value="3">拒绝</option>
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
                <h2 class="modal-title">确认删除此球队吗？此操作无法撤消</h2>
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