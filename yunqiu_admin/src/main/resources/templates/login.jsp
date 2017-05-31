<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <title>云球后台管理系统 - 登录</title>
    <meta name="keywords" content="云球后台管理系统"/>
    <meta name="description" content="云球后台管理系统"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/css/font-awesome.css?v=4.4.0" rel="stylesheet"/>
    <link href="/css/animate.css" rel="stylesheet"/>
    <link href="/css/style.css" rel="stylesheet"/>
    <link href="/css/login.css" rel="stylesheet"/>
    <link href="/css/plugins/sweetalert/sweetalert.css" rel="stylesheet" />
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location;
        }
    </script>

</head>

<body class="signin">
<div class="signinpanel">
    <div class="row">
        <div class="col-sm-7">
            <div class="signin-info">
                <div class="logopanel m-b">
                    <h1>云球管理系统</h1>
                </div>
                <div class="m-b"></div>
                <!--<h4>学艺术，让我们更近一点</h4>-->
                <ul class="m-b">
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 1.此系统公司内部使用,请勿外传网址</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 2.此系统不提供注册,请联系管理员开户</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 3.此系统不提供密码找回,请联系管理员更改</li>
                </ul>
                <strong>祝各位同事工作愉快</strong>
                <!--<strong>还没有账号？ <a href="#">立即注册&raquo;</a></strong>-->
            </div>
        </div>
        <div class="col-sm-5">
            <form id="login_form" onsubmit="return form_login();"><!--onsubmit="return false;"-->
                <h4 class="no-margins">登录：</h4>
                <input type="text" name="userName" class="form-control uname" placeholder="用户名" required="" style="width: 100%"/>
                <input type="password" name="password" class="form-control pword m-b" placeholder="密码" required="" style="width: 100%"/>
                <!--<a href="">忘记密码了？</a>-->
                <button class="btn btn-success btn-block full-width m-b">登录</button>
            </form>
        </div>
    </div>
    <div class="signup-footer">
        <div class="pull-left">
            &copy; 2017 云球管理系统
        </div>
    </div>
</div>
<!-- 全局js -->
<script src="/js/jquery.min.js?v=2.1.4"></script>
<script src="/js/bootstrap.min.js?v=3.3.6"></script>
<script src="/js/plugins/sweetalert/sweetalert.min.js"></script>
<!-- 自定义js 与服务端交互js -->
<script src="/js/general.js?v=1.0.0"></script>
<script src="/js/operation/login.js?v=1.0"></script>
</body>

</html>
