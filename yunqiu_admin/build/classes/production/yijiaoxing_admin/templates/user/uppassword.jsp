<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="renderer" content="webkit"/>
    <title>云球 - 修改密码</title>

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

        $("#update").bind("click",function(){
            //开始ajax操作
            $("#updateForm").ajaxSubmit({
                type: "POST",//提交类型
                dataType: "json",//返回结果格式
                url: "/perm/uppassword",//请求地址
                data:$("#updateForm").serialize(),
                success: function (data) {//请求成功后的函数
                    if (data["error_code"] == 1500){
                        swal("系统错误!", data["msg"], "error");
                    }else if (data["error_code"] != 1200){
                        swal("操作失败!",data["msg"],"warning");
                    }else{
                        $("#password").value="";
                        $("#again_password").value="";
                        swal("修改","修改成功！");
                    }
                },
                error: function (data) { swal("修改失败","网络异常！","error"); },//请求失败的函数
                async: false
            });
        });

    });


</script>

<form id="updateForm">
    <div class="col-sm-5 col-sm-offset-3" >
        <div class="ibox float-e-margins">
            <div class="ibox-content">
                <div class="form-group">
                    <label class="col-sm-3 control-label">密码：</label>
                    <div class="col-sm-8">
                        <input type="password" class="form-control" name="password" id="password"/>
                    </div>
                </div>
                <br/>

                <div class="form-group">
                    <label class="col-sm-3 control-label">再次输入密码：</label>
                    <div class="col-sm-8">
                        <input type="password" class="form-control" id="again_password"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-8">
                        <button type="button" class="btn btn-primary" id="update">修改</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

</body>
</html>