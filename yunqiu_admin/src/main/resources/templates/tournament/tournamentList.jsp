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

    <script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
</head>

<body>
<script>
    $(document).ready(function(){
        $.jgrid.defaults.styleUI="Bootstrap";
        $("#list4").jqGrid({
            url:'/tournament/selectTournament',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            height:$(window).height()-239,
            autowidth:true,//自动宽
            shrinkToFit:true,
            colModel:[
                {label:"比赛Id",name:'gameId',index:'gameId', width:'15%',align:'center',hidden:true},
                {label:"主场比分",name:'scoreTeamA',index:'scoreTeamA', width:'15%',align:'center',hidden:true},
                {label:"客场比分",name:'scoreTeamB',index:'scoreTeamB', width:'15%',align:'center',hidden:true},
                {label:"主场名称",name:'teamAName',index:'teamAName', width:'15%',align:'center',hidden:true},
                {label:"客场名称",name:'teamBName',index:'teamBName', width:'15%',align:'center',hidden:true},
                {label:"主场队服颜色",name:'uniformTeamA',index:'uniformTeamA', width:'15%',align:'center',hidden:true},
                {label:"客场队服颜色",name:'uniformTeamB',index:'uniformTeamB', width:'15%',align:'center',hidden:true},
                {label:"比赛状态",name:'gameStatus',index:'gameStatus', width:'15%',align:'center',hidden:true},
                {label:"比分录入状态",name:'scoreStatus',index:'scoreStatus', width:'15%',align:'center',hidden:true},
                {label:"比赛名称",name:'gameName',index:'gameName', width:'35%',align:'center'},
                {label:"比赛分类",name:'classify',index:'classify', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){
                            return "友谊赛";
                        }else if(cellvalue==2){
                            return "赛事比赛";
                        }else if(cellvalue==3){
                            return "训练";
                        }else{
                            return "";
                        }
                    }
                },
                {label:"赛制",name:'gameSystem',index:'gameSystem', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){
                            return "3人";
                        }else if(cellvalue==2){
                            return "5人";
                        }else if(cellvalue==3){
                            return "7人";
                        }else if(cellvalue==4){
                            return "8人";
                        }else if(cellvalue==5){
                            return "9人";
                        }else if(cellvalue==6){
                            return "11人";
                        }else{
                            return "";
                        }
                    }
                },
                {label:"报名截止时间",name:'applyEndTime',index:'applyEndTime', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if (typeof(cellvalue)=="undefined" || typeof(cellvalue)=="" || cellvalue==0){
                            return "";
                        }
                        var date = new Date(cellvalue);
                        return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();;
                    }
                },
                {label:"比赛开始时间",name:'gameTime',index:'gameTime', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if (typeof(cellvalue)=="undefined" || typeof(cellvalue)=="" || cellvalue==0){
                            return "";
                        }
                        var date = new Date(cellvalue);
                        return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();;
                    }
                },
                {label:"持续时长",name:'continueTime',index:'continueTime', width:'35%',align:'center'},
                {label:"比赛场地",name:'gameSite',index:'gameSite', width:'35%',align:'center'},
                {label:"比赛双方球队",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return "主场："+rowObject.teamAName+"<br/>"+"客场："+rowObject.teamBName;
                    }
                },
                {label:"比赛状态",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(rowObject.gameStatus==0){
                            return '<a href="/view/go_tournametStatus?gameId='+rowObject.gameId+'">不需要审核</a>';
                        }else if(rowObject.gameStatus==1){
                            return '<a href="/view/go_tournametStatus?gameId='+rowObject.gameId+'">待审核</a>';
                        }else if(rowObject.gameStatus==2){
                            return '<a href="/view/go_tournametStatus?gameId='+rowObject.gameId+'">待应战</a>';
                        }else if(rowObject.gameStatus==3){
                            return '<a href="/view/go_tournametStatus?gameId='+rowObject.gameId+'">报名中</a>';
                        }else if(rowObject.gameStatus==4){
                            return '<a href="/view/go_tournametStatus?gameId='+rowObject.gameId+'">报名结束</a>';
                        }else if(rowObject.gameStatus==5){
                            return '<a href="/view/go_tournametStatus?gameId='+rowObject.gameId+'">进行中</a>';
                        }else if(rowObject.gameStatus==6){
                            return '<a href="/view/go_tournametStatus?gameId='+rowObject.gameId+'">已结束</a>';
                        }else if(rowObject.gameStatus==7){
                            return '<a href="/view/go_tournametStatus?gameId='+rowObject.gameId+'">已取消</a>';
                        }else if(rowObject.gameStatus==8){
                            return '<a href="/view/go_tournametStatus?gameId='+rowObject.gameId+'">拒绝比赛</a>';
                        }else if(rowObject.gameStatus==9){
                            return '<a href="/view/go_tournametStatus?gameId='+rowObject.gameId+'">拒绝应战</a>';
                        }else{
                            return "";
                        }
                    }
                },
                {label:"比分",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return rowObject.scoreTeamA+"："+rowObject.scoreTeamB;
                    }
                },
                {label:"比赛数据",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return '<a href="/view/go_tournametData?gameId='+rowObject.gameId+'">详情</a>';
                    }
                },
                {label:"比赛视频",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return '<a href="/view/go_video?classify=2">详情</a>';
                    }
                },
                {label:"费用",name:'gameCost',index:'gameCost', width:'35%',align:'center'},
                {label:"视频是否通知",name:'informVideo',index:'informVideo', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){
                            return "是";
                        }else if(cellvalue==2){
                            return "否";
                        }else{
                            return "";
                        }
                    }
                },
                {label:"选购服务",name:'valueAdded',index:'valueAdded', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==0){
                            return "暂不选购";
                        }else if(cellvalue==1){
                            return "数据";
                        }else if(cellvalue==2){
                            return "视频";
                        }else if(cellvalue==3){
                            return "视频+数据";
                        }else{
                            return "";
                        }
                    }
                },
                {label:"是否后台录入",name:'enteringStatus',index:'enteringStatus', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){
                            return "是";
                        }else if(cellvalue==2){
                            return "否";
                        }else{
                            return "是";
                        }
                    }
                },
                {label:"是否启用",name:'showStatus',index:'showStatus', width:'35%',align:'center',
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

        //获取所有球队
        $.ajax({
            url: "/team/getTeamList",
            async : false,
            datatype:"json",
            type:"get",
            success: function (data) {
                $("#entryTeamA").empty();
                items = data["data"];
                var selectList = "";
                $.each(eval(items), function (idx, obj) {
                    selectList += '<option value="'+obj.teamId+'" >'+obj.teamName+'</option>';
                });
                $("#entryTeamA").append(selectList);

                $("#up_entryTeamA").empty();
                items = data["data"];
                var selectList = "";
                $.each(eval(items), function (idx, obj) {
                    selectList += '<option value="'+obj.teamId+'" >'+obj.teamName+'</option>';
                });
                $("#up_entryTeamA").append(selectList);
            }
        });

        $.ajax({
            url: "/team/getTeamList",
            async : false,
            datatype:"json",
            type:"get",
            success: function (data) {
                $("#entryTeamB").empty();
                items = data["data"];
                var selectList = "";
                $.each(eval(items), function (idx, obj) {
                    selectList += '<option value="'+obj.teamId+'" >'+obj.teamName+'</option>';
                });
                $("#entryTeamB").append(selectList);

                $("#up_entryTeamB").empty();
                items = data["data"];
                var selectList = "";
                $.each(eval(items), function (idx, obj) {
                    selectList += '<option value="'+obj.teamId+'" >'+obj.teamName+'</option>';
                });
                $("#up_entryTeamB").append(selectList);
            }
        });


        //获取队服颜色
        /*$.ajax({
            url: "/teamcolour/getTeamColourList",
            async : false,
            datatype:"json",
            type:"get",
            success: function (data) {
                $("#uniformTeamA").empty();
                items = data["data"];
                var selectList = "";
                $.each(eval(items), function (idx, obj) {
                    selectList += '<option value="'+obj.teamcolorId+'" >'+obj.colour+'</option>';
                });
                $("#uniformTeamA").append(selectList);
            }
        });

        $.ajax({
            url: "/teamcolour/getTeamColourList",
            async : false,
            datatype:"json",
            type:"get",
            success: function (data) {
                $("#uniformTeamB").empty();
                items = data["data"];
                var selectList = "";
                $.each(eval(items), function (idx, obj) {
                    selectList += '<option value="'+obj.teamcolorId+'" >'+obj.colour+'</option>';
                });
                $("#uniformTeamB").append(selectList);
            }
        });*/

        //添加窗口
        $("#btn_add").bind("click",function(){
            $("#add_winfrom").modal('show');
            $("#add").unbind("click");

            $("#add").bind("click",function(){

                var gameName=$("#gameName").val();
                var gameTime=$("#gameTime").val();
                var applyEndTime=$("#applyEndTime").val();
                var gameSite=$("#gameSite").val();
                var scoreTeamA=$("#scoreTeamA").val();
                var scoreTeamB=$("#scoreTeamB").val();
                var gameCost=$("#gameCost").val();
                var uniformTeamA = $("#uniformTeamA").find("option:selected").val();
                var uniformTeamB = $("#uniformTeamB").find("option:selected").val();


                $("#va_gameName").html("");
                $("#va_gameTime").html("");
                $("#va_applyEndTime").html("");
                $("#va_gameSite").html("");
                $("#va_scoreTeamAB").html("");
                $("#va_gameCost").html("");
                $("#va_uniformTeamB").html("");

               /* if(!gameName.length>0){
                    $("#va_gameName").html("请输入比赛名称");
                    return;
                }*/

                if(uniformTeamA == uniformTeamB){
                    $("#va_uniformTeamB").html("两队队服不能相同");
                    return;
                }

                if(!gameTime.length>0){
                    $("#va_gameTime").html("请输入比赛开始时间");
                    return;
                }

                if(!applyEndTime.length>0){
                    $("#va_applyEndTime").html("请输入报名截止时间");
                    return;
                }

                if(!gameSite.length>0){
                    $("#va_gameSite").html("请输入比赛场地");
                    return;
                }

                if(!scoreTeamA.length>0){
                    $("#va_scoreTeamAB").html("请输入主场比分");
                    return;
                }

                if(!scoreTeamB.length>0){
                    $("#va_scoreTeamAB").html("请输入客场比分");
                    return;
                }

                /*if(!gameCost.length>0){
                    $("#va_gameCost").html("请输入费用");
                    return;
                }*/


                //开始ajax操作
                $("#addForm").ajaxSubmit({
                    type: "POST",//提交类型
                    dataType: "json",//返回结果格式
                    url: "/tournament/addTournamentList",//请求地址
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

                $("#up_gameId").val(select.gameId);
                $("#up_gameName").val(select.gameName);
                $("#up_classify option:contains('"+select.classify+"')").prop("selected", true);
                $("#up_gameSystem option:contains('"+select.gameSystem+"')").prop("selected", true);
                //$("#up_gameSystem").find("option[value="+select.gameSystem+"]").prop("selected",true);
                $("#up_entryTeamA option:contains('"+select.teamAName+"')").prop("selected", true);
                $("#up_uniformTeamA").find("option[value="+select.uniformTeamA+"]").prop("selected",true);
                $("#up_entryTeamB option:contains('"+select.teamBName+"')").prop("selected", true);
                $("#up_uniformTeamB").find("option[value="+select.uniformTeamB+"]").prop("selected",true);
                $("#up_gameTime").val(select.gameTime);
                $("#up_applyEndTime").val(select.applyEndTime);
                $("#up_continueTime option:contains('"+select.continueTime+"')").prop("selected", true);
                $("#up_gameSite").val(select.gameSite);
                $("#up_scoreTeamA").val(select.scoreTeamA);
                $("#up_scoreTeamB").val(select.scoreTeamB);
                $("#up_gameCost").val(select.gameCost);
                $("#up_valueAdded option:contains('"+select.valueAdded+"')").prop("selected", true);
                $("#up_informVideo option:contains('"+select.informVideo+"')").prop("selected", true);
                $("#up_showStatus option:contains('"+select.showStatus+"')").prop("selected", true);
                $("#up_gameStatus").find("option[value="+select.gameStatus+"]").prop("selected",true);
                $("#up_scoreStatus").find("option[value="+select.scoreStatus+"]").prop("selected",true);

                $("#update_winfrom").modal('show');

                //$("#up_teamType option:contains('"+select.teamType+"')").prop("selected", true);

                $("#update").bind("click",function(){

                    var gameName=$("#up_gameName").val();
                    var gameTime=$("#up_gameTime").val();
                    var applyEndTime=$("#up_applyEndTime").val();
                    var gameSite=$("#up_gameSite").val();
                    var scoreTeamA=$("#up_scoreTeamA").val();
                    var scoreTeamB=$("#up_scoreTeamB").val();
                    var gameCost=$("#up_gameCost").val();
                    var uniformTeamA = $("#up_uniformTeamA").find("option:selected").val();
                    var uniformTeamB = $("#up_uniformTeamB").find("option:selected").val();

                    $("#vu_gameName").html("");
                    $("#vu_gameTime").html("");
                    $("#vu_applyEndTime").html("");
                    $("#vu_gameSite").html("");
                    $("#vu_scoreTeamAB").html("");
                    $("#vu_gameCost").html("");
                    $("#vu_uniformTeamB").html("");

                    /*if(!gameName.length>0){
                        $("#vu_gameName").html("请输入比赛名称");
                        return;
                    }
*/
                    if(uniformTeamA == uniformTeamB){
                        $("#vu_uniformTeamB").html("两队队服不能相同");
                        return;
                    }

                    if(!gameTime.length>0){
                        $("#vu_gameTime").html("请输入比赛开始时间");
                        return;
                    }

                    if(!applyEndTime.length>0){
                        $("#vu_applyEndTime").html("请输入报名截止时间");
                        return;
                    }

                    if(!gameSite.length>0){
                        $("#vu_gameSite").html("请输入比赛场地");
                        return;
                    }

                    if(!scoreTeamA.length>0){
                        $("#vu_scoreTeamAB").html("请输入主场比分");
                        return;
                    }

                    if(!scoreTeamB.length>0){
                        $("#vu_scoreTeamAB").html("请输入客场比分");
                        return;
                    }

                    /*if(!gameCost.length>0){
                        $("#vu_gameCost").html("请输入费用");
                        return;
                    }*/

                    //开始ajax操作
                    $("#updateForm").ajaxSubmit({
                        type: "POST",//提交类型
                        dataType: "json",//返回结果格式
                        url: "/tournament/upTournamentList",//请求地址
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


        //更新云五数据窗口
        $("#btn_yunwu").bind("click",function(){
            var selectedId = $("#list4").jqGrid("getGridParam", "selrow");
            var select= jQuery("#list4").jqGrid('getRowData', selectedId);
            if (selectedId == null){
                swal("更新","请选择需要编辑的信息！","info");
            }else{
                $.ajax({
                    url:"/gameGrand/upUserCloudData",
                    data:{
                        gameId:select.gameId
                    },
                    success:function(data){
                        if (data["error_code"] == 1500){
                            swal("系统错误!", data["msg"], "error");
                        }else if (data["error_code"] != 1200){
                            swal("操作失败!",data["msg"],"warning");
                        }else{
                            swal("更新","更新成功！");
                        }
                    },error:function(){
                        swal("删除失败","网络异常！");
                    }
                });
            }
        });

    });
    

    //删除窗口
    function deleteInfo(gameId) {
        $("#delete_winfrom").modal('show');

        $("#delete").bind("click",function(){
            $.ajax({
                url:"/tournament/delTournament",
                data:{
                    gameId:gameId
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


<div  id="user_button" >
</div>


<div class="wrapper wrapper-content animated">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox ">
                <div class="ibox-title">
                    <form class="form-inline">

                        <div class="form-group">
                            <label>队服颜色</label>
                            <input type="text" id="s_colour" name="colour" class="form-control"/>
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

                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-5 control-label">比赛名称<span class="validt" id="va_gameName"></span></label>
                        <input id="gameName" name="gameName" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">比赛分类<span class="validt" id="va_classify"></span></label>
                        <select id="classify" name="classify" class="form-control">
                            <option value="1">友谊赛</option>
                            <option value="2">赛事比赛</option>
                            <option value="3">训练</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">赛制<span class="validt" id="va_gameSystem"></span></label>
                        <select id="gameSystem" name="gameSystem" class="form-control">
                            <option value="1">3人</option>
                            <option value="2">5人</option>
                            <option value="3">7人</option>
                            <option value="4">8人</option>
                            <option value="5">9人</option>
                            <option value="6">11人</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">主场队伍<span class="validt" id="va_entryTeamA"></span></label>
                        <select id="entryTeamA" name="entryTeamA" class="form-control">
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">队服颜色<span class="validt" id="va_uniformTeamA"></span></label>
                        <select id="uniformTeamA" name="uniformTeamA" class="form-control">
                            <option value="1">红</option>
                            <option value="2">蓝</option>
                            <option value="3">白</option>
                            <option value="4">紫</option>
                            <option value="5">橙</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">客场队伍<span class="validt" id="va_entryTeamB"></span></label>
                        <select id="entryTeamB" name="entryTeamB" class="form-control">
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">队服颜色<span class="validt" id="va_uniformTeamB"></span></label>
                        <select id="uniformTeamB" name="uniformTeamB" class="form-control">
                            <option value="1">红</option>
                            <option value="2">蓝</option>
                            <option value="3">白</option>
                            <option value="4">紫</option>
                            <option value="5">橙</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">比赛开始时间<span class="validt" id="va_gameTime"></span></label>
                        <input  id="gameTime" name="gameTime" type="text"  class="form-control"  onfocus="WdatePicker({lang:'zh-cn',dateFmt: 'yyyy-MM-dd HH:mm'})" style="width:186px" />
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">报名截止时间<span class="validt" id="va_applyEndTime"></span></label>
                        <input  id="applyEndTime" name="applyEndTime" type="text"  class="form-control"  onfocus="WdatePicker({lang:'zh-cn',dateFmt: 'yyyy-MM-dd HH:mm'})" style="width:186px" />
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">持续时长<span class="validt" id="va_continueTime"></span></label>
                        <select id="continueTime" name="continueTime" class="form-control">
                            <option value="2">2h</option>
                            <option value="2.5">2.5h</option>
                            <option value="3">3h</option>
                            <option value="3.5">3.5h</option>
                            <option value="4">4h</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">比赛场地<span class="validt" id="va_gameSite"></span></label>
                        <textarea id="gameSite" name="gameSite" class="form-control"></textarea>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">比分<span class="validt" id="va_scoreTeamAB"></span></label>
                        主：<input id="scoreTeamA" name="scoreTeamA" style="width:41px;" class="form-control" />
                        客：<input id="scoreTeamB" name="scoreTeamB" style="width:41px;" class="form-control" />
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">费用<span class="validt" id="va_gameCost"></span></label>
                        <input id="gameCost" name="gameCost" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">选购服务<span class="validt" id="va_valueAdded"></span></label>
                        <select id="valueAdded" name="valueAdded" class="form-control">
                            <option value="0">不选购</option>
                            <option value="1">数据</option>
                            <option value="2">视频</option>
                            <option value="3">视频+数据</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">视频是否通知<span class="validt" id="va_informVideo"></span></label>
                        <select id="informVideo" name="informVideo" class="form-control">
                            <option value="1">是</option>
                            <option value="2">否</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">是否启用<span class="validt" id="va_showStatus"></span></label>
                        <select id="showStatus" name="showStatus" class="form-control">
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
                <div class="modal-body">
                    <input type="hidden" id="up_gameId"  name="gameId"/>
                    <div class="form-group">
                        <label class="col-sm-5 control-label">比赛名称<span class="validt" id="vu_gameName"></span></label>
                        <input id="up_gameName" name="gameName" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">比赛分类<span class="validt" id="vu_classify"></span></label>
                        <select id="up_classify" name="classify" class="form-control">
                            <option value="1">友谊赛</option>
                            <option value="2">赛事比赛</option>
                            <option value="3">训练</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">赛制<span class="validt" id="vu_gameSystem"></span></label>
                        <select id="up_gameSystem" name="gameSystem" class="form-control">
                            <option value="1">3人</option>
                            <option value="2">5人</option>
                            <option value="3">7人</option>
                            <option value="4">8人</option>
                            <option value="5">9人</option>
                            <option value="6">11人</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">主场队伍<span class="validt" id="vu_entryTeamA"></span></label>
                        <select id="up_entryTeamA" name="entryTeamA" class="form-control">
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">队服颜色<span class="validt" id="vu_uniformTeamA"></span></label>
                        <select id="up_uniformTeamA" name="uniformTeamA" class="form-control">
                            <option value="1">红</option>
                            <option value="2">蓝</option>
                            <option value="3">白</option>
                            <option value="4">紫</option>
                            <option value="5">橙</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">客场队伍<span class="validt" id="vu_entryTeamB"></span></label>
                        <select id="up_entryTeamB" name="entryTeamB" class="form-control">
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">队服颜色<span class="validt" id="vu_uniformTeamB"></span></label>
                        <select id="up_uniformTeamB" name="uniformTeamB" class="form-control">
                            <option value="1">红</option>
                            <option value="2">蓝</option>
                            <option value="3">白</option>
                            <option value="4">紫</option>
                            <option value="5">橙</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">比赛开始时间<span class="validt" id="vu_gameTime"></span></label>
                        <input  id="up_gameTime" name="gameTime" type="text"  class="form-control"  onfocus="WdatePicker({lang:'zh-cn',dateFmt: 'yyyy-MM-dd HH:mm'})" style="width:186px" />
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">报名截止时间<span class="validt" id="vu_applyEndTime"></span></label>
                        <input  id="up_applyEndTime" name="applyEndTime" type="text"  class="form-control"  onfocus="WdatePicker({lang:'zh-cn',dateFmt: 'yyyy-MM-dd HH:mm'})" style="width:186px" />
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">持续时长<span class="validt" id="vu_continueTime"></span></label>
                        <select id="up_continueTime" name="continueTime" class="form-control">
                            <option value="2">2h</option>
                            <option value="2.5">2.5h</option>
                            <option value="3">3h</option>
                            <option value="3.5">3.5h</option>
                            <option value="4">4h</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">比赛场地<span class="validt" id="vu_gameSite"></span></label>
                        <textarea id="up_gameSite" name="gameSite" class="form-control"></textarea>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">比分<span class="validt" id="vu_scoreTeamAB"></span></label>
                        主：<input id="up_scoreTeamA" name="scoreTeamA" style="width:41px;" class="form-control" />
                        客：<input id="up_scoreTeamB" name="scoreTeamB" style="width:41px;" class="form-control" />
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">费用<span class="validt" id="vu_gameCost"></span></label>
                        <input id="up_gameCost" name="gameCost" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">选购服务<span class="validt" id="vu_valueAdded"></span></label>
                        <select id="up_valueAdded" name="valueAdded" class="form-control">
                            <option value="0">不选购</option>
                            <option value="1">数据</option>
                            <option value="2">视频</option>
                            <option value="3">视频+数据</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">视频是否通知<span class="validt" id="vu_informVideo"></span></label>
                        <select id="up_informVideo" name="informVideo" class="form-control">
                            <option value="1">是</option>
                            <option value="2">否</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">是否启用<span class="validt" id="vu_showStatus"></span></label>
                        <select id="up_showStatus" name="showStatus" class="form-control">
                            <option value="1">是</option>
                            <option value="2">否</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">比分录入状态<span class="validt" id="vu_scoreStatus"></span></label>
                        <select id="up_scoreStatus" name="scoreStatus" class="form-control">
                            <option value="1">已录入</option>
                            <option value="0">未录入</option>
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
                <h2 class="modal-title">确认删除此比赛吗？此操作无法撤消</h2>
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