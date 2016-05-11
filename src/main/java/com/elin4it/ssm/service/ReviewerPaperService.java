package com.elin4it.ssm.service;

import com.elin4it.ssm.mapper.dao.ReviewerPaperMapperDao;
import com.elin4it.ssm.pojo.ReviewerPaper;
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

    public int getPaperCountByReviewerId(int id) {
        List<ReviewerPaper> reviewerPaperList = reviewerPaperMapperDao.selectReviewerPaperByReviewerId(id);
        int count = 0;
        for (ReviewerPaper reviewerPaper : reviewerPaperList) {
            if (reviewerPaper != null) {
                count++;
            }
        }
        return count;
    }

    public int getReviewerCountByPaperId(int id) {
        List<ReviewerPaper> reviewerPaperList = reviewerPaperMapperDao.selectReviewerByPaperId(id);
        int count = 0;
        for (ReviewerPaper reviewerPaper : reviewerPaperList) {
            if (reviewerPaper != null) {
                count++;
            }
        }
        return count;
    }
}
