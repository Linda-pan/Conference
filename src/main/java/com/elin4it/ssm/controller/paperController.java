package com.elin4it.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.elin4it.ssm.exception.BusinessException;
import com.elin4it.ssm.mybatis.pagination.Order;
import com.elin4it.ssm.mybatis.pagination.PageBounds;
import com.elin4it.ssm.pojo.Paper;
import com.elin4it.ssm.service.PaperService;
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
@RequestMapping("/paper")
public class PaperController extends BaseController{
    @Autowired
    private PaperService paperService;

    @RequestMapping("")
    public String index(ModelMap model) {
        model.put("paperReviewerUrl", ConfigPropertiesUtil.getProperties("paper_reviewer_list_url"));
        model.put("authorDetailUrl", ConfigPropertiesUtil.getProperties("author_detail_url"));
        model.put("paperCommentUrl", ConfigPropertiesUtil.getProperties("paper_comment_url"));
        return "/paper/allpaper";
    }

    @RequestMapping("list")
    public
    @ResponseBody
    String getList(@RequestParam(required = false, defaultValue = "1") int pageNo, @RequestParam(required = false, defaultValue = "50") int pageSize) {
        PageBounds<JSONObject> pageBounds = new PageBounds<>(pageNo, pageSize, Order.create("paper_id", "desc"));
        paperService.findAllPaperPage(pageBounds);

        Grid grid = new Grid(pageBounds.getPageList().getTotalCount(), pageBounds.getPageList().getResult());

        return grid.toJSONString();
    }

    @RequestMapping("my")
    public String index1(ModelMap model,@RequestParam(required = false)Integer uid) {
        if(uid ==  null) {
            uid = WebUtil.getCurrentUser().getUserId();
        }
        model.put("reviewerPaperUrl", ConfigPropertiesUtil.getProperties("reviewer_paper_list_url"));
        model.put("paperReviewerUrl", ConfigPropertiesUtil.getProperties("paper_reviewer_list_url"));
        model.put("paperCommentUrl", ConfigPropertiesUtil.getProperties("paper_comment_url"));

        model.put("userId", uid);
       return "/paper/mypaper";
    }

    @RequestMapping("mypaper")
    public
    @ResponseBody
    String getOneList(@RequestParam(required = false, defaultValue = "1") int pageNo, @RequestParam(required = false, defaultValue = "50") int pageSize,@RequestParam(required = false)Integer userId) {
        PageBounds<JSONObject> pageBounds = new PageBounds<>(pageNo, pageSize, Order.create("paper_id", "desc"));
        paperService.findPaperPageByAuthorId(pageBounds,userId);

        Grid grid = new Grid(pageBounds.getPageList().getTotalCount(), pageBounds.getPageList().getResult());

        return grid.toJSONString();
    }

    @RequestMapping("reviewer")
    public String index2(ModelMap model,@RequestParam(required = false)Integer uid) {
        model.put("reviewerPaperUrl", ConfigPropertiesUtil.getProperties("reviewer_paper_list_url"));
        model.put("paperCommentUrl", ConfigPropertiesUtil.getProperties("paper_comment_url"));
        model.put("emptyCommentUrl", ConfigPropertiesUtil.getProperties("empty_comment_url"));
        model.put("authorDetailUrl", ConfigPropertiesUtil.getProperties("author_detail_url"));

        if(uid==null){
            uid = WebUtil.getCurrentUser().getUserId();
        }
        model.put("userId", uid);
        return "/paper/reviewerpaper";
    }

    @RequestMapping("reviewerpaper")
    public
    @ResponseBody
    String getPaperByRList(@RequestParam(required = false, defaultValue = "1") int pageNo, @RequestParam(required = false, defaultValue = "50") int pageSize,@RequestParam(required = false)Integer userId) {
        PageBounds<JSONObject> pageBounds = new PageBounds<>(pageNo, pageSize, Order.create("paper_id", "desc"));
        paperService.findPaperPageByReviewerId(pageBounds,userId);

        Grid grid = new Grid(pageBounds.getPageList().getTotalCount(), pageBounds.getPageList().getResult());

        return grid.toJSONString();
    }

    @RequestMapping("endreviewer")
    public String index3(ModelMap model,@RequestParam(required = false)Integer uid) {
        model.put("authorDetailUrl", ConfigPropertiesUtil.getProperties("author_detail_url"));
        model.put("reviewerPaperUrl", ConfigPropertiesUtil.getProperties("reviewer_paper_list_url"));
        model.put("paperCommentUrl", ConfigPropertiesUtil.getProperties("paper_comment_url"));
        model.put("reviewerCommentUrl", ConfigPropertiesUtil.getProperties("reviewer_comment_url"));

        if(uid==null){
            uid = WebUtil.getCurrentUser().getUserId();
        }
        model.put("userId", uid);
        return "/paper/endreviewerpaper";
    }

    @RequestMapping("endreviewerpaper")
    public
    @ResponseBody
    String getPaperByRList1(@RequestParam(required = false, defaultValue = "1") int pageNo, @RequestParam(required = false, defaultValue = "50") int pageSize,@RequestParam(required = false)Integer userId) {
        PageBounds<JSONObject> pageBounds = new PageBounds<>(pageNo, pageSize, Order.create("paper_id", "desc"));
        paperService.findEndPaperPageByReviewerId(pageBounds,userId);

        Grid grid = new Grid(pageBounds.getPageList().getTotalCount(), pageBounds.getPageList().getResult());

        return grid.toJSONString();
    }

    @RequestMapping("new")
    public String index4(ModelMap model) {
       int uid = WebUtil.getCurrentUser().getUserId();
        model.put("userId", uid);
        return "/paper/newpaper";
    }

    @RequestMapping("/save")
    public String addConference(String paperName, ModelMap model) {
        try {
            paperService.insert(paperName);
        } catch (BusinessException e) {
            model.put("message", e.getLocalizedMessage());
            model.put("status", false);

            return "forward:/conference/detail";
        }
        model.put("message", "保存成功");
        model.put("status", true);

        return "index";

    }

}
