package com.elin4it.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.elin4it.ssm.constant.ErrorCodeConst;
import com.elin4it.ssm.constant.UserRoleConst;
import com.elin4it.ssm.exception.BusinessException;
import com.elin4it.ssm.model.JsonDataModel;
import com.elin4it.ssm.mybatis.pagination.Order;
import com.elin4it.ssm.mybatis.pagination.PageBounds;
import com.elin4it.ssm.pojo.User;
import com.elin4it.ssm.service.UserService;
import com.elin4it.ssm.utils.Grid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * Created by jpan on 2016/5/3.
 */
@Controller
@RequestMapping("/user/alluser")
public class AllUserController {
@Autowired
private UserService userService;

    @RequestMapping("")
    public String index(ModelMap model) {
        JSONObject status = new JSONObject();

        status.put("0", UserRoleConst.AUTHOR0);
        status.put("1",UserRoleConst.ADMIN1);
        status.put("2",UserRoleConst.REVIEWER2);
        status.put("3","所有用户");

        model.put("status",status);

        return "/user/alluser";
    }

    @RequestMapping("list")
    public @ResponseBody
    String getSpeechTrailerList(@RequestParam(required = false, defaultValue = "1") int pageNo, @RequestParam(required = false, defaultValue = "50") int pageSize,@RequestParam(required = false)Integer status) {
        PageBounds<User> pageBounds = new PageBounds<>(pageNo, pageSize, Order.create("user_id", "desc"));
        if(status==3) {
            userService.findAllUserPage(pageBounds);
        }else{
            userService.findUserPageByRoleId(pageBounds,status);
        }

        Grid grid = new Grid(pageBounds.getPageList().getTotalCount(), pageBounds.getPageList().getResult());

        return grid.toJSONString();
    }

    @RequestMapping("/save")
    public @ResponseBody String changeRole(int userId,byte roleId, ModelMap model) {

        JsonDataModel jsonDataModel = new JsonDataModel();
        try {
            User user = new User();
            user.setUserId(userId);
            user.setRoleId(roleId);

            userService.update(user);
        } catch (BusinessException e) {
            jsonDataModel.setErrorCode(ErrorCodeConst.BUS_EXCEPTION);
            jsonDataModel.setStatus("false");
            jsonDataModel.setMessage(e.getLocalizedMessage());
        }

        model.put("message", "修改成功");
        model.put("status", true);
        return jsonDataModel.toJSONString();
    }
}
