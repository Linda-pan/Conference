package com.elin4it.ssm;

import com.alibaba.fastjson.JSON;
import com.elin4it.ssm.mapper.dao.UserMapperDao;
import com.elin4it.ssm.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jpan on 2016/4/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring.xml")
public class DaoTest {
    @Autowired
    UserMapperDao userMapperDao;

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


}