package com.elin4it.ssm.mapper;

import com.elin4it.ssm.pojo.Notice;

public interface NoticeMapper {
    int deleteByPrimaryKey(Integer noticeId);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer noticeId);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);
}