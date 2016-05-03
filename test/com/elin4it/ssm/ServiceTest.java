package com.elin4it.ssm;

import com.elin4it.ssm.pojo.User;
import com.elin4it.ssm.service.UserService;
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
public class ServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testSelectByName(){
        User user =userService.selectByName("Linda");
        System.out.print(user);
    }

}