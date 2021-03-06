package com.elin4it.ssm.mapper.dao;

import com.elin4it.ssm.mapper.PaperMapper;
import com.elin4it.ssm.mybatis.pagination.PageBounds;
import com.elin4it.ssm.pojo.Paper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jpan on 2016/4/7.
 */
@Repository
public interface PaperMapperDao extends PaperMapper {
    List<Paper> selectPaper(PageBounds<Paper> pageBounds);

    List<Paper> selectPaperByStatus(PageBounds<Paper> pageBounds,@Param("paper_status")int paper_status);

    List<Paper> selectPaperByUid(int user_id);

    List<Paper> selectPaperByUid(PageBounds<Paper> pageBounds, @Param("user_id") int uid);

}
