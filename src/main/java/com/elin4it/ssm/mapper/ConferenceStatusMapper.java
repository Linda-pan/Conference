package com.elin4it.ssm.mapper;

import com.elin4it.ssm.pojo.ConferenceStatus;

public interface ConferenceStatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ConferenceStatus record);

    int insertSelective(ConferenceStatus record);

    ConferenceStatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ConferenceStatus record);

    int updateByPrimaryKey(ConferenceStatus record);
}