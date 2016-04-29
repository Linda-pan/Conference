package com.elin4it.ssm.mapper;

import com.elin4it.ssm.pojo.CommentQuestionnaire;

public interface CommentQuestionnaireMapper {
    int deleteByPrimaryKey(Integer commentQId);

    int insert(CommentQuestionnaire record);

    int insertSelective(CommentQuestionnaire record);

    CommentQuestionnaire selectByPrimaryKey(Integer commentQId);

    int updateByPrimaryKeySelective(CommentQuestionnaire record);

    int updateByPrimaryKey(CommentQuestionnaire record);
}