<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="renderer" content="webkit"/>
    <title> </title>

    <!--[if lt IE 9]>    <meta http-equiv="refresh" content="0;ie.html"/>
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
        $("#a_grandId").val("");

        $.jgrid.defaults.styleUI="Bootstrap";
        $("#list4").jqGrid({
            url:'/gameGrand/selectLie',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            height:$(window).height()-239,
            autowidth:true,//自动宽
            shrinkToFit:false,
            autoScroll:true,
            colModel:[
                {label:"Id",name:'marchId',index:'marchId', width:'15%',align:'center',hidden:true},
                {label:"所属球队",name:'teamName',index:'teamName', width:'35%',align:'center'},
                {label:"球员",name:'nickname',index:'nickname', width:'35%',align:'center'},
                {label:"进球数",name:'type',index:'type', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){
                            return rowObject.result;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"助攻数",name:'type',index:'type', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==6){
                            return rowObject.result;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"红牌数",name:'type',index:'type', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==3){
                            return rowObject.result;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"点球数",name:'type',index:'type', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==2){
                            return rowObject.result;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"黄牌数",name:'type',index:'type', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==4){
                            return rowObject.result;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"乌龙球",name:'type',index:'type', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==5){
                            return rowObject.result;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"射门",name:'type',index:'type', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==7){
                            return rowObject.result;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"射正",name:'type',index:'type', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==8){
                            return rowObject.result;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"过人",name:'type',index:'type', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==9){
                            return rowObject.result;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"威胁传球",name:'type',index:'type', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==10){
                            return rowObject.result;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"任意球",name:'type',index:'type', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==11){
                            return rowObject.result;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"角球",name:'type',index:'type', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==12){
                            return rowObject.result;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"抢断",name:'type',index:'type', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==13){
                            return rowObject.result;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"解围",name:'type',index:'type', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==14){
                            return rowObject.result;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"扑救",name:'type',index:'type', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==15){
                            return rowObject.result;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"犯规",name:'type',index:'type', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==16){
                            return rowObject.result;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"控球率",name:'kongqiulv',index:'kongqiulv', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return cellvalue+"%";
                    }
                },
                {label:"关联球员",name:'glnickname',index:'glnickname', width:'35%',align:'center'},
                {label:"进球数",name:'gltype',index:'gltype', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){
                            return rowObject.glresult;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"助攻数",name:'gltype',index:'gltype', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==6){
                            return rowObject.glresult;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"红牌数",name:'gltype',index:'gltype', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==3){
                            return rowObject.glresult;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"点球数",name:'gltype',index:'gltype', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==2){
                            return rowObject.glresult;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"黄牌数",name:'gltype',index:'gltype', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==4){
                            return rowObject.glresult;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"乌龙球",name:'gltype',index:'gltype', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==5){
                            return rowObject.glresult;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"射门",name:'gltype',index:'gltype', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==7){
                            return rowObject.glresult;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"射正",name:'gltype',index:'gltype', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==8){
                            return rowObject.glresult;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"过人",name:'gltype',index:'gltype', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==9){
                            return rowObject.glresult;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"威胁传球",name:'gltype',index:'gltype', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==10){
                            return rowObject.glresult;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"任意球",name:'gltype',index:'gltype', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==11){
                            return rowObject.glresult;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"角球",name:'gltype',index:'gltype', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==12){
                            return rowObject.glresult;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"抢断",name:'gltype',index:'gltype', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==13){
                            return rowObject.glresult;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"解围",name:'gltype',index:'gltype', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==14){
                            return rowObject.glresult;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"扑救",name:'gltype',index:'gltype', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==15){
                            return rowObject.glresult;
                        }else{
                            return "0";
                        }
                    }
                },
                {label:"犯规",name:'gltype',index:'gltype', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==16){
                            return rowObject.glresult;
                        }else{
                            return "0";
                        }
                    }
                },
                //{label:"比赛次数",name:'type',index:'type', width:'35%',align:'center'}
            ],
            pager:'#gridPager',
            viewrecords: true,//是否在浏览导航栏显示记录总数
            rowNum:10, //每页显示记录数
            rownumbers: true,
            sortname: "nickname",
            sortorder: "desc",
            rowList:[10,30,50],//用于改变显示行数的下拉列表框的元素数组。
            jsonReader:{
                id: "marchId",//设置返回参数中，表格ID的名字为blackId
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

        $("#list2").jqGrid({
            url:'/gameGrand/select',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            height:$(window).height()-239,
            width:$("#divWidth2").width(),
            //autowidth:true,//自动宽
            shrinkToFit:true,
            colModel:[
                {label:"Id",name:'grandId',index:'grandId', width:'15%',align:'center',hidden:true},
                {label:"userId",name:'userId',index:'userId', width:'15%',align:'center',hidden:true},
                {label:"guserId",name:'guserId',index:'guserId', width:'15%',align:'center',hidden:true},
                {label:"teamId",name:'teamId',index:'teamId', width:'15%',align:'center',hidden:true},
                {label:"时间点",name:'time',index:'time', width:'35%',align:'center'},
                {label:"球员",name:'nickname',index:'nickname', width:'35%',align:'center'},
                {label:"所属球队",name:'teamName',index:'teamName', width:'35%',align:'center'},
                {label:"行为",name:'type',index:'type', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){return "进球";}
                        else if(cellvalue==2){return "点球";}
                        else if(cellvalue==3){return "红牌";}
                        else if(cellvalue==4){return "黄牌";}
                        else if(cellvalue==5){return "乌龙球";}
                        else if(cellvalue==6){return "助攻";}
                        else if(cellvalue==7){return "射门";}
                        else if(cellvalue==8){return "射正";}
                        else if(cellvalue==9){return "过人";}
                        else if(cellvalue==10){return "威胁传球";}
                        else if(cellvalue==11){return "任意球";}
                        else if(cellvalue==12){return "角球";}
                        else if(cellvalue==13){return "抢断";}
                        else if(cellvalue==14){return "解围";}
                        else if(cellvalue==15){return "扑救";}
                        else if(cellvalue==16){return "犯规";}
                        else{return ""}
                    }
                },
                {label:"关联球员",name:'guserName',index:'guserName', width:'35%',align:'center'},
                {label:"关联行为",name:'guserType',index:'guserType', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){return "进球";}
                        else if(cellvalue==2){return "点球";}
                        else if(cellvalue==3){return "红牌";}
                        else if(cellvalue==4){return "黄牌";}
                        else if(cellvalue==5){return "乌龙球";}
                        else if(cellvalue==6){return "助攻";}
                        else if(cellvalue==7){return "射门";}
                        else if(cellvalue==8){return "射正";}
                        else if(cellvalue==9){return "过人";}
                        else if(cellvalue==10){return "威胁传球";}
                        else if(cellvalue==11){return "任意球";}
                        else if(cellvalue==12){return "角球";}
                        else if(cellvalue==13){return "抢断";}
                        else if(cellvalue==14){return "解围";}
                        else if(cellvalue==15){return "扑救";}
                        else if(cellvalue==16){return "犯规";}
                        else{return ""}
                    }
                },
                {label:"操作",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        var text1 = "'"+rowObject.grandId+"'"+",'"+rowObject.time+"'"+",'"+rowObject.teamId+"'"+",'"+rowObject.userId+"'"+",'"+rowObject.type+"'"+",'"+rowObject.guserId+"'"+",'"+rowObject.guserType+"'";
                        var text2 = "'"+rowObject.grandId+"'";
                        //var text1 = "'"+rowObject.grandId+"'"+",'"+rowObject.rounds+"'"+",'"+rowObject.scheduleId+"'"+",'"+rowObject.gameId+"'";
                        return '<a href="javascript:updateInfo('+text1+')">编辑</a>   <a href="javascript:deleteInfo('+text2+')">删除</a>';
                    }
                }
            ],
            pager:'#gridPager2',
            viewrecords: true,//是否在浏览导航栏显示记录总数
            rowNum:10, //每页显示记录数
            rownumbers: true,
            sortname: "time",
            sortorder: "desc",
            rowList:[10,30,50],//用于改变显示行数的下拉列表框的元素数组。
            jsonReader:{
                id: "grandId",//设置返回参数中，表格ID的名字为blackId
                root: "list", // json中代表实际模型数据的入口
                page: "page", // json中代表当前页码的数据
                total: "total", // json中代表页码总数的数据
                records: "records", // json中代表数据行总数的数据
                repeatitems : false
            },
            add:false,
            edit:false,
        });
        $("#list2").jqGrid("navGrid","#gridPager2",{edit:false,add:true,addfunc : function(){

            //所属球队
            $.ajax({
                url: "/tournament/getEntryTeamNameAB",
                async : false,
                datatype:"json",
                type:"get",
                success: function (data) {
                    if (data["error_code"] == 1500){
                        swal("系统错误!", data["msg"], "error");
                    }else if (data["error_code"] != 1200){
                        swal("操作失败!",data["msg"],"warning");
                    }else{
                        $("#a_teamId").empty();
                        items = data["data"];

                        $("#a_teamId").append('<option value="'+items.entryTeamAId+'">'+items.entryTeamAName+'</option>');
                        $("#a_teamId").append('<option value="'+items.entryTeamBId+'">'+items.entryTeamBName+'</option>');
                    }
                }
            });

            var teamId = $("#a_teamId").val();
            $.ajax({
                url: "/tournament/getEntryTeamByTeamId",
                async : false,
                datatype:"json",
                data:{"teamId":teamId},
                type:"get",
                success: function (data) {
                    if (data["error_code"] == 1500){
                        swal("系统错误!", data["msg"], "error");
                    }else if (data["error_code"] != 1200){
                        swal("操作失败!",data["msg"],"warning");
                    }else{
                        $("#a_userId").empty();
                        items = data["data"];
                        var selectList = "";
                        $.each(eval(items), function (idx, obj) {
                            selectList += '<option value="'+obj.userId+'">'+obj.nickname+'</option>';
                        });
                        $("#a_userId").append(selectList);

                    }
                }
            });

            //查询全部的队员
            $.ajax({
                url: "/tournament/getEntryTeamAllUser",
                async : false,
                datatype:"json",
                type:"get",
                success: function (data) {
                    if (data["error_code"] == 1500){
                        swal("系统错误!", data["msg"], "error");
                    }else if (data["error_code"] != 1200){
                        swal("操作失败!",data["msg"],"warning");
                    }else{
                        $("#a_gluserId").empty();
                        items = data["data"];
                        var selectList = "";
                        $.each(eval(items), function (idx, obj) {
                            selectList += '<option value="'+obj.userId+'">'+obj.nickname+'</option>';
                        });
                        $("#a_gluserId").append(selectList);

                        $("#u_gluserId").empty();
                        $("#u_gluserId").append(selectList);

                    }
                }
            });

            $("#addTournament_winfrom").modal('show');
            $("#addTournament").unbind("click");

            //添加
            $("#addTournament").bind("click",function(){

                $("#va_time").html("");
                var time = $("#a_time").val();

                if(!time.length>0){
                    $("#va_time").html("请输入时间");
                    return;
                }

                //开始ajax操作
                $("#addTournamentForm").ajaxSubmit({
                    type: "POST",//提交类型
                    dataType: "json",//返回结果格式
                    url: "/gameGrand/addGameGrand2",//请求地址
                    data:$("#addForm").serialize(),
                    success: function (data) {//请求成功后的函数
                        if (data["error_code"] == 1500){
                            swal("系统错误!", data["msg"], "error");
                        }else if (data["error_code"] != 1200){
                            swal("操作失败!",data["msg"],"warning");
                        }else{
                            $("#addTournament_winfrom").modal('hide');
                            swal("添加","添加成功！");
                            $("#list2").trigger("reloadGrid");
                        }
                    },
                    error: function (data) { swal("添加失败","网络异常！","error"); },//请求失败的函数
                    async: false
                });
            });


        },del:false,search:false,},{reloadAfterSubmit:true,});

        $(window).bind("resize",function(){
            var width=$("#divWidth").width();
            var width2=$("#divWidth2").width();
            $("#list4").setGridWidth(width);
            $("#list2").setGridWidth(width2);
        });

        //A队队名称
        $.ajax({
            url: "/tournament/getEntryTeamAName",
            async : false,
            datatype:"json",
            type:"get",
            success: function (data) {
                if (data["error_code"] == 1500){
                    swal("系统错误!", data["msg"], "error");
                }else if (data["error_code"] != 1200){
                    swal("操作失败!",data["msg"],"warning");
                }else{
                    $("#entryTeamA").empty();
                    items = data["data"];
                    $("#lb_entryTeamA").text(items);
                }
            }
        });

        //A队队员
        $.ajax({
            url: "/tournament/getEntryTeamAList",
            async : false,
            datatype:"json",
            type:"get",
            success: function (data) {
                if (data["error_code"] == 1500){
                    swal("系统错误!", data["msg"], "error");
                }else if (data["error_code"] != 1200){
                    swal("操作失败!",data["msg"],"warning");
                }else{
                    $("#entryTeamA").empty();
                    items = data["data"];
                    var selectList = "";
                    $.each(eval(items), function (idx, obj) {
                        if(obj.jerseyNo == undefined){
                            var text = "this,'"+obj.teamId+"'";
                            selectList += '<button type="button" class="btn btn-primary duiyuan" style="width: 82px;margin-top: 10px;margin-left: 10px;" value="'+obj.userId+'" onclick="duiyuanClick('+text+')" >'+obj.nickname+'</button>';
                        }else{
                            var text = "this,'"+obj.teamId+"'";
                            selectList += '<button type="button" class="btn btn-primary duiyuan" style="width: 82px;margin-top: 10px;margin-left: 10px;" value="'+obj.userId+'" onclick="duiyuanClick('+text+')">'+obj.jerseyNo+':'+obj.nickname+'</button>';
                        }
                    });
                    $("#entryTeamA").append(selectList);
                }
            }
        });

        //B队队名称
        $.ajax({
            url: "/tournament/getEntryTeamBName",
            async : false,
            datatype:"json",
            type:"get",
            success: function (data) {
                if (data["error_code"] == 1500){
                    swal("系统错误!", data["msg"], "error");
                }else if (data["error_code"] != 1200){
                    swal("操作失败!",data["msg"],"warning");
                }else{
                    $("#entryTeamB").empty();
                    items = data["data"];
                    $("#lb_entryTeamB").text(items);
                }
            }
        });

        //B队队员
        $.ajax({
            url: "/tournament/getEntryTeamBList",
            async : false,
            datatype:"json",
            type:"get",
            success: function (data) {
                if (data["error_code"] == 1500){
                    swal("系统错误!", data["msg"], "error");
                }else if (data["error_code"] != 1200){
                    swal("操作失败!",data["msg"],"warning");
                }else{
                    $("#entryTeamB").empty();
                    items = data["data"];
                    var selectList = "";
                    $.each(eval(items), function    (idx, obj) {
                        if(obj.jerseyNo == undefined){
                            var text = "this,'"+obj.teamId+"'";
                            selectList += '<button type="button" class="btn btn-primary duiyuan" style="width: 82px;margin-top: 10px;margin-left: 10px;" value="'+obj.userId+'" onclick="duiyuanClick('+text+')">'+obj.nickname+'</button>';
                        }else{
                            var text = "this,'"+obj.teamId+"'";
                            selectList += '<button type="button" class="btn btn-primary duiyuan" style="width: 82px;margin-top: 10px;margin-left: 10px;" value="'+obj.userId+'" onclick="duiyuanClick('+text+')">'+obj.jerseyNo+':'+obj.nickname+'</button>';
                        }
                    });
                    $("#entryTeamB").append(selectList);
                }
            }
        });


        //添加窗口
        $("#btn_add").bind("click",function(){

            $("#txt").val("");
            clearTimeout(t);
            clearTimeout(t1);
            clearTimeout(t2);

            $("#beginControlA").css("display","inline-block");
            $("#endControlA").css("display","none");

            $("#beginControlB").css("display","inline-block");
            $("#endControlB").css("display","none");

            $("#add_winfrom").modal('show');
            $("#add").unbind("click");

            $("#add").bind("click",function(){
                var txt = $("#txt").val();//时间
                var result = Math.round((c1/(c1+c2))*100);

                if (isNaN(result)) {
                    result = 50;
                }
                var extra = $("#extra").val();//是否是加时赛 1是 2否
                var type = 17;
                var teamId = "";

                var result2 = Math.round((c2/(c1+c2))*100);
                if (isNaN(result2)) {
                    result2 = 50;
                }

                $.ajax({
                    url: "/gameGrand/addGameGrandKongqiu",
                    async : false,
                    datatype:"json",
                    type:"post",
                    data:{
                        "time":txt,
                        "result":result,
                        "result2":result2,
                        "extra":extra,
                        "teamId":teamId,
                        "type":type
                    },
                    success: function (data) {
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
                    error: function (data) { swal("添加失败","网络异常！","error"); }//请求失败的函数
                });

            });


        });


    });


    function updateInfo(grandId,time,teamIdInfo,userId,type,gluserId,gltype){
        $("#update").unbind("click");

        $.ajax({
            url: "/tournament/getEntryTeamNameAB",
            async : false,
            datatype:"json",
            type:"get",
            success: function (data) {
                if (data["error_code"] == 1500){
                    swal("系统错误!", data["msg"], "error");
                }else if (data["error_code"] != 1200){
                    swal("操作失败!",data["msg"],"warning");
                }else{
                    $("#u_teamId").empty();
                    items = data["data"];

                    $("#u_teamId").append('<option value="'+items.entryTeamAId+'">'+items.entryTeamAName+'</option>');
                    $("#u_teamId").append('<option value="'+items.entryTeamBId+'">'+items.entryTeamBName+'</option>');
                }
            }
        });

        var teamId = $("#u_teamId").val();
        $.ajax({
            url: "/tournament/getEntryTeamByTeamId",
            async : false,
            datatype:"json",
            data:{"teamId":teamId},
            type:"get",
            success: function (data) {
                if (data["error_code"] == 1500){
                    swal("系统错误!", data["msg"], "error");
                }else if (data["error_code"] != 1200){
                    swal("操作失败!",data["msg"],"warning");
                }else{
                    $("#u_userId").empty();
                    items = data["data"];
                    var selectList = "";
                    $.each(eval(items), function (idx, obj) {
                        selectList += '<option value="'+obj.userId+'">'+obj.nickname+'</option>';
                    });
                    $("#u_userId").append(selectList);

                }
            }
        });

        //查询全部的队员
        $.ajax({
            url: "/tournament/getEntryTeamAllUser",
            async : false,
            datatype:"json",
            type:"get",
            success: function (data) {
                if (data["error_code"] == 1500){
                    swal("系统错误!", data["msg"], "error");
                }else if (data["error_code"] != 1200){
                    swal("操作失败!",data["msg"],"warning");
                }else{
                    $("#u_gluserId").empty();
                    items = data["data"];
                    var selectList = "";
                    $.each(eval(items), function (idx, obj) {
                        selectList += '<option value="'+obj.userId+'">'+obj.nickname+'</option>';
                    });
                    $("#u_gluserId").append(selectList);
                }
            }
        });

        $("#up_grandId").val(grandId);
        $("#u_time").val(time);

        $("#u_teamId").find("option[value="+teamIdInfo+"]").prop("selected",true);
        $("#u_userId").find("option[value="+userId+"]").prop("selected",true);
        $("#u_type option:contains('"+type+"')").prop("selected", true);
        $("#u_gluserId option:contains('"+gluserId+"')").prop("selected", true);
        $("#u_gltype option:contains('"+gltype+"')").prop("selected", true);

        //$("#u_teamId option:contains('"+select.classify+"')").prop("selected", true);

        $("#update_winfrom").modal('show');
        $("#updateTournament").bind("click",function(){
            var time=$("#u_time").val();

             $("#vp_time").html("");

             if(!time.length>0){
             $("#vp_time").html("请输入时间点");
             return;
             }

             //开始ajax操作
             $("#updateForm").ajaxSubmit({
                 type: "POST",//提交类型
                 dataType: "json",//返回结果格式
                 url: "/gameGrand/upGameGrand",//请求地址
                 data:$("#updateForm").serialize(),
                 success: function (data) {//请求成功后的函数
                     if (data["error_code"] == 1500){
                        swal("系统错误!", data["msg"], "error");
                     }else if (data["error_code"] != 1200){
                        swal("操作失败!",data["msg"],"warning");
                     }else{
                         $("#update_winfrom").modal('hide');
                         swal("修改","修改成功！");
                         $("#list2").trigger("reloadGrid");
                     }
                 },
                 error: function (data) { swal("添加失败","网络异常！","error"); },//请求失败的函数
                 async: false
             });

        });
    }

    var c=0
    var t
    var c1=0
    var t1
    var c2=0
    var t2
    function timedCount()
    {
        var cm = Math.floor(c/60)+":"+(c-Math.floor(c/60)*60);
        document.getElementById('txt').value=cm;
        c=c+1
        t=setTimeout("timedCount()",1000)
    }

    function stopTimer()
    {
        //计时器暂停（清除定时器）
        clearInterval(t); //计时器暂停
    }


    function stopCount()
    {
        clearInterval(t); //计时器暂停
        //c=0;
        //setTimeout("document.getElementById('txt').value=0",0);
        //clearTimeout(t);
        //去掉加时赛的disabled
        $("#start").attr("disabled",true);
        $("#break").attr("disabled",true);
        $("#end").attr("disabled",true);
        $("#extraStart").removeAttr("disabled");
        $("#extraEnd").removeAttr("disabled");
        $("#extra").val(1);
    }


    function xingweixuanze(obj){
        $("#type").val($(obj).val());

        $(".xingwei").css("background-color","#1ab394");
        $(".xingwei").css("border-color","#1ab394");

        $(obj).css("background-color","#1AB357");
        $(obj).css("border-color","#1AB357");
    }

    function duiyuanClick(obj,teamId){
        $("#userId").val($(obj).val());
        $("#teamId").val(teamId);

        $(".duiyuan").css("background-color","#1ab394");
        $(".duiyuan").css("border-color","#1ab394");

        $(obj).css("background-color","#1AB357");
        $(obj).css("border-color","#1AB357");
    }


    function luru(){
        var txt = $("#txt").val();//时间
        var type = $("#type").val();//行为
        var userId = $("#userId").val();//用户id
        var teamId = $("#teamId").val();//球队id
        var result = 1;
        var extra = $("#extra").val();//是否是加时赛 1是 2否

        if(txt==undefined || txt=="" || txt==null){
            swal("消息", "时间不能为空");
            return;
        }

        if(userId==undefined || userId=="" || userId==null){
            swal("消息", "请选择用户");
            return;
        }

        if(type==undefined || type=="" || type==null){
            swal("消息", "请选择行为");
            return;
        }

        $.ajax({
            url: "/gameGrand/addGameGrand",
            async : false,
            datatype:"json",
            type:"post",
            data:{
                "time":txt,
                "type":type,
                "userId":userId,
                "teamId":teamId,
                "result":result,
                "extra":extra
            },
            success: function (data) {
                if (data["error_code"] == 1500){
                    swal("系统错误!", data["msg"], "error");
                }else if (data["error_code"] != 1200){
                    swal("操作失败!",data["msg"],"warning");
                }else{
                    $("#a_grandId").val(data["data"]);

                    swal("添加","添加成功！");
                    $(".duiyuan").css("background-color","#1ab394");
                    $(".duiyuan").css("border-color","#1ab394");

                    $(".xingwei").css("background-color","#1ab394");
                    $(".xingwei").css("border-color","#1ab394");

                    $("#list2").trigger("reloadGrid");
                }
            }
        });

    }

    function guanlian(){

        var selectedId = $("#list2").jqGrid("getGridParam", "selrow");
        var select= jQuery("#list2").jqGrid('getRowData', selectedId);
        if (selectedId == null){
            swal("修改","请选择需要关联的数据！","info");
            return ;
        }

        var type = $("#type").val();//行为
        var userId = $("#userId").val();//用户id
        var teamId = $("#teamId").val();//球队id
        var result = 1;
        var extra = $("#extra").val();//是否是加时赛 1是 2否
        var grandId = select.grandId;

        if(userId==undefined || userId=="" || userId==null){
            swal("消息", "请选择用户");
            return;
        }

        if(type==undefined || type=="" || type==null){
            swal("消息", "请选择行为");
            return;
        }

        $.ajax({
            url: "/gameGrand/addGameGrand",
            async : false,
            datatype:"json",
            type:"post",
            data:{
                "type":type,
                "userId":userId,
                "teamId":teamId,
                "result":result,
                "extra":extra,
                "parentId":select.grandId
            },
            success: function (data) {
                if (data["error_code"] == 1500){
                    swal("系统错误!", data["msg"], "error");
                }else if (data["error_code"] != 1200){
                    swal("操作失败!",data["msg"],"warning");
                }else{

                    swal("关联","关联成功！");
                    $(".duiyuan").css("background-color","#1ab394");
                    $(".duiyuan").css("border-color","#1ab394");

                    $(".xingwei").css("background-color","#1ab394");
                    $(".xingwei").css("border-color","#1ab394");

                    $("#list2").trigger("reloadGrid");
                }
            }
        });


    }

    //确定
    function queding(){
        clearTimeout(t);//清除第一个定时器
        clearTimeout(t1);//清除球队A定时器
        clearTimeout(t2);//清除球队B定时器
    }

    //A队开始控球
    function timedCountA(){
        c1=c1+1;
        t1=setTimeout("timedCountA()",1000);
        $("#beginControlA").css("display","none");
        $("#endControlA").css("display","inline-block");
    }

    //B队开始控球
    function timedCountB(){
        c2=c2+1;
        t2=setTimeout("timedCountB()",1000);
        $("#beginControlB").css("display","none");
        $("#endControlB").css("display","inline-block");
    }

    //A队暂停控球
    function stopTimerA()
    {
        //计时器暂停（清除定时器）
        clearInterval(t1); //计时器暂停
        $("#beginControlA").css("display","inline-block");
        $("#endControlA").css("display","none");
    }

    //B队暂停控球
    function stopTimerB()
    {
        //计时器暂停（清除定时器）
        clearInterval(t2); //计时器暂停
        $("#beginControlB").css("display","inline-block");
        $("#endControlB").css("display","none");
    }

    function qiuyuan(){
        var teamId = $("#a_teamId").val();
        //A队队员
        $.ajax({
            url: "/tournament/getEntryTeamByTeamId",
            async : false,
            datatype:"json",
            data:{"teamId":teamId},
            type:"get",
            success: function (data) {
                if (data["error_code"] == 1500){
                    swal("系统错误!", data["msg"], "error");
                }else if (data["error_code"] != 1200){
                    swal("操作失败!",data["msg"],"warning");
                }else{
                    $("#a_userId").empty();
                    items = data["data"];
                    var selectList = "";
                    $.each(eval(items), function (idx, obj) {
                        selectList += '<option value="'+obj.userId+'">'+obj.nickname+'</option>';
                    });
                    $("#a_userId").append(selectList);

                }
            }
        });
    }

    function qiuyuan2(){
        var teamId = $("#u_teamId").val();
        //A队队员
        $.ajax({
            url: "/tournament/getEntryTeamByTeamId",
            async : false,
            datatype:"json",
            data:{"teamId":teamId},
            type:"get",
            success: function (data) {
                if (data["error_code"] == 1500){
                    swal("系统错误!", data["msg"], "error");
                }else if (data["error_code"] != 1200){
                    swal("操作失败!",data["msg"],"warning");
                }else{
                    $("#u_userId").empty();
                    items = data["data"];
                    var selectList = "";
                    $.each(eval(items), function (idx, obj) {
                        selectList += '<option value="'+obj.userId+'">'+obj.nickname+'</option>';
                    });
                    $("#u_userId").append(selectList);

                }
            }
        });
    }

    //删除轮次窗口
    function deleteInfo(grandId) {
        $.ajax({
            url:"/gameGrand/delGameGrand",
            data:{
                grandId:grandId
            },
            success:function(data){
                if (data["error_code"] == 1500){
                    swal("系统错误!", data["msg"], "error");
                }else if (data["error_code"] != 1200){
                    swal("操作失败!",data["msg"],"warning");
                }else{
                    if (data["error_code"] == 1500) {
                        swal("系统错误!", data["msg"], "error");
                    } else if (data["error_code"] != 1200) {
                        swal("操作失败!", data["msg"], "warning");
                    } else {
                        $("#delete_winfrom").modal('hide');
                        /*swal("删除","删除成功！");*/
                        $("#list2").trigger("reloadGrid");
                    }
                }
            },error:function(){
                swal("删除失败","网络异常！");
            }
        });
    }


</script>



<div>
    <div class="ibox-title">
        <a data-toggle="modal" style="margin-right: 10px;" class="btn btn-primary" id="btn_add">录入</a>
    </div>
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
        <div class="modal-dialog" style="width: 72%;">
            <div class="modal-content">

                <div class="modal-body">

                    <div class="form-group">
                        <input class="form-control"  id="txt"  style="width: 60px;" />
                        <input type="hidden" id="type" />
                        <input type="hidden" id="userId" />
                        <input type="hidden" id="teamId" />
                        <input type="hidden" id="extra" value="2" />
                        <input type="hidden" id="a_grandId"  />
                        <button type="button" class="btn btn-primary" onClick="timedCount()" id="start">开始比赛</button>
                        <button type="button" class="btn btn-primary" onclick="stopTimer()" id="break">暂停比赛</button>
                        <button type="button" class="btn btn-primary" onClick="stopCount()" id="end">结束比赛</button>
                        <button type="button" class="btn btn-primary" style="margin-left: 111px;" disabled="disabled" onClick="timedCount()" id="extraStart">加时赛开始</button>
                        <button type="button" class="btn btn-primary" disabled="disabled" onclick="stopTimer()" id="extraEnd">加时赛结束</button>
                    </div>

                    <div class="form-group">
                        <div class="info" style=" float:  left">
                            <label id="lb_entryTeamA"></label>&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-primary" id="beginControlA" onclick="timedCountA()">开始控球</button><button  style="display: none" type="button" class="btn btn-primary" id="endControlA" onclick="stopTimerA()">暂停控球</button>
                            <div style="border: 1px  #e7eaec solid;width: 310px;min-height: 150px;margin-top: 3px;" id="entryTeamA">

                            </div>
                        </div>
                        <div class="info" style=" float:  left;padding-left: 120px;">
                            <label id="lb_entryTeamB"></label>&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-primary" id="beginControlB" onclick="timedCountB()">开始控球</button><button  style="display: none" type="button" class="btn btn-primary" id="endControlB" onclick="stopTimerB()">暂停控球</button>
                            <div style="border: 1px  #e7eaec solid;width: 310px;min-height: 150px;margin-top: 3px;" id="entryTeamB">

                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="info2" style=" float:  left">
                            <button type="button" class="btn btn-primary xingwei" style="width: 82px;margin-top: 10px;" value="1" onclick="xingweixuanze(this)">进球</button>
                            <button type="button" class="btn btn-primary xingwei" style="width: 82px;margin-top: 10px;" value="2" onclick="xingweixuanze(this)">点球</button>
                            <button type="button" class="btn btn-primary xingwei" style="width: 82px;margin-top: 10px;" value="3" onclick="xingweixuanze(this)">红牌</button>
                            <button type="button" class="btn btn-primary xingwei" style="width: 82px;margin-top: 10px;" value="4" onclick="xingweixuanze(this)">黄牌</button>
                            <button type="button" class="btn btn-primary xingwei" style="width: 82px;margin-top: 10px;" value="5" onclick="xingweixuanze(this)">乌龙球</button>
                            <button type="button" class="btn btn-primary xingwei" style="width: 82px;margin-top: 10px;" value="6" onclick="xingweixuanze(this)">助攻</button>
                            <button type="button" class="btn btn-primary xingwei" style="width: 82px;margin-top: 10px;" value="7" onclick="xingweixuanze(this)">射门</button>
                            <button type="button" class="btn btn-primary xingwei" style="width: 82px;margin-top: 10px;" value="8" onclick="xingweixuanze(this)">射正</button>
                            <br/>
                            <button type="button" class="btn btn-primary xingwei" style="width: 82px;margin-top: 10px;" value="9" onclick="xingweixuanze(this)">过人</button>
                            <button type="button" class="btn btn-primary xingwei" style="width: 82px;margin-top: 10px;" value="10" onclick="xingweixuanze(this)">威胁传球</button>
                            <button type="button" class="btn btn-primary xingwei" style="width: 82px;margin-top: 10px;" value="11" onclick="xingweixuanze(this)">任意球</button>
                            <button type="button" class="btn btn-primary xingwei" style="width: 82px;margin-top: 10px;" value="12" onclick="xingweixuanze(this)">角球</button>
                            <button type="button" class="btn btn-primary xingwei" style="width: 82px;margin-top: 10px;" value="13" onclick="xingweixuanze(this)">抢断</button>
                            <button type="button" class="btn btn-primary xingwei" style="width: 82px;margin-top: 10px;" value="14" onclick="xingweixuanze(this)">解围</button>
                            <button type="button" class="btn btn-primary xingwei" style="width: 82px;margin-top: 10px;" value="15" onclick="xingweixuanze(this)">扑救</button>
                            <button type="button" class="btn btn-primary xingwei" style="width: 82px;margin-top: 10px;" value="16" onclick="xingweixuanze(this)">犯规</button>
                        </div>
                        <div class="info2" style=" float:  left;width: 34px;">
                            <button type="button" class="btn btn-primary" style="margin-left: 31px;margin-top: 10px;" onclick="luru()">录入</button>
                            <button type="button" class="btn btn-primary" style="margin-left: 31px;margin-top: 10px;" onclick="guanlian()">关联</button>
                        </div>
                    </div>

                    <div class="ibox-content">
                        <div class="jqGrid_wrapper" id="divWidth2" style="width:720px;">
                            <table id="list2"></table>
                            <div id="gridPager2"></div>
                        </div>
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

<form id="addTournamentForm">
    <div id="addTournament_winfrom" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-5 control-label">时间点<span class="validt" id="va_time"></span></label>
                        <input class="form-control" name = "time" id="a_time"/>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-5 control-label">所属球队</label>
                        <select name="teamId" class="form-control" id="a_teamId" onchange="qiuyuan()">

                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-5 control-label">球员</label>
                        <select name="userId" class="form-control" id="a_userId">

                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-5 control-label">行为</label>
                        <select class="form-control" name="type">
                            <option value="1">进球</option>
                            <option value="2">点球</option>
                            <option value="3">红牌</option>
                            <option value="4">黄牌</option>
                            <option value="5">乌龙球</option>
                            <option value="6">助攻</option>
                            <option value="7">射门</option>
                            <option value="8">射正</option>
                            <option value="9">过人</option>
                            <option value="10">威胁传球</option>
                            <option value="11">任意球</option>
                            <option value="12">角球</option>
                            <option value="13">抢断</option>
                            <option value="14">解围</option>
                            <option value="15">扑救</option>
                            <option value="16">犯规</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-5 control-label">关联球员</label>
                        <select name="gluserId" class="form-control" id="a_gluserId">

                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-5 control-label">关联行为</label>
                        <select class="form-control" name="gltype">
                            <option value="1">进球</option>
                            <option value="2">点球</option>
                            <option value="3">红牌</option>
                            <option value="4">黄牌</option>
                            <option value="5">乌龙球</option>
                            <option value="6">助攻</option>
                            <option value="7">射门</option>
                            <option value="8">射正</option>
                            <option value="9">过人</option>
                            <option value="10">威胁传球</option>
                            <option value="11">任意球</option>
                            <option value="12">角球</option>
                            <option value="13">抢断</option>
                            <option value="14">解围</option>
                            <option value="15">扑救</option>
                            <option value="16">犯规</option>
                        </select>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="addTournament">添加</button>
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
                    <input id="up_grandId" name="grandId" type="hidden"/>
                    <div class="form-group">
                        <label class="col-sm-5 control-label">时间点<span class="validt" id="up_time"></span></label>
                        <input class="form-control" name = "time" id="u_time"/>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-5 control-label">所属球队</label>
                        <select name="teamId" class="form-control" id="u_teamId" onchange="qiuyuan2()">

                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-5 control-label">球员</label>
                        <select name="userId" class="form-control" id="u_userId">

                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-5 control-label">行为</label>
                        <select class="form-control" name="type">
                            <option value="1">进球</option>
                            <option value="2">点球</option>
                            <option value="3">红牌</option>
                            <option value="4">黄牌</option>
                            <option value="5">乌龙球</option>
                            <option value="6">助攻</option>
                            <option value="7">射门</option>
                            <option value="8">射正</option>
                            <option value="9">过人</option>
                            <option value="10">威胁传球</option>
                            <option value="11">任意球</option>
                            <option value="12">角球</option>
                            <option value="13">抢断</option>
                            <option value="14">解围</option>
                            <option value="15">扑救</option>
                            <option value="16">犯规</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-5 control-label">关联球员</label>
                        <select name="gluserId" class="form-control" id="u_gluserId">

                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-5 control-label">关联行为</label>
                        <select class="form-control" name="gltype">
                            <option value="1">进球</option>
                            <option value="2">点球</option>
                            <option value="3">红牌</option>
                            <option value="4">黄牌</option>
                            <option value="5">乌龙球</option>
                            <option value="6">助攻</option>
                            <option value="7">射门</option>
                            <option value="8">射正</option>
                            <option value="9">过人</option>
                            <option value="10">威胁传球</option>
                            <option value="11">任意球</option>
                            <option value="12">角球</option>
                            <option value="13">抢断</option>
                            <option value="14">解围</option>
                            <option value="15">扑救</option>
                            <option value="16">犯规</option>
                        </select>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="updateTournament">修改</button>
                </div>
            </div>
        </div>
    </div>
</form>

</body>
</html>