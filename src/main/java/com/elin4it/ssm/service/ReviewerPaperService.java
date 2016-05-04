package com.elin4it.ssm.service;

import com.elin4it.ssm.mapper.dao.ReviewerPaperMapperDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jpan on 2016/5/3.
 */
@Service
public class ReviewerPaperService {
    @Autowired
    private ReviewerPaperMapperDao reviewerPaperMapperDao;

    public int getCount(int id) {
        List<Integer> paperIdList = reviewerPaperMapperDao.selectPaperByRId(id);
        int count = 0;
        for (Integer integer : paperIdList) {
            if (integer != null) {
                count++;
            }
        }
        return count;
    }
}
