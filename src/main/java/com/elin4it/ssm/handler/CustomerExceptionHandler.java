package com.elin4it.ssm.handler;

import com.elin4it.ssm.constant.ErrorCodeConst;
import com.elin4it.ssm.exception.AjaxRequestSessionExpireException;
import com.elin4it.ssm.model.JsonDataModel;
import com.elin4it.ssm.utils.ConfigPropertiesUtil;
import com.elin4it.ssm.utils.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 全局处理系统中未知的没有捕获的异常
 */
public class CustomerExceptionHandler implements HandlerExceptionResolver {
    private Logger logger = LoggerFactory.getLogger(CustomerExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        //判断是否是ajax请求session过期异常
        if (e instanceof AjaxRequestSessionExpireException){

            return doAjaxRequestException(httpServletResponse,e, ErrorCodeConst.AJAZ_SESSION_EXPIRE_EXCEPTION);
        }else {
            logger.error("Exception",e);
            //判断是否ajax请求
            if (!WebUtil.isAjaxRequest(httpServletRequest)){
                ModelAndView modelAndView = new ModelAndView("/error/error");
                ModelMap model = new ModelMap();
                model.put("errorMessage",e.getMessage());
                modelAndView.addAllObjects(model);

                return modelAndView;
            }else{

                return doAjaxRequestException(httpServletResponse,e,ErrorCodeConst.SERVER_EXCEPTION);
            }
        }
    }

    private ModelAndView doAjaxRequestException(HttpServletResponse httpServletResponse, Exception e, String errorCode) {
        JsonDataModel jsonDataModel = new JsonDataModel();
        jsonDataModel.setErrorCode(errorCode);
        jsonDataModel.setStatus("false");
        jsonDataModel.setMessage(e.getLocalizedMessage());

        //如果是ajax请求session过期异常，则需要把db登录页面url传给客户端
        if (ErrorCodeConst.AJAZ_SESSION_EXPIRE_EXCEPTION.equals(errorCode)){
            jsonDataModel.setData(ConfigPropertiesUtil.getProperties("login_db_url"));
        }

        try {
            httpServletResponse.resetBuffer();
            //设置相应状态码,这个方法被用于当响应结果正常时，设置响应状态码(只有这样才可以被ajax的error捕获),而不应该用sendError,sendError使用指定的状态码发送一个错误响应至客户端,sendError服务器默认会创建一个HTML格式的服务错误页面作为响应结果
            httpServletResponse.setStatus(500);
            //设置错误信息
            PrintWriter printWriter = httpServletResponse.getWriter();
            printWriter.write(jsonDataModel.toJSONString());
            printWriter.flush();
            printWriter.close();

        } catch (IOException ex) {
            logger.error("Exception", e);
        }

        return null;
    }
}
