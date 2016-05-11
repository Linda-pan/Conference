package com.elin4it.ssm.service;

import com.alibaba.fastjson.JSONObject;
import com.elin4it.ssm.constant.PaperStatusConst;
import com.elin4it.ssm.mapper.dao.CommentMapperDao;
import com.elin4it.ssm.mapper.dao.CommentQuestionnaireMapperDao;
import com.elin4it.ssm.mapper.dao.PaperMapperDao;
import com.elin4it.ssm.mapper.dao.ReviewerPaperMapperDao;
import com.elin4it.ssm.mybatis.pagination.PageBounds;
import com.elin4it.ssm.mybatis.pagination.PageList;
import com.elin4it.ssm.pojo.*;
import com.elin4it.ssm.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jpan on 2016/5/10.
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapperDao commentMapperDao;

    @Autowired
    private CommentQuestionnaireService commentQuestionnaireService;

    @Autowired
    private PaperService paperService;

    @Autowired
    private PaperMapperDao paperMapperDao;

    @Autowired
    private ReviewerPaperMapperDao reviewerPaperMapperDao;

    @Autowired
    private ReviewerPaperService reviewerPaperService;

    public void insertComment(Comment comment) {
        List<Byte> result = new ArrayList<>();
        result.add(0, comment.getQ1());
        result.add(1, comment.getQ2());
        result.add(2, comment.getQ3());
        result.add(3, comment.getQ4());
        result.add(4, comment.getQ5());
        int score = 0;
        for (Byte aByte : result) {
            if (aByte.equals((byte) 0)) {
                score += 100;
            }
            if (aByte.equals((byte) 1)) {
                score += 75;
            }
            if (aByte.equals((byte) 2)) {
                score += 50;
            }
            if (aByte.equals((byte) 3)) {
                score += 25;
            }
        }
        score = score / 5;
        comment.setScore((byte) score);

        int userId = WebUtil.getCurrentUser().getUserId();
        comment.setUserId(userId);
        commentMapperDao.insertSelective(comment);

        if (this.getCommentCountByPaperId(comment.getPaperId()) >= 3) {

            int averageScore = 0;
            int count = 0;
            List<Comment> comments = commentMapperDao.selectCommentByPaperId(comment.getPaperId());
            for (Comment comment1 : comments) {
                averageScore += comment1.getScore();
                count++;
            }
            Paper paper=paperService.getByPaperId(comment.getPaperId());
            paper.setAverageScore(averageScore / count);

            if(paper.getAverageScore()>=75){
                paper.setPaperStatus((byte)2);
                /**此处应该发送邮件通知***/


            }else{
                paper.setPaperStatus((byte)6);
                /**此处应该发送邮件通知***/

            }
        }
    }

    public Comment selectCommentByPAndU(int paperId) {
        int userId = WebUtil.getCurrentUser().getUserId();
        CommentKey commentKey = new CommentKey();
        commentKey.setUserId(userId);
        commentKey.setPaperId(paperId);

        return commentMapperDao.selectByPrimaryKey(commentKey);
    }

    public List<JSONObject> findCommentPageByPaperId(PageBounds<JSONObject> pageBounds, int paperId) {
        PageBounds<Comment> param = new PageBounds<>(pageBounds.getPageNo(), pageBounds.getPageSize(), pageBounds.getOrders());
        List<Comment> commentList = commentMapperDao.selectCommentByPaperId(param, paperId);

        List<JSONObject> commentModelList = new ArrayList<>();
        for (Comment comment : commentList) {

            JSONObject commentModel = new JSONObject();
            commentModel.put("reviewerId", comment.getUserId());
            commentModel.put("shortComment", comment.getShortComment());
            commentModel.put("score", comment.getScore());
            CommentQuestionnaire cq = commentQuestionnaireService.selectCommentQByTheme(0);
            String[] que = new String[5];
            que[0] = cq.getQ1();
            que[1] = cq.getQ2();
            que[2] = cq.getQ3();
            que[3] = cq.getQ4();
            que[4] = cq.getQ5();

            String[] rStr = new String[5];
            byte[] result = new byte[5];
            result[0] = comment.getQ1();
            result[1] = comment.getQ2();
            result[2] = comment.getQ3();
            result[3] = comment.getQ4();
            result[4] = comment.getQ5();

            for (int i = 0; i <= 4; i++) {
                if (result[i] == 0) {
                    rStr[i] = que[i] + " : 优";
                }
                if (result[i] == 1) {
                    rStr[i] = que[i] + " : 良";
                }
                if (result[i] == 2) {
                    rStr[i] = que[i] + " : 及格";
                }
                if (result[i] == 3) {
                    rStr[i] = que[i] + " : 差";
                }
            }

            commentModel.put("Q1", rStr[0]);
            commentModel.put("Q2", rStr[1]);
            commentModel.put("Q3", rStr[2]);
            commentModel.put("Q4", rStr[3]);
            commentModel.put("Q5", rStr[4]);

            commentModelList.add(commentModel);
        }

        PageList<JSONObject> pageList = new PageList<>(param.getPageList().getPageNo(), param.getPageList().getPageSize(), param.getPageList().getTotalCount(), commentModelList);
        pageBounds.setPageList(pageList);

        return commentModelList;
    }

    public int getCommentCountByPaperId(int id) {
        List<Comment> comments = commentMapperDao.selectCommentByPaperId(id);
        int count = 0;
        for (Comment comment : comments) {
            if (comment != null) {
                count++;
            }
        }
        return count;
    }
}
