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
        int level = 0;
        if (roleId == 0) {
            level = 2;
        } else if (roleId == 1) {
            level = 0;
        } else if (roleId == 2) {
            level = 1;
        }

        return menuInfoMapperDao.selectByLevel(level);

    }
}
