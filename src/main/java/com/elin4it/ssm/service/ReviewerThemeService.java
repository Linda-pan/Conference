package com.elin4it.ssm.service;

import com.elin4it.ssm.mapper.ReviewerThemeMapper;
import com.elin4it.ssm.mapper.dao.ReviewerThemeMapperDao;
import com.elin4it.ssm.mybatis.pagination.PageBounds;
import com.elin4it.ssm.pojo.ReviewerTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jpan on 2016/5/13.
 */
@Service
public class ReviewerThemeService {
    @Autowired
    private ReviewerThemeMapperDao reviewerThemeMapperDao;


}
