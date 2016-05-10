package com.elin4it.ssm.mapper.dao;

import com.elin4it.ssm.mapper.CommentQuestionnaireMapper;
import com.elin4it.ssm.pojo.CommentQuestionnaire;
import org.springframework.stereotype.Repository;

/**
 * Created by jpan on 2016/4/7.
 */
@Repository
public interface CommentQuestionnaireMapperDao extends CommentQuestionnaireMapper {
    CommentQuestionnaire selectByThemeId(Integer themeId);
}
