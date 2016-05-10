package com.elin4it.ssm.controller;

import com.elin4it.ssm.constant.ErrorCodeConst;
import com.elin4it.ssm.exception.BusinessException;
import com.elin4it.ssm.model.JsonDataModel;
import com.elin4it.ssm.pojo.Conference;
import com.elin4it.ssm.pojo.ConferenceStatus;
import com.elin4it.ssm.service.ConferenceStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jpan on 2016/5/9.
 */
@Controller
@RequestMapping("/conferencestatus")
public class ConferenceStatusController extends BaseController{
    @Autowired
    private ConferenceStatusService conferenceStatusService;

    @RequestMapping("/savestatus")
    public @ResponseBody String changeS(ConferenceStatus conferenceStatus, ModelMap model) {
        JsonDataModel jsonDataModel =new JsonDataModel();

        try {
            conferenceStatusService.updateConference(conferenceStatus);

        } catch (BusinessException e) {
            jsonDataModel.setErrorCode(ErrorCodeConst.BUS_EXCEPTION);
            jsonDataModel.setStatus("false");
            jsonDataModel.setMessage(e.getLocalizedMessage());
        }

        return jsonDataModel.toJSONString();
    }
}
