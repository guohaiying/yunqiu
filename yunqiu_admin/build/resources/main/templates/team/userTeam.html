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
            url:'/teamMember/select',
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
                {label:"球队队徽",name:'badge',index:'badge', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==undefined){
                            return "";
                        }else{
                            return '<img  style="width: 88px;height: 81px;" src="http://picture-services.b0.upaiyun.com/'+cellvalue+'"/>';
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
                        if(cellvalue==undefined){
                            return "";
                        }else{
                            return cellvalue;
                        }
                    }
                },
                {label:"球队标签",name:'label',index:'label', width:'35%',align:'center'},
                {label:"球队人数",name:'tpcount',index:'tpcount', width:'35%',align:'center' },
                {label:"参与比赛",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return '详情';
                    }
                },
                {label:"球队数据",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return '详情';
                    }
                },
                {label:"出勤率",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return "50%";
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
            sortname: "enqueueTime",
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

        //获取用户名称
        $.ajax({
            url: "/teamMember/getUserName",
            success: function (data) {
                if (data["error_code"] == 1500) {
                    swal("系统错误!", data["msg"], "error");
                } else if (data["error_code"] != 1200) {
                    swal("操作失败!", data["msg"], "warning");
                } else {
                    $("#userName").empty();
                    $("#userName").text(data["data"]);
                }
            }, error: function () {
                swal("删除失败", "网络异常！");
            }
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

                var teamId = $("#teamId").find("option:selected").val();//球队
                var type = $("#type").find("option:selected").val();//球队

                $("#va_teamId").html("");
                $("#va_type").html("");

                if(!teamId.length>0){
                    $("#va_teamId").html("请选择球队");
                    return;
                }
                if(!type.length>0){
                    $("#va_type").html("请选择加入方式");
                    return;
                }

                //开始ajax操作
                $("#addForm").ajaxSubmit({
                    type: "POST",//提交类型
                    dataType: "json",//返回结果格式
                    url: "/teamMember/addTeamMember",//请求地址
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

        //球队
        $.ajax({
            url:"/team/getTeamList",
            success:function(data){
                $("#teamId").empty();
                items = data["data"];
                var selectList = "";
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

<div>
    <div class="ibox-title">
        <a data-toggle="modal" style="margin-right: 10px;" class="btn btn-primary" id="btn_add">添加</a>
    </div>
</div>

<div style="font-size: 25px;margin-left: 38px;margin-top: 39px;" id="userName">

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
                <div class="modal-header">
                    <h2 class="modal-title" id="myModalLabel">加入球队</h2>
                    <input type="hidden" name="identity" value="5"/>
                    <input type="hidden" name="jurisdiction" value="0"/>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-5 control-label">加入球队名称<span class="validt" id="va_teamId"></span></label>
                        <select id="teamId" name="teamId"  class="form-control">

                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">加入方式<span class="validt" id="va_type"></span></label>
                        <select class="form-control" id="type" name="status">
                            <option value="2">直接加入</option>
                            <option value="1">审核加入</option>
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

</body>
</html>