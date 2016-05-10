package com.elin4it.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.elin4it.ssm.constant.ConferenceStatusConst;
import com.elin4it.ssm.constant.ErrorCodeConst;
import com.elin4it.ssm.exception.BusinessException;
import com.elin4it.ssm.model.JsonDataModel;
import com.elin4it.ssm.pojo.Conference;
import com.elin4it.ssm.pojo.ConferenceStatus;
import com.elin4it.ssm.service.ConferenceService;
import com.elin4it.ssm.utils.DateUtils;
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
@RequestMapping("/conference")
public class ConferenceController extends BaseController{
    @Autowired
    private ConferenceService conferenceService;

    @RequestMapping("detail")
    public String index(ModelMap model) {
        JSONObject statusMap = new JSONObject();
        statusMap.put("0", ConferenceStatusConst.S0);
        statusMap.put("1", ConferenceStatusConst.S1);
        statusMap.put("2", ConferenceStatusConst.S2);
        statusMap.put("3", ConferenceStatusConst.S3);
        statusMap.put("4", ConferenceStatusConst.S4);
        model.put("StatusMap", statusMap);

        return "/conference/detail";
    }

    @RequestMapping("add")
    public String index1(ModelMap model) {
        return "/conference/add";
    }

    @RequestMapping("/save")
    public String addConference(Conference conference, ModelMap model) {
        try {
            conferenceService.insertConference(conference);
        } catch (BusinessException e) {
            model.put("message", e.getLocalizedMessage());
            model.put("status", false);

            return "forward:/conference/detail";
        }
        model.put("message", "保存成功");
        model.put("status", true);

        return "index";

    }

    @RequestMapping("getdetail")
    public @ResponseBody String getDetail(){
        JsonDataModel jsonDataModel = new JsonDataModel();
        try {
            JSONObject conference = conferenceService.selectLastConference();

            jsonDataModel.setData(conference);

        } catch (BusinessException e) {
            jsonDataModel.setErrorCode(ErrorCodeConst.BUS_EXCEPTION);
            jsonDataModel.setStatus("false");
            jsonDataModel.setMessage(e.getLocalizedMessage());
        }

        return jsonDataModel.toJSONString();
    }
}
