/**
 * cookie操作
 */
var cookieOperate = {
    setCookie:function (name,value) {
        var Days=1;//过期时间1天
        var date=new Date();
        date.setTime(date.getTime()+Days*24*60*60*1000);
        document.cookie=name+"="+escape(value)+";expires="+date.toGMTString();
    },
    getCookie:function (name) {
        var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
        if(arr=document.cookie.match(reg)){
            return unescape(arr[2]);
        }
        else{
            return null;
        }
    }
};