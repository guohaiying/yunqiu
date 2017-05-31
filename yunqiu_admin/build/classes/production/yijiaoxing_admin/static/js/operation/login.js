/**
 * 登录
 */
function form_login() {
    $.ajax({
        url:"/perm/login",
        type:"post",
        data:$("#login_form").serialize(),
        dataType:"json",
        async:false,
        success:function (data) {
            if (data["error_code"] == 1500){
                swal("系统错误!", data["msg"], "error");
            }else if (data["error_code"] != 1200){
                swal("操作失败!",data["msg"],"warning");
            }else{
                cookieOperate.setCookie("user_id",data["data"]["userId"]);
                cookieOperate.setCookie("token",data["data"]["token"]);
                window.location.href = "/view/index?userId="+cookieOperate.getCookie("user_id")+"&token="+cookieOperate.getCookie("token");
            }
        },
        error:function (errorThrown) {
            console.log("登录请求错误====");
            console.log(errorThrown);
        }

    });
    return false;
}
