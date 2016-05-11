package com.elin4it.ssm.mapper;

import com.elin4it.ssm.pojo.AllPaperTheme;

public interface AllPaperThemeMapper {
    int deleteByPrimaryKey(Integer themeId);

    int insert(AllPaperTheme record);

    int insertSelective(AllPaperTheme record);

    AllPaperTheme selectByPrimaryKey(Integer themeId);

    int updateByPrimaryKeySelective(AllPaperTheme record);

    int updateByPrimaryKey(AllPaperTheme record);
}