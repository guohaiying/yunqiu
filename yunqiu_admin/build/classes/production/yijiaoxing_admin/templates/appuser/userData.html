<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="renderer" content="webkit"/>
    <title>云球 -球队数据</title>

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

        $(".ibox-content").hide();
        $("#team_shooter_ibox-content").show();
        teamShooterFun();

        $("#team_shooter").click(function(){
            $(".ibox-content").hide();
            $("#team_shooter_ibox-content").show();
            teamShooterFun();
        });

        $("#team_assisting").click(function(){
            $(".ibox-content").hide();
            $("#team_assisting_ibox-content").show();
            teamAssistingFun();
        });

        $("#team_red").click(function(){
            $(".ibox-content").hide();
            $("#team_red_ibox-content").show();
            teamRedFun();
        });


    });

    function teamShooterFun(){
        $("#team_shooter_list").jqGrid({
            url:'/teamcolour/select',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            height:$(window).height()-239,
            autowidth:true,//自动宽
            shrinkToFit:true,
            colModel:[
                {label:"Id",name:'teamcolorId',index:'teamcolorId', width:'15%',align:'center',hidden: true},
                {label:"所在球队",name:'colour',index:'colour', width:'35%',align:'center'},
                {label:"进球数",name:'colour',index:'colour', width:'35%',align:'center'},
                {label:"点球数",name:'colour',index:'colour', width:'35%',align:'center'},
                {label:"助攻数",name:'createTime',index:'createTime', width:'35%',align:'center'},
                {label:"红牌数",name:'createTime',index:'createTime', width:'35%',align:'center'},
                {label:"黄牌数",name:'createTime',index:'createTime', width:'35%',align:'center'},
                {label:"乌龙球",name:'createTime',index:'createTime', width:'35%',align:'center'},
                {label:"失败场数",name:'createTime',index:'createTime', width:'35%',align:'center'},
                {label:"比赛场次",name:'createTime',index:'createTime', width:'35%',align:'center'},
                {label:"操作",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return '<a href="javascript:jibenFun()">编辑</a>';
                    }
                }
            ],
            pager:'#team_shooter_gridPager',
            viewrecords: true,//是否在浏览导航栏显示记录总数
            rowNum:10, //每页显示记录数
            rownumbers: true,
            sortname: "createTime",
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
        $("#team_shooter_list").jqGrid("navGrid","#team_shooter_gridPager",{edit:false,add:false,del:false,search:false},{reloadAfterSubmit:true,});

        $(window).bind("resize",function(){
            var width=$("#team_shooter_divWidth").width();
            $("#team_shooter_list").setGridWidth(width);
        });
    }


    function teamAssistingFun(){
        $("#team_assisting_list").jqGrid({
            url:'/teamcolour/select',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            height:$(window).height()-239,
            autowidth:true,//自动宽
            shrinkToFit:true,
            colModel:[
                {label:"Id",name:'teamcolorId',index:'teamcolorId', width:'15%',align:'center',hidden: true},
                {label:"射门",name:'colour',index:'colour', width:'35%',align:'center'},
                {label:"射正",name:'colour',index:'colour', width:'35%',align:'center'},
                {label:"过人",name:'createTime',index:'createTime', width:'35%',align:'center'},
                {label:"传球",name:'createTime',index:'createTime', width:'35%',align:'center'},
                {label:"任意球",name:'createTime',index:'createTime', width:'35%',align:'center'},
                {label:"角球",name:'createTime',index:'createTime', width:'35%',align:'center'},
                {label:"抢断",name:'createTime',index:'createTime', width:'35%',align:'center'},
                {label:"解围",name:'createTime',index:'createTime', width:'35%',align:'center'},
                {label:"扑救",name:'createTime',index:'createTime', width:'35%',align:'center'},
                {label:"犯规",name:'createTime',index:'createTime', width:'35%',align:'center'},
                {label:"威胁传球",name:'createTime',index:'createTime', width:'35%',align:'center'},
                {label:"操作",name:'',index:'', width:'35%',align:'center',
                    formatter:function(cellvalue, options, rowObject){
                        return '<a href="javascript:quanxianFun()">编辑</a>';
                    }
                }
            ],
            pager:'#team_assisting_gridPager',
            viewrecords: true,//是否在浏览导航栏显示记录总数
            rowNum:10, //每页显示记录数
            rownumbers: true,
            sortname: "createTime",
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
        $("#team_assisting_list").jqGrid("navGrid","#team_assisting_gridPager",{edit:false,add:false,del:false,search:false},{reloadAfterSubmit:true,});

        $(window).bind("resize",function(){
            var width=$("#team_assisting_divWidth").width();
            $("#team_assisting_list").setGridWidth(width);
        });
    }


    function teamRedFun(){
        $("#team_red_list").jqGrid({
            url:'/teamcolour/select',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            height:$(window).height()-239,
            autowidth:true,//自动宽
            shrinkToFit:true,
            colModel:[
                {label:"Id",name:'teamcolorId',index:'teamcolorId', width:'15%',align:'center',hidden: true},
                {label:"时间",name:'colour',index:'colour', width:'35%',align:'center'},
                {label:"进攻",name:'colour',index:'colour', width:'35%',align:'center'},
                {label:"防守",name:'createTime',index:'createTime', width:'35%',align:'center'},
                {label:"身体",name:'createTime',index:'createTime', width:'35%',align:'center'},
                {label:"技术",name:'createTime',index:'createTime', width:'35%',align:'center'},
                {label:"侵略性",name:'createTime',index:'createTime', width:'35%',align:'center'},
                {label:"战斗值",name:'createTime',index:'createTime', width:'35%',align:'center'}
            ],
            pager:'#team_red_gridPager',
            viewrecords: true,//是否在浏览导航栏显示记录总数
            rowNum:10, //每页显示记录数
            rownumbers: true,
            sortname: "createTime",
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
        $("#team_red_list").jqGrid("navGrid","#team_red_gridPager",{edit:false,add:false,del:false,search:false},{reloadAfterSubmit:true,});

        $(window).bind("resize",function(){
            var width=$("#team_red_divWidth").width();
            $("#team_red_list").setGridWidth(width);
        });
    }
    
    function jibenFun() {
        $("#add_winfrom").modal('show');
        $("#add").unbind("click");
    }

    function quanxianFun() {
        $("#add_winfrom2").modal('show');
        $("#add2").unbind("click");
    }
    
</script>


<div class="wrapper wrapper-content animated">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox ">
                <div class="wrapper wrapper-content animated">
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="ibox ">
                                <div class="ibox-title">
                                    <button type="button" class="btn btn-default" id="team_shooter">基本数据</button>
                                    <button type="button" class="btn btn-default" id="team_assisting">全项数据</button>
                                    <button type="button" class="btn btn-default" id="team_red">云五数据</button>
                                </div>
                                <div class="ibox-content" id="team_shooter_ibox-content">
                                    <div class="jqGrid_wrapper" id="team_shooter_divWidth">
                                        <table id="team_shooter_list"></table>
                                        <div id="team_shooter_gridPager"></div>
                                    </div>
                                </div>
                                <div class="ibox-content" id="team_assisting_ibox-content">
                                    <div class="jqGrid_wrapper" id="team_assisting_divWidth">
                                        <table id="team_assisting_list"></table>
                                        <div id="team_assisting_gridPager"></div>
                                    </div>
                                </div>
                                <div class="ibox-content" id="team_red_ibox-content">
                                    <div class="jqGrid_wrapper" id="team_red_divWidth">
                                        <table id="team_red_list"></table>
                                        <div id="team_red_gridPager"></div>
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
                        <label class="col-sm-5 control-label">进球数调整值</label>
                        <input type="text" id="1" name="teamName" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">点球数调整值</label>
                        <input type="text" id="2" name="teamName" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">助攻数调整值</label>
                        <input type="text" id="3" name="teamName" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">红牌数调整值</label>
                        <input type="text" id="4" name="teamName" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">黄牌数调整值</label>
                        <input type="text" id="5" name="teamName" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">乌龙球调整值</label>
                        <input type="text" id="6" name="teamName" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">比赛场数调整值</label>
                        <input type="text" id="9" name="teamName" class="form-control"/>
                    </div>


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="add">确定</button>
                </div>
            </div>
        </div>
    </div>
</form>



<form id="addForm2">
    <div id="add_winfrom2" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-5 control-label">射门调整值</label>
                        <input type="text" id="a1" name="teamName" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">射正调整值</label>
                        <input type="text" id="a2" name="teamName" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">过人调整值</label>
                        <input type="text" id="a3" name="teamName" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">传球调整值</label>
                        <input type="text" id="a4" name="teamName" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">任意球调整值</label>
                        <input type="text" id="a5" name="teamName" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">角球调整值</label>
                        <input type="text" id="a6" name="teamName" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">抢断调整值</label>
                        <input type="text" id="a7" name="teamName" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">解围调整值</label>
                        <input type="text" id="a8" name="teamName" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">扑救调整值</label>
                        <input type="text" id="a9" name="teamName" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">犯规调整值</label>
                        <input type="text" id="a10" name="teamName" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 control-label">威胁传球调整值</label>
                        <input type="text" id="a11" name="teamName" class="form-control"/>
                    </div>


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="aadd">确定</button>
                </div>
            </div>
        </div>
    </div>
</form>


</body>
</html>