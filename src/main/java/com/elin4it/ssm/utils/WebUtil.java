package com.elin4it.ssm.utils;

import com.elin4it.ssm.model.AdminRights;
import com.elin4it.ssm.model.SessionUser;
import com.elin4it.ssm.model.SessionUserMenuInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class WebUtil {
    private static Logger logger = LoggerFactory.getLogger(WebUtil.class);

    private static final String SESSION_CURRENT_USER = "session_current_user";

    private static final String SESSION_APP_ADMIN_RIGHTS = "app_admin_rights";

    private static final String redirect_url = "login_redirect_url";

    private static final String SESSION_CURRENT_USER_MENU = "user_menu";

    private static final String SESSION_CURRENT_STATIC_WEB_RESOURCE_TAG = "static_resource_tag";

    private static final String SESSION_CURRENT_URL = "current_url";

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

	/*public static String encodeFileName(HttpServletRequest request, String fileName){

		String agent = request.getHeader("USER-AGENT");
		if (agent==null){
			return fileName;
		}

		if (agent.indexOf("MSIE")!=-1){
			try {
				return URLEncoder.encode(fileName, "UTF-8");
			} catch (UnsupportedEncodingException e) {
			}
		}

		if (agent.indexOf("Mozilla")!=-1){
			try {
				return "=?UTF-8?B?" + Base64.encode(fileName.getBytes("UTF-8")) + "?=";
			} catch (UnsupportedEncodingException e) {
			}

		}

		return fileName;
	}*/

    public static String getFullRequiestUrl(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        String queryString = request.getQueryString();
        if ((queryString != null) && (queryString.length() > 0)) {
            requestUrl += "?" + queryString;
        }

        return requestUrl;
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        return ("XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
    }

    public static WebApplicationContext getWebApplicationContext() {
        return RequestContextUtils.getWebApplicationContext(getRequest());
    }


    public static SessionUser getCurrentUser() {
        return getCurrentUser(getRequest());
    }

    public static SessionUser getCurrentUser(HttpServletRequest request) {
        return (SessionUser) request.getSession().getAttribute(SESSION_CURRENT_USER);
    }

    public static void setCurrentUser(SessionUser userInfo) {
        setCurrentUser(getRequest(), userInfo);
    }

    public static void setCurrentUser(HttpServletRequest request, SessionUser userInfo) {
        request.getSession().setAttribute(SESSION_CURRENT_USER, userInfo);
    }

    public static void setAppAdminRights(AdminRights adminRights) {
        HttpServletRequest request = getRequest();
        request.getSession().setAttribute(SESSION_APP_ADMIN_RIGHTS, adminRights);
    }

    public static AdminRights getAppAdminRights() {
        HttpServletRequest request = getRequest();
        return (AdminRights) request.getSession().getAttribute(SESSION_APP_ADMIN_RIGHTS);
    }

    public static void setSessionUserMenuInfo(SessionUserMenuInfo sessionUserMenuInfo) {
        HttpServletRequest request = getRequest();
        request.getSession().setAttribute(SESSION_CURRENT_USER_MENU, sessionUserMenuInfo);
    }

    public static SessionUserMenuInfo getSessionUserMenuInfo() {
        HttpServletRequest request = getRequest();
        return (SessionUserMenuInfo) request.getSession().getAttribute(SESSION_CURRENT_USER_MENU);
    }

    public static void setRedirectUrl(String redirectUrl) {
        HttpServletRequest request = getRequest();
        request.getSession().setAttribute(redirect_url, redirectUrl);
    }

    public static String getStaticResourceTag() {
        HttpServletRequest request = getRequest();
        return (String) request.getSession().getAttribute(SESSION_CURRENT_STATIC_WEB_RESOURCE_TAG);
    }

    public static void setStaticResourceTag(String staticResourceTag) {
        HttpServletRequest request = getRequest();
        request.getSession().setAttribute(SESSION_CURRENT_STATIC_WEB_RESOURCE_TAG, staticResourceTag);
    }

    public static String getRedirectUrl() {
        HttpServletRequest request = getRequest();
        return (String) request.getSession().getAttribute(redirect_url);
    }

    public static void setSessionCurrentUrl(String currentUrl) {
        HttpServletRequest request = getRequest();
        request.getSession().setAttribute(SESSION_CURRENT_URL, currentUrl);
    }

    public static String getSessionCurrentUrl() {
        HttpServletRequest request = getRequest();
        return (String) request.getSession().getAttribute(SESSION_CURRENT_URL);
    }

    public static boolean isParamEmpty(Object param) {
        if (param == null) return true;

        if (param instanceof String) {
            return StringUtils.isEmpty(param);
        }

        if (param instanceof Integer) {
            return ((Integer) param) == Integer.MIN_VALUE;
        }

        if (param instanceof Double) {
            return ((Double) param) == Double.MIN_VALUE;
        }

        return true;
    }

    public static void downloadExcel(HttpServletResponse response, byte[] aFile, String aFileName) throws Exception {
        ServletOutputStream tOutputStream = null;
        try {

            aFileName += ".xls";
            LogManager.d(logger, "[WebUtil]输出文件...");
            // 2.1、设置报文头
            response.setHeader("Content-Type", "application/vnd.ms-excel"); // 弹出Excel
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(aFileName.getBytes("GBK"), "ISO-8859-1"));
            response.setHeader("Content-Description", aFileName);

            // 2.2、输出文件
            tOutputStream = response.getOutputStream();

            // 3.2、客户端浏览器不支持GZIP压缩方式传输
            // LogWriter.debug("客户端浏览器不支持GZIP压缩方式传输");
            // 3.2.1、设置内容编码方式为不压缩
            response.setHeader("Content-Encoding", "none");
            // 3.2.3、输出文件内容
            tOutputStream.write(aFile);

            tOutputStream.flush();

        } catch (Exception e) {
            Logger logger = LoggerFactory.getLogger(WebUtil.class);
            logger.error(e.getLocalizedMessage(), e);
        }
    }

    /**
     * 获取web项目的服务器的域名和项目的根目录组成的根url
     */
    public static String getWebProjectRootUrl(HttpServletRequest request) {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName();
        //如果是80端口则可以不显示
        if (request.getServerPort() == 80) {
            basePath += path + "/";
        } else {
            basePath += ":" + request.getServerPort() + path + "/";
        }

        return basePath;
    }
}
