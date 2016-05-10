package com.elin4it.ssm.service;

import com.elin4it.ssm.constant.ConferenceStatusConst;
import com.elin4it.ssm.mapper.dao.ConferenceStatusMapperDao;
import com.elin4it.ssm.mapper.dao.UserMapperDao;
import com.elin4it.ssm.pojo.Conference;
import com.elin4it.ssm.pojo.ConferenceStatus;
import com.elin4it.ssm.pojo.User;
import com.elin4it.ssm.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by jpan on 2016/5/9.
 */
@Service
public class ConferenceStatusService {
    @Autowired
    private ConferenceStatusMapperDao conferenceStatusMapperDao;

    @Autowired
    private UserMapperDao userMapperDao;

    public void updateConference(ConferenceStatus conferenceStatus) {
        conferenceStatus.setUpdateTime(new Date());

        int userId = WebUtil.getCurrentUser().getUserId();
        User user = userMapperDao.selectByPrimaryKey(userId);
        conferenceStatus.setAdminId(user.getAdminId());

        conferenceStatusMapperDao.insertSelective(conferenceStatus);
    }

    public void insertByNewConference(Conference conference) {
        ConferenceStatus conferenceStatus = new ConferenceStatus();
        conferenceStatus.setUpdateTime(new Date());

        int userId = WebUtil.getCurrentUser().getUserId();
        User user = userMapperDao.selectByPrimaryKey(userId);
        conferenceStatus.setAdminId(user.getAdminId());
             /**ConferenceStatusConst.S0**/
        conferenceStatus.setConferenceStatus((byte)0);

        conferenceStatus.setConferenceId(conference.getConferenceId());

        conferenceStatusMapperDao.insertSelective(conferenceStatus);
    }
}
