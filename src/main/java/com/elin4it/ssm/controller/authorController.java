package com.elin4it.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.elin4it.ssm.constant.ErrorCodeConst;
import com.elin4it.ssm.exception.BusinessException;
import com.elin4it.ssm.model.JsonDataModel;
import com.elin4it.ssm.mybatis.pagination.Order;
import com.elin4it.ssm.mybatis.pagination.PageBounds;
import com.elin4it.ssm.pojo.User;
import com.elin4it.ssm.service.UserService;
import com.elin4it.ssm.utils.ConfigPropertiesUtil;
import com.elin4it.ssm.utils.Grid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jpan on 2016/5/4.
 */
@Controller
@RequestMapping("/user/author")
public class AuthorController extends BaseController{
    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String index(ModelMap model) {
        model.put("authorPaperUrl", ConfigPropertiesUtil.getProperties("author_paper_list_url"));

        JSONObject status = new JSONObject();
        status.put("true", "通过审核");
        status.put("false","未通过审核");
        model.put("status",status);

        return "/user/author";
    }

    @RequestMapping("list")
    public
    @ResponseBody
    String getAuthorList(@RequestParam(required = false, defaultValue = "1") int pageNo, @RequestParam(required = false, defaultValue = "50") int pageSize) {
        PageBounds<JSONObject> pageBounds = new PageBounds<>(pageNo, pageSize, Order.create("user_id", "desc"));
        userService.findAuthorPage(pageBounds);

        Grid grid = new Grid(pageBounds.getPageList().getTotalCount(), pageBounds.getPageList().getResult());

        return grid.toJSONString();
    }

    @RequestMapping("savepayment")
    public String savepayment(int userId,Boolean isPaymentConfirmed ,ModelMap model) {

            User user=userService.selectById(userId);
            user.setIsPaymentConfirmed(isPaymentConfirmed);
            userService.update(user);

        return "redirect:/user/author" ;
    }
}
