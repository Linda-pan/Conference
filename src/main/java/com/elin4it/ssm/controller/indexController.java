package com.elin4it.ssm.controller;

import com.elin4it.ssm.model.AdminRights;
import com.elin4it.ssm.model.SessionUser;
import com.elin4it.ssm.model.SessionUserMenuInfo;
import com.elin4it.ssm.pojo.User;
import com.elin4it.ssm.service.AppAdminService;
import com.elin4it.ssm.service.MenuInfoService;
import com.elin4it.ssm.service.UserService;
import com.elin4it.ssm.utils.ConfigPropertiesUtil;
import com.elin4it.ssm.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by jpan on 2016/4/22.
 */
@Controller
@RequestMapping("")
public class indexController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private MenuInfoService menuInfoService;
    @Autowired
    private AppAdminService appAdminService;

    @RequestMapping("")
    public String index() {
        //设置静态资源的版本号
        WebUtil.setStaticResourceTag(ConfigPropertiesUtil.getProperties("static_resource_tag"));

        return "/login";
    }

    @RequestMapping("index")
    public String homePage(ModelMap model, HttpServletResponse response) {
        SessionUser sessionUser = WebUtil.getCurrentUser();

       // List<String> firstMenuList = null;
       // WebUtil.setSessionUserMenuInfo(new SessionUserMenuInfo());
      //  model.put("firstMenuList", firstMenuList);

        Cookie cookie = new Cookie("user", String.valueOf(sessionUser.getUserId()) + ":" + sessionUser.getUsername());
        response.addCookie(cookie);

        return "/index";
    }

    @RequestMapping(value = "login", method = {RequestMethod.POST})
    public String login(@RequestParam(required = true) String userName, @RequestParam(required = true) String password, HttpServletResponse response, ModelMap model) {
        User user = userService.login(userName, password);
        if (user != null) {

            SessionUser sessionUser = new SessionUser(user.getUserId(), user.getUsername());
            WebUtil.setCurrentUser(sessionUser);

        } else {
            model.put("error", "用户名或者密码错误!");

            return "/login";
        }

		/*用户登录后不直接跳转到index.jsp页面，而是用重定向redirect:/index，在index的url对应的处理函数homepage中跳转到该页面，这样可以隐藏login的url，
		在网址中页不会出现login的url，就可以刷新index而不会向原来一样是刷新login而报错，同时可以隐藏db的get方式登录的token参数值，是其他人无法看到
		（否则其他人看到就可以直接登录了）*/
        return "redirect:/index";
    }

    @RequestMapping(value = "login", method = {RequestMethod.GET})
    public String loginByGet(@RequestParam(required = true) String username, HttpServletResponse response, ModelMap model) throws Exception {
        User user = userService.selectByName(username);
        if (username != null && user != null){
            AdminRights adminRights =new AdminRights();
            adminRights.setRoleId(user.getRoleId());
            adminRights.setUid(user.getUserId());
            adminRights.setUsername(username);

            WebUtil.setAppAdminRights(adminRights);
            WebUtil.setCurrentUser(new SessionUser(user.getUserId(), user.getUsername()));

        }else{
            response.setHeader("Access-Control-Allow-Origin", "*");
            try {
                response.sendRedirect(ConfigPropertiesUtil.getProperties("login_db_url"));
            } catch (IOException e) {
                logger.error(e.getLocalizedMessage(), e);
                return "/error";
            }
        }

        //设置静态资源的版本号
        WebUtil.setStaticResourceTag(ConfigPropertiesUtil.getProperties("static_resource_tag"));

		/*用户登录后不直接跳转到index.jsp页面，而是用重定向redirect:/index，在index的url对应的处理函数homepage中跳转到该页面，这样可以隐藏login的url，
		在网址中页不会出现login的url，就可以刷新index而不会向原来一样是刷新login而报错，同时可以隐藏db的get方式登录的token参数值，是其他人无法看到
		（否则其他人看到就可以直接登录了）*/
        return "redirect:/index";
    }

    @RequestMapping("/logout")
    public String logout() {
        WebUtil.setCurrentUser(null);

        return "/login";
    }
}
