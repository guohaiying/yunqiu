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
            url:'/team/select',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            height:$(window).height()-239,
            autowidth:true,//自动宽
            shrinkToFit:true,
            colModel:[
                {label:"球队Id",name:'teamId',index:'teamId', width:'15%',align:'center',hidden: true},
                {label:"省",name:'province',index:'province', width:'15%',align:'center',hidden: true},
                {label:"市",name:'city',index:'city', width:'15%',align:'center',hidden: true},
                {label:"区",name:'area',index:'area', width:'15%',align:'center',hidden: true},
                {label:"球队标签",name:'label',index:'label', width:'15%',align:'center',hidden: true},
                {label:"邀请码",name:'invite',index:'invite', width:'15%',align:'center',hidden: true},
                {label:"球队队徽",name:'badge',index:'badge', width:'15%',align:'center',hidden: true},
                {label:"球队背景图",name:'background',index:'background', width:'15%',align:'center',hidden: true},
                {label:"球队名称",name:'teamName',index:'teamName', width:'35%',align:'center'},
                {label:"球队类型",name:'teamType',index:'teamType', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){
                            return "校园球队";
                        }else if(cellvalue==2){
                            return "业余爱好";
                        }else if(cellvalue==3){
                            return "公司球队";
                        }else if(cellvalue==4){
                            return "青少年球队";
                        }else{
                            return "";
                        }
                    }
                },
                {label:"球队队徽",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(rowObject.badge==undefined){
                            return "";
                        }else{
                            return '<img  style="width: 88px;height: 81px;" src="http://picture-services.b0.upaiyun.com/'+rowObject.badge+'"/>';
                        }
                    }
                },
                {label:"地区",name:'address',index:'address', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return rowObject.province+" "+rowObject.city+" "+rowObject.area;
                    }
                },
                {label:"常驻场地",name:'home',index:'home', width:'35%',align:'center'},
                {label:"创建时间",name:'enterTime',index:'enterTime', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if (typeof(cellvalue)=="undefined" || typeof(cellvalue)=="" || cellvalue==0){
                            return "";
                        }
                        var date = new Date(cellvalue);

                        return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();;
                    }
                },
                {label:"球队管理员",name:'userName',index:'userName', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        /*if (!typeof(cellvalue) == "undefined") {
                            return '<a href="">'+cellvalue+'</a>';
                        }else{
                            return "";
                        }*/
                        if(cellvalue==undefined){
                            return "";
                        }else{
                            return cellvalue;
                        }
                    }
                },
                {label:"球队标签",name:'label',index:'label', width:'35%',align:'center'},
                {label:"球队人数",name:'tpcount',index:'tpcount', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return '<a href="/view/go_TeamUser?teamId='+rowObject.teamId+'">'+cellvalue+'</a>';
                    }
                },
                /*{label:"球队排名",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return '<a href="">ffkaj</a>';
                    }
                },
                {label:"参与比赛",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return '<a href="">详情</a>';
                    }
                },
                {label:"参与赛事",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return '<a href="">详情</a>';
                    }
                },*/
                {label:"球队数据",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return '<a href="/view/go_teamData?teamId='+rowObject.teamId+'">详情</a>';
                    }
                },
                {label:"邀请码",name:'invite',index:'invite', width:'35%',align:'center'},
                {label:"是否解散",name:'status',index:'status', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){
                            return "否";
                        }else if(cellvalue==2){
                            return "是";
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
            sortname: "enterTime",
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
                selectList += '<option name="city" value="0" >请选择</option>';
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


        //添加窗口
        $("#btn_add").bind("click",function(){
            $("#add_winfrom").modal('show');
            $("#add").unbind("click");

            $("#add").bind("click",function(){

                var teamName=$("#teamName").val();//球队名称
                var badge=$("#badge").val();//球队队徽
                var home=$("#home").val();//常驻场地
                var enterTime=$("#enterTime").val();//入住时间
                var province = $("#province").find("option:selected").val();//省

                $("#va_teamName").html("");
                $("#va_badge").html("");
                $("#va_home").html("");
                $("#va_enterTime").html("");
                $("#va_PCA").html("");

                if(!teamName.length>0){
                    $("#va_teamName").html("请输入球队名称");
                    return;
                }
                if(!badge.length>0){
                    $("#va_badge").html("请选择队徽");
                    return;
                }
                if(!home.length>0){
                    $("#va_home").html("请输入常驻场地");
                    return;
                }
                if(!enterTime.length>0){
                    $("#va_enterTime").html("请选择创建时间");
                    return;
                }
                if(province==0){
                    $("#va_PCA").html("请选择省");
                    return;
                }

                var options={
                    url: "/upload/upload",
                    type:"post",
                    success: function (data) {
                        $("#add_badge").val(data);
                        //开始ajax操作
                        $("#addForm").ajaxSubmit({
                            type: "POST",//提交类型
                            dataType: "json",//返回结果格式
                            url: "/team/addTeam",//请求地址
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
                    }
                }
                $("#addForm").ajaxSubmit(options); //xx 你form表单的id,或者直接$("form").ajaxSubmit(options);

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
                        selectList += '<option name="city" value="0" >请选择</option>';
                        $.each(eval(items), function (idx, obj) {
                            selectList += '<option name="province" value="'+obj.provinceId+'" >'+obj.province+'</option>';
                        });
                        $("#up_province").append(selectList);
                    }
                });
                $("#update_badge").val(select.badge);
                $("#update_background").val(select.background);
                $("#up_teamId").val(select.teamId);
                $("#up_teamName").val(select.teamName);
                $("#up_teamType option:contains('"+select.teamType+"')").prop("selected", true);
                $("#up_home").val(select.home);
                $("#up_enterTime").val(select.enterTime);
                $("#up_province option:contains('"+select.province+"')").prop("selected", true);

                $("#up_label").val(select.label);
                $("#up_invite").val(select.invite);
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

                $("#update").bind("click",function(){

                    var teamName=$("#up_teamName").val();//球队名称
                    var badge=$("#up_badge").val();//球队队徽
                    var background=$("#up_background").val();//球队队徽
                    var home=$("#up_home").val();//常驻场地
                    var enterTime=$("#up_enterTime").val();//入住时间
                    var province = $("#up_province").find("option:selected").val();//省

                    $("#vu_teamName").html("");
                    $("#vu_badge").html("");
                    $("#vu_home").html("");
                    $("#vu_enterTime").html("");
                    $("#vu_PCA").html("");

                    if(!teamName.length>0){
                        $("#vu_teamName").html("请输入球队名称");
                        return;
                    }
                    /*if(!badge.length>0){
                        $("#vu_badge").html("请选择队徽");
                        return;
                    }*/
                    if(!home.length>0){
                        $("#vu_home").html("请输入常驻场地");
                        return;
                    }
                    if(!enterTime.length>0){
                        $("#vu_enterTime").html("请选择创建时间");
                        return;
                    }
                    if(province==0){
                        $("#vu_PCA").html("请选择省");
                        return;
                    }

                    if(badge.length>0) {
                        var options = {
                            url: "/upload/upload",
                            type: "post",
                            async:false,
                            success: function (data) {
                                $("#update_badge").val(data);
                            },
                            error: function (data) { swal("添加失败","网络异常！","error"); return;},//请求失败的函数
                        }
                        $("#updateForm").ajaxSubmit(options); //xx 你form表单的id,或者直接$("form").ajaxSubmit(options);
                    }

                    if(background.length>0) {
                        var options = {
                            url: "/upload/upload2",
                            type: "post",
                            async:false,
                            success: function (data) {
                                $("#update_background").val(data);
                            },
                            error: function (data) { swal("添加失败","网络异常！","error"); return;},//请求失败的函数
                        }
                        $("#updateForm").ajaxSubmit(options); //xx 你form表单的id,或者直接$("form").ajaxSubmit(options);
                    }

                    //开始ajax操作
                    $("#updateForm").ajaxSubmit({
                        type: "POST",//提交类型
                        dataType: "json",//返回结果格式
                        url: "/team/upTeam",//请求地址
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
                        async:false
                    });


                });
            }
        });

        //解散窗口
        $("#btn_disband").bind("click",function(){
            var selectedId = $("#list4").jqGrid("getGridParam", "selrow");
            var select= jQuery("#list4").jqGrid('getRowData', selectedId);
            if (selectedId == null){
                swal("信息","请选择需要解散的球队！","info");
            }else{
                $("#disband_winfrom").modal('show');
                $("#disband").bind("click",function(){
                    $.ajax({
                        url:"/team/disbandTeam",
                        data:{
                            teamId:select.teamId
                        },
                        success:function(data){
                            if (data["error_code"] == 1500){
                                swal("系统错误!", data["msg"], "error");
                            }else if (data["error_code"] != 1200){
                                swal("操作失败!",data["msg"],"warning");
                            }else{
                                $("#disband_winfrom").modal('hide');
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

    });


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
                            <label>球队名称</label>
                            <input type="text" id="s_teamName" name="teamName" class="form-control"/>
                        </div>

                        <div class="form-group">
                            <label>球队类型</label>
                            <select class="form-control" id="s_teamType">
                                <option value="0" selected="selected">全部</option>
                                <option value="1">校园球队</option>
                                <option value="2">业余爱好</option>
                                <option value="3">公司球队</option>
                                <option value="4">青少年球队</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>是否解散</label>
                            <select class="form-control" id="s_status">
                                <option value="0" selected="selected">全部</option>
                                <option value="2">是</option>
                                <option value="1">否</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>所在地区</label>
                            <select class="form-control" id="s_province" style="width:160px">
                            </select>
                            <select class="form-control" id="s_city" style="width:160px">
                                <option value="0" selected="selected">全部</option>
                            </select>
                            <select class="form-control" id="s_area" style="width:160px">
                                <option value="0" selected="selected">全部</option>
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
                    <h2 class="modal-title" id="myModalLabel">球队添加</h2>
                    <input type="hidden" id="add_badge" name="badge" class="form-control"/>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-5 control-label">球队名称<span class="validt" id="va_teamName"></span></label>
                        <input type="text" id="teamName" name="teamName" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">球队类型<span class="validt" id="va_teamType"></span></label>
                        <select class="form-control" id="teamType" name="teamType">
                            <option value="1">校园球队</option>
                            <option value="2">业余爱好</option>
                            <option value="3">公司球队</option>
                            <option value="4">青少年球队</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">球队队徽<span class="validt" id="va_badge"></span></label>
                        <input type="file" id="badge" name="file" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">常驻场地<span class="validt" id="va_home"></span></label>
                        <input type="text" id="home" name="home" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">球队创建时间<span class="validt" id="va_enterTime"></span></label>
                        <input  id="enterTime" name="enterTime" type="text"  class="form-control"  onfocus="WdatePicker({lang:'zh-cn',dateFmt: 'yyyy-MM-dd HH:mm'})" style="width:186px" />
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
                    <h2 class="modal-title">球队修改</h2>
                    <input type="hidden" id="up_teamId" name="teamId"/>
                    <input type="hidden" id="update_badge" name="badge" class="form-control"/>
                    <input type="hidden" id="update_background" name="background" class="form-control"/>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-5 control-label">球队名称<span class="validt" id="vu_teamName"></span></label>
                        <input type="text" id="up_teamName" name="teamName" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">球队类型<span class="validt" id="vu_teamType"></span></label>
                        <select class="form-control" id="up_teamType" name="teamType">
                            <option value="1">校园球队</option>
                            <option value="2">业余爱好</option>
                            <option value="3">公司球队</option>
                            <option value="4">青少年球队</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">球队队徽<span class="validt" id="vu_badge"></span></label>
                        <input type="file" id="up_badge" name="file" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">球队背景图<span class="validt" id="vu_background"></span></label>
                        <input type="file" id="up_background" name="file2" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">常驻场地<span class="validt" id="vu_home"></span></label>
                        <textarea  id="up_home" name="home" class="form-control" onfocus="if(value=='多个请用逗号隔开') {value=''}" onblur="if(value=='') {value='多个请用逗号隔开'}"></textarea>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">球队创建时间<span class="validt" id="vu_enterTime"></span></label>
                        <input  id="up_enterTime" name="enterTime" type="text"  class="form-control"  onfocus="WdatePicker({lang:'zh-cn',dateFmt: 'yyyy-MM-dd HH:mm'})" style="width:186px" />
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
                        <label class="col-sm-5 control-label">球队标签<span class="validt" id="vu_label"></span></label>
                        <textarea  id="up_label" name="label" class="form-control" onfocus="if(value=='多个请用逗号隔开') {value=''}" onblur="if(value=='') {value='多个请用逗号隔开'}"></textarea>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">球队邀请码<span class="validt" id="vu_invite"></span></label>
                        <input type="text" id="up_invite" name="invite"  class="form-control" />
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

<div id="disband_winfrom" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title">确定解散此球队吗？此操作不可撤销</h2>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="disband">确定</button>
            </div>
        </div>
    </div>
</div>




</body>
</html>