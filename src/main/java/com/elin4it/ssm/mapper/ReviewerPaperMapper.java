package com.elin4it.ssm.mapper;

import com.elin4it.ssm.pojo.ReviewerPaperKey;

public interface ReviewerPaperMapper {
    int deleteByPrimaryKey(ReviewerPaperKey key);

    int insert(ReviewerPaperKey record);

    int insertSelective(ReviewerPaperKey record);
}