package com.elin4it.ssm.service;

import com.elin4it.ssm.mapper.dao.ConferenceStatusMapperDao;
import com.elin4it.ssm.mapper.dao.UserMapperDao;
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
        User user =userMapperDao.selectByPrimaryKey(userId);
        conferenceStatus.setAdminId(user.getAdminId());

        conferenceStatusMapperDao.insertSelective(conferenceStatus);
    }
}
