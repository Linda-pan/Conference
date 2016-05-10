package com.elin4it.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.elin4it.ssm.constant.ErrorCodeConst;
import com.elin4it.ssm.exception.BusinessException;

import com.elin4it.ssm.model.JsonDataModel;
import com.elin4it.ssm.mybatis.pagination.Order;
import com.elin4it.ssm.mybatis.pagination.PageBounds;
import com.elin4it.ssm.pojo.Comment;
import com.elin4it.ssm.pojo.CommentQuestionnaire;
import com.elin4it.ssm.service.CommentQuestionnaireService;
import com.elin4it.ssm.service.CommentService;
import com.elin4it.ssm.utils.ConfigPropertiesUtil;
import com.elin4it.ssm.utils.Grid;
import com.elin4it.ssm.utils.WebUtil;
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
@RequestMapping("comment")
public class CommentController extends BaseController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentQuestionnaireService commentQuestionnaireService;

    @RequestMapping("")
    public String index(ModelMap model, @RequestParam(required = true, defaultValue = "1") Integer paperId) {
        model.put("paperId", paperId);
        JSONObject statusMap = new JSONObject();

        statusMap.put("3", "D");
        statusMap.put("2", "C");
        statusMap.put("1", "B");
        statusMap.put("0", "A");

        model.put("status", statusMap);

        return "/comment/commentdetail";
    }

    @RequestMapping("qdetail")
    public String index1(ModelMap model, @RequestParam(required = true, defaultValue = "1") Integer paperId) {
        model.put("paperId", paperId);

        JSONObject statusMap = new JSONObject();

        statusMap.put("3", "D");
        statusMap.put("2", "C");
        statusMap.put("1", "B");
        statusMap.put("0", "A");

        model.put("status", statusMap);
        return "/comment/commentqdetail";
    }

    @RequestMapping("detail")
    public
    @ResponseBody
    String showDetail(HttpServletRequest request, @RequestParam(required = false) Integer themeId, @RequestParam(required = true, defaultValue = "1") Integer paperId, ModelMap model) {
        JsonDataModel jsonDataModel = new JsonDataModel();

        try {
            Comment comment = commentService.selectCommentByPAndU(paperId);
            jsonDataModel.setData(comment);

        } catch (BusinessException e) {
            jsonDataModel.setErrorCode(ErrorCodeConst.BUS_EXCEPTION);
            jsonDataModel.setStatus("false");
            jsonDataModel.setMessage(e.getLocalizedMessage());
        }

        return jsonDataModel.toJSONString();
    }

    @RequestMapping("/save")
    public String addComment(Comment comment, ModelMap model) {
        try {
            commentService.insertComment(comment);
        } catch (BusinessException e) {
            model.put("message", e.getLocalizedMessage());
            model.put("status", false);

            return "/comment/commentqdetail";
        }
        model.put("message", "保存成功");
        model.put("status", true);

        return "index";
    }

    @RequestMapping("list")
    public
    @ResponseBody
    String getCommentList(@RequestParam(required = false, defaultValue = "1") int pageNo, @RequestParam(required = false, defaultValue = "50") int pageSize, @RequestParam(required = false, defaultValue = "1") Integer paperId) {
        PageBounds<JSONObject> pageBounds = new PageBounds<JSONObject>(pageNo, pageSize, Order.create("user_id", "desc"));

       // commentService.findCommentPageByPaperId(paperId);

        Grid grid = new Grid(pageBounds.getPageList().getTotalCount(), pageBounds.getPageList().getResult());

        return grid.toJSONString();
    }
}
