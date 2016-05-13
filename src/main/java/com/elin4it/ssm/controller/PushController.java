package com.elin4it.ssm.controller;

import com.elin4it.ssm.constant.ErrorCodeConst;
import com.elin4it.ssm.exception.BusinessException;
import com.elin4it.ssm.model.JsonDataModel;
import com.elin4it.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.mail.internet.ParseException;

/**
 * Created by jpan on 2016/5/13.
 */
@Controller
@RequestMapping("push")
public class PushController {
    @Autowired
    private UserService userService;

    @RequestMapping("passinform")
    public @ResponseBody String index1(int userId)throws ParseException {

        JsonDataModel jsonDataModel = new JsonDataModel();
            try {
                userService.processInformPass(userId);
                jsonDataModel.setMessage( "邮件发送成功");

            } catch (BusinessException e) {
                jsonDataModel.setErrorCode(ErrorCodeConst.BUS_EXCEPTION);
                jsonDataModel.setStatus("false");
                jsonDataModel.setMessage(e.getLocalizedMessage());
            }

            return jsonDataModel.toJSONString();
    }

    @RequestMapping("uploadinform")
    public @ResponseBody String index2(int userId)throws ParseException {

            JsonDataModel jsonDataModel = new JsonDataModel();
            try {
                userService.processInformUpload(userId);
                jsonDataModel.setMessage( "邮件发送成功");

            } catch (BusinessException e) {
                jsonDataModel.setErrorCode(ErrorCodeConst.BUS_EXCEPTION);
                jsonDataModel.setStatus("false");
                jsonDataModel.setMessage(e.getLocalizedMessage());
            }

            return jsonDataModel.toJSONString();
    }

    @RequestMapping("readyinform")
    public @ResponseBody  String index3(int userId, boolean result)throws ParseException {
        JsonDataModel jsonDataModel = new JsonDataModel();
        try {
            userService.processInformReady(userId,result);
            jsonDataModel.setMessage( "邮件发送成功");

        } catch (BusinessException e) {
            jsonDataModel.setErrorCode(ErrorCodeConst.BUS_EXCEPTION);
            jsonDataModel.setStatus("false");
            jsonDataModel.setMessage(e.getLocalizedMessage());
        }

        return jsonDataModel.toJSONString();
    }

}
