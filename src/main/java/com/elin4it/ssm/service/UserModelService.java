package com.elin4it.ssm.service;

import com.elin4it.ssm.mapper.dao.UserMapperDao;
import com.elin4it.ssm.model.UserModel;
import com.elin4it.ssm.pojo.User;
import com.sun.tracing.dtrace.ProviderAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by jpan on 2016/5/12.
 */
@Service
public class UserModelService {
    @Autowired
    private UserMapperDao userMapperDao;

    public UserModel getByUserId(int userId){
        User user=userMapperDao.selectByPrimaryKey(userId);
        UserModel userModel=new UserModel();
        userModel.setUserId(user.getUserId());

        Date date=new Date();
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(date);//设置当前日期
        calendar.add(Calendar.DAY_OF_MONTH, 1);//天数加一，为-1的话是天数减1
        date=calendar.getTime();

        userModel.setRegisterTime(date);
        userModel.setValidateCode("^^^^^^^");
        return userModel;
    }
}
