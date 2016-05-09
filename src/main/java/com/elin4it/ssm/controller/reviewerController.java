package com.elin4it.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.elin4it.ssm.mybatis.pagination.Order;
import com.elin4it.ssm.mybatis.pagination.PageBounds;
import com.elin4it.ssm.pojo.User;
import com.elin4it.ssm.service.UserService;
import com.elin4it.ssm.utils.ConfigPropertiesUtil;
import com.elin4it.ssm.utils.Grid;
import com.elin4it.ssm.utils.WebUtil;
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
@RequestMapping("/user/reviewer")
public class ReviewerController {
    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String index(ModelMap model) {
        model.put("reviewerPaperUrl", ConfigPropertiesUtil.getProperties("reviewer_paper_list_url"));
        return "/user/reviewer";
    }

    @RequestMapping("list")
    public
    @ResponseBody
    String getReviewerList(@RequestParam(required = false, defaultValue = "1") int pageNo, @RequestParam(required = false, defaultValue = "50") int pageSize) {
        PageBounds<JSONObject> pageBounds = new PageBounds<>(pageNo, pageSize, Order.create("user_id", "desc"));
        userService.findReviewerPage(pageBounds);

        Grid grid = new Grid(pageBounds.getPageList().getTotalCount(), pageBounds.getPageList().getResult());

        return grid.toJSONString();
    }

    @RequestMapping("my")
    public String index1(ModelMap model,@RequestParam(required = true,defaultValue = "1")Integer paperid) {

        model.put("reviewerPaperUrl", ConfigPropertiesUtil.getProperties("reviewer_paper_list_url"));
        model.put("paperId", paperid);
        return "/user/myreviewer";
    }

    @RequestMapping("myreviewer")
    public
    @ResponseBody
    String getOneList(@RequestParam(required = false, defaultValue = "1") int pageNo, @RequestParam(required = false, defaultValue = "50") int pageSize,@RequestParam(required = true)Integer paperId) {
        PageBounds<JSONObject> pageBounds = new PageBounds<>(pageNo, pageSize, Order.create("user_id", "desc"));
        userService.findReviewerPageByPaperId(pageBounds,paperId);

        Grid grid = new Grid(pageBounds.getPageList().getTotalCount(), pageBounds.getPageList().getResult());

        return grid.toJSONString();
    }
}
