package com.elin4it.ssm.mapper.dao;

import com.elin4it.ssm.mapper.ReviewerPaperMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jpan on 2016/4/7.
 */
@Repository
public interface ReviewerPaperMapperDao extends ReviewerPaperMapper {
    List<Integer> selectPaperByRId(int userId);


}
