package com.elin4it.ssm.mapper;

import com.elin4it.ssm.pojo.Comment;
import com.elin4it.ssm.pojo.CommentKey;

public interface CommentMapper {
    int deleteByPrimaryKey(CommentKey key);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(CommentKey key);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}