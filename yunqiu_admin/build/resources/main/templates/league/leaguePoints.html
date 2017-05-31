<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="renderer" content="webkit"/>
    <title>云球 -榜单</title>

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
        $("#user_yellow_list").jqGrid({
            url:'/leaguePoints/selectPoints',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            height:$(window).height()-239,
            postData:{"type":4},
            autowidth:true,//自动宽
            shrinkToFit:true,
            colModel:[
                {label:"Id",name:'teamcolorId',index:'teamcolorId', width:'15%',align:'center',hidden: true},
                {label:"球队排名",name:'currentRanking',index:'currentRanking', width:'35%',align:'center'},
                {label:"球队名称",name:'teamName',index:'teamName', width:'35%',align:'center'},
                {label:"分组",name:'scheduleName',index:'scheduleName', width:'35%',align:'center'},
                {label:"比赛次数",name:'gameNumber',index:'gameNumber', width:'35%',align:'center'},
                {label:"胜利次数",name:'victory',index:'victory', width:'35%',align:'center'},
                {label:"失败次数",name:'negation',index:'negation', width:'35%',align:'center'},
                {label:"平局次数",name:'flat',index:'flat', width:'35%',align:'center'},
                {label:"进/失",name:'goal',index:'goal', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return cellvalue+"/"+rowObject.lose;
                    }
                },
                {label:"积分",name:'integral',integral:'res', width:'35%',align:'center'}
            ],
            pager:'#user_yellow_gridPager',
            viewrecords: true,//是否在浏览导航栏显示记录总数
            rowNum:10, //每页显示记录数
            rownumbers: true,
            sortname: "victory",
            sortorder: "desc",
            rowList:[10,30,50],//用于改变显示行数的下拉列表框的元素数组。
            jsonReader:{
                id: "teamcolorId",//设置返回参数中，表格ID的名字为blackId
                root: "list", // json中代表实际模型数据的入口
                page: "page", // json中代表当前页码的数据
                total: "total", // json中代表页码总数的数据
                records: "records", // json中代表数据行总数的数据
                repeatitems : false
            },
            add:false,
            edit:false,
        });
        $("#user_yellow_list").jqGrid("navGrid","#user_yellow_gridPager",{edit:false,add:false,del:false,search:false},{reloadAfterSubmit:true,});

        $(window).bind("resize",function(){
            var width=$("#user_yellow_divWidth").width();
            $("#user_yellow_list").setGridWidth(width);
        });


        //添加窗口
        $("#btn_add").bind("click",function(){

            $.ajax({
                url:"/integrate/getIntegrate",
                success:function(data){
                    $("#integrateId").val(data.id);
                    $("#win").val(data.win);
                    $("#fail").val(data.fail);
                    $("#tie").val(data.tie);
                },error:function(){
                    swal("删除失败","网络异常！");
                }
            });

            $("#add_winfrom").modal('show');
            $("#add").unbind("click");

            $("#add").bind("click",function(){
                //开始ajax操作
                $("#addForm").ajaxSubmit({
                    type: "POST",//提交类型
                    dataType: "json",//返回结果格式
                    url: "/integrate/addIntegrate",//请求地址
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

    });



</script>

<div>
    <div class="ibox-title">
        <a data-toggle="modal" style="margin-right: 10px;" class="btn btn-primary" id="btn_add">积分规则</a>
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
                                <div class="ibox-content" id="user_yellow_ibox-content">
                                    <div class="jqGrid_wrapper" id="user_yellow_divWidth">
                                        <table id="user_yellow_list"></table>
                                        <div id="user_yellow_gridPager"></div>
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
                <input  type="hidden" id="integrateId" name="id"/>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-5 control-label">胜利积分</label>
                        <input type="text" id="win" name="win" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">失败积分</label>
                        <input type="text" id="fail" name="fail" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">平局积分</label>
                        <input type="text" id="tie" name="tie" class="form-control"/>
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