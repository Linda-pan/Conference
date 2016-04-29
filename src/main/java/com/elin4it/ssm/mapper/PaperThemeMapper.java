package com.elin4it.ssm.mapper;

import com.elin4it.ssm.pojo.PaperTheme;

public interface PaperThemeMapper {
    int deleteByPrimaryKey(Integer paperId);

    int insert(PaperTheme record);

    int insertSelective(PaperTheme record);

    PaperTheme selectByPrimaryKey(Integer paperId);

    int updateByPrimaryKeySelective(PaperTheme record);

    int updateByPrimaryKey(PaperTheme record);
}