package com.elin4it.ssm.service;

import com.elin4it.ssm.mapper.dao.CommentQuestionnaireMapperDao;
import com.elin4it.ssm.mapper.dao.UserMapperDao;
import com.elin4it.ssm.pojo.CommentQuestionnaire;
import com.elin4it.ssm.pojo.User;
import com.elin4it.ssm.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by jpan on 2016/5/10.
 */
@Service
public class CommentQuestionnaireService {
    @Autowired
    private CommentQuestionnaireMapperDao commentQuestionnaireMapperDao;

    @Autowired
    private UserMapperDao userMapperDao;

    public void insertCommentQ(CommentQuestionnaire commentQuestionnaire) {
        commentQuestionnaire.setUpdateTime(new Date());

        User user = userMapperDao.selectByPrimaryKey( WebUtil.getCurrentUser().getUserId());
        commentQuestionnaire.setAdminId(user.getAdminId());

        commentQuestionnaireMapperDao.insertSelective(commentQuestionnaire);
    }

    public CommentQuestionnaire selectCommentQByTheme(int themeId){

        return commentQuestionnaireMapperDao.selectByThemeId(themeId);
    }

}
