package com.elin4it.ssm.service;

import com.elin4it.ssm.mapper.dao.PaperMapperDao;
import com.elin4it.ssm.mybatis.pagination.PageBounds;
import com.elin4it.ssm.mybatis.pagination.PageList;
import com.elin4it.ssm.pojo.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jpan on 2016/5/3.
 */
@Service
public class PaperService {
    @Autowired
    private PaperMapperDao paperMapperDao;

    public List<Paper> findPage(PageBounds<Paper> pageBounds) {
        List<Paper> paperList = paperMapperDao.selectPaper(pageBounds);

        PageList<Paper> pageList = new PageList<>(pageBounds.getPageList().getPageNo(), pageBounds.getPageList().getPageSize(), pageBounds.getPageList().getTotalCount(), paperList);
        pageBounds.setPageList(pageList);

        return paperList;
    }

    public int findPaperNumByUid(int uid){
        List<Paper> paperList =paperMapperDao.selectPaperByUid(uid);
        int count =0;
        for (Paper paper : paperList) {
            count++;
        }

        return count;
    }

}
