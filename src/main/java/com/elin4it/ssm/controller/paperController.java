package com.elin4it.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.elin4it.ssm.exception.BusinessException;
import com.elin4it.ssm.mapper.dao.AllPaperThemeMapperDao;
import com.elin4it.ssm.mapper.dao.ReviewerPaperMapperDao;
import com.elin4it.ssm.mybatis.pagination.Order;
import com.elin4it.ssm.mybatis.pagination.PageBounds;
import com.elin4it.ssm.pojo.AllPaperTheme;
import com.elin4it.ssm.pojo.Paper;
import com.elin4it.ssm.pojo.ReviewerPaper;
import com.elin4it.ssm.pojo.User;
import com.elin4it.ssm.service.AllPaperThemeService;
import com.elin4it.ssm.service.PaperService;
import com.elin4it.ssm.service.ReviewerPaperService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jpan on 2016/5/3.
 */
@Controller
@RequestMapping("/paper")
public class PaperController extends BaseController {
    @Autowired
    private PaperService paperService;

    @Autowired
    private UserService userService;

    @Autowired
    private AllPaperThemeService allPaperThemeService;

    @Autowired
    private AllPaperThemeMapperDao allPaperThemeMapperDao;

    @Autowired
    private ReviewerPaperService reviewerPaperService;

    @Autowired
    private ReviewerPaperMapperDao reviewerPaperMapperDao;

    /**
     * 显示所有论文
     *
     * @param model
     * @return
     */
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

    /**
     * 显示作者所有论文
     *
     * @param model
     * @return
     */
    @RequestMapping("my")
    public String index1(ModelMap model, @RequestParam(required = false) Integer uid) {
        if (uid == null) {
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
    String getOneList(@RequestParam(required = false, defaultValue = "1") int pageNo, @RequestParam(required = false, defaultValue = "50") int pageSize, @RequestParam(required = false) Integer userId) {
        PageBounds<JSONObject> pageBounds = new PageBounds<>(pageNo, pageSize, Order.create("paper_id", "desc"));
        paperService.findPaperPageByAuthorId(pageBounds, userId);

        Grid grid = new Grid(pageBounds.getPageList().getTotalCount(), pageBounds.getPageList().getResult());

        return grid.toJSONString();
    }

    /**
     * 显示审阅者所有论文
     *
     * @param model
     * @return
     */
    @RequestMapping("reviewer")
    public String index2(ModelMap model, @RequestParam(required = false) Integer uid) {
        model.put("reviewerPaperUrl", ConfigPropertiesUtil.getProperties("reviewer_paper_list_url"));
        model.put("paperCommentUrl", ConfigPropertiesUtil.getProperties("paper_comment_url"));
        model.put("emptyCommentUrl", ConfigPropertiesUtil.getProperties("empty_comment_url"));
        model.put("authorDetailUrl", ConfigPropertiesUtil.getProperties("author_detail_url"));

        if (uid == null) {
            uid = WebUtil.getCurrentUser().getUserId();
        }
        model.put("userId", uid);
        return "/paper/reviewerpaper";
    }

    @RequestMapping("reviewerpaper")
    public
    @ResponseBody
    String getPaperByRList(@RequestParam(required = false, defaultValue = "1") int pageNo, @RequestParam(required = false, defaultValue = "50") int pageSize, @RequestParam(required = false) Integer userId) {
        PageBounds<JSONObject> pageBounds = new PageBounds<>(pageNo, pageSize, Order.create("paper_id", "desc"));
        paperService.findPaperPageByReviewerId(pageBounds, userId);

        Grid grid = new Grid(pageBounds.getPageList().getTotalCount(), pageBounds.getPageList().getResult());

        return grid.toJSONString();
    }

    /**
     * 显示审阅者已审核的所有论文
     *
     * @param model
     * @return
     */
    @RequestMapping("endreviewer")
    public String index3(ModelMap model, @RequestParam(required = false) Integer uid) {
        model.put("authorDetailUrl", ConfigPropertiesUtil.getProperties("author_detail_url"));
        model.put("reviewerPaperUrl", ConfigPropertiesUtil.getProperties("reviewer_paper_list_url"));
        model.put("paperCommentUrl", ConfigPropertiesUtil.getProperties("paper_comment_url"));
        model.put("reviewerCommentUrl", ConfigPropertiesUtil.getProperties("reviewer_comment_url"));

        if (uid == null) {
            uid = WebUtil.getCurrentUser().getUserId();
        }
        model.put("userId", uid);
        return "/paper/endreviewerpaper";
    }

    @RequestMapping("endreviewerpaper")
    public
    @ResponseBody
    String getPaperByRList1(@RequestParam(required = false, defaultValue = "1") int pageNo, @RequestParam(required = false, defaultValue = "50") int pageSize, @RequestParam(required = false) Integer userId) {
        PageBounds<JSONObject> pageBounds = new PageBounds<>(pageNo, pageSize, Order.create("paper_id", "desc"));
        paperService.findEndPaperPageByReviewerId(pageBounds, userId);

        Grid grid = new Grid(pageBounds.getPageList().getTotalCount(), pageBounds.getPageList().getResult());

        return grid.toJSONString();
    }

    /**
     * 管理员分配论文
     *
     * @param model
     * @return
     */
    @RequestMapping("allocation")
    public String index4(ModelMap model) {
        model.put("alloctionReviewerUrl", ConfigPropertiesUtil.getProperties("alloction_reviewer_url"));

        return "/paper/allocationpaper";
    }

    /**
     * 显示所有未分配论文
     *
     * @param
     * @return
     */
    @RequestMapping("paperlist")
    public
    @ResponseBody
    String getPaperUnAList(@RequestParam(required = false, defaultValue = "1") int pageNo, @RequestParam(required = false, defaultValue = "50") int pageSize) {
        PageBounds<JSONObject> pageBounds = new PageBounds<>(pageNo, pageSize, Order.create("paper_id", "desc"));
        paperService.findAllUndoPaperPage(pageBounds);

        Grid grid = new Grid(pageBounds.getPageList().getTotalCount(), pageBounds.getPageList().getResult());

        return grid.toJSONString();
    }

    /**
     * 显示论文主题对应的审稿员列表
     *
     * @param model
     * @return
     */
    @RequestMapping("allocationreviewer")
    public String index5(ModelMap model, @RequestParam(required = true) int themeId, @RequestParam(required = true) int paperId) {
        model.put("alloctionReviewerUrl", ConfigPropertiesUtil.getProperties("alloction_reviewer_url"));
        model.put("themeId", themeId);
        model.put("paperId", paperId);
        AllPaperTheme allPaperTheme = allPaperThemeMapperDao.selectByThemeId(themeId);
        model.put("theme", allPaperTheme.getTheme());

        List<User> userList = userService.findReviewerByTheme(themeId);

        model.put("userList", userList);

        return "/user/allocationreviewer";
    }

    @RequestMapping("reviewerlist")
    public
    @ResponseBody
    String getReviewerByThemeList(@RequestParam(required = false, defaultValue = "1") int pageNo, @RequestParam(required = false, defaultValue = "50") int pageSize, @RequestParam(required = true) int themeId) {
        PageBounds<JSONObject> pageBounds = new PageBounds<>(pageNo, pageSize);
        userService.findReviewerPageByTheme(pageBounds, themeId);

        Grid grid = new Grid(pageBounds.getPageList().getTotalCount(), pageBounds.getPageList().getResult());

        return grid.toJSONString();
    }


    /**
     * 管理员保存论文分配
     *
     * @param
     * @return
     */
    @RequestMapping("/reviewersave")
    public String addReviewerPaper(int userId, int paperId,ModelMap modelMap) {
        try {
            ReviewerPaper reviewerPaper = new ReviewerPaper();
            reviewerPaper.setPaperId(paperId);
            reviewerPaper.setUserId(userId);
            reviewerPaperService.insert(reviewerPaper);

        } catch (BusinessException e) {
            modelMap.put("message", e.getLocalizedMessage());
            return "redirect:/paper/allocation";
        }
        modelMap.put("message", "保存成功");

        return "redirect:/paper/allocation";

    }
}
