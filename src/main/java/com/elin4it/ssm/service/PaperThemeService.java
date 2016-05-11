package com.elin4it.ssm.service;

import com.elin4it.ssm.mapper.dao.AllPaperThemeMapperDao;
import com.elin4it.ssm.mapper.dao.PaperThemeMapperDao;
import com.elin4it.ssm.pojo.AllPaperTheme;
import com.elin4it.ssm.pojo.Paper;
import com.elin4it.ssm.pojo.PaperTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jpan on 2016/5/10.
 */
@Service
public class PaperThemeService {
    @Autowired
    private PaperThemeMapperDao paperThemeMapperDao;

}
