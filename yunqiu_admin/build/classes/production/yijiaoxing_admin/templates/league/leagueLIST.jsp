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

        $("#team_yellow").click(function(){
            $(".ibox-content").hide();
            $("#team_yellow_ibox-content").show();
            teamYellowFun()
        });

        $("#user_shooter").click(function(){
            $(".ibox-content").hide();
            $("#user_shooter_ibox-content").show();
            userShooterFun();
        });

        $("#user_assisting").click(function(){
            $(".ibox-content").hide();
            $("#user_assisting_ibox-content").show();
            userAssistingFun();
        });

        $("#user_red").click(function(){
            $(".ibox-content").hide();
            $("#user_red_ibox-content").show();
            userRedFun();
        });

        $("#user_yellow").click(function(){
            $(".ibox-content").hide();
            $("#user_yellow_ibox-content").show();
            userYellowFun();
        });


    });

    function teamShooterFun(){
        $("#team_shooter_list").jqGrid({
            url:'/leagueLIST/selectTeam',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            height:$(window).height()-239,
            postData:{"type":1},
            autowidth:true,//自动宽
            shrinkToFit:true,
            colModel:[
                {label:"Id",name:'teamcolorId',index:'teamcolorId', width:'15%',align:'center',hidden: true},
                {label:"球队排名",name:'n',index:'n', width:'35%',align:'center'},
                {label:"球队名称",name:'teamName',teamName:'colour', width:'35%',align:'center'},
                {label:"进球数",name:'res',index:'res', width:'35%',align:'center'}
            ],
            pager:'#team_shooter_gridPager',
            viewrecords: true,//是否在浏览导航栏显示记录总数
            rowNum:10, //每页显示记录数
            rownumbers: true,
            sortname: "res",
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
            url:'/leagueLIST/selectTeam',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            postData:{"type":6},
            height:$(window).height()-239,
            autowidth:true,//自动宽
            shrinkToFit:true,
            colModel:[
                {label:"Id",name:'teamcolorId',index:'teamcolorId', width:'15%',align:'center',hidden: true},
                {label:"球队排名",name:'n',index:'n', width:'35%',align:'center'},
                {label:"球队名称",name:'teamName',index:'teamName', width:'35%',align:'center'},
                {label:"进助攻次数",name:'res',index:'res', width:'35%',align:'center'}
            ],
            pager:'#team_assisting_gridPager',
            viewrecords: true,//是否在浏览导航栏显示记录总数
            rowNum:10, //每页显示记录数
            rownumbers: true,
            sortname: "res",
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
            url:'/leagueLIST/selectTeam',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            postData:{"type":3},
            height:$(window).height()-239,
            autowidth:true,//自动宽
            shrinkToFit:true,
            colModel:[
                {label:"Id",name:'teamcolorId',index:'teamcolorId', width:'15%',align:'center',hidden: true},
                {label:"球队排名",name:'n',index:'n', width:'35%',align:'center'},
                {label:"球队名称",name:'teamName',index:'teamName', width:'35%',align:'center'},
                {label:"红牌数",name:'res',index:'res', width:'35%',align:'center'}
            ],
            pager:'#team_red_gridPager',
            viewrecords: true,//是否在浏览导航栏显示记录总数
            rowNum:10, //每页显示记录数
            rownumbers: true,
            sortname: "res",
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


    function teamYellowFun(){
        $("#team_yellow_list").jqGrid({
            url:'/leagueLIST/selectTeam',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            postData:{"type":4},
            height:$(window).height()-239,
            autowidth:true,//自动宽
            shrinkToFit:true,
            colModel:[
                {label:"Id",name:'teamcolorId',index:'teamcolorId', width:'15%',align:'center',hidden: true},
                {label:"球队排名",name:'n',index:'n', width:'35%',align:'center'},
                {label:"球队名称",name:'teamName',index:'teamName', width:'35%',align:'center'},
                {label:"黄牌数",name:'res',index:'res', width:'35%',align:'center'}
            ],
            pager:'#team_yellow_gridPager',
            viewrecords: true,//是否在浏览导航栏显示记录总数
            rowNum:10, //每页显示记录数
            rownumbers: true,
            sortname: "res",
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
        $("#team_yellow_list").jqGrid("navGrid","#team_yellow_gridPager",{edit:false,add:false,del:false,search:false},{reloadAfterSubmit:true,});

        $(window).bind("resize",function(){
            var width=$("#team_yellow_divWidth").width();
            $("#team_yellow_list").setGridWidth(width);
        });
    }

    function userShooterFun(){
        $("#user_shooter_list").jqGrid({
            url:'/leagueLIST/selectUser',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            postData:{"type":1},
            height:$(window).height()-239,
            autowidth:true,//自动宽
            shrinkToFit:true,
            colModel:[
                {label:"Id",name:'teamcolorId',index:'teamcolorId', width:'15%',align:'center',hidden: true},
                {label:"球员排名",name:'n',index:'n', width:'35%',align:'center'},
                {label:"球员名称",name:'nickname',index:'nickname', width:'35%',align:'center'},
                {label:"所属球队",name:'teamName',index:'teamName', width:'35%',align:'center'},
                {label:"进球数",name:'res',index:'res', width:'35%',align:'center'}
            ],
            pager:'#user_shooter_gridPager',
            viewrecords: true,//是否在浏览导航栏显示记录总数
            rowNum:10, //每页显示记录数
            rownumbers: true,
            sortname: "res",
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
        $("#user_shooter_list").jqGrid("navGrid","#user_shooter_gridPager",{edit:false,add:false,del:false,search:false},{reloadAfterSubmit:true,});

        $(window).bind("resize",function(){
            var width=$("#user_shooter_divWidth").width();
            $("#user_shooter_list").setGridWidth(width);
        });
    }

    function userAssistingFun(){
        $("#user_assisting_list").jqGrid({
            url:'/leagueLIST/selectUser',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            postData:{"type":6},
            height:$(window).height()-239,
            autowidth:true,//自动宽
            shrinkToFit:true,
            colModel:[
                {label:"Id",name:'teamcolorId',index:'teamcolorId', width:'15%',align:'center',hidden: true},
                {label:"球员排名",name:'n',index:'n', width:'35%',align:'center'},
                {label:"球员名称",name:'nickname',index:'nickname', width:'35%',align:'center'},
                {label:"所属球队",name:'teamName',index:'teamName', width:'35%',align:'center'},
                {label:"助攻次数",name:'res',index:'res', width:'35%',align:'center'}
            ],
            pager:'#user_assisting_gridPager',
            viewrecords: true,//是否在浏览导航栏显示记录总数
            rowNum:10, //每页显示记录数
            rownumbers: true,
            sortname: "res",
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
        $("#user_assisting_list").jqGrid("navGrid","#user_assisting_gridPager",{edit:false,add:false,del:false,search:false},{reloadAfterSubmit:true,});

        $(window).bind("resize",function(){
            var width=$("#user_shooter_divWidth").width();
            $("#user_assisting_list").setGridWidth(width);
        });
    }

    function userRedFun(){
        $("#user_red_list").jqGrid({
            url:'/leagueLIST/selectUser',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            height:$(window).height()-239,
            postData:{"type":3},
            autowidth:true,//自动宽
            shrinkToFit:true,
            colModel:[
                {label:"Id",name:'teamcolorId',index:'teamcolorId', width:'15%',align:'center',hidden: true},
                {label:"球员排名",name:'n',index:'n', width:'35%',align:'center'},
                {label:"球员名称",name:'nickname',index:'nickname', width:'35%',align:'center'},
                {label:"所属球队",name:'teamName',index:'teamName', width:'35%',align:'center'},
                {label:"红牌次数",name:'res',index:'res', width:'35%',align:'center'}
            ],
            pager:'#user_red_gridPager',
            viewrecords: true,//是否在浏览导航栏显示记录总数
            rowNum:10, //每页显示记录数
            rownumbers: true,
            sortname: "res",
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
        $("#user_red_list").jqGrid("navGrid","#user_red_gridPager",{edit:false,add:false,del:false,search:false},{reloadAfterSubmit:true,});

        $(window).bind("resize",function(){
            var width=$("#user_red_divWidth").width();
            $("#user_red_list").setGridWidth(width);
        });
    }

    function userYellowFun(){
        $("#user_yellow_list").jqGrid({
            url:'/leagueLIST/selectUser',
            datatype:"json", //数据来源，本地数据
            mtype:"GET",//提交方式
            height:$(window).height()-239,
            postData:{"type":4},
            autowidth:true,//自动宽
            shrinkToFit:true,
            colModel:[
                {label:"Id",name:'teamcolorId',index:'teamcolorId', width:'15%',align:'center',hidden: true},
                {label:"球员排名",name:'n',index:'n', width:'35%',align:'center'},
                {label:"球员名称",name:'nickname',index:'nickname', width:'35%',align:'center'},
                {label:"所属球队",name:'teamName',index:'teamName', width:'35%',align:'center'},
                {label:"黄牌次数",name:'res',index:'res', width:'35%',align:'center'}
            ],
            pager:'#user_yellow_gridPager',
            viewrecords: true,//是否在浏览导航栏显示记录总数
            rowNum:10, //每页显示记录数
            rownumbers: true,
            sortname: "res",
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
                                    <button type="button" class="btn btn-default" id="team_shooter">球队射手榜</button>
                                    <button type="button" class="btn btn-default" id="team_assisting">球队助攻榜</button>
                                    <button type="button" class="btn btn-default" id="team_red">球队红牌榜</button>
                                    <button type="button" class="btn btn-default" id="team_yellow">球队黄牌榜</button>
                                    <button type="button" class="btn btn-default" id="user_shooter">球员射手榜</button>
                                    <button type="button" class="btn btn-default" id="user_assisting">球员助攻榜</button>
                                    <button type="button" class="btn btn-default" id="user_red">球员红牌榜</button>
                                    <button type="button" class="btn btn-default" id="user_yellow">球员黄牌榜</button>
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
                                <div class="ibox-content" id="team_yellow_ibox-content">
                                    <div class="jqGrid_wrapper" id="team_yellow_divWidth">
                                        <table id="team_yellow_list"></table>
                                        <div id="team_yellow_gridPager"></div>
                                    </div>
                                </div>
                                <div class="ibox-content" id="user_shooter_ibox-content">
                                    <div class="jqGrid_wrapper" id="user_shooter_divWidth">
                                        <table id="user_shooter_list"></table>
                                        <div id="user_shooter_gridPager"></div>
                                    </div>
                                </div>
                                <div class="ibox-content" id="user_assisting_ibox-content">
                                    <div class="jqGrid_wrapper" id="user_assisting_divWidth">
                                        <table id="user_assisting_list"></table>
                                        <div id="user_assisting_gridPager"></div>
                                    </div>
                                </div>
                                <div class="ibox-content" id="user_red_ibox-content">
                                    <div class="jqGrid_wrapper" id="user_red_divWidth">
                                        <table id="user_red_list"></table>
                                        <div id="user_red_gridPager"></div>
                                    </div>
                                </div>
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



</body>
</html>