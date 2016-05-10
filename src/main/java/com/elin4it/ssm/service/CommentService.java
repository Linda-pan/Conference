package com.elin4it.ssm.service;

import com.alibaba.fastjson.JSONObject;
import com.elin4it.ssm.mapper.dao.CommentMapperDao;
import com.elin4it.ssm.mapper.dao.CommentQuestionnaireMapperDao;
import com.elin4it.ssm.mybatis.pagination.PageBounds;
import com.elin4it.ssm.mybatis.pagination.PageList;
import com.elin4it.ssm.pojo.Comment;
import com.elin4it.ssm.pojo.CommentKey;
import com.elin4it.ssm.pojo.CommentQuestionnaire;
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
        score = score / 4;
        comment.setScore((byte) score);

        int userId = WebUtil.getCurrentUser().getUserId();
        comment.setUserId(userId);
        commentMapperDao.insertSelective(comment);
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
            CommentQuestionnaire cq =commentQuestionnaireService.selectCommentQByTheme(0);
            String str="";

            List<String> rStr=new ArrayList<>();
            List<Byte> result = new ArrayList<>();
            result.add(0, comment.getQ1());
            result.add(1, comment.getQ2());
            result.add(2, comment.getQ3());
            result.add(3, comment.getQ4());
            result.add(4, comment.getQ5());
            int score = 0;
            for (int i=0;i<=4;i++) {
              /*  if (result[i]==0) {
                    rStr.add(i,"");
                }
                if (aByte.equals((byte) 1)) {
                    score += 75;
                }
                if (aByte.equals((byte) 2)) {
                    score += 50;
                }
                if (aByte.equals((byte) 3)) {
                    score += 25;
                }*/
            }

          /*  commentModel.put("Q1", comment.getQ1());
            commentModel.put("passpaperNum", count);
            commentModel.put("userId", user.getUserId());
            commentModel.put("trueName", user.getTrueName());
            commentModel.put("telephone", user.getTelephone());
            commentModel.put("email", user.getEmail());
            commentModel.put("username", user.getUsername());
            commentModel.put("title", user.getTitle());
            commentModel.put("isPaymentConfirmed", user.getIsPaymentConfirmed());*/

            commentModelList.add(commentModel);
        }

    PageList<JSONObject> pageList = new PageList<>(param.getPageList().getPageNo(), param.getPageList().getPageSize(), param.getPageList().getTotalCount(), commentModelList);
    pageBounds.setPageList(pageList);

    return commentModelList;
}
}
