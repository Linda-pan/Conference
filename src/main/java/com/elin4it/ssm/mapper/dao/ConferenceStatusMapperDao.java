package com.elin4it.ssm.mapper.dao;

import com.elin4it.ssm.mapper.ConferenceStatusMapper;
import com.elin4it.ssm.pojo.ConferenceStatus;
import org.springframework.stereotype.Repository;

/**
 * Created by jpan on 2016/4/7.
 */
@Repository
public interface ConferenceStatusMapperDao extends ConferenceStatusMapper {
    ConferenceStatus selectLastOne(int conferenceId);


}
