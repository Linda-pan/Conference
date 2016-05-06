package com.elin4it.ssm;

import com.cpvsn.core.util.DateUtil;
import com.elin4it.ssm.pojo.Conference;
import com.elin4it.ssm.pojo.User;
import com.elin4it.ssm.service.ConferenceService;
import com.elin4it.ssm.service.ReviewerPaperService;
import com.elin4it.ssm.service.UserService;
import com.elin4it.ssm.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by jpan on 2016/4/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring.xml")
public class ServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewerPaperService reviewerPaperService;

    @Autowired
    private ConferenceService conferenceService;

    @Test
    public void testSelectByName(){
        User user =userService.selectByName("Linda");
        System.out.print(user);
    }
    @Test
    public void testSelectCount(){
        int count =reviewerPaperService.getCount(3);
        System.out.print(count);
    }

    @Test
    public void testAddConference(){
        Conference conference =new Conference();
        conference.setConferenceName("大会议");
        conference.setConferenceIntro("始无来者");

        String time1= "2016-05-06 14:19:11";
        String time2= "2016-05-10 14:19:11";
        try {
            Date date = DateUtils.stringToDate(time1, "yyyy-MM-dd HH:mm:ss");
            conference.setStartTime(date);
            Date date1 = DateUtils.stringToDate(time2, "yyyy-MM-dd HH:mm:ss");
            conference.setEndTime(date1);
        }catch (ParseException e){

        }

        conferenceService.insertConference(conference);
    }

}