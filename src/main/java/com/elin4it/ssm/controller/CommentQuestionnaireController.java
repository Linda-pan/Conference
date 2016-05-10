package com.elin4it.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.elin4it.ssm.constant.ErrorCodeConst;
import com.elin4it.ssm.exception.BusinessException;
import com.elin4it.ssm.model.JsonDataModel;
import com.elin4it.ssm.pojo.Comment;
import com.elin4it.ssm.pojo.CommentQuestionnaire;
import com.elin4it.ssm.service.CommentQuestionnaireService;
import com.elin4it.ssm.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jpan on 2016/5/10.
 */
@Controller
@RequestMapping("commentq")
public class CommentQuestionnaireController {
    @Autowired
    private CommentQuestionnaireService commentQuestionnaireService;

    @RequestMapping("new")
    public String newDetail(HttpServletRequest request, @RequestParam(required = false) Integer type, ModelMap model) {

        return "comment/commentq";
    }

    @RequestMapping("/save")
    public String addQComment(CommentQuestionnaire commentQuestionnaire, ModelMap model) {
        try {
            commentQuestionnaireService.insertCommentQ(commentQuestionnaire);
        } catch (BusinessException e) {
            model.put("message", e.getLocalizedMessage());
            model.put("status", false);

            return "comment/commentq";
        }
        model.put("message", "保存成功");
        model.put("status", true);

        return "index";
    }

    @RequestMapping("detail")
    public @ResponseBody
    String showDetail(HttpServletRequest request, @RequestParam(required = true,defaultValue = "0") Integer themeId, ModelMap model) {
        JsonDataModel jsonDataModel = new JsonDataModel();

        try {
            CommentQuestionnaire commentQuestionnaire = commentQuestionnaireService.selectCommentQByTheme(themeId);
            jsonDataModel.setData(commentQuestionnaire);

        } catch (BusinessException e) {
            jsonDataModel.setErrorCode(ErrorCodeConst.BUS_EXCEPTION);
            jsonDataModel.setStatus("false");
            jsonDataModel.setMessage(e.getLocalizedMessage());
        }

        return jsonDataModel.toJSONString();

    }
}
