package com.elin4it.ssm.mapper;

import com.elin4it.ssm.pojo.ReviewerPaper;

public interface ReviewerPaperMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReviewerPaper record);

    int insertSelective(ReviewerPaper record);

    ReviewerPaper selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReviewerPaper record);

    int updateByPrimaryKey(ReviewerPaper record);
}