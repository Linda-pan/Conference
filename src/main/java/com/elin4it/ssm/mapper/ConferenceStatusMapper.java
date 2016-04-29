package com.elin4it.ssm.mapper;

import com.elin4it.ssm.pojo.ConferenceStatus;
import com.elin4it.ssm.pojo.ConferenceStatusKey;

public interface ConferenceStatusMapper {
    int deleteByPrimaryKey(ConferenceStatusKey key);

    int insert(ConferenceStatus record);

    int insertSelective(ConferenceStatus record);

    ConferenceStatus selectByPrimaryKey(ConferenceStatusKey key);

    int updateByPrimaryKeySelective(ConferenceStatus record);

    int updateByPrimaryKey(ConferenceStatus record);
}