package com.elin4it.ssm.mapper.dao;

import com.elin4it.ssm.mapper.PaperMapper;
import com.elin4it.ssm.mybatis.pagination.PageBounds;
import com.elin4it.ssm.pojo.Paper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jpan on 2016/4/7.
 */
@Repository
public interface PaperMapperDao extends PaperMapper {
    List<Paper> selectPaper(PageBounds<Paper> pageBounds);

    List<Paper> selectPaperByUid(int user_id);
}
