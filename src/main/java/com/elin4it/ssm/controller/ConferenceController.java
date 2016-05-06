package com.elin4it.ssm.controller;

import com.elin4it.ssm.constant.ErrorCodeConst;
import com.elin4it.ssm.exception.BusinessException;
import com.elin4it.ssm.model.JsonDataModel;
import com.elin4it.ssm.pojo.Conference;
import com.elin4it.ssm.pojo.ConferenceStatus;
import com.elin4it.ssm.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jpan on 2016/5/4.
 */
@Controller
@RequestMapping("/conference")
public class ConferenceController {
    @Autowired
    private ConferenceService conferenceService;

    @RequestMapping("")
    public String index(ModelMap model) {
        return "/conference/add";
    }

    @RequestMapping("/add")
    public String addConference(Conference conference, ModelMap model) {
        try {
            conferenceService.insertConference(conference);
        } catch (BusinessException e) {
            model.put("message", e.getLocalizedMessage());
            model.put("status", false);

            return "redirect:/conference";
        }
        model.put("message", "保存成功");
        model.put("status", true);

        return "redirect:/conference";

    }
}
