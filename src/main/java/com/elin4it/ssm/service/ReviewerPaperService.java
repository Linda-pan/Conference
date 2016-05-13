package com.elin4it.ssm.service;

import com.elin4it.ssm.exception.BusinessException;
import com.elin4it.ssm.mapper.dao.PaperMapperDao;
import com.elin4it.ssm.mapper.dao.ReviewerPaperMapperDao;
import com.elin4it.ssm.mapper.dao.UserMapperDao;
import com.elin4it.ssm.pojo.Paper;
import com.elin4it.ssm.pojo.ReviewerPaper;
import com.elin4it.ssm.pojo.User;
import com.elin4it.ssm.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by jpan on 2016/5/3.
 */
@Service
public class ReviewerPaperService {
    @Autowired
    private ReviewerPaperMapperDao reviewerPaperMapperDao;
    @Autowired
    private UserMapperDao userMapperDao;
    @Autowired
    private PaperService paperService;
    @Autowired
    private PaperMapperDao paperMapperDao;

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

    public void insert(ReviewerPaper reviewerPaper)throws BusinessException{
        int userId = WebUtil.getCurrentUser().getUserId();
        User user = userMapperDao.selectByPrimaryKey(userId);
        reviewerPaper.setAdminId(user.getAdminId());

        List<ReviewerPaper> reviewpapers = reviewerPaperMapperDao.selectReviewerByPaperId(reviewerPaper.getPaperId());

        int num=0;
        for (ReviewerPaper reviewpaper : reviewpapers) {
            num++;
            if (reviewpaper.getUserId().equals(reviewerPaper.getUserId())){
                throw new BusinessException("已分配给该审核员");
            }
        }
        reviewerPaperMapperDao.insert(reviewerPaper);

        if(num==2){
            Paper paper =paperService.getByPaperId(reviewerPaper.getPaperId());
            paper.setPaperStatus((byte)1);
            paper.setUpdateTime(new Date());
            paperMapperDao.updateByPrimaryKeySelective(paper);
        }
    }
}
