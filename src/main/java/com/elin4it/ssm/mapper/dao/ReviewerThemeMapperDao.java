package com.elin4it.ssm.mapper.dao;

import com.elin4it.ssm.mapper.ReviewerThemeMapper;
import com.elin4it.ssm.mybatis.pagination.PageBounds;
import com.elin4it.ssm.pojo.ReviewerTheme;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jpan on 2016/5/13.
 */
@Repository
public interface ReviewerThemeMapperDao extends ReviewerThemeMapper{
    List<ReviewerTheme> getByThemeId(PageBounds<ReviewerTheme> pageBounds, @Param("theme") int themeId);

    List<ReviewerTheme> getByThemeId(@Param("theme") int themeId);
}
