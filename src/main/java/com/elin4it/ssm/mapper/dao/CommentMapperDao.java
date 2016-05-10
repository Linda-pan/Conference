package com.elin4it.ssm.mapper.dao;

import com.elin4it.ssm.mapper.CommentMapper;
import com.elin4it.ssm.mybatis.pagination.PageBounds;
import com.elin4it.ssm.pojo.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jpan on 2016/4/7.
 */
@Repository
public interface CommentMapperDao extends CommentMapper {
    List<Comment> selectCommentByPaperId(int paperId);

    List<Comment> selectCommentByPaperId(@Param("pageBounds") PageBounds<Comment> pageBounds ,int paperId);
}
