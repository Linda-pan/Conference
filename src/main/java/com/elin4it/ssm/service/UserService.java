package com.elin4it.ssm.service;

import com.alibaba.fastjson.JSONObject;
import com.elin4it.ssm.mapper.dao.PaperMapperDao;
import com.elin4it.ssm.mapper.dao.UserMapperDao;
import com.elin4it.ssm.mybatis.pagination.PageBounds;
import com.elin4it.ssm.mybatis.pagination.PageList;
import com.elin4it.ssm.pojo.Paper;
import com.elin4it.ssm.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.plugin2.message.JavaObjectOpMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jpan on 2016/4/28.
 */
@Service
public class UserService {

    @Autowired
    private UserMapperDao userMapperDao;

    @Autowired
    private PaperMapperDao paperMapperDao;

    @Autowired
    private PaperService paperService;

    @Autowired
    private ReviewerPaperService reviewerPaperService;

    public User login(String userName, String password) {

        return userMapperDao.selectByNameAndPwd(userName, password);
    }

    public User selectByName(String name) {
        return userMapperDao.selectByName(name);
    }

    public User selectById(int id) {
        return userMapperDao.selectByPrimaryKey(id);
    }

    public int insert(User user) {
        return userMapperDao.insertSelective(user);
    }

    public int update(User user) {
        return userMapperDao.updateByPrimaryKeySelective(user);
    }

    public List<User> findPage(PageBounds<User> pageBounds) {
        List<User> userList = userMapperDao.selectUser(pageBounds);

        PageList<User> pageList = new PageList<>(pageBounds.getPageList().getPageNo(), pageBounds.getPageList().getPageSize(), pageBounds.getPageList().getTotalCount(), userList);
        pageBounds.setPageList(pageList);

        return userList;
    }

    public List<JSONObject> findDCPage(PageBounds<JSONObject> pageBounds) {
        PageBounds<User> param = new PageBounds<>(pageBounds.getPageNo(), pageBounds.getPageSize(), pageBounds.getOrders());
        List<User> userList = userMapperDao.selectUser(param);
        List<JSONObject> userModelList = new ArrayList<>();
        for (User user : userList) {
            if (user.getRoleId() == 2) {
                JSONObject userModel = new JSONObject();
                userModel.put("reviewPaperNum", reviewerPaperService.getCount(user.getUserId()));
                userModel.put("userId", user.getUserId());
                userModel.put("trueName", user.getTrueName());
                userModel.put("telephone", user.getTelephone());
                userModel.put("email", user.getEmail());
                userModel.put("username", user.getUsername());
                userModel.put("title", user.getTitle());

                userModelList.add(userModel);
            }
        }

        PageList<JSONObject> pageList = new PageList<>(param.getPageList().getPageNo(), param.getPageList().getPageSize(), param.getPageList().getTotalCount(), userModelList);
        pageBounds.setPageList(pageList);

        return userModelList;
    }

    public List<JSONObject> findAPage(PageBounds<JSONObject> pageBounds) {
        PageBounds<User> param = new PageBounds<>(pageBounds.getPageNo(), pageBounds.getPageSize(), pageBounds.getOrders());
        List<User> userList = userMapperDao.selectUser(param);
        List<JSONObject> userModelList = new ArrayList<>();
        for (User user : userList) {
            if (user.getRoleId() == 0) {
                JSONObject userModel = new JSONObject();
                List<Paper> paperList = paperMapperDao.selectPaperByUid(user.getUserId());
                userModel.put("paperList", paperList);
                userModel.put("paperNum", paperService.findPaperNumByUid(user.getUserId()));

                userModel.put("userId", user.getUserId());
                userModel.put("trueName", user.getTrueName());
                userModel.put("telephone", user.getTelephone());
                userModel.put("email", user.getEmail());
                userModel.put("username", user.getUsername());
                userModel.put("title", user.getTitle());

                userModelList.add(userModel);
            }
        }

        PageList<JSONObject> pageList = new PageList<>(param.getPageList().getPageNo(), param.getPageList().getPageSize(), param.getPageList().getTotalCount(), userModelList);
        pageBounds.setPageList(pageList);

        return userModelList;
    }
}
