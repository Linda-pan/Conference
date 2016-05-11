package com.elin4it.ssm.mapper.dao;

import com.elin4it.ssm.mapper.ReviewerPaperMapper;
import com.elin4it.ssm.mybatis.pagination.PageBounds;
import com.elin4it.ssm.pojo.Paper;
import com.elin4it.ssm.pojo.ReviewerPaper;
import com.elin4it.ssm.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jpan on 2016/4/7.
 */
@Repository
public interface ReviewerPaperMapperDao extends ReviewerPaperMapper {
    List<ReviewerPaper> selectReviewerPaperByReviewerId(int userId);

    List<ReviewerPaper> selectReviewerPaperByReviewerId(PageBounds<Paper> pageBounds, int userId);

    List<ReviewerPaper> selectReviewerByPaperId(PageBounds<User> pageBounds, @Param("paperId") int paperId);

    List<ReviewerPaper> selectReviewerByPaperId(@Param("paperId") int paperId);
}
