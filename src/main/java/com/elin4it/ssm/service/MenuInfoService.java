package com.elin4it.ssm.service;

import com.elin4it.ssm.constant.UserRoleConst;
import com.elin4it.ssm.mapper.dao.MenuInfoMapperDao;
import com.elin4it.ssm.pojo.MenuInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jpan on 2016/4/28.
 */
@Service
public class MenuInfoService {

    @Autowired
    private MenuInfoMapperDao menuInfoMapperDao;

    public List<MenuInfo> getAllFirstMenuInfo(Integer roleId) {
       //此处要修改权限

            return menuInfoMapperDao.selectByLevel(0);

    }
}
