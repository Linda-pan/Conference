package com.elin4it.ssm.service;

import com.elin4it.ssm.mapper.ConferenceMapper;
import com.elin4it.ssm.mapper.dao.CommentMapperDao;
import com.elin4it.ssm.mapper.dao.ConferenceMapperDao;
import com.elin4it.ssm.pojo.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * Created by jpan on 2016/5/4.
 */
@Service
public class ConferenceService {
    @Autowired
    private ConferenceMapperDao conferenceMapperDao;

    public void insertConference(Conference conference) {
        conference.setCreativeTime(new Date());
        conferenceMapperDao.insertSelective(conference);
    }
}
