package com.elin4it.ssm.service;

import com.alibaba.fastjson.JSONObject;
import com.elin4it.ssm.exception.BusinessException;
import com.elin4it.ssm.mapper.ConferenceMapper;
import com.elin4it.ssm.mapper.dao.CommentMapperDao;
import com.elin4it.ssm.mapper.dao.ConferenceMapperDao;
import com.elin4it.ssm.mapper.dao.ConferenceStatusMapperDao;
import com.elin4it.ssm.pojo.Conference;
import com.elin4it.ssm.pojo.ConferenceStatus;
import com.elin4it.ssm.utils.DateUtils;
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

    @Autowired
    private ConferenceStatusMapperDao conferenceStatusMapperDao;

    @Autowired
    private ConferenceStatusService conferenceStatusService;

    public void insertConference(Conference conference) {
        conference.setCreativeTime(new Date());
        conferenceMapperDao.insert(conference);
        conferenceStatusService.insertByNewConference(conference);
    }

    public JSONObject selectLastConference() {
        JSONObject conference = new JSONObject();
        if (conferenceMapperDao.selectLastConference() == null) {
            conference.put("conferenceName", "仅显示最近的一次会议");
            conference.put("conferenceIntro", "无当前会议，请去添加会议!");
            return conference;
        }
        Conference conference1 = conferenceMapperDao.selectLastConference();
        String startTime = DateUtils.dateToString(conference1.getStartTime(), "yyyy-MM-dd HH:mm");
        String endTime = DateUtils.dateToString(conference1.getEndTime(), "yyyy-MM-dd HH:mm");

        conference.put("conferenceName", conference1.getConferenceName());
        conference.put("conferenceIntro", conference1.getConferenceIntro());
        conference.put("startTime", startTime);
        conference.put("endTime", endTime);
        conference.put("conferenceId", conference1.getConferenceId());

        ConferenceStatus conferenceStatus = conferenceStatusMapperDao.selectLastOne(conference1.getConferenceId());
        conference.put("conferenceStatus", conferenceStatus.getConferenceStatus());

        return conference;
    }
}
