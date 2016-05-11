package com.elin4it.ssm.mapper.dao;

import com.elin4it.ssm.mapper.AllPaperThemeMapper;
import com.elin4it.ssm.pojo.AllPaperTheme;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jpan on 2016/4/7.
 */
@Repository
public interface AllPaperThemeMapperDao extends AllPaperThemeMapper {
    List<AllPaperTheme> selectAllTheme();

    AllPaperTheme selectByThemeId(int themeId);

    int insertByName(String theme);

    AllPaperTheme getByName(String theme);

}
