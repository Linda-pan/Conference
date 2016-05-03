package com.elin4it.ssm.mapper.dao;

import com.elin4it.ssm.mapper.UserMapper;
import com.elin4it.ssm.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by jpan on 2016/4/5.
 */
@Repository
public interface UserMapperDao extends UserMapper {

    User selectByNameAndPwd(@Param(value = "userName") String userName, @Param(value = "password") String password);

    User selectByName(String username);


}
