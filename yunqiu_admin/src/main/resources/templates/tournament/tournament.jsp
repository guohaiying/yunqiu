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
            url:'/schedule/select',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            height:$(window).height()-239,
            autowidth:true,//自动宽
            shrinkToFit:true,
            colModel:[
                {label:"比赛Id",name:'relateId',index:'relateId', width:'15%',align:'center',hidden:true},
                {label:"轮次Id",name:'scheduleId',index:'scheduleId', width:'15%',align:'center',hidden:true},
                {label:"比赛Id",name:'gameId',index:'gameId', width:'15%',align:'center',hidden:true},
                {label:"赛事类型",name:'leagueType',index:'leagueType', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){
                            return "小组赛";
                        }else if(cellvalue==2){
                            return "杯赛 ";
                        }else if(cellvalue==3){
                            return "淘汰赛";
                        }else if(cellvalue==4){
                            return "联赛";
                        }else{
                            return "";
                        }
                    }
                },
                {label:"主场",name:'entryTeamA',index:'entryTeamA', width:'35%',align:'center'},
                {label:"客场",name:'entryTeamB',index:'entryTeamB', width:'35%',align:'center'},
                {label:"比赛时间",name:'gameTime',index:'gameTime', width:'35%',align:'center'},
                {label:"轮次名称",name:'scheduleName',index:'scheduleName', width:'35%',align:'center'},
                {label:"排序",name:'rounds',index:'rounds', width:'35%',align:'center'},
                /*{label:"比赛分类",name:'classify',index:'classify', width:'35%',align:'center'},*/
                {label:"场地",name:'gameSite',index:'gameSite', width:'35%',align:'center'},
                {label:"比赛状态",name:'gameStatus',index:'gameStatus', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==0){
                            return "不需要审核，不需要应战";
                        }else if(cellvalue==1){
                            return "待审核";
                        }else if(cellvalue==2){
                            return "待应战";
                        }else if(cellvalue==3){
                            return "报名中";
                        }else if(cellvalue==4){
                            return "报名结束";
                        }else if(cellvalue==5){
                            return "进行中";
                        }else if(cellvalue==6){
                            return "已结束";
                        }else if(cellvalue==7){
                            return "已取消";
                        }else if(cellvalue==8){
                            return "拒绝比赛";
                        }else if(cellvalue==9){
                            return "拒绝应战";
                        }else{
                            return "";
                        }
                    }
                },
                {label:"操作",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        var text = "'"+rowObject.relateId+"'";
                        var text1 = "'"+rowObject.relateId+"'"+",'"+rowObject.rounds+"'"+",'"+rowObject.scheduleId+"'"+",'"+rowObject.gameId+"'";
                        return '<a href="javascript:editInfo('+text1+')">编辑</a>   <a href="javascript:deleteSCInfo('+text+')">删除</a>  <a href="javascript:void(0)">跳转到比赛管理</a>';
                    }
                }
            ],
            pager:'#gridPager',
            viewrecords: true,//是否在浏览导航栏显示记录总数
            rowNum:10, //每页显示记录数
            rownumbers: true,
            sortname: "gameTime",
            sortorder: "desc",
            rowList:[10,30,50],//用于改变显示行数的下拉列表框的元素数组。
            jsonReader:{
                id: "gameId",//设置返回参数中，表格ID的名字为blackId
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

        //获取所有比赛
        $.ajax({
            url: "/tournament/getTournamentList",
            async : false,
            datatype:"json",
            type:"get",
            success: function (data) {
                $("#gameId").empty();
                items = data["data"];
                var selectList = "";
                $.each(eval(items), function (idx, obj) {
                    selectList += '<option value="'+obj.gameId+'" >'+obj.gameName+'</option>';
                });
                $("#gameId").append(selectList);

                $("#up_gameId").empty();
                $("#up_gameId").append(selectList);
            }
        });

        //获取所有轮次
        $.ajax({
            url: "/schedule/getScheduleList",
            async : false,
            datatype:"json",
            type:"get",
            success: function (data) {
                $("#scheduleId").empty();
                items = data["data"];
                var selectList = "";
                $.each(eval(items), function (idx, obj) {
                    selectList += '<option value="'+obj.scheduleId+'" >'+obj.scheduleName+'</option>';
                });
                $("#scheduleId").append(selectList);

                $("#up_scheduleId").empty();
                $("#up_scheduleId").append(selectList);
            }
        });

        //获取当前参赛球队名称
        $.ajax({
            url: "/tournament/getLeagueName",
            async : false,
            datatype:"json",
            type:"get",
            success: function (data) {
                $("#leagueNameInfo").text("赛程："+data);
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
                    url: "/schedule/addScheduleRelateGame",//请求地址
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

        //轮次管理
        $("#btn_lunci").bind("click",function(){
            $("#lunci_winfrom").modal('show');
            $("#lunci").unbind("click");

            lunList();

            $("#lunci").bind("click",function(){

                var scheduleName=$("#scheduleName").val();
                var totalRounds=$("#totalRounds").val();

                $("#va_scheduleName").html("");
                $("#va_totalRounds").html("");

                if(!scheduleName.length>0){
                    $("#va_scheduleName").html("请输入轮次名称");
                    return;
                }

                if(!totalRounds.length>0){
                    $("#va_totalRounds").html("请输入总轮次");
                    return;
                }

                //开始ajax操作
                $("#lunciForm").ajaxSubmit({
                    type: "POST",//提交类型
                    dataType: "json",//返回结果格式
                    url: "/schedule/addSchedule",//请求地址
                    data:$("#addForm").serialize(),
                    success: function (data) {//请求成功后的函数
                        if (data["error_code"] == 1500){
                            swal("系统错误!", data["msg"], "error");
                        }else if (data["error_code"] != 1200){
                            swal("操作失败!",data["msg"],"warning");
                        }else{
                            lunList();
                            $.ajax({
                                url: "/schedule/getScheduleList",
                                async : false,
                                datatype:"json",
                                type:"get",
                                success: function (data) {
                                    $("#scheduleId").empty();
                                    items = data["data"];
                                    var selectList = "";
                                    $.each(eval(items), function (idx, obj) {
                                        selectList += '<option value="'+obj.scheduleId+'" >'+obj.scheduleName+'</option>';
                                    });
                                    $("#scheduleId").append(selectList);

                                    $("#up_scheduleId").empty();
                                    $("#up_scheduleId").append(selectList);
                                }
                            });
                        }
                    },
                    error: function (data) { swal("添加失败","网络异常！","error"); },//请求失败的函数
                    async: false
                });
            });
        });


    });


    function lunList(){
        $.ajax({
            url:"/schedule/getScheduleList",
            success:function(data){
                if (data["error_code"] == 1500){
                    swal("系统错误!", data["msg"], "error");
                }else if (data["error_code"] != 1200){
                    swal("操作失败!",data["msg"],"warning");
                }else{
                    $("#allLunci").empty();
                    items = data["data"];
                    var lunci = "";
                    $.each(eval(items), function (idx, obj) {
                        var status= "";
                        if(obj.leagueType==1){
                            status="小组赛";
                        }else if(obj.leagueType==2){
                            status="杯赛";
                        }else if(obj.leagueType==3){
                            status="淘汰赛";
                        }else if(obj.leagueType==4){
                            status="联赛";
                        }
                        var text = "'"+obj.scheduleId+"'";
                        lunci+= ((idx+1)+'.'+obj.scheduleName+'   '+status+'   '+obj.totalRounds +'     <a href="javascript:deleteInfo('+text+')">删除</a><br/>');
                    });
                    $("#allLunci").append(lunci);
                }
            },error:function(){
                swal("修改失败","网络异常！");
            }
        });
    }
    
    //修改窗口
    function editInfo(relateId,rounds,scheduleId,gameId) {
        $("#update").unbind("click");

        $("#update_winfrom").modal('show');

        $("#up_relateId").val(relateId);
        $("#up_rounds").val(rounds);
        //$("#up_teamType option:contains('"+select.teamType+"')").prop("selected", true);
        $("#up_gameId").find("option[value="+gameId+"]").prop("selected",true);
        $("#up_scheduleId").find("option[value="+scheduleId+"]").prop("selected",true);

        $("#update").bind("click",function(){
            //开始ajax操作
            $("#updateForm").ajaxSubmit({
                type: "POST",//提交类型
                dataType: "json",//返回结果格式
                url: "/schedule/updateScheduleRelateGame",//请求地址
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

    //删除轮次窗口
    function deleteInfo(scheduleId) {
        $.ajax({
            url:"/schedule/delSchedule",
            data:{
                scheduleId:scheduleId
            },
            success:function(data){
                if (data["error_code"] == 1500){
                    swal("系统错误!", data["msg"], "error");
                }else if (data["error_code"] != 1200){
                    swal("操作失败!",data["msg"],"warning");
                }else{
                    lunList();
                    $.ajax({
                        url: "/schedule/getScheduleList",
                        async : false,
                        datatype:"json",
                        type:"get",
                        success: function (data) {
                            $("#scheduleId").empty();
                            items = data["data"];
                            var selectList = "";
                            $.each(eval(items), function (idx, obj) {
                                selectList += '<option value="'+obj.scheduleId+'" >'+obj.scheduleName+'</option>';
                            });
                            $("#scheduleId").append(selectList);

                            $("#up_scheduleId").empty();
                            $("#up_scheduleId").append(selectList);
                        }
                    });
                }
            },error:function(){
                swal("删除失败","网络异常！");
            }
        });
    }


    //删除赛程窗口
    function deleteSCInfo(relateId) {
        $("#delete_winfrom").modal('show');
        $("#delete").bind("click", function () {
            var username = $("#username").val();
            var password = $("#password").val();
            $.ajax({
                url: "/schedule/delScheduleRelateGame",
                data: {
                    relateId: relateId,
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


</script>


<div  id="user_button" style="margin-left: 24px;">
    <div class="ibox-title">
        <a data-toggle="modal" style="margin-right: 10px;" class="btn btn-primary" id="btn_add">新建赛程</a>
        <a data-toggle="modal" style="margin-right: 10px;" class="btn btn-primary" id="btn_lunci">轮次管理</a>
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
                        <label class="col-sm-5 control-label">比赛名称</label>
                        <select id="gameId" name="gameId" class="form-control">

                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">轮次</label>
                        <select id="scheduleId" name="scheduleId" class="form-control"></select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">顺序</label>
                        <input type="text" id="rounds" name="rounds" class="form-control"/>
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
                    <input type="hidden" id="up_relateId"  name="relateId"/>
                    <div class="form-group">
                        <label class="col-sm-5 control-label">比赛名称</label>
                        <select id="up_gameId" name="gameId" class="form-control"></select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">轮次</label>
                        <select id="up_scheduleId" name="scheduleId" class="form-control"></select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">顺序</label>
                        <input type="text" id="up_rounds" name="rounds" class="form-control"/>
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



<form id="lunciForm">
    <div id="lunci_winfrom" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <input type="hidden" id="leagueIdPlace"  name="leagueId"/>
                    <div class="form-group">
                        <label class="col-sm-5 control-label">轮次名称<span class="validt" id="va_scheduleName"></span></label>
                        <input type="text" id="scheduleName" name="scheduleName" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-5 control-label">赛事类型</label>
                        <select id="leagueType" name="leagueType"  class="form-control">
                            <option value="1">小组赛</option>
                            <option value="2">杯赛</option>
                            <option value="3">淘汰赛</option>
                            <option value="4">联赛</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">总轮次<span class="validt" id="va_totalRounds"></span></label>
                        <input type="text" id="totalRounds" name="totalRounds" class="form-control"/>
                    </div>
                    <div class="form-group" id="allLunci" style="margin-left: 41%">
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="lunci">确定</button>
                </div>
            </div>
        </div>
    </div>
</form>


</body>
</html>