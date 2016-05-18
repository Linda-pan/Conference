package com.elin4it.ssm.controller;

import com.elin4it.ssm.exception.BusinessException;
import com.elin4it.ssm.mapper.UserMapper;
import com.elin4it.ssm.model.SessionUserMenuInfo;
import com.elin4it.ssm.model.UserModel;
import com.elin4it.ssm.pojo.MenuInfo;
import com.elin4it.ssm.pojo.User;
import com.elin4it.ssm.model.AdminRights;
import com.elin4it.ssm.model.SessionUser;
import com.elin4it.ssm.service.MenuInfoService;
import com.elin4it.ssm.service.UserModelService;
import com.elin4it.ssm.service.UserService;
import com.elin4it.ssm.utils.ConfigPropertiesUtil;
import com.elin4it.ssm.utils.Md5;
import com.elin4it.ssm.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;


import javax.mail.internet.ParseException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jpan on 2016/4/22.
 */
@Controller
@RequestMapping("")
public class IndexController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private MenuInfoService menuInfoService;
    @Autowired
    private UserModelService userModelService;

    @RequestMapping("")
    public String index(@RequestParam(required = false) String message) {
        //设置静态资源的版本号
        WebUtil.setStaticResourceTag(ConfigPropertiesUtil.getProperties("static_resource_tag"));

        return "/login";
    }

    @RequestMapping("index")
    public String homePage(ModelMap model, HttpServletResponse response) {
        SessionUser sessionUser = WebUtil.getCurrentUser();

        List<MenuInfo> firstMenuList = menuInfoService.getAllFirstMenuInfo(sessionUser.getRoleId());

        WebUtil.setSessionUserMenuInfo(new SessionUserMenuInfo(firstMenuList, null));
        model.put("firstMenuList", firstMenuList);

        Cookie cookie = new Cookie("user", String.valueOf(sessionUser.getUserId()) + ":" + sessionUser.getUsername());
        response.addCookie(cookie);

        return "/index";
    }

    @RequestMapping(value = "login", method = {RequestMethod.POST})
    public String login(@RequestParam(required = true) String userName, @RequestParam(required = true) String password, HttpServletResponse response, ModelMap model) {
        User user = userService.selectByName(userName);

        if (userName != null && user != null) {
            if (!user.getIsEmailConfirmed()) {
                model.put("message", "邮箱尚未验证");
                model.put("status", false);
                return "/login";
            }

            if(!user.getPassword().equals(Md5.encode2hex(password))){
                model.put("message", "密码不正确");
                model.put("status", false);
                return "/login";
            }
            AdminRights adminRights = new AdminRights();
            adminRights.setRoleId(user.getRoleId());
            adminRights.setUid(user.getUserId());

            WebUtil.setAppAdminRights(adminRights);
            WebUtil.setCurrentUser(new SessionUser(user.getUserId(), user.getUsername(), user.getRoleId()));

        } else {
            model.put("message", "账号未注册");
            model.put("status", false);
            return "/login";

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

        return "redirect:/";
    }

    @RequestMapping("/register")
    public String load(ModelMap model) {
        return "/registernow";
    }

    @RequestMapping("/registersave")
    public String load1(User user, ModelMap model) throws ParseException {
        User user1 = userService.selectByName(user.getUsername());
        if (user1 != null) {
            /****补**/
            model.put("message", "用户名重复");
            model.put("status", false);
            return "/registernow";
        }

        String password = user.getPassword();
        if (password.length() < 6) {
            model.put("message", "密码长度不低于6位");
            model.put("status", true);
            return "/registernow";
        }

        boolean flag = false;
        String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(user.getEmail());
        flag = matcher.matches();
        if (!flag) {
            model.put("message", "邮箱格式不对");
            model.put("status", false);
            return "/registernow";
        }

        Pattern regex1 = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
        Matcher matcher1 = regex1.matcher(user.getTelephone());
        flag = matcher1.matches();
        if (!flag) {
            model.put("message", "手机号码格式不对");
            model.put("status", false);
            return "/registernow";
        }

        userService.processregister(user);
        model.put("message", "邮件发送成功");
        model.put("status", true);

        return "/login";
    }

    @RequestMapping("/emaillogin")
    public String load1(int id, String validateCode, ModelMap model) throws ParseException {
        try {
            UserModel userModel = userModelService.getByUserId(id);
            userService.processActivate(userModel, validateCode);

        } catch (BusinessException e) {
            model.put("message", e.getLocalizedMessage());
            model.put("status", false);
            return "/login";
        }
        model.put("message", "验证成功");
        model.put("status", true);
        return "redirect:/";
    }

}
