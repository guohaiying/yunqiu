package com.yunqiu.general.interceptor;

import com.yunqiu.general.token.Tokens;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.GsonUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器，验证是否已登录
 * @author 武尊
 * @time 2017-01-11
 * @version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private Tokens tokens;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //设置浏览器用utf8来解析返回的数据
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        //设置servlet用UTF-8转码，而不是用默认的ISO8859
        response.setCharacterEncoding("UTF-8");


        String access_token = request.getHeader("access_token");
        String user_id = request.getHeader("user_id");
        //验证token值是否正常
        if (Utils.isNull(access_token) || Utils.isNull(user_id)){
            response.getWriter().print(GsonUtil.objectToJson(ControllerReturnBase.errorResule(1501,"缺少用户id跟token")));
            return false;
        }else if (tokens.verifyToken(user_id,access_token)){
            response.getWriter().print(GsonUtil.objectToJson(ControllerReturnBase.errorResule(1504,"未登录或token已失效，请重新登录")));
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
