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
            url:'/gameMember/select',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            height:$(window).height()-239,
            autowidth:true,//自动宽
            shrinkToFit:true,
            colModel:[
                {label:"Id",name:'memberId',index:'memberId', width:'15%',align:'center',hidden:true},
                {label:"userId",name:'userId',index:'userId', width:'15%',align:'center',hidden:true},
                {label:"teamId",name:'teamId',index:'teamId', width:'15%',align:'center',hidden:true},
                {label:"所属球队",name:'teamName',index:'teamName', width:'35%',align:'center'},
                {label:"主客场", name: '', index: '', width: '35%', align: 'center',
                    formatter:function(cellvalue, options, rowObject){
                        return "主场："+rowObject.entryTeamA+"<br/>"+"客场："+rowObject.entryTeamB;
                    }
                },
                {label:"球员姓名",name:'nickname',index:'nickname', width:'35%',align:'center'},
                {label:"球员头像",name:'portrait',index:'portrait', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==undefined){
                            return "";
                        }else{
                            return '<img  style="width: 88px;height: 81px;" src="http://picture-services.b0.upaiyun.com/'+cellvalue+'"/>';
                        }
                    }
                },
                {label:"参赛状态",name:'status',index:'status', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){
                            return '正常';
                        }else if(cellvalue==2){
                            return '待定';
                        }else if(cellvalue==3){
                            return '请假';
                        }else{
                            return "";
                        }
                    }
                },
                {label:"操作",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        var text = "'"+rowObject.userId+"','"+rowObject.teamId+"',"+rowObject.status+"";
                        return '<a href="javascript:updateStatus('+text+')">编辑</a> ';
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
                id: "memberId",//设置返回参数中，表格ID的名字为blackId
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
    });


    function updateStatus(userId,teamId,status){

        $("#update").unbind("click");

        $("#up_userId").val(userId);
        $("#up_teamId").val(teamId);
        $("#up_status").find("option[value="+status+"]").prop("selected",true);

        $("#update_winfrom").modal('show');

        $("#update").bind("click",function(){
            //开始ajax操作
            $("#updateForm").ajaxSubmit({
                type: "POST",//提交类型
                dataType: "json",//返回结果格式
                url: "/gameMember/upStatus",//请求地址
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


</script>


<div  id="user_button" >


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


<form id="updateForm">
    <div id="update_winfrom" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <input type="hidden" id="up_matchId"  name="matchId"/>
                    <input type="hidden" id="up_userId"  name="userId"/>
                    <input type="hidden" id="up_teamId"  name="teamId"/>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">参赛状态</label>
                        <select id="up_status" name="status" class="form-control">
                            <option value="1">正常</option>
                            <option value="2">待定</option>
                            <option value="3">请假</option>
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

</body>
</html>