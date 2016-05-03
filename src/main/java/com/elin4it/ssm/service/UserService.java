package com.elin4it.ssm.service;

import com.elin4it.ssm.mapper.dao.UserMapperDao;
import com.elin4it.ssm.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jpan on 2016/4/28.
 */
@Service
public class UserService{

    @Autowired
   private UserMapperDao userMapperDao;

    public User login(String userName, String password){

        return userMapperDao.selectByNameAndPwd(userName,password);
    }

    public User selectByName(String name){
        return  userMapperDao.selectByName(name);
    }

    public User selectById(int id){
        return userMapperDao.selectByPrimaryKey(id);
    }

    public int insert(User user){
        return userMapperDao.insertSelective(user);
    }

    public int update(User user){
        return userMapperDao.updateByPrimaryKeySelective(user);
    }
}
