package com.elin4it.ssm.model;

import com.elin4it.ssm.pojo.MenuInfo;

import java.util.List;

/**
 * Created by jpan on 2016/4/28.
 */
public class SessionUserMenuInfo {
    private List<MenuInfo> firstMenuList;
    private List<MenuInfo> secondMenuList;

    public SessionUserMenuInfo(List<MenuInfo> firstMenuList,List<MenuInfo> secondMenuList){
        this.firstMenuList=firstMenuList;
        this.secondMenuList=secondMenuList;
    }

    public List<MenuInfo> getFirstMenuList() {
        return firstMenuList;
    }
    public void setFirstMenuList(List<MenuInfo> firstMenuList) {
        this.firstMenuList = firstMenuList;
    }
    public List<MenuInfo> getSecondMenuList() {
        return secondMenuList;
    }
    public void setSecondMenuList(List<MenuInfo> secondMenuList) {
        this.secondMenuList = secondMenuList;
    }
}
