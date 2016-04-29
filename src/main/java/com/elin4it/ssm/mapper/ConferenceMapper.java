package com.elin4it.ssm.mapper;

import com.elin4it.ssm.pojo.Conference;

public interface ConferenceMapper {
    int deleteByPrimaryKey(Integer conferenceId);

    int insert(Conference record);

    int insertSelective(Conference record);

    Conference selectByPrimaryKey(Integer conferenceId);

    int updateByPrimaryKeySelective(Conference record);

    int updateByPrimaryKey(Conference record);
}