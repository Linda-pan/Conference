package com.elin4it.ssm.mapper;

import com.elin4it.ssm.pojo.ReviewerTheme;

public interface ReviewerThemeMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(ReviewerTheme record);

    int insertSelective(ReviewerTheme record);

    ReviewerTheme selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(ReviewerTheme record);

    int updateByPrimaryKey(ReviewerTheme record);
}