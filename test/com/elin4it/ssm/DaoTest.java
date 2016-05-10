package com.elin4it.ssm;

import com.alibaba.fastjson.JSON;
import com.elin4it.ssm.mapper.dao.CommentQuestionnaireMapperDao;
import com.elin4it.ssm.mapper.dao.ConferenceMapperDao;
import com.elin4it.ssm.mapper.dao.UserMapperDao;
import com.elin4it.ssm.mybatis.pagination.Order;
import com.elin4it.ssm.mybatis.pagination.PageBounds;
import com.elin4it.ssm.pojo.CommentQuestionnaire;
import com.elin4it.ssm.pojo.Conference;
import com.elin4it.ssm.pojo.ConferenceStatus;
import com.elin4it.ssm.pojo.User;
import com.elin4it.ssm.service.ConferenceService;
import com.elin4it.ssm.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by jpan on 2016/4/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring.xml")
public class DaoTest {
    @Autowired
    UserMapperDao userMapperDao;

    @Autowired
    ConferenceMapperDao conferenceMapperDao;

    @Autowired
    ConferenceService conferenceService;

    @Autowired
    CommentQuestionnaireMapperDao commentQuestionnaireMapperDao;

    @Test
    public void testSelectByNameAndPwd() throws Exception {
    }

    @Test
    public void testSelectByPrimaryKey() {
        User user = userMapperDao.selectByPrimaryKey(1);

        String result = JSON.toJSONString(user);
        System.out.println(result);
        System.out.println("sdafsiadjfilajsdf");
    }

    @Test
    public void testFindPageByPaperId(){
       // PageBounds<User> pageBounds = new PageBounds<>(1, 50, Order.create("paper_id", "desc"));
      /* List<User> userList= userMapperDao.findPageByPaperId(1);
        System.out.println(userList);*/
    }

    @Test
    public void testAddConference(){
        Conference conference1 =new Conference();
        conference1.setConferenceName("111111");
        conference1.setConferenceIntro("始wefw无来者");

        String time1= "2016-05-06 14:19:11";
        String time2= "2016-05-10 14:19:11";
        try {
            int a=1;
            Date date1 = DateUtils.stringToDate(time1, "yyyy-MM-dd HH:mm:ss");
            conference1.setStartTime(date1);
            Date date2 = DateUtils.stringToDate(time2, "yyyy-MM-dd HH:mm:ss");
            conference1.setEndTime(date2);
        }catch (ParseException e){

        }

        conferenceMapperDao.insert(conference1);
        System.out.print(conference1);
        System.out.print("*******************");
    }

    @Test
    public void getCommentQ(){
        CommentQuestionnaire themes=commentQuestionnaireMapperDao.selectByThemeId(0);
        System.out.print("**************");
        System.out.println(themes);

    }

}