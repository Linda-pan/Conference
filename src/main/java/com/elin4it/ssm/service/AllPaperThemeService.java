package com.elin4it.ssm.service;

import com.elin4it.ssm.mapper.dao.AllPaperThemeMapperDao;
import com.elin4it.ssm.mapper.dao.PaperThemeMapperDao;
import com.elin4it.ssm.pojo.AllPaperTheme;
import com.elin4it.ssm.pojo.PaperTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jpan on 2016/5/10.
 */
@Service
public class AllPaperThemeService {
    @Autowired
    private AllPaperThemeMapperDao allPaperThemeMapperDao;

    @Autowired
    private PaperThemeMapperDao paperThemeMapperDao;

    public List<AllPaperTheme> getThemeByPaperId(int paperId){
        PaperTheme paperTheme=paperThemeMapperDao.selectByPrimaryKey(paperId);
        List<AllPaperTheme> allPaperThemes =new ArrayList<>();

        AllPaperTheme themeone=allPaperThemeMapperDao.selectByThemeId(paperTheme.getFirstThemeId());
        allPaperThemes.add(0,themeone);

        if(paperTheme.getSecondThemeId()!=null){
            AllPaperTheme themetwo=allPaperThemeMapperDao.selectByThemeId(paperTheme.getSecondThemeId());
            allPaperThemes.add(1,themetwo);
        }
        return allPaperThemes;
    }

    public String getThemeStrByPaperId(int paperId){
        PaperTheme paperTheme=paperThemeMapperDao.selectByPrimaryKey(paperId);

        AllPaperTheme themeone=allPaperThemeMapperDao.selectByThemeId(paperTheme.getFirstThemeId());
        String str="分类一:";
        str+=  themeone.getTheme();
        if(paperTheme.getSecondThemeId()!=null){
            AllPaperTheme themetwo=allPaperThemeMapperDao.selectByThemeId(paperTheme.getSecondThemeId());
            str+="  分类二:";
            str+=themetwo.getTheme();
        }
        return str;
    }
}
