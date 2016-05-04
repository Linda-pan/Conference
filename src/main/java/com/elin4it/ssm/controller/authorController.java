package com.elin4it.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.elin4it.ssm.mybatis.pagination.Order;
import com.elin4it.ssm.mybatis.pagination.PageBounds;
import com.elin4it.ssm.service.UserService;
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
public class AuthorController {
    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String index(ModelMap model) {
        return "/user/author";
    }

    @RequestMapping("list")
    public
    @ResponseBody
    String getAuthorList(@RequestParam(required = false, defaultValue = "1") int pageNo, @RequestParam(required = false, defaultValue = "50") int pageSize) {
        PageBounds<JSONObject> pageBounds = new PageBounds<>(pageNo, pageSize, Order.create("user_id", "desc"));
        userService.findAPage(pageBounds);

        Grid grid = new Grid(pageBounds.getPageList().getTotalCount(), pageBounds.getPageList().getResult());

        return grid.toJSONString();
    }
}
