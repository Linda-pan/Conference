package com.elin4it.ssm.mapper.dao;

import com.elin4it.ssm.mapper.ConferenceMapper;
import com.elin4it.ssm.pojo.Conference;
import org.springframework.stereotype.Repository;

/**
 * Created by jpan on 2016/4/7.
 */
@Repository
public interface ConferenceMapperDao extends ConferenceMapper {

    Conference selectLastConference();
}
