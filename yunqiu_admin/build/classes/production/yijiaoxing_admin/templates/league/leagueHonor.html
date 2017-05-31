<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="renderer" content="webkit"/>
    <title>云球 -队服颜色</title>

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
            url:'/leagueHonor/select',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            height:$(window).height()-239,
            autowidth:true,//自动宽
            shrinkToFit:true,
            colModel:[
                {label:"主键",name:'zid',index:'zid', width:'15%',align:'center',hidden: true},
                {label:"主键",name:'honorId',index:'honorId', width:'15%',align:'center',hidden: true},
                {label:"奖项名称",name:'honorName',index:'honorName', width:'35%',align:'center'},
                {label:"奖项图片",name:'imgUrl',index:'imgUrl', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==undefined){
                            return "";
                        }else if(cellvalue==""){
                            return "";
                        }else{
                            return '<img  style="width: 88px;height: 81px;" src="http://picture-services.b0.upaiyun.com/'+cellvalue+'"/>';
                        }
                    }
                },
                {label:"荣誉排名",name:'sorting',index:'sorting', width:'35%',align:'center'},
                {label:"球员名称",name:'nickname',index:'nickname', width:'35%',align:'center'},
                {label:"球员头像",name:'portrait',index:'portrait', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==undefined){
                            return "";
                        }else if(cellvalue==""){
                            return "";
                        }else{
                            return '<img  style="width: 88px;height: 81px;" src="http://picture-services.b0.upaiyun.com/'+cellvalue+'"/>';
                        }
                    }
                },
                {label:"所属球队",name:'teamName',index:'teamName', width:'35%',align:'center'},
                {label:"球队队徽",name:'badge',index:'badge', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==undefined){
                            return "";
                        }else if(cellvalue==""){
                            return "";
                        }else{
                            return '<img  style="width: 88px;height: 81px;" src="http://picture-services.b0.upaiyun.com/'+cellvalue+'"/>';
                        }
                    }
                },
                {label:"创建时间",name:'gainTime',index:'gainTime', width:'35%',align:'center',formatter:"date",
                    formatter:function(cellvalue, options, rowObject){
                        if (typeof(cellvalue)=="undefined" || typeof(cellvalue)=="" || cellvalue==0){
                            return "";
                        }
                        var date = new Date(cellvalue);

                        return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
                    }
                }
            ],
            pager:'#gridPager',
            viewrecords: true,//是否在浏览导航栏显示记录总数
            rowNum:10, //每页显示记录数
            rownumbers: true,
            sortname: "gainTime",
            sortorder: "desc",
            rowList:[10,30,50],//用于改变显示行数的下拉列表框的元素数组。
            jsonReader:{
                id: "zid",//设置返回参数中，表格ID的名字为blackId
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
            var colour=$("#s_colour").val();

            $("#list4").jqGrid('setGridParam',{
                datatype:'json',
                url:'/teamcolour/select',
                page:1,
                postData:{
                    'colour':colour
                }
            }).trigger("reloadGrid"); //重新载入
        });


        //添加窗口
        $("#btn_add").bind("click",function(){
            $("#add_winfrom").modal('show');
            $("#add").unbind("click");

            $("#add").bind("click",function(){
                //开始ajax操作
                honorName = $("#honorName").val();
                sorting = $("#sorting").val();
                file = $("#file").val();

                $("#va_honorName").html("");
                $("#va_sorting").html("");

                if(!honorName.length>0){
                    $("#va_honorName").html("请输入荣誉名称");
                    return;
                }

                if(!honorName.length>0){
                    $("#va_sorting").html("请输入排序");
                    return;
                }

                if(file.length>0) {
                    var options = {
                        url: "/upload/upload",
                        type: "post",
                        async:false,
                        success: function (data) {
                            $("#add_imgUrl").val(data);
                        },
                        error: function (data) { swal("添加失败","网络异常！","error"); return;},//请求失败的函数
                    }
                    $("#addForm").ajaxSubmit(options); //xx 你form表单的id,或者直接$("form").ajaxSubmit(options);
                }



                $("#addForm").ajaxSubmit({
                    type: "POST",//提交类型
                    dataType: "json",//返回结果格式
                    url: "/leagueHonor/addLeagueHonor",//请求地址
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

        //添加窗口
        $("#btn_add_team").bind("click",function(){
            $("#add_team_winfrom").modal('show');
            $("#add_team").unbind("click");

            $.ajax({
                url: "/leagueHonor/getLeagueHonorList",
                async : false,
                datatype:"json",
                type:"get",
                success: function (data) {
                    $("#team_honorName").empty();
                    items = data["data"];
                    var selectList = "";
                    $.each(eval(items), function (idx, obj) {
                        selectList += '<option value="'+obj.honorId+'" >'+obj.honorName+'</option>';
                    });
                    $("#team_honorName").append(selectList);
                }
            });

            //获取荣誉名称的值
            var teamHonorNameVal = $("#team_honorName").val();

            $.ajax({
                url: "/leagueHonor/getLeagueTY",
                async : false,
                datatype:"json",
                data:{"honorId":teamHonorNameVal},
                type:"get",
                success: function (data) {
                    $("#gl_id").empty();
                    items = data["data"];
                    var selectList = "";
                    $.each(eval(items), function (idx, obj) {
                        selectList += '<option value="'+obj.id+'" >'+obj.name+'</option>';
                    });
                    $("#gl_id").append(selectList);
                }
            });


            $("#add_team").bind("click",function(){

                var honorId = $("#team_honorName").val();
                var glId = $("#gl_id").val();

                $("#addTeamForm").ajaxSubmit({
                    type: "POST",//提交类型
                    dataType: "json",//返回结果格式
                    url: "/leagueHonor/addLeagueHonorGL",//请求地址
                    data:{"honorId":honorId,"glId":glId},
                    success: function (data) {//请求成功后的函数
                        if (data["error_code"] == 1500){
                            swal("系统错误!", data["msg"], "error");
                        }else if (data["error_code"] != 1200){
                            swal("操作失败!",data["msg"],"warning");
                        }else{
                            $("#add_team_winfrom").modal('hide');
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

                $.ajax({
                    url: "/leagueHonor/getLeagueHonorList",
                    async : false,
                    datatype:"json",
                    type:"get",
                    success: function (data) {
                        $("#up_honorName").empty();
                        items = data["data"];
                        var selectList = "";
                        $.each(eval(items), function (idx, obj) {
                            selectList += '<option value="'+obj.honorId+'" >'+obj.honorName+'</option>';
                        });
                        $("#up_honorName").append(selectList);
                    }
                });

                $("#up_honorName option:contains('"+select.honorName+"')").prop("selected", true);

                //获取荣誉名称的值
                var teamHonorNameVal = $("#up_honorName").val();

                $.ajax({
                    url: "/leagueHonor/getLeagueTY",
                    async : false,
                    datatype:"json",
                    data:{"honorId":teamHonorNameVal},
                    type:"get",
                    success: function (data) {
                        $("#up_team_honorType").empty();
                        items = data["data"];
                        var selectList = "";
                        $.each(eval(items), function (idx, obj) {
                            selectList += '<option value="'+obj.id+'" >'+obj.name+'</option>';
                        });
                        $("#up_team_honorType").append(selectList);
                    }
                });

                $("#up_team_honorType").find("option[value="+select.glId+"]").prop("selected",true);

                $("#update_winfrom").modal('show');

                $("#update").bind("click",function(){

                    //开始ajax操作
                    $("#updateForm").ajaxSubmit({
                        type: "POST",//提交类型
                        dataType: "json",//返回结果格式
                        url: "/leagueHonor/upLeagueHonorGL",//请求地址
                        data:{"zid":select.zid,"honorId":$("#up_honorName").val(),"glId":$("#up_team_honorType").val()},
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
                swal("球队标签","请选择需要删除的信息！","info");
            }else{
                $("#delete_winfrom").modal('show');
                $("#delete").bind("click",function(){
                    $.ajax({
                        url:"/leagueHonor/delLeagueHonor",
                        data:{
                            honorId:select.honorId,
                            zid:select.zid
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

    });

    function changeVar(){
        var teamHonorNameVal = $("#team_honorName").val();
        $.ajax({
            url: "/leagueHonor/getLeagueTY",
            async : false,
            datatype:"json",
            data:{"honorId":teamHonorNameVal},
            type:"get",
            success: function (data) {
                $("#gl_id").empty();
                items = data["data"];
                var selectList = "";
                $.each(eval(items), function (idx, obj) {
                    selectList += '<option value="'+obj.id+'" >'+obj.name+'</option>';
                });
                $("#gl_id").append(selectList);
            }
        });
    }


</script>

<div  id="user_button">
    <div class="ibox-title">
        <a data-toggle="modal" style="margin-right: 10px;" class="btn btn-primary" id="btn_add">新建荣誉</a>
        <a data-toggle="modal" style="margin-right: 10px;" class="btn btn-primary" id="btn_edit">编辑</a>
        <a data-toggle="modal" style="margin-right: 10px;" class="btn btn-primary" id="btn_delete">删除</a>
        <a data-toggle="modal" style="margin-right: 10px;" class="btn btn-primary" id="btn_add_team">关联球员/球队</a>
    </div>
</div>


<div class="wrapper wrapper-content animated">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox ">
                <!--<div class="ibox-title">
                    <form class="form-inline">

                        <div class="form-group">
                            <label>队服颜色</label>
                            <input type="text" id="s_colour" name="colour" class="form-control"/>
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
                    <h2 class="modal-title" id="myModalLabel">添加</h2>
                    <input id="add_imgUrl" type="hidden" name="imgUrl"/>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-5 control-label">荣誉名称<span class="validt" id="va_honorName"></span></label>
                        <input type="text" id="honorName" name="honorName" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">荣誉图片<span class="validt" id="va_file"></span></label>
                        <input type="file" id="file" name="file" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">排序<span class="validt" id="va_sorting"></span></label>
                        <input type="text" id="sorting" name="sorting" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">荣誉类型<span class="validt" id="va_honorType"></span></label>
                        <select  id="honorType" name="honorType" class="form-control">
                            <option value="1">球员</option>
                            <option value="2">球队</option>
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

<form id="addTeamForm">
    <div id="add_team_winfrom" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h2 class="modal-title" id="">添加</h2>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-5 control-label">荣誉名称</label>
                        <select id="team_honorName" name="team_honorName" class="form-control" onchange="changeVar()">

                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">获奖球员/球队<span class="validt" id="va_glId"></span></label>
                        <select  id="gl_id" name="team_honorType" class="form-control">
                        </select>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="add_team">添加</button>
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
                    <h2 class="modal-title">修改</h2>
                    <input type="hidden" id="up_teamcolorId" name="teamcolorId"/>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label class="col-sm-5 control-label">荣誉名称</label>
                        <select id="up_honorName" name="team_honorName" class="form-control" onchange="changeVar()">

                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">获奖球员/球队</label>
                        <select  id="up_team_honorType" name="team_honorType" class="form-control">
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
                <h2 class="modal-title">确定要删除吗？此操作不可被撤销</h2>
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