package com.elin4it.ssm.controller;


import com.alibaba.fastjson.JSONObject;
import com.elin4it.ssm.constant.ErrorCodeConst;
import com.elin4it.ssm.exception.BusinessException;
import com.elin4it.ssm.model.JsonDataModel;
import com.elin4it.ssm.pojo.User;
import com.elin4it.ssm.service.UserService;
import com.elin4it.ssm.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


/**
 * Created by jpan on 2016/4/5.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping("detail")
    public String showDetail(HttpServletRequest request, ModelMap model) {
        JSONObject statusMap = new JSONObject();
        statusMap.put("0", "是");
        statusMap.put("1", "否");
        model.put("StatusMap", statusMap);
        model.put("userId", WebUtil.getCurrentUser().getUserId());
        return "user/userdetail";
    }

    @RequestMapping("userdetail")
    public
    @ResponseBody
    String getUserDetail(int userId) {
        JsonDataModel jsonDataModel = new JsonDataModel();
        try {
            User user = userService.selectById(userId);
            jsonDataModel.setData(user);

        } catch (BusinessException e) {
            jsonDataModel.setErrorCode(ErrorCodeConst.BUS_EXCEPTION);
            jsonDataModel.setStatus("false");
            jsonDataModel.setMessage(e.getLocalizedMessage());
        }

        return jsonDataModel.toJSONString();
    }

    @RequestMapping("save")
    public String add(User user, ModelMap model) {

        if (user.getUserId() != null) {
            int result = userService.update(user);
            if (result == 1) {
                model.put("message", "保存成功");
                model.put("status", true);
                return "user/userdetail";
            }
        }
        model.put("message", "保存失败");
        model.put("status", false);

        return "user/userdetail";
    }
}
