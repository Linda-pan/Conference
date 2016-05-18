package com.elin4it.ssm.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.elin4it.ssm.exception.AjaxRequestSessionExpireException;
import com.elin4it.ssm.model.SessionUser;
import com.elin4it.ssm.model.SessionUserMenuInfo;
import com.elin4it.ssm.pojo.MenuInfo;
import com.elin4it.ssm.service.MenuInfoService;
import com.elin4it.ssm.utils.ConfigPropertiesUtil;
import com.elin4it.ssm.utils.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by jpan on 2016/5/16.
 */
public class AuthorizationInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    private List<String> excludedUrls;

    @Autowired
    protected MenuInfoService menuInfoService;

    /**
     * 请求过滤
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SessionUser uInfo = WebUtil.getCurrentUser(request);

        String requestUrl = request.getRequestURL().toString();
        String contextPath = request.getContextPath();
        //传过来的参数
        String queryString = request.getQueryString();

        //获得提交的表单参数
        Enumeration<String> paramNameList = request.getParameterNames();
        String paramBody = "{";
        while (paramNameList.hasMoreElements()) {
            String paramName = paramNameList.nextElement();
            if (paramName != null) {
                paramBody += paramName + ":";
                String[] paramValue = request.getParameterValues(paramName);
                paramBody += JSONObject.toJSONString(paramValue);
                paramBody += ",";
            }
        }
        if (paramBody.length()>1) paramBody = paramBody.substring(0, paramBody.length() - 1);
        paramBody += "}";

        //发送请求的客户端主机hostip
        String clientHost = request.getRemoteHost();
        //输出访问日志
        logger.debug("[Request] Body:");
        logger.debug("[Request] url: {}", request.getRequestURI());
        logger.debug("[Request] param: {}", paramBody);
        logger.debug("[Request] clientHost: {}", clientHost);
        logger.debug("[Request] user: {}",uInfo == null ? "" : uInfo.getUsername());

        if (requestUrl.indexOf("/login;jsessionid=") != -1) {
            requestUrl = requestUrl.replaceAll("(;jsessionid=)(\\w+)", "");
        }

        //excluded URLs
        if (excludedUrls != null) {
            for (String url : excludedUrls) {
                if (requestUrl.endsWith(url)) {
                    return true;
                }
            }
        }

        if ((queryString != null) && (queryString.length() > 0)) {
            requestUrl += "?" + queryString;
        }

        //判断用户是否登录，未登录必须要登录才能访问
        //获取登录的用户信息
        if (uInfo == null) {
            //ajax请求
            if (WebUtil.isAjaxRequest(request)) {
				/*response.getWriter().print("notlogin");
				return false;*/
                throw new AjaxRequestSessionExpireException("长时间未操作,请重新登录");
            }

            //throw new NotLoginException(requestUrl, "notlogin");
            String loginUrl = contextPath + "/?to=" + URLEncoder.encode(requestUrl, "UTF-8");
            WebUtil.setRedirectUrl(requestUrl);
            //response.sendRedirect(loginUrl);

            //跳转到db登录页
           /* response.setHeader("Access-Control-Allow-Origin", "*");
            response.sendRedirect(ConfigPropertiesUtil.getProperties("login_db_url"));*/
            return false;
        }

        //判断用户权限是否发生改变如果改变就重新登录,如果用户的所属权限最后更新时间大于用户最后一次登录时间，则用户权限发生改变，需要重新登录
   /*     Date maxUpdateUserPermissionTime = userPermissionLogService.getMaxUserPermissionTimeByUserId(uInfo.getUserId());
        try {
            NdbUserExtend ndbUserExtend = ndbUserExtendService.getNdbUserExtendByPrimaryKey(uInfo.getUserId());
            if (maxUpdateUserPermissionTime == null || DateUtils.compareDate(maxUpdateUserPermissionTime,ndbUserExtend.getLoginTime())>0){
                //如果是ajax请求，则抛出一个ajax请求session过期的异常
                if (WebUtil.isAjaxRequest(request)){
                    throw new AjaxRequestSessionExpireException("用户权限更新,请重新登录");
                }
                //跳转到db登录页
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.sendRedirect(ConfigPropertiesUtil.getProperties("login_db_url"));
                return false;
            }
        }catch (BusinessException e){
            if (WebUtil.isAjaxRequest(request)){
                throw new AjaxRequestSessionExpireException("用户权限更新,请重新登录");
            }
            //跳转到db登录页
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.sendRedirect(ConfigPropertiesUtil.getProperties("login_db_url"));

            return false;
        }*/

        //如果是非ajax请求，设置该请求的菜单
        if (!WebUtil.isAjaxRequest(request)) {
            List<MenuInfo> firstMenuList = menuInfoService.getAllFirstMenuInfo(uInfo.getUserId());
            String currentUrl = request.getServletPath();

            for (MenuInfo menuInfo : firstMenuList) {
                if (menuInfo != null) {
                    if (currentUrl.startsWith(menuInfo.getUrl())) {
                        setSubMenu(currentUrl, menuInfo.getId(), request);

                        WebUtil.setSessionCurrentUrl(currentUrl);

                        break;
                    }
                }
            }

        }

        return true;
    }

    /**
     * url请求（controller中的方法)执行完成后，在dispatchServlet进行视图渲染之前执行可以对ModelAndView操作
     */
    public void postHandle(HttpServletRequest request,   HttpServletResponse response, Object handler,   ModelAndView modelAndView) throws Exception {
//	    System.out.println("Post-handle");
    }
    /**
     * url请求（controller中的方法)执行完成后，在dispatchServlet进行视图渲染执行后执行，可以做清理垃圾
     */
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
       /* System.out.println("After completion handle");*/
    }

    public List<String> getExcludedUrls() {
        return excludedUrls;
    }

    public void setExcludedUrls(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }

    /**
     * 设置当前Url访问的子菜单
     * @param request
     * @return
     */
    private void setSubMenu(String currentUrl, int parentMenuId, HttpServletRequest request) {
        if (!StringUtils.isEmpty(currentUrl)) {
            SessionUserMenuInfo sessionUserMenuInfo = WebUtil.getSessionUserMenuInfo();

            List<MenuInfo> subMenuList = menuInfoService.getAllFirstMenuInfo(WebUtil.getCurrentUser().getRoleId());

            if (sessionUserMenuInfo != null) {
                sessionUserMenuInfo.setSecondMenuList(subMenuList);
            } else {
                sessionUserMenuInfo = new SessionUserMenuInfo(null, subMenuList);
            }
        }
    }

}