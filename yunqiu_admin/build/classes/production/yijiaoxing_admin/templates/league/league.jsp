<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="renderer" content="webkit"/>
    <title>云球 - 赛事</title>

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
        var arr = "";
        var leagueSite ="";
        $.jgrid.defaults.styleUI="Bootstrap";
        $("#list4").jqGrid({
            url:'/league/select',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            height:$(window).height()-239,
            autowidth:true,//自动宽
            shrinkToFit:true,
            colModel:[
                {label:"赛事Id",name:'leagueId',index:'leagueId', width:'15%',align:'center',hidden: true},
                {label:"省",name:'province',index:'province', width:'15%',align:'center',hidden: true},
                {label:"市",name:'city',index:'city', width:'15%',align:'center',hidden: true},
                {label:"区",name:'area',index:'area', width:'15%',align:'center',hidden: true},
                {label:"介绍",name:'introduce',index:'introduce', width:'15%',align:'center',hidden: true},
                {label:"新闻",name:'news',index:'news', width:'15%',align:'center',hidden: true},
                {label:"开始时间",name:'startTime',index:'startTime', width:'15%',align:'center',hidden: true,
                    formatter:function(cellvalue, options, rowObject){
                        if (typeof(cellvalue)=="undefined" || typeof(cellvalue)=="" || cellvalue==0){
                            return "";
                        }
                        var date = new Date(cellvalue);

                        return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();;
                    }
                },
                {label:"赛事结束时间",name:'endTime',index:'endTime', width:'15%',align:'center',hidden: true,
                    formatter:function(cellvalue, options, rowObject){

                        if (typeof(cellvalue)=="undefined" || typeof(cellvalue)=="" || cellvalue==0){
                            return "";
                        }
                        var date = new Date(cellvalue);

                        return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();;
                    }
                },
                {label:"报名开始时间",name:'applyStartTime',index:'applyStartTime', width:'15%',align:'center',hidden: true,
                    formatter:function(cellvalue, options, rowObject){
                        if (typeof(cellvalue)=="undefined" || typeof(cellvalue)=="" || cellvalue==0){
                            return "";
                        }
                        var date = new Date(cellvalue);

                        return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();;
                    }
                },
                {label:"报名结束时间",name:'applyEndTime',index:'applyEndTime', width:'15%',align:'center',hidden: true,
                    formatter:function(cellvalue, options, rowObject){
                        if (typeof(cellvalue)=="undefined" || typeof(cellvalue)=="" || cellvalue==0){
                            return "";
                        }
                        var date = new Date(cellvalue);

                        return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();;
                    }
                },
                {label:"图标",name:'leagueIcon',index:'leagueIcon', width:'15%',align:'center',hidden: true},
                {label:"背景图",name:'background',index:'background', width:'15%',align:'center',hidden: true},
                {label:"赛事全称",name:'leagueName',index:'leagueName', width:'35%',align:'center'},
                {label:"赛事简称",name:'leagueAbbreviation',index:'leagueAbbreviation', width:'35%',align:'center'},
                {label:"联赛类型",name:'leagueType',index:'leagueType', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){
                            return "联赛单循环";
                        }else if(cellvalue==2){
                            return "联赛双循环";
                        }else if(cellvalue==3){
                            return "小组赛单循环+淘汰赛";
                        }else if(cellvalue==4){
                            return "小组赛双循环+淘汰赛";
                        }else if(cellvalue==5){
                            return "杯赛";
                        }else{
                            return "";
                        }
                    }
                },
                {label:"联赛时间",name:'time',index:'time', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){

                        if (typeof(rowObject.startTime)=="undefined" || typeof(rowObject.startTime)=="" || rowObject.startTime==0){
                            return "";
                        }
                        var date = new Date(rowObject.startTime);

                        var beginTime = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();

                        if (typeof(rowObject.endTime)=="undefined" || typeof(rowObject.endTime)=="" || rowObject.endTime==0){
                            return "";
                        }
                        var enddate = new Date(rowObject.endTime);

                        var endTime = enddate.getFullYear()+"-"+(enddate.getMonth()+1)+"-"+enddate.getDate()+" "+enddate.getHours()+":"+enddate.getMinutes()+":"+enddate.getSeconds();

                        if (typeof(rowObject.applyEndTime)=="undefined" || typeof(rowObject.applyEndTime)=="" || rowObject.applyEndTime==0){
                            return "";
                        }
                        var applyEnddate= new Date(rowObject.applyEndTime);

                        var applyEndTime = applyEnddate.getFullYear()+"-"+(applyEnddate.getMonth()+1)+"-"+applyEnddate.getDate()+" "+applyEnddate.getHours()+":"+applyEnddate.getMinutes()+":"+applyEnddate.getSeconds();

                        if (typeof(rowObject.applyStartTime)=="undefined" || typeof(rowObject.applyStartTime)=="" || rowObject.applyStartTime==0){
                            return "";
                        }
                        var applyStartdate= new Date(rowObject.applyStartTime);

                        var applyStartTime = applyStartdate.getFullYear()+"-"+(applyStartdate.getMonth()+1)+"-"+applyStartdate.getDate()+" "+applyStartdate.getHours()+":"+applyStartdate.getMinutes()+":"+applyStartdate.getSeconds();


                        return " 赛事开始时间：" + beginTime +"<br/>报名开始时间："+applyStartTime+"<br/>报名结束时间:"+applyEndTime+"<br/>赛事结束时间："+endTime;
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
                {label:"地区",name:'pd',index:'pd', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        var text = "";
                        if(rowObject.province!="undefined"){
                            text+=rowObject.province;
                        }
                        if(rowObject.city!="undefined"){
                            text+=" "+rowObject.city;
                        }
                        if(rowObject.area!="undefined"){
                            text+=" "+rowObject.area;
                        }
                        return text;
                    }},
                {label:"场地",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        var text = "'"+rowObject.leagueId+"'";
                        return '<a href="javascript:hostPlace('+text+')">详情</a>';
                    }
                },
                {label:"举办单位",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        var text = "'"+rowObject.leagueId+"'";
                        return '<a href="javascript:hostUnitFun('+text+')">详情</a>';
                    }
                },
                {label:"赛事状态",name:'status',index:'status', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){
                            return "报名中";
                        }else if(cellvalue==2){
                            return "进行中";
                        }else if(cellvalue==3){
                            return "已结束";
                        }else if(cellvalue==4){
                            return "已取消";
                        }else if(cellvalue==5){
                            return "待开赛";
                        }else{
                            return "";
                        }
                    }},
                {label:"参赛球队",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return '<a href="/view/go_joinleague?leagueId='+rowObject.leagueId+'">详情</a>';
                    }
                },
                {label:"赛程",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return '<a href="/view/go_tournament?leagueId='+rowObject.leagueId+'">详情</a>';
                    }
                },
                {label:"榜单",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return '<a href="/view/go_leagueLIST?leagueId='+rowObject.leagueId+'">详情</a>';
                    }
                },
                {label:"积分榜",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return '<a href="/view/go_leaguePoints?leagueId='+rowObject.leagueId+'">详情</a>';
                    }
                },
                {label:"荣誉",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return '<a href="/view/go_leagueHonor?leagueId='+rowObject.leagueId+'">详情</a>';
                    }
                },
                /*{label:"球员奖项",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return '<a href="#">详情</a>';
                    }
                },
                {label:"球队奖项",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return '<a href="#">详情</a>';
                    }
                },*/
                {label:"赛事视频",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return '<a href="/view/go_video?classify=1">详情</a>';
                    }
                },
                {label:"视频是否通知",name:'ifNotice',index:'ifNotice', width:'35%',align:'center',
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
                {label:"是否启用",name:'ifOpen',index:'ifOpen', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){
                            return "是";
                        }else if(cellvalue==2){
                            return "否";
                        }else{
                            return "";
                        }
                    }}
            ],
            pager:'#gridPager',
            viewrecords: true,//是否在浏览导航栏显示记录总数
            rowNum:10, //每页显示记录数
            rownumbers: true,
            sortname: "startTime",
            sortorder: "desc",
            rowList:[10,30,50],//用于改变显示行数的下拉列表框的元素数组。
            jsonReader:{
                id: "leagueId",//设置返回参数中，表格ID的名字为blackId
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

        //省
        $.ajax({
            url: "/zone/getProvinceList",
            async : false,
            datatype:"json",
            type:"get",
            success: function (data) {
                $("#province").empty();
                items = data["data"];
                var selectList = "";
                selectList += '<option name="province" value="0" >请选择省</option>';
                $.each(eval(items), function (idx, obj) {
                    selectList += '<option name="province" value="'+obj.provinceId+'" >'+obj.province+'</option>';
                });
                $("#province").append(selectList);
            }
        });

        //市
        $("#province").change(function(){
            var provinceId = $("#province").val();
            $.ajax({
                url: "/zone/getCityList",
                async : false,
                datatype:"json",
                type:"get",
                data:{"provinceId":provinceId},
                success: function (data) {
                    $("#city").empty();
                    items = data["data"];
                    var selectList = "";
                    $.each(eval(items), function (idx, obj) {
                        selectList += '<option name="city" value="'+obj.cityId+'" >'+obj.city+'</option>';
                    });
                    $("#city").append(selectList);
                }
            });
        });

        //区/县
        $("#city").change(function(){
            var cityId = $("#city").val();
            $.ajax({
                url: "/zone/getAreaList",
                async : false,
                datatype:"json",
                type:"get",
                data:{"cityId":cityId},
                success: function (data) {
                    $("#area").empty();
                    items = data["data"];
                    var selectList = "";
                    $.each(eval(items), function (idx, obj) {
                        selectList += '<option name="city" value="'+obj.areaId+'" >'+obj.area+'</option>';
                    });
                    $("#area").append(selectList);
                }
            });
        });

        //省
        $.ajax({
            url: "/zone/getProvinceList",
            async : false,
            datatype:"json",
            type:"get",
            success: function (data) {
                $("#up_province").empty();
                items = data["data"];
                var selectList = "";
                selectList += '<option name="province" value="0" >请选择省</option>';
                $.each(eval(items), function (idx, obj) {
                    selectList += '<option name="province" value="'+obj.provinceId+'" >'+obj.province+'</option>';
                });
                $("#up_province").append(selectList);
            }
        });

        //市
        $("#up_province").change(function(){
            var provinceId = $("#up_province").val();
            $.ajax({
                url: "/zone/getCityList",
                async : false,
                datatype:"json",
                type:"get",
                data:{"provinceId":provinceId},
                success: function (data) {
                    $("#up_city").empty();
                    items = data["data"];
                    var selectList = "";
                    $.each(eval(items), function (idx, obj) {
                        selectList += '<option name="city" value="'+obj.cityId+'" >'+obj.city+'</option>';
                    });
                    $("#up_city").append(selectList);
                }
            });
        });

        //区/县
        $("#up_city").change(function(){
            var cityId = $("#up_city").val();
            $.ajax({
                url: "/zone/getAreaList",
                async : false,
                datatype:"json",
                type:"get",
                data:{"cityId":cityId},
                success: function (data) {
                    $("#up_area").empty();
                    items = data["data"];
                    var selectList = "";
                    $.each(eval(items), function (idx, obj) {
                        selectList += '<option name="city" value="'+obj.areaId+'" >'+obj.area+'</option>';
                    });
                    $("#up_area").append(selectList);
                }
            });
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
            }
        });


        //获取所有场地
        $.ajax({
            url: "/place/getPlaceList",
            async : false,
            datatype:"json",
            type:"get",
            success: function (data) {
                $("#leagueSite").empty();
                items = data["data"];
                var selectList = "";
                $.each(eval(items), function (idx, obj) {
                    selectList += '<option value="'+obj.placeName+'" >'+obj.placeName+'</option>';
                });
                $("#leagueSite").append(selectList);
            }
        });


        //查询条件传值
        $("#serc").click(function(){
            var leagueName=$("#s_leagueName").val();
            var leagueAbbreviation=$("#s_leagueAbbreviation").val();
            //var leagueType=$("#s_leagueType").val();
            var leagueType = "";
            $("#s_leagueType option:selected").each(function(){
                //alert($(this).val()); //这里得到的就是
                if($(this).val()!=0){
                    leagueType += $(this).val()+",";
                }
            })
            //var gameSystem=$("#s_gameSystem").val();
            var gameSystem = "";
            $("#s_gameSystem option:selected").each(function(){
                //alert($(this).val()); //这里得到的就是
                if($(this).val()!=0){
                    gameSystem += $(this).val()+",";
                }
            })
            //var status=$("#s_status").val();
            var status = "";
            $("#s_status option:selected").each(function(){
                //alert($(this).val()); //这里得到的就是
                if($(this).val()!=0){
                    status += $(this).val()+",";
                }
            })
            var ifOpen=$("#s_ifOpen").val();

            $("#list4").jqGrid('setGridParam',{
                datatype:'json',
                url:'/league/select',
                page:1,
                postData:{
                    'leagueName':leagueName,
                    'leagueAbbreviation':leagueAbbreviation,
                    'leagueType':leagueType,
                    'gameSystem':gameSystem,
                    'status':status,
                    'ifOpen':ifOpen
                }
            }).trigger("reloadGrid"); //重新载入
        });


        //添加窗口
        $("#btn_add").bind("click",function(){
            $("#add_winfrom").modal('show');
            $("#add").unbind("click");

            $("#add").bind("click",function(){

                var leagueName=$("#leagueName").val();//赛事全称
                var leagueAbbreviation=$("#leagueAbbreviation").val();//赛事简称
                var startTime=$("#startTime").val();//开始时间
                var endTime=$("#endTime").val();//结束时间
                var applyEndTime=$("#applyEndTime").val();//报名结束时间
                var province = $("#province").find("option:selected").val();//省

                $("#va_leagueName").html("");
                $("#va_leagueAbbreviation").html("");
                $("#va_startTime").html("");
                $("#va_endTime").html("");
                $("#va_applyEndTime").html("");
                $("#va_PCA").html("");

                if(!leagueName.length>0){
                    $("#va_leagueName").html("请输入赛事全称");
                    return;
                }
                if(!leagueAbbreviation.length>0){
                    $("#va_leagueAbbreviation").html("请输入赛事简称");
                    return;
                }
                if(!startTime.length>0){
                    $("#va_startTime").html("请输入赛事开始时间");
                    return;
                }
                if(!endTime.length>0){
                    $("#va_endTime").html("请输入赛事结束时间");
                    return;
                }
                if(!applyEndTime.length>0){
                    $("#va_applyEndTime").html("请输入赛事报名结束时间");
                    return;
                }
                if(province==0){
                    $("#va_PCA").html("请选择省");
                    return;
                }

                //开始ajax操作
                $("#addForm").ajaxSubmit({
                    type: "POST",//提交类型
                    dataType: "json",//返回结果格式
                    url: "/league/addLeague",//请求地址
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

                $("#update_leagueIcon").val(select.leagueIcon);
                $("#update_background").val(select.background);
                $("#up_leagueId").val(select.leagueId);
                $("#up_leagueName").val(select.leagueName);
                $("#up_leagueAbbreviation").val(select.leagueAbbreviation);
                $("#up_leagueType option:contains('"+select.leagueType+"')").prop("selected", true);
                $("#up_introduce").val(select.introduce);
                $("#up_news").val(select.news);
                $("#up_startTime").val(select.startTime);
                $("#up_endTime").val(select.endTime);
                $("#up_applyStartTime").val(select.applyStartTime);
                $("#up_applyEndTime").val(select.applyEndTime);
                $("#up_gameSystem option:contains('"+select.gameSystem+"')").prop("selected", true);
                $("#up_province option:contains('"+select.province+"')").prop("selected", true);

                //市
                var provinceId = $("#up_province").val();
                $.ajax({
                    url: "/zone/getCityList",
                    async : false,
                    datatype:"json",
                    type:"get",
                    data:{"provinceId":provinceId},
                    success: function (data) {
                        $("#up_city").empty();
                        items = data["data"];
                        var selectList = "";
                        $.each(eval(items), function (idx, obj) {
                            selectList += '<option name="city" value="'+obj.cityId+'" >'+obj.city+'</option>';
                        });
                        $("#up_city").append(selectList);
                    }
                });

                $("#up_city option:contains('"+select.city+"')").prop("selected", true);

                //区/县
                var cityId = $("#up_city").val();
                $.ajax({
                    url: "/zone/getAreaList",
                    async : false,
                    datatype:"json",
                    type:"get",
                    data:{"cityId":cityId},
                    success: function (data) {
                        $("#up_area").empty();
                        items = data["data"];
                        var selectList = "";
                        $.each(eval(items), function (idx, obj) {
                            selectList += '<option name="city" value="'+obj.areaId+'" >'+obj.area+'</option>';
                        });
                        $("#up_area").append(selectList);
                    }
                });

                $("#up_area option:contains('"+select.area+"')").prop("selected", true);
                $("#up_status option:contains('"+select.status+"')").prop("selected", true);
                $("#up_ifNotice option:contains('"+select.ifNotice+"')").prop("selected", true);
                $("#up_ifOpen option:contains('"+select.ifOpen+"')").prop("selected", true);


                $("#update").bind("click",function(){
                    var leagueName=$("#up_leagueName").val();//赛事全称
                    var leagueAbbreviation=$("#up_leagueAbbreviation").val();//赛事简称
                    var startTime=$("#up_startTime").val();//开始时间
                    var endTime=$("#up_endTime").val();//结束时间
                    var applyEndTime=$("#up_applyEndTime").val();//报名结束时间
                    var province = $("#up_province").find("option:selected").val();//省
                    var leagueIcon=$("#up_leagueIcon").val();
                    var background=$("#up_background").val();

                    $("#vu_leagueName").html("");
                    $("#vu_leagueAbbreviation").html("");
                    $("#vu_startTime").html("");
                    $("#vu_endTime").html("");
                    $("#vu_applyEndTime").html("");
                    $("#vu_PCA").html("");

                    if(!leagueName.length>0){
                        $("#vu_leagueName").html("请输入赛事全称");
                        return;
                    }
                    if(!leagueAbbreviation.length>0){
                        $("#vu_leagueAbbreviation").html("请输入赛事简称");
                        return;
                    }
                    if(!startTime.length>0){
                        $("#vu_startTime").html("请输入赛事开始时间");
                        return;
                    }
                    if(!endTime.length>0){
                        $("#vu_endTime").html("请输入赛事结束时间");
                        return;
                    }
                    if(!applyEndTime.length>0){
                        $("#vu_applyEndTime").html("请输入赛事报名结束时间");
                        return;
                    }
                    if(province==0){
                        $("#vu_PCA").html("请选择省");
                        return;
                    }

                    if(leagueIcon.length>0){
                        var options={
                            url: "/upload/upload",
                            type:"post",
                            async:false,
                            success: function (data) {
                                $("#update_leagueIcon").val(data);
                            }
                        }
                        $("#updateForm").ajaxSubmit(options); //xx 你form表单的id,或者直接$("form").ajaxSubmit(options);
                    }

                    if(background.length>0){
                        var options={
                            url: "/upload/upload2",
                            type:"post",
                            async:false,
                            success: function (data) {
                                $("#update_background").val(data);
                            }
                        }
                        $("#updateForm").ajaxSubmit(options); //xx 你form表单的id,或者直接$("form").ajaxSubmit(options);
                    }


                    $("#updateForm").ajaxSubmit({
                        type: "POST",//提交类型
                        dataType: "json",//返回结果格式
                        url: "/league/upLeague",//请求地址
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

        //关闭窗口
        $("#btn_close").bind("click",function(){
            var selectedId = $("#list4").jqGrid("getGridParam", "selrow");
            var select= jQuery("#list4").jqGrid('getRowData', selectedId);
            if (selectedId == null){
                swal("赛事","请选择需要关闭的赛事！","info");
            }else{
                $("#close_winfrom").modal('show');
                $("#close").bind("click",function(){
                    var username = $("#username").val();
                    var password = $("#password").val();
                    $.ajax({
                        url:"/league/closeLeague",
                        data:{
                            "leagueId":select.leagueId,
                            "username":username,
                            "password":password
                        },
                        success:function(data){
                            if (data["error_code"] == 1500){
                                swal("系统错误!", data["msg"], "error");
                            }else if (data["error_code"] != 1200){
                                swal("操作失败!",data["msg"],"warning");
                            }else{
                                $("#close_winfrom").modal('hide');
                                swal("赛事","关闭成功！");
                                $("#list4").trigger("reloadGrid");
                            }
                        },error:function(){
                            swal("关闭失败","网络异常！");
                        }
                    });
                });
            }
        });




        //导出窗口
        $("#btn_export").bind("click",function(){
            $("#export_winfrom").modal('show');
            var excelStyle = $("#excelStyle").val();
            var excelUrl = "";
            if(excelStyle==1){//excel

            }else if(excelStyle==2){//json

            }else if(excelStyle==3){//xml

            }else if(excelStyle==4) {//txt

            }else if(excelStyle==5) {//csv

            }

            $("#export").bind("click",function(){
                $.ajax({
                    url:"/place/delPlace",
                    success:function(data){
                        if (data["error_code"] == 1500){
                            swal("系统错误!", data["msg"], "error");
                        }else if (data["error_code"] != 1200){
                            swal("操作失败!",data["msg"],"warning");
                        }else{
                            $("#export_winfrom").modal('hide');
                        }
                    },error:function(){
                        swal("导出失败","网络异常！");
                    }
                });
            });
        });
    });

    function getAllPlact(leagueId){
        //获取该赛程的场地
        $.ajax({
            url:"/league/getHostUnit",
            data:{"leagueId":leagueId},
            success:function(data){
                leagueSite = data.leagueSite;
                if(leagueSite!=""){
                    $("#allPlace").empty();
                    arr = leagueSite.split(',');
                    $.each(arr, function (idx, obj) {
                        var text = "'"+leagueSite+"'"+",'"+obj+"'"+","+"'"+leagueId+"'";
                        $("#allPlace").append(idx+1+'.'+obj+'            <a href="javascript:delPlace('+text+')">删除</a><br/>');
                    });
                }
            },error:function(){
                swal("获取失败","网络异常！");
            }
        });
    }

    //场地
    function hostPlace(leagueId){
        $.ajax({
            url:"/league/getHostUnit",
            data:{"leagueId":leagueId},
            success:function(data){
                leagueSite = data.leagueSite;
                $("#leagueSite").val(leagueSite);
            },error:function(){
                swal("获取失败","网络异常！");
            }
        });
        $("#place_winfrom").modal('show');

        $("#place").bind("click",function(){
            leagueSite = $("#leagueSite").val();
            $.ajax({
                url:"/league/updateLeagueSite",
                data:{"leagueSite":leagueSite,"leagueId":leagueId},
                success:function(data){
                    if (data["error_code"] == 1500){
                        swal("系统错误!", data["msg"], "error");
                    }else if (data["error_code"] != 1200){
                        swal("操作失败!",data["msg"],"warning");
                    }else{
                        $("#place_winfrom").modal('hide');
                        swal("场地","修改成功！");
                        $("#list4").trigger("reloadGrid");
                    }
                },error:function(){
                    swal("修改失败","网络异常！");
                }
            });
        })







        /*$("#allPlace").empty();

        getAllPlact(leagueId);

        $("#place_winfrom").modal('show');

        $("#place").bind("click",function(){
            var selleaguesite = $("#leagueSite option:selected").val();
            if(leagueSite!=""){
                leagueSite = leagueSite +","+selleaguesite;
            }else{
                leagueSite = selleaguesite;
            }
            $.ajax({
                url:"/league/updateLeagueSite",
                data:{"leagueSite":leagueSite,"leagueId":leagueId},
                success:function(data){
                    if (data["error_code"] == 1500){
                        swal("系统错误!", data["msg"], "error");
                    }else if (data["error_code"] != 1200){
                        swal("操作失败!",data["msg"],"warning");
                    }else{
                        getAllPlact(leagueId);
                        /!*alert(leagueSite);
                        arr = leagueSite.split(',');
                        $("#allPlace").empty();
                        $.each(arr, function (idx, obj) {
                            var text = "'"+leagueSite+"'"+",'"+obj+"'"+","+"'"+leagueId+"'";
                            $("#allPlace").append(idx+1+'.'+obj+'            <a href="javascript:delPlace('+text+')">删除</a><br/>');
                        });*!/
                    }
                },error:function(){
                    swal("修改失败","网络异常！");
                }
            });
        });*/


    }

    //举办单位
    function hostUnitFun(leagueId){
        //先查询举办单位
        $.ajax({
            url:"/league/getHostUnit",
            data:{"leagueId":leagueId},
            success:function(data){
                $("#direct").val(data["direct"]);
                $("#sponsor").val(data["sponsor"]);
                $("#undertake").val(data["undertake"]);
                $("#assisting").val(data["assisting"]);

                $("#hostUnit_winfrom").modal('show');
                $("#leagueId").val(leagueId);//给id赋值
            },error:function(){
                swal("获取失败","网络异常！");
            }
        });

        $("#hostUnit").bind("click",function(){
            $.ajax({
                url:"/league/updateHostUnit",
                data:$("#hostUnitForm").serialize(),
                success:function(data){
                    if (data["error_code"] == 1500){
                        swal("系统错误!", data["msg"], "error");
                    }else if (data["error_code"] != 1200){
                        swal("操作失败!",data["msg"],"warning");
                    }else{
                        swal("修改成功");
                        $("#hostUnit_winfrom").modal('hide');
                    }
                },error:function(){
                    swal("修改失败","网络异常！");
                }
            });
        });
    }
    
    function  delPlace(leagueSite,obje,leagueId) {
        $.each(arr, function (idx, obj) {
            if(obj == obje) {
                arr.splice(idx, 1);
            }
        });
        leagueSite = arr.toString();

        $.ajax({
            url:"/league/updateLeagueSite",
            data:{"leagueSite":arr.toString(),"leagueId":leagueId},
            success:function(data){
                if (data["error_code"] == 1500){
                    swal("系统错误!", data["msg"], "error");
                }else if (data["error_code"] != 1200){
                    swal("操作失败!",data["msg"],"warning");
                }else{
                    getAllPlact(leagueId);
                }
            },error:function(){
                swal("修改失败","网络异常！");
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
                <div class="ibox-title">
                    <form class="form-inline">

                        <div class="form-group">
                            <label>赛事全称</label>
                            <input type="text" id="s_leagueName" name="leagueName" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>赛事简称</label>
                            <input type="text" id="s_leagueAbbreviation" name="leagueAbbreviation" class="form-control"/>
                        </div>

                        <div class="form-group">
                            <label>赛事类型</label>
                            <select id="s_leagueType" name="leagueType" class="form-control"  multiple="multiple">
                                <option value="0">全部</option>
                                <option value="1">联赛单循环</option>
                                <option value="2">联赛双循环</option>
                                <option value="3">小组赛单循环+淘汰赛</option>
                                <option value="4">小组赛双循环+淘汰赛</option>
                                <option value="5">杯赛</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>赛制</label>
                            <select name="gameSystem" id="s_gameSystem" class="form-control" multiple="multiple">
                                <option value="0">全部</option>
                                <option value="1">3人</option>
                                <option value="2">5人</option>
                                <option value="3">7人</option>
                                <option value="4">8人 </option>
                                <option value="5">9人 </option>
                                <option value="5">11人 </option>
                            </select>
                        </div>


                        <div class="form-group">
                            <label>赛事状态</label>
                            <select name="status" id="s_status" class="form-control" multiple="multiple">
                                <option value="0">全部</option>
                                <option value="1">报名中</option>
                                <option value="2">进行中</option>
                                <option value="3">已结束</option>
                                <option value="4">已取消 </option>
                                <option value="5">待开赛</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>是否启用</label>
                            <select id="s_ifOpen" name="ifOpen" class="form-control" >
                                <option value="0">全部</option>
                                <option value="1">是</option>
                                <option value="2">否</option>
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
                    <h2 class="modal-title" id="myModalLabel">赛事添加</h2>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-5 control-label">赛事全称<span class="validt" id="va_leagueName"></span></label>
                        <input type="text" id="leagueName" name="leagueName" class="form-control"  />
                    </div>
                    <div class="form-group">
                        <label class="col-sm-5 control-label">赛事简称<span class="validt" id="va_leagueAbbreviation"></span></label>
                        <input type="text" id="leagueAbbreviation" name="leagueAbbreviation" class="form-control" />
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">赛事类型<span class="validt" id="va_leagueType"></span></label>
                        <select id="leagueType" name="leagueType" class="form-control" >
                            <option value="1">联赛单循环</option>
                            <option value="2">联赛双循环</option>
                            <option value="3">小组赛单循环+淘汰赛</option>
                            <option value="4">小组赛双循环+淘汰赛</option>
                            <option value="5">杯赛</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">赛事开始时间<span class="validt" id="va_startTime"></span></label>
                        <input  id="startTime" name="startTime" type="text"  class="form-control"  onfocus="WdatePicker({lang:'zh-cn',dateFmt: 'yyyy-MM-dd HH:mm'})" style="width:186px" />
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">赛事结束时间<span class="validt" id="va_endTime"></span></label>
                        <input  id="endTime" name="endTime" type="text"  class="form-control"  onfocus="WdatePicker({lang:'zh-cn',dateFmt: 'yyyy-MM-dd HH:mm'})" style="width:186px" />
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">赛事报名开始<span class="validt" id="va_applyStartTime"></span></label>
                        <input  id="applyStartTime" name="applyStartTime" type="text"  class="form-control"  onfocus="WdatePicker({lang:'zh-cn',dateFmt: 'yyyy-MM-dd HH:mm'})" style="width:186px" />
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">赛事报名结束<span class="validt" id="va_applyEndTime"></span></label>
                        <input  id="applyEndTime" name="applyEndTime" type="text"  class="form-control"  onfocus="WdatePicker({lang:'zh-cn',dateFmt: 'yyyy-MM-dd HH:mm'})" style="width:186px" />
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">赛制</label>
                        <select name="gameSystem" id="gameSystem" class="form-control" >
                            <option value="1">3人</option>
                            <option value="2">5人</option>
                            <option value="3">7人</option>
                            <option value="4">8人 </option>
                            <option value="5">9人 </option>
                            <option value="5">11人 </option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-5 control-label" style="height: 95px;">地区<span class="validt" id="va_PCA"></span></label>
                        <select id="province" name="province" class="form-control" >
                        </select>
                        <select id="city" name="city" class="form-control" style="margin-top: 14px;">
                        </select>
                        <select id="area" name="area" class="form-control" style="margin-top: 14px;">
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">赛事状态</label>
                        <select name="status" id="status" class="form-control" >
                            <option value="1">报名中</option>
                            <option value="2">进行中</option>
                            <option value="3">已结束</option>
                            <option value="4">已取消 </option>
                            <option value="5">待开赛</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">视频是否通知</label>
                        <select name="ifNotice" id="ifNotice" class="form-control" >
                            <option value="1">是</option>
                            <option value="2">否</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">是否启用</label>
                        <select id="ifOpen" name="ifOpen" class="form-control" >
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
                    <h2 class="modal-title">赛事修改</h2>
                    <input type="hidden" id="up_leagueId" name="leagueId"/>
                    <input type="hidden" id="update_leagueIcon" name="leagueIcon"/>
                    <input type="hidden" id="update_background" name="background"/>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-5 control-label">赛事全称<span class="validt" id="vu_leagueName"></span></label>
                        <input type="text" id="up_leagueName" name="leagueName" class="form-control"  />
                    </div>
                    <div class="form-group">
                        <label class="col-sm-5 control-label">赛事简称<span class="validt" id="vu_leagueAbbreviation"></span></label>
                        <input type="text" id="up_leagueAbbreviation" name="leagueAbbreviation" class="form-control" />
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">赛事类型</label>
                        <select id="up_leagueType" name="leagueType" class="form-control" >
                            <option value="1">联赛单循环</option>
                            <option value="2">联赛双循环</option>
                            <option value="3">小组赛单循环+淘汰赛</option>
                            <option value="4">小组赛双循环+淘汰赛</option>
                            <option value="5">杯赛</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">赛事介绍<span class="validt" id="vu_introduce"></span></label>
                        <input type="text" id="up_introduce" name="introduce" class="form-control" />
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">赛事新闻<span class="validt" id="vu_news"></span></label>
                        <input type="text" id="up_news" name="news" class="form-control" />
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">联赛图标<span class="validt" id="vu_leagueIcon"></span></label>
                        <input type="file" id="up_leagueIcon" name="file" />
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">联赛背景图<span class="validt" id="vu_background"></span></label>
                        <input type="file" id="up_background" name="file2" />
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">赛事开始时间<span class="validt" id="vu_startTime"></span></label>
                        <input  id="up_startTime" name="startTime" type="text"  class="form-control"  onfocus="WdatePicker({lang:'zh-cn',dateFmt: 'yyyy-MM-dd HH:mm'})" style="width:186px" />
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">赛事结束时间<span class="validt" id="vu_endTime"></span></label>
                        <input  id="up_endTime" name="endTime" type="text"  class="form-control"  onfocus="WdatePicker({lang:'zh-cn',dateFmt: 'yyyy-MM-dd HH:mm'})" style="width:186px" />
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">赛事报名开始<span class="validt" id="vu_applyStartTime"></span></label>
                        <input  id="up_applyStartTime" name="applyStartTime" type="text"  class="form-control"  onfocus="WdatePicker({lang:'zh-cn',dateFmt: 'yyyy-MM-dd HH:mm'})" style="width:186px" />
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">赛事报名结束<span class="validt" id="vu_applyEndTime"></span></label>
                        <input  id="up_applyEndTime" name="applyEndTime" type="text"  class="form-control"  onfocus="WdatePicker({lang:'zh-cn',dateFmt: 'yyyy-MM-dd HH:mm'})" style="width:186px" />
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">赛制</label>
                        <select name="gameSystem" id="up_gameSystem" class="form-control" >
                            <option value="1">3人</option>
                            <option value="2">5人</option>
                            <option value="3">7人</option>
                            <option value="4">8人 </option>
                            <option value="5">9人 </option>
                            <option value="5">11人</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-5 control-label" style="height: 95px;">地区<span class="validt" id="vu_PCA"></span></label>
                        <select id="up_province" name="province" class="form-control" >
                        </select>
                        <select id="up_city" name="city" class="form-control" style="margin-top: 14px;">
                        </select>
                        <select id="up_area" name="area" class="form-control" style="margin-top: 14px;">
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">赛事状态</label>
                        <select name="status" id="up_status" class="form-control" >
                            <option value="1">报名中</option>
                            <option value="2">进行中</option>
                            <option value="3">已结束</option>
                            <option value="4">已取消 </option>
                            <option value="5">待开赛</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">视频是否通知</label>
                        <select name="ifNotice" id="up_ifNotice" class="form-control" >
                            <option value="1">是</option>
                            <option value="2">否</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">是否启用</label>
                        <select id="up_ifOpen" name="ifOpen" class="form-control" >
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

<div id="close_winfrom" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title">你确定要关闭此赛事吗？此操作无法撤销</h2>
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
                <button type="button" class="btn btn-primary" id="close">确定</button>
            </div>
        </div>
    </div>
</div>


<div id="export_winfrom" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div  class="modal-header">
                <div class="form-group">
                    <label class="col-sm-5 control-label">导出格式</label>
                    <select class="form-control" id="excelStyle" style="width:160px">
                        <option value="1" selected="selected">excel</option>
                        <option value="2" >json</option>
                        <option value="3" >xml</option>
                        <option value="4" >txt</option>
                        <option value="5" >csv</option>
                    </select>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="export">确定</button>
            </div>
        </div>
    </div>
</div>


<form id="hostUnitForm">
    <div id="hostUnit_winfrom" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-5 control-label">主办方</label>
                        <textarea  id="direct" name="direct" class="form-control" onfocus="if(value=='多个请用逗号隔开') {value=''}" onblur="if(value=='') {value='多个请用逗号隔开'}"></textarea>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-5 control-label">赞助方</label>
                        <textarea  id="sponsor" name="sponsor" class="form-control" onfocus="if(value=='多个请用逗号隔开') {value=''}" onblur="if(value=='') {value='多个请用逗号隔开'}"></textarea>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">承办方</label>
                        <textarea  id="undertake" name="undertake" class="form-control" onfocus="if(value=='多个请用逗号隔开') {value=''}" onblur="if(value=='') {value='多个请用逗号隔开'}"></textarea>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">协办方</label>
                        <textarea  id="assisting" name="assisting" class="form-control" onfocus="if(value=='多个请用逗号隔开') {value=''}" onblur="if(value=='') {value='多个请用逗号隔开'}"></textarea>
                    </div>
                    <input type="hidden" name="leagueId" id="leagueId"/>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="hostUnit">确定</button>
                </div>
            </div>
        </div>
    </div>
</form>


<form id="placeForm">
    <div id="place_winfrom" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <input type="hidden" id="leagueIdPlace"  name="leagueId"/>
                    <div class="form-group">
                        <label class="col-sm-5 control-label">常驻场地</label>
                        <textarea  id="leagueSite" name="leagueSite" class="form-control" onfocus="if(value=='多个请用逗号隔开') {value=''}" onblur="if(value=='') {value='多个请用逗号隔开'}"></textarea>
                        <!--<select name="leagueSite"  id="leagueSite" class="form-control">
                        </select>-->
                    </div>
                    <div class="form-group" id="allPlace" style="margin-left: 41%">
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="place">确定</button>
                </div>
            </div>
        </div>
    </div>
</form>



</body>
</html>