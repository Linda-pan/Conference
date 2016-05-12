package com.elin4it.ssm.service;

import com.alibaba.fastjson.JSONObject;
import com.elin4it.ssm.mapper.ReviewerPaperMapper;
import com.elin4it.ssm.mapper.dao.AllPaperThemeMapperDao;
import com.elin4it.ssm.mapper.dao.PaperMapperDao;
import com.elin4it.ssm.mapper.dao.ReviewerPaperMapperDao;
import com.elin4it.ssm.mybatis.pagination.PageBounds;
import com.elin4it.ssm.mybatis.pagination.PageList;
import com.elin4it.ssm.pojo.Comment;
import com.elin4it.ssm.pojo.Paper;
import com.elin4it.ssm.pojo.ReviewerPaper;
import com.elin4it.ssm.pojo.User;
import com.elin4it.ssm.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jpan on 2016/5/3.
 */
@Service
public class PaperService {
    @Autowired
    private PaperMapperDao paperMapperDao;

    @Autowired
    private ReviewerPaperMapperDao reviewerPaperMapperDao;

    @Autowired
    private AllPaperThemeService allPaperThemeService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    public List<Paper> findAllPaperPage(PageBounds<JSONObject> pageBounds) {
        PageBounds<Paper> param = new PageBounds<>(pageBounds.getPageNo(), pageBounds.getPageSize(), pageBounds.getOrders());
        List<Paper> paperList = paperMapperDao.selectPaper(param);

        List<JSONObject> paperModelList = new ArrayList<>();
        for (Paper paper : paperList) {
            JSONObject paperModel = new JSONObject();
            paperModel.put("paperId", paper.getPaperId());
            paperModel.put("paperName", paper.getPaperName());
            paperModel.put("userId", paper.getUserId());
            paperModel.put("paperStatus", paper.getPaperStatus());
            paperModel.put("averageScore", paper.getAverageScore());
            paperModel.put("isEmailPost", paper.getIsEmailPost());
            paperModel.put("paperContent", paper.getPaperContent());
            paperModel.put("updateTime", paper.getUpdateTime());

            paperModel.put("themeStr", allPaperThemeService.getThemeStrByPaperId(paper.getPaperId()));
            paperModel.put("theme", allPaperThemeService.getThemeByPaperId(paper.getPaperId()));
            paperModelList.add(paperModel);
        }

        PageList<JSONObject> pageList = new PageList<>(param.getPageList().getPageNo(), param.getPageList().getPageSize(), param.getPageList().getTotalCount(), paperModelList);
        pageBounds.setPageList(pageList);

        return paperList;
    }

    public List<Paper> findPaperPageByAuthorId(PageBounds<JSONObject> pageBounds, int uid) {

        PageBounds<Paper> param = new PageBounds<>(pageBounds.getPageNo(), pageBounds.getPageSize(), pageBounds.getOrders());
        List<Paper> paperList = paperMapperDao.selectPaperByUid(param, uid);

        List<JSONObject> paperModelList = new ArrayList<>();
        for (Paper paper : paperList) {
            JSONObject paperModel = new JSONObject();
            paperModel.put("paperId", paper.getPaperId());
            paperModel.put("paperName", paper.getPaperName());
            paperModel.put("paperStatus", paper.getPaperStatus());
            paperModel.put("averageScore", paper.getAverageScore());
            paperModel.put("themeStr", allPaperThemeService.getThemeStrByPaperId(paper.getPaperId()));
            paperModel.put("paperContent", paper.getPaperContent());
            paperModel.put("updateTime", paper.getUpdateTime());
            paperModel.put("isEmailPost", paper.getIsEmailPost());
            paperModelList.add(paperModel);
        }

        PageList<JSONObject> pageList = new PageList<>(param.getPageList().getPageNo(), param.getPageList().getPageSize(), param.getPageList().getTotalCount(), paperModelList);
        pageBounds.setPageList(pageList);

        return paperList;
    }

    public List<Paper> findPaperByAuthorId(int uid) {
        List<Paper> paperList = paperMapperDao.selectPaperByUid(uid);

        return paperList;
    }

    public int findPaperNumByAuthorId(int uid) {
        List<Paper> paperList = paperMapperDao.selectPaperByUid(uid);
        int count = 0;
        for (Paper paper : paperList) {
            count++;
        }

        return count;
    }

    public List<JSONObject> findPaperPageByReviewerId(PageBounds<JSONObject> pageBounds, int userId) {
        PageBounds<Paper> param = new PageBounds<>(pageBounds.getPageNo(), pageBounds.getPageSize(), pageBounds.getOrders());

        List<JSONObject> paperModelList = new ArrayList<>();
        List<ReviewerPaper> reviewerPapers = reviewerPaperMapperDao.selectReviewerPaperByReviewerId(param, userId);
        for (ReviewerPaper reviewerPaper : reviewerPapers) {

            Paper paper = paperMapperDao.selectByPrimaryKey(reviewerPaper.getPaperId());
            JSONObject paperModel = new JSONObject();
            paperModel.put("paperId", paper.getPaperId());
            paperModel.put("userId", paper.getUserId());
            paperModel.put("paperName", paper.getPaperName());
            paperModel.put("paperStatus", paper.getPaperStatus());
            paperModel.put("averageScore", paper.getAverageScore());
            paperModel.put("isEmailPost", paper.getIsEmailPost());
            paperModel.put("paperContent", paper.getPaperContent());
            paperModel.put("updateTime", paper.getUpdateTime());

            Comment comment = commentService.selectCommentByPAndU(paper.getPaperId());
            String is = "";
            if (comment == null) {
                is = "未填写";
            } else {
                is = "已填写";
            }

            paperModel.put("isComment", is);
            paperModel.put("themeStr", allPaperThemeService.getThemeStrByPaperId(paper.getPaperId()));

            User user = userService.selectById(paper.getUserId());
            String authorName = "";
            if (user.getIsShowName()) {
                authorName = user.getTrueName();
            } else {
                authorName = "匿名";
            }
            paperModel.put("authorName", authorName);
            paperModel.put("isShowName", user.getIsShowName());

            paperModelList.add(paperModel);
        }

        PageList<JSONObject> pageList = new PageList<>(param.getPageList().getPageNo(), param.getPageList().getPageSize(), param.getPageList().getTotalCount(), paperModelList);
        pageBounds.setPageList(pageList);

        return paperModelList;
    }

    public Paper getByPaperId(int paperId) {
        return paperMapperDao.selectByPrimaryKey(paperId);
    }

    public List<JSONObject> findEndPaperPageByReviewerId(PageBounds<JSONObject> pageBounds, int userId) {
        PageBounds<Paper> param = new PageBounds<>(pageBounds.getPageNo(), pageBounds.getPageSize(), pageBounds.getOrders());

        List<JSONObject> paperModelList = new ArrayList<>();
        List<ReviewerPaper> reviewerPapers = reviewerPaperMapperDao.selectReviewerPaperByReviewerId(param, userId);
        for (ReviewerPaper reviewerPaper : reviewerPapers) {
            Paper paper = paperMapperDao.selectByPrimaryKey(reviewerPaper.getPaperId());

            Comment comment = commentService.selectCommentByPAndU(paper.getPaperId());
            if (comment != null) {

                JSONObject paperModel = new JSONObject();
                paperModel.put("paperId", paper.getPaperId());
                paperModel.put("userId", paper.getUserId());
                paperModel.put("paperName", paper.getPaperName());
                paperModel.put("paperStatus", paper.getPaperStatus());
                paperModel.put("averageScore", paper.getAverageScore());
                paperModel.put("isEmailPost", paper.getIsEmailPost());
                paperModel.put("paperContent", paper.getPaperContent());
                paperModel.put("updateTime", paper.getUpdateTime());

                paperModel.put("comment", "查看问卷");
                paperModel.put("themeStr", allPaperThemeService.getThemeStrByPaperId(paper.getPaperId()));

                User user = userService.selectById(paper.getUserId());
                String authorName = "";
                if (user.getIsShowName()) {
                    authorName = user.getTrueName();
                } else {
                    authorName = "匿名";
                }
                paperModel.put("authorName", authorName);
                paperModel.put("isShowName", user.getIsShowName());

                paperModelList.add(paperModel);
            }
        }

        PageList<JSONObject> pageList = new PageList<>(param.getPageList().getPageNo(), param.getPageList().getPageSize(), param.getPageList().getTotalCount(), paperModelList);
        pageBounds.setPageList(pageList);

        return paperModelList;
    }

    public void insert(String name){
        Paper paper =new Paper();

        paper.setUserId(WebUtil.getCurrentUser().getUserId());
        paper.setCreateTime(new Date());
        paper.setPaperStatus((byte)0);
        paper.setPaperName(name);

        /***补***/
        paper.setPaperContent("");
        paper.setIsEmailPost(false);

        paperMapperDao.insertSelective(paper);
    }
}
