<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="renderer" content="webkit"/>
    <title>云球 - 用户</title>

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
            url:'/user/select',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            height:$(window).height()-239,
            autowidth:true,//自动宽
            shrinkToFit:true,
            colModel:[
                {label:"账户Id",name:'authId',index:'authId', width:'15%',align:'center',hidden: true},
                {label:"用户Id",name:'userId',index:'userId', width:'15%',align:'center',hidden: true},
                {label:"身高",name:'stature',index:'stature', width:'15%',align:'center',hidden: true},
                {label:"体重",name:'weight',index:'weight', width:'15%',align:'center',hidden: true},
                {label:"省",name:'province',index:'province', width:'15%',align:'center',hidden: true},
                {label:"市",name:'city',index:'city', width:'15%',align:'center',hidden: true},
                {label:"区",name:'area',index:'area', width:'15%',align:'center',hidden: true},
                {label:"个人标签",name:'label',index:'label', width:'15%',align:'center',hidden: true},
                {label:"惯用脚",name:'foot',index:'foot', width:'15%',align:'center',hidden: true},
                {label:"擅长位置",name:'position',index:'position', width:'15%',align:'center',hidden: true},
                {label:"头像",name:'portrait',index:'portrait', width:'15%',align:'center',hidden: true},
                {label:"登录账户",name:'identifier',index:'identifier', width:'35%',align:'center'},
                {label:"认领状态",name:'standby',index:'standby', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==0){
                            return "无";
                        }else if(cellvalue==1){
                            return "待认领";
                        }else if(cellvalue==2){
                            return "已认领";
                        }else{
                            return "无";
                        }
                    }
                },
                {label:"昵称",name:'nickname',index:'nickname', width:'20%',align:'center',key:true },
                {label:"性别",name:'sex',index:'sex', width:'15%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){
                            return "男";
                        }else if(cellvalue==2){
                            return "女";
                        }else{
                            return "";
                        }
                    }
                },
                {label:"头像",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(rowObject.portrait==undefined){
                            return "";
                        }else if(rowObject.portrait==""){
                            return "";
                        }else{
                            return '<img  style="width: 88px;height: 81px;" src="http://picture-services.b0.upaiyun.com/'+rowObject.portrait+'"/>';
                        }
                    }
                },
                {label:"年龄",name:'age',index:'age', width:'35%',align:'center',hidden: true},
                {label:"出生日期",name:'birthday',index:'birthday', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if (typeof(cellvalue)=="undefined" || typeof(cellvalue)=="" || cellvalue==0){
                            return "";
                        }
                        var date = new Date(cellvalue);

                        return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
                    }
                },
                {label:"地区",name:'pca',index:'pca', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return rowObject.province+" "+rowObject.city+" "+rowObject.area;
                    }
                },
                {label:"球员数据",name:'qiuyuan',index:'qiuyuan', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return '<a href="/view/go_userData?userId='+rowObject.userId+'">详情</a>';
                    }
                },
                {label:"所属球队",name:'qiudui',index:'qiudui', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return '<a href="/view/go_UserTeam?userId='+rowObject.userId+'">详情</a>';
                    }
                },
                {label:"是否生效",name:'status',index:'status', width:'35%',align:'center',
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
            sortname: "nickname",
            sortorder: "desc",
            rowList:[10,30,50],//用于改变显示行数的下拉列表框的元素数组。
            jsonReader:{
                id: "authId",//设置返回参数中，表格ID的名字为blackId
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
            var identifier=$("#s_identifier").val();
            var nickname=$("#s_nickname").val();
            var sex=$("#s_sex").val();
            var status=$("#s_status").val();

            $("#list4").jqGrid('setGridParam',{
                datatype:'json',
                url:'/user/select',
                page:1,
                postData:{
                    'identifier':identifier,
                    'nickname':nickname,
                    'sex':sex,
                    'status':status
                }
            }).trigger("reloadGrid"); //重新载入
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
                selectList += '<option  value="0" >请选择</option>';
                $.each(eval(items), function (idx, obj) {
                    selectList += '<option value="'+obj.provinceId+'" >'+obj.province+'</option>';
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
                        selectList += '<option value="'+obj.cityId+'" >'+obj.city+'</option>';
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
                        selectList += '<option  value="'+obj.areaId+'" >'+obj.area+'</option>';
                    });
                    $("#up_area").append(selectList);
                }
            });
        });


        //添加窗口
        $("#btn_add").bind("click",function(){
            $("#add_winfrom").modal('show');
            $("#add").unbind("click");

            $("#add").bind("click",function(){

                var nickname=$("#nickname").val();//姓名
                var identifier = $("#identifier").val();//手机号
                var teamId = $("#teamId").find("option:selected").val();//所属球队
                var type = $("#type").find("option:selected").val();//加入方式
                var jerseyNo=$("#jerseyNo").val();//球衣号码

                $("#va_nickname").html("");
                $("#va_teamId").html("");
                $("#va_type").html("");
                $("#va_jerseyNo").html("");
                $("#va_identifier").html("");

                if(!nickname.length>0){
                    $("#va_nickname").html("请输入姓名");
                    return;
                }
                if(!identifier.length>0){//手机号没填
                    if(teamId==0){
                        $("#va_teamId").html("请选择所属球队");
                        return;
                    }
                    if(type==0){
                        $("#va_type").html("请选择加入方式");
                        return;
                    }
                    if(!jerseyNo.length>0){
                        $("#va_jerseyNo").html("请输入球衣号码");
                        return;
                    }
                }else{
                    if(!identifier.length>0){
                        $("#va_identifier").html("请输入手机号码");
                        return;
                    }
                }

                //开始ajax操作
                $("#addForm").ajaxSubmit({
                    type: "POST",//提交类型
                    dataType: "json",//返回结果格式
                    url: "/user/addUser",//请求地址
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

                $("#update_portrait").val(select.portrait);
                $("#up_userId").val(select.userId);
                $("#up_credential").val(select.credential);
                $("#up_identifier").val(select.identifier);
                $("#up_nickname").val(select.nickname);
                if(select.sex==""){
                    $("#up_sex option:contains('男')").prop("selected", true);
                }else{
                    $("#up_sex option:contains('"+select.sex+"')").prop("selected", true);
                }
                $("#up_birthday").val(select.birthday);
                $("#up_stature").val(select.stature);
                $("#up_weight").val(select.weight);
                if(select.province!=""){
                    $("#up_province option:contains('"+select.province+"')").prop("selected", true);

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
                            selectList += '<option  value="0" >请选择</option>';
                            $.each(eval(items), function (idx, obj) {
                                selectList += '<option value="'+obj.cityId+'" >'+obj.city+'</option>';
                            });
                            $("#up_city").append(selectList);
                        }
                    });

                    if(select.city!="") {
                        $("#up_city option:contains('"+select.city+"')").prop("selected", true);
                    }
                    var cityId = $("#up_city").val();
                    $.ajax({
                        url: "/zone/getAreaList",
                        async: false,
                        datatype: "json",
                        type: "get",
                        data: {"cityId": cityId},
                        success: function (data) {
                            $("#up_area").empty();
                            items = data["data"];
                            var selectList = "";
                            selectList += '<option  value="0" >请选择</option>';
                            $.each(eval(items), function (idx, obj) {
                                selectList += '<option  value="' + obj.areaId + '" >' + obj.area + '</option>';
                            });
                            $("#up_area").append(selectList);
                        }
                    });
                    if(select.area!="") {
                        $("#up_area option:contains('"+select.area+"')").prop("selected", true);
                    }
                }
                $("#up_foot").find("option[value='"+select.foot+"']").attr("selected",true);
                $("#up_position option:contains('"+select.position+"')").prop("selected", true);
                $("#up_label").html(select.label);

                $("#update").bind("click",function(){

                    var portrait = $("#file").val();

                    if(!portrait.length > 0){
                        //开始ajax操作
                        $("#updateForm").ajaxSubmit({
                            type: "POST",//提交类型
                            dataType: "json",//返回结果格式
                            url: "/user/upUser",//请求地址
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
                    }else{
                        //上传图片
                        var options={
                            url: "/upload/upload",
                            type:"post",
                            success: function (data) {

                                $("#update_portrait").val(data);

                                //开始ajax操作
                                $("#updateForm").ajaxSubmit({
                                    type: "POST",//提交类型
                                    dataType: "json",//返回结果格式
                                    url: "/user/upUser",//请求地址
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
                            }
                        }
                        $("#updateForm").ajaxSubmit(options); //xx 你form表单的id,或者直接$("form").ajaxSubmit(options);
                    }

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


        //球队
        $.ajax({
            url:"/team/getTeamList",
            success:function(data){
                $("#teamId").empty();
                items = data["data"];
                var selectList = "";
                selectList += '<option value="0">请选择</option>'
                $.each(eval(items), function (idx, obj) {
                    selectList += '<option value="'+obj.teamId+'" >'+obj.teamName+'</option>';
                });
                $("#teamId").append(selectList);
            },error:function(){
                swal("获取失败","网络异常！");
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
                            <label>登录账户</label>
                            <input type="text" id="s_identifier" name="identifier" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>昵称</label>
                            <input type="text" id="s_nickname" name="nickname" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>性别</label>
                            <select class="form-control" id="s_sex">
                                <option value="0" selected="selected">全部</option>
                                <option value="1">男</option>
                                <option value="2">女</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>状态</label>
                            <select class="form-control" id="s_status">
                                <option value="0" selected="selected">全部</option>
                                <option value="1">正常</option>
                                <option value="2">冻结</option>
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
                        <label class="col-sm-5 control-label">姓名<span class="validt" id="va_nickname"></span></label>
                        <input type="text" id="nickname" name="nickname" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">手机号<span class="validt" id="va_identifier"></span></label>
                        <input type="text" id="identifier" name="identifier" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label" >所属球队<span class="validt" id="va_teamId"></span></label>
                        <select id="teamId" name="teamId"  class="form-control">
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">加入方式<span class="validt" id="va_type"></span></label>
                        <select id="type" name="type" class="form-control">
                            <option value="0">请选择</option>
                            <option value="2">直接加入</option>
                            <option value="1">审核加入</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">球衣号码<span class="validt" id="va_jerseyNo"></span></label>
                        <input type="text" id="jerseyNo" name="jerseyNo" class="form-control"/>
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
                    <input type="hidden" id="up_userId" name="userId"/>
                    <input type="hidden" id="update_portrait" name="portrait"/>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-5 control-label">密码</label>
                        <input type="password" id="up_credential" name="credential"  class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">手机号</label>
                        <input type="text" id="up_identifier" name="identifier" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">姓名</label>
                        <input type="text" id="up_nickname" name="nickname" class="form-control"/>
                    </div>

                    <div class="form-group" >
                        <label class="col-sm-5 control-label">性别</label>
                        <select id="up_sex" name="sex" class="form-control">
                            <option value="1" selected="selected">男</option>
                            <option value="2">女</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">头像</label>
                        <input type="file" id="file" name="file" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">出生年月</label>
                        <input type="text" id="up_birthday" name="birthday" class="form-control" onfocus="WdatePicker({lang:'zh-cn',dateFmt: 'yyyy-MM-dd'})"  />
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">身高</label>
                        <input type="text" id="up_stature" name="stature" class="form-control" />
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">体重</label>
                        <input type="text" id="up_weight" name="weight" class="form-control" />
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
                        <label class="col-sm-5 control-label" >位置</label>
                        <select tabindex="4" id="up_position" name="position" class="form-control" multiple="multiple">
                            <option value="左边锋">左边锋</option>
                            <option value="前锋">前锋</option>
                            <option value="中锋">中锋</option>
                            <option value="影锋">影锋</option>
                            <option value="右边锋">右边锋</option>
                            <option value="左前卫">左前卫</option>
                            <option value="前腰">前腰</option>
                            <option value="中前卫">中前卫</option>
                            <option value="后腰">后腰</option>
                            <option value="右前卫">右前卫</option>
                            <option value="左翼卫">左翼卫</option>
                            <option value="左后卫">左后卫</option>
                            <option value="中后卫">中后卫</option>
                            <option value="清道夫">清道夫</option>
                            <option value="右后卫">右后卫</option>
                            <option value="右翼卫">右翼卫</option>
                            <option value="门将">门将</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">惯用脚</label>
                        <select id="up_foot" name="foot" class="form-control" >
                            <option value="0">无</option>
                            <option value="1">左脚</option>
                            <option value="2">右脚</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">球员标签</label>
                        <textarea id="up_label" name="label" class="form-control"></textarea>
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


</body>
</html>