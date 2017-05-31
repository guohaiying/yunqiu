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
    $(document).ready(function(){
        $.jgrid.defaults.styleUI="Bootstrap";
        $("#list4").jqGrid({
            url:'/video/select',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            postData:{'videoType':1,'classify':0},
            height:$(window).height()-239,
            autowidth:true,//自动宽
            shrinkToFit:true,
            colModel:[
                {label:"视频Id",name:'videoId',index:'videoId', width:'15%',align:'center',hidden: true},
                {label:"比赛Id",name:'gameId',index:'gameId', width:'15%',align:'center',hidden: true},
                {label:"赛程Id",name:'leagueId',index:'leagueId', width:'15%',align:'center',hidden: true},
                {label:"视频Id",name:'vid',index:'vid', width:'15%',align:'center',hidden: true},
                {label:"简介",name:'brief',index:'brief', width:'15%',align:'center',hidden: true},
                {label:"分类",name:'classify',index:'classify', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){
                            return "赛事视频";
                        }else if(cellvalue==2){
                            return "比赛视频";
                        }else if(cellvalue==3){
                            return "发现视频";
                        }else{
                            return "";
                        }
                    }
                },
                {label:"视频标签",name:'videoTag',index:'videoTag', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){
                            return "开幕式视频";
                        }else if(cellvalue==2){
                            return "闭幕式视频";
                        }else if(cellvalue==3){
                            return "五佳球视频 ";
                        }else if(cellvalue==4){
                            return "十佳球视频";
                        }else if(cellvalue==5){
                            return "整场视频";
                        }else if(cellvalue==6){
                            return "集锦视频";
                        }else if(cellvalue==7){
                            return "比赛精彩片段";
                        }else if(cellvalue==8){
                            return "精彩视频集锦";
                        }else if(cellvalue==9){
                            return "足球教学片";
                        }else if(cellvalue==10){
                            return "职业球星视频";
                        }else{
                            return "";
                        }
                    }
                },
                {label:"创建时间",name:'createTime',index:'createTime', width:'35%',align:'center'},
                {label:"创建人",name:'userName',index:'userName', width:'35%',align:'center'},
                {label:"视频名称",name:'videoName',index:'videoName', width:'35%',align:'center'},
                {label:"视频地址",name:'videoAddressHigh',index:'videoAddressHigh', width:'35%',align:'center'},
                {label:"视频地址",name:'videoAddressOrdinary',index:'videoAddressOrdinary', width:'35%',align:'center',hidden: true},
                {label:"视频地址",name:'videoAddressSmooth',index:'videoAddressSmooth', width:'35%',align:'center',hidden: true},
                {label:"视频截图",name:'screenshots',index:'screenshots', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return '<img style="width: 88px;height: 81px;" src="'+cellvalue+'"/>';
                    }
                },
                {label:"视频播放时长(s)",name:'duration',index:'duration', width:'35%',align:'center'},
                {label:"关联赛事",name:'leagueName',index:'leagueName', width:'35%',align:'center'},
                {label:"关联比赛",name:'gameName',index:'gameName', width:'35%',align:'center'},
                {label:"是否显示",name:'ifShow',index:'ifShow', width:'35%',align:'center',
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
            sortname: "createTime",
            sortorder: "desc",
            rowList:[10,30,50],//用于改变显示行数的下拉列表框的元素数组。
            jsonReader:{
                id: "videoId",//设置返回参数中，表格ID的名字为blackId
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

        //查询条件传值
        $("#serc").click(function(){
            var userName=$("#s_userName").val();
            var classify = $("#s_classify").find("option:selected").val();
            var ifShow = $("#s_ifShow").find("option:selected").val();
            var videoTag = "";
            $("#s_videoTag option:selected").each(function(){
                //alert($(this).val()); //这里得到的就是
                if($(this).val()!=0){
                    videoTag += $(this).val()+",";
                }
            })

            $("#list4").jqGrid('setGridParam',{
                datatype:'json',
                url:'/video/select',
                page:1,
                postData:{
                    'userName':userName,
                    'classify':classify,
                    'videoTag':videoTag,
                    'ifShow':ifShow
                }
            }).trigger("reloadGrid"); //重新载入
        });


        //添加窗口
        $("#btn_add").bind("click",function(){
            $("#add_winfrom").modal('show');
            $("#add").unbind("click");
            $("#add").bind("click",function(){

                var classify = $("#a_classify").find("option:selected").val();//视频类型
                var videoName=$("#a_videoName").val();//视频名称
                var videoAddressHigh=$("#a_videoAddressHigh").val();//高清地址
                var videoAddressOrdinary=$("#a_videoAddressOrdinary").val();//普通地址
                var videoAddressSmooth=$("#a_videoAddressSmooth").val();//流畅地址
                var vid=$("#a_vid").val();//视频ID
                var ifShow = $("#a_ifShow").find("option:selected").val();//是否显示

                $("#va_classify").html("");
                $("#va_videoName").html("");
                $("#va_va_videoHOS").html("");
                $("#va_ifShow").html("");
                $("#va_vid").html("");

                if(!classify.length>0){
                    $("#va_classify").html("请选择视频类型");
                    return;
                }
                if(!videoName.length>0){
                    $("#va_videoName").html("请输入视频名称");
                    return;
                }
                if(!videoAddressHigh.length>0){
                    $("#va_videoHOS").html("请输入高清地址");
                    return;
                }
                if(!checkUrl(videoAddressHigh)){
                    $("#va_videoHOS").html("请输入有效高清地址");
                    return;
                }
                if(!videoAddressOrdinary.length>0){
                    $("#va_videoHOS").html("请输入普通地址");
                    return;
                }
                if(!checkUrl(videoAddressOrdinary)){
                    $("#va_videoHOS").html("请输入有效普通地址");
                    return;
                }
                if(!videoAddressSmooth.length>0){
                    $("#va_videoHOS").html("请输入流畅地址");
                    return;
                }
                if(!checkUrl(videoAddressSmooth)){
                    $("#va_videoHOS").html("请输入有效流畅地址");
                    return;
                }
                if(!vid.length>0){
                    $("#va_vid").html("请输入视频id");
                    return;
                }
                if(!ifShow.length>0){
                    $("#va_ifShow").html("请选择是否显示");
                    return;
                }

                //开始ajax操作
                $("#addForm").ajaxSubmit({
                    type: "POST",//提交类型
                    dataType: "json",//返回结果格式
                    url: "/video/addVideo",//请求地址
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

                if(select.classify=='赛事视频'){
                    $("#up_videoTag").empty();
                    $("#up_leagueId").empty();
                    $("#up_gameId").empty();
                    $("#up_videoTag").append('<option value="1">开幕式视频</option>');
                    $("#up_videoTag").append('<option value="2">闭幕式视频</option>');
                    $("#up_videoTag").append('<option value="3">五佳球视频</option>');
                    $("#up_videoTag").append('<option value="4">十佳球视频</option>');
                    //获取所有赛事
                    leagueList1();
                }else if(select.classify=='比赛视频'){
                    $("#up_videoTag").empty();
                    $("#up_leagueId").empty();
                    $("#up_gameId").empty();
                    $("#up_videoTag").append('<option value="5">整场视频</option>');
                    $("#up_videoTag").append('<option value="6">集锦视频</option>');
                    $("#up_videoTag").append('<option value="7">比赛精彩片段</option>');
                    //获取所有比赛
                    tournamentList1();
                }else if(select.classify=='发现视频'){//发现
                    $("#up_videoTag").empty();
                    $("#up_leagueId").empty();
                    $("#up_gameId").empty();
                    $("#up_videoTag").append('<option value="8">精彩视频集锦</option>');
                    $("#up_videoTag").append('<option value="9">足球教学片</option>');
                    $("#up_videoTag").append('<option value="10">职业球星视频</option>');
                }else{//GIF
                    $("#up_videoTag").empty();
                    $("#up_leagueId").empty();
                    $("#up_gameId").empty();
                }

                $("#up_videoId").val(select.videoId);
                $("#up_videoName").val(select.videoName);
                $("#up_videoAddressHigh").val(select.videoAddressHigh);
                $("#up_videoAddressOrdinary").val(select.videoAddressOrdinary);
                $("#up_videoAddressSmooth").val(select.videoAddressSmooth);
                $("#up_classify option:contains('"+select.classify+"')").prop("selected", true);
                $("#up_videoTag option:contains('"+select.videoTag+"')").prop("selected", true);
                if(select.leagueId!=""){
                    $("#up_leagueId").find("option[value="+select.leagueId+"]").prop("selected",true);
                }
                if(select.gameId!=""){
                    $("#up_gameId").find("option[value="+select.gameId+"]").prop("selected",true);
                }
                $("#up_ifShow option:contains('"+select.ifShow+"')").prop("selected", true);
                $("#up_vid").val(select.vid);
                $("#up_brief").html(select.brief);

                $("#update_winfrom").modal('show');

                $("#update").bind("click",function(){

                    var classify = $("#up_classify").find("option:selected").val();//视频类型
                    var videoName=$("#up_videoName").val();//视频名称
                    var videoAddressHigh=$("#up_videoAddressHigh").val();//高清地址
                    var videoAddressOrdinary=$("#up_videoAddressOrdinary").val();//普通地址
                    var videoAddressSmooth=$("#up_videoAddressSmooth").val();//流畅地址
                    var vid=$("#up_vid").val();//视频ID
                    var ifShow = $("#up_ifShow").find("option:selected").val();//是否显示

                    $("#vu_classify").html("");
                    $("#vu_videoName").html("");
                    $("#vu_videoHOS").html("");
                    $("#vu_ifShow").html("");
                    $("#vu_vid").html("");

                    if(!classify.length>0){
                        $("#vu_classify").html("请选择视频类型");
                        return;
                    }
                    if(!videoName.length>0){
                        $("#vu_videoName").html("请输入视频名称");
                        return;
                    }
                    if(!videoAddressHigh.length>0){
                        $("#vu_videoHOS").html("请输入高清地址");
                        return;
                    }
                    if(!checkUrl(videoAddressHigh)){
                        $("#vu_videoHOS").html("请输入有效高清地址");
                        return;
                    }
                    if(!videoAddressOrdinary.length>0){
                        $("#vu_videoHOS").html("请输入普通地址");
                        return;
                    }
                    if(!checkUrl(videoAddressOrdinary)){
                        $("#vu_videoHOS").html("请输入有效普通地址");
                        return;
                    }
                    if(!videoAddressSmooth.length>0){
                        $("#vu_videoHOS").html("请输入流畅地址");
                        return;
                    }
                    if(!checkUrl(videoAddressSmooth)){
                        $("#vu_videoHOS").html("请输入有效流畅地址");
                        return;
                    }

                    if(!vid.length>0){
                        $("#vu_vid").html("请输入视频id");
                        return;
                    }
                    if(!ifShow.length>0){
                        $("#vu_ifShow").html("请选择是否显示");
                        return;
                    }

                    //开始ajax操作
                    $("#updateForm").ajaxSubmit({
                        type: "POST",//提交类型
                        dataType: "json",//返回结果格式
                        url: "/video/upVideo",//请求地址
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
                swal("用户","请选择需要删除的信息！","info");
            }else{
                $("#delete_winfrom").modal('show');
                $("#delete").bind("click",function(){
                    var username = $("#username").val();
                    var password = $("#password").val();
                    $.ajax({
                        url:"/video/delVideo",
                        data:{
                            videoId:select.videoId,
                            username:username,
                            password:password
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

        leagueList();//默认调用
        leagueList1();
        //获取所有赛事
        function leagueList(){
            $.ajax({
                url: "/league/getLeagueList",
                async : false,
                datatype:"json",
                type:"get",
                success: function (data) {
                    $("#a_leagueId").empty();
                    items = data["data"];
                    var selectList = "";
                    $.each(eval(items), function (idx, obj) {
                        selectList += '<option value="'+obj.leagueId+'" >'+obj.leagueName+'</option>';
                    });
                    $("#a_leagueId").append(selectList);
                }
            });
        }

        //获取所有赛事
        function leagueList1(){
            $.ajax({
                url: "/league/getLeagueList",
                async : false,
                datatype:"json",
                type:"get",
                success: function (data) {
                    $("#up_leagueId").empty();
                    items = data["data"];
                    var selectList = "";
                    $.each(eval(items), function (idx, obj) {
                        selectList += '<option value="'+obj.leagueId+'" >'+obj.leagueName+'</option>';
                    });
                    $("#up_leagueId").append(selectList);
                }
            });
        }

        //获取所有比赛
        function tournamentList(){
            $.ajax({
                url: "/tournament/getTournamentList",
                async : false,
                datatype:"json",
                type:"get",
                success: function (data) {
                    $("#a_gameId").empty();
                    items = data["data"];
                    var selectList = "";
                    $.each(eval(items), function (idx, obj) {
                        selectList += '<option value="'+obj.gameId+'" >'+obj.gameName+'</option>';
                    });
                    $("#a_gameId").append(selectList);
                }
            });
        }


        //获取所有比赛
        function tournamentList1(){
            $.ajax({
                url: "/tournament/getTournamentList",
                async : false,
                datatype:"json",
                type:"get",
                success: function (data) {
                    $("#up_gameId").empty();
                    items = data["data"];
                    var selectList = "";
                    $.each(eval(items), function (idx, obj) {
                        selectList += '<option value="'+obj.gameId+'" >'+obj.gameName+'</option>';
                    });
                    $("#up_gameId").append(selectList);
                }
            });
        }


        $("#a_classify").change(function(){
            var classify = $("#a_classify") .val();
            if(classify==1){
                $("#a_videoTag").empty();
                $("#a_leagueId").empty();
                $("#a_gameId").empty();
                $("#a_videoTag").append('<option value="1">开幕式视频</option>');
                $("#a_videoTag").append('<option value="2">闭幕式视频</option>');
                $("#a_videoTag").append('<option value="3">五佳球视频</option>');
                $("#a_videoTag").append('<option value="4">十佳球视频</option>');
                //获取所有赛事
                leagueList();
            }else if(classify==2){
                $("#a_videoTag").empty();
                $("#a_leagueId").empty();
                $("#a_gameId").empty();
                $("#a_videoTag").append('<option value="5">整场视频</option>');
                $("#a_videoTag").append('<option value="6">集锦视频</option>');
                $("#a_videoTag").append('<option value="7">比赛精彩片段</option>');
                //获取所有比赛
                tournamentList();
            }else if(classify==3){//发现
                $("#a_videoTag").empty();
                $("#a_leagueId").empty();
                $("#a_gameId").empty();
                $("#a_videoTag").append('<option value="8">精彩视频集锦</option>');
                $("#a_videoTag").append('<option value="9">足球教学片</option>');
                $("#a_videoTag").append('<option value="10">职业球星视频</option>');
            }else{//GIF
                $("#a_videoTag").empty();
                $("#a_leagueId").empty();
                $("#a_gameId").empty();
            }
        });

        $("#s_classify").change(function(){
            var classify = $("#s_classify") .val();
            if(classify==1){
                $("#s_videoTag").empty();
                $("#s_videoTag").append('<option value="1">开幕式视频</option>');
                $("#s_videoTag").append('<option value="2">闭幕式视频</option>');
                $("#a_videoTag").append('<option value="3">五佳球视频</option>');
                $("#s_videoTag").append('<option value="4">十佳球视频</option>');
            }else if(classify==2){
                $("#s_videoTag").empty();
                $("#s_videoTag").append('<option value="5">整场视频</option>');
                $("#s_videoTag").append('<option value="6">集锦视频</option>');
                $("#s_videoTag").append('<option value="7">比赛精彩片段</option>');
            }else if(classify==3){//发现
                $("#s_videoTag").empty();
                $("#s_videoTag").append('<option value="8">精彩视频集锦</option>');
                $("#s_videoTag").append('<option value="9">足球教学片</option>');
                $("#s_videoTag").append('<option value="10">职业球星视频</option>');
            }else{//GIF
                $("#s_videoTag").empty();
            }
        });

        $("#up_classify").change(function(){
            var classify = $("#up_classify") .val();
            if(classify==1){
                $("#up_videoTag").empty();
                $("#up_videoTag").append('<option value="1">开幕式视频</option>');
                $("#up_videoTag").append('<option value="2">闭幕式视频</option>');
                $("#up_videoTag").append('<option value="3">五佳球视频</option>');
                $("#up_videoTag").append('<option value="4">十佳球视频</option>');
                //获取所有赛事
                leagueList1();
            }else if(classify==2){
                $("#up_videoTag").empty();
                $("#up_leagueId").empty();
                $("#up_videoTag").append('<option value="5">整场视频</option>');
                $("#up_videoTag").append('<option value="6">集锦视频</option>');
                $("#up_videoTag").append('<option value="7">比赛精彩片段</option>');
                //获取所有比赛
                tournamentList1();
            }else if(classify==3){//发现
                $("#up_videoTag").empty();
                $("#up_leagueId").empty();
                $("#up_videoTag").append('<option value="8">精彩视频集锦</option>');
                $("#up_videoTag").append('<option value="9">足球教学片</option>');
                $("#up_videoTag").append('<option value="10">职业球星视频</option>');
            }else{//GIF
                $("#up_videoTag").empty();
                $("#up_leagueId").empty();
            }
        });


    });

    function checkUrl(url) {
        var strReg = "http(s)?:*";
        var re = new RegExp(strReg);
        if(!re.test(url)) {
            return false;
        } else {
            return true;
        }
    }
</script>

<div  id="user_button">

</div>




<div class="wrapper wrapper-content animated">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox ">
                <!--<div class="ibox-title">
                    <form class="form-inline">

                        <div class="form-group">
                            <label>创建人</label>
                            <input type="text" id="s_userName" name="userName" class="form-control"/>
                        </div>

                        <div class="form-group">
                            <label>视频类型</label>
                            <select class="form-control" id="s_classify" >
                                <option value="0" selected="selected">全部</option>
                                <option value="1">赛事视频</option>
                                <option value="2">比赛视频</option>
                                <option value="3">发现视频</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>视频标签</label>
                            <select class="form-control" id="s_videoTag"  multiple="multiple">
                                <option value="0" selected="selected">全部</option>
                                <option value="1">开幕式视频</option>
                                <option value="2">闭幕式视频</option>
                                <option value="3">五佳球视频 </option>
                                <option value="4">十佳球视频</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>是否显示</label>
                            <select class="form-control" id="s_ifShow">
                                <option value="0" selected="selected">全部</option>
                                <option value="1">是</option>
                                <option value="2">否</option>
                            </select>
                        </div>

                        <button type="button" class="btn btn-default" id="serc">查询</button>
                    </form>
                </div>-->

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
                    <h2 class="modal-title" id="myModalLabel">视频添加</h2>
                    <input type="hidden" name="videoType"  class="form-control" value="1"/>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-5 control-label">所属分类<span class="validt" id="va_classify"></span></label>
                        <select class="form-control" name="classify" id="a_classify">
                            <option value="1">赛事视频</option>
                            <option value="2">比赛视频</option>
                            <option value="3">发现视频</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">视频标签<span class="validt" id="va_videoTag"></span></label>
                        <select class="form-control" name="videoTag" id="a_videoTag">
                            <option value="1">开幕式视频</option>
                            <option value="2">闭幕式视频</option>
                            <option value="3">五佳球视频 </option>
                            <option value="4">十佳球视频</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">视频名称<span class="validt" id="va_videoName"></span></label>
                        <input type="text" name="videoName"  class="form-control" id="a_videoName"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label" style="height: 140px">视频地址<span class="validt" id="va_videoHOS"></span></label>
                        <input type="text" name="videoAddressHigh"  class="form-control" id="a_videoAddressHigh" style="margin-bottom: 14px;"/>高清
                        <input type="text" name="videoAddressOrdinary"  class="form-control" id="a_videoAddressOrdinary" style="margin-bottom: 14px;"/>普通
                        <input type="text" name="videoAddressSmooth"  class="form-control" id="a_videoAddressSmooth" />流畅
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">关联赛事<span class="validt" id="va_leagueId"></span></label>
                        <select class="form-control" name="leagueId" id="a_leagueId">
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">关联比赛<span class="validt" id="va_gameId"></span></label>
                        <select class="form-control" name="gameId" id="a_gameId">
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">视频ID<span class="validt" id="va_vid"></span></label>
                        <input type="text" name="vid"  class="form-control" id="a_vid" />
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
                    <h2 class="modal-title">视频修改</h2>
                    <input type="hidden" id="up_videoId" name="videoId"/>
                    <input type="hidden" name="videoType"  class="form-control" value="1"/>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-5 control-label">视频类型<span class="validt" id="vu_classify"></span></label>
                        <select class="form-control" name="classify" id="up_classify">
                            <option value="1">赛事视频</option>
                            <option value="2">比赛视频</option>
                            <option value="3">发现视频</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">视频标签<span class="validt" id="vu_videoTag"></span></label>
                        <select class="form-control" name="videoTag" id="up_videoTag">
                            <option value="1">开幕式视频</option>
                            <option value="2">闭幕式视频</option>
                            <option value="3">五佳球视频 </option>
                            <option value="4">十佳球视频</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">视频名称<span class="validt" id="vu_videoName"></span></label>
                        <input type="text" name="videoName" class="form-control" id="up_videoName"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label" style="height: 140px">视频地址<span class="validt" id="vu_videoHOS"></span></label>
                        <input type="text" name="videoAddressHigh"  class="form-control" id="up_videoAddressHigh"  style="margin-bottom: 14px;"/>高清
                        <input type="text" name="videoAddressOrdinary"  class="form-control" id="up_videoAddressOrdinary"  style="margin-bottom: 14px;"/>普通
                        <input type="text" name="videoAddressSmooth"  class="form-control" id="up_videoAddressSmooth" />流畅
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">关联赛事<span class="validt" id="vu_leagueId"></span></label>
                        <select class="form-control" name="leagueId" id="up_leagueId">
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">关联比赛<span class="validt" id="vu_gameId"></span></label>
                        <select class="form-control" name="gameId" id="up_gameId">
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">视频ID<span class="validt" id="vu_vid"></span></label>
                        <input type="text" name="vid"  class="form-control" id="up_vid" />
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">是否显示<span class="validt" id="vu_ifShow"></span></label>
                        <select class="form-control" name="ifShow" id="up_ifShow">
                            <option value="1">是</option>
                            <option value="2">否</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">简介<span class="validt" id="vu_brief"></span></label>
                        <textarea name="brief"  class="form-control" id="up_brief" ></textarea>
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