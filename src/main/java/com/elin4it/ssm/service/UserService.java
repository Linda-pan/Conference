package com.elin4it.ssm.service;

import com.alibaba.fastjson.JSONObject;
import com.elin4it.ssm.constant.PaperStatusConst;
import com.elin4it.ssm.mapper.ReviewerPaperMapper;
import com.elin4it.ssm.mapper.dao.PaperMapperDao;
import com.elin4it.ssm.mapper.dao.ReviewerPaperMapperDao;
import com.elin4it.ssm.mapper.dao.UserMapperDao;
import com.elin4it.ssm.mybatis.pagination.PageBounds;
import com.elin4it.ssm.mybatis.pagination.PageList;
import com.elin4it.ssm.pojo.Paper;
import com.elin4it.ssm.pojo.ReviewerPaper;
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

    @Autowired
    private ReviewerPaperMapperDao reviewerPaperMapperDao;

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

    public List<User> findAllUserPage(PageBounds<User> pageBounds) {
        List<User> userList = userMapperDao.selectUser(pageBounds);

        PageList<User> pageList = new PageList<>(pageBounds.getPageList().getPageNo(), pageBounds.getPageList().getPageSize(), pageBounds.getPageList().getTotalCount(), userList);
        pageBounds.setPageList(pageList);

        return userList;
    }

    public List<User> findUserPageByRoleId(PageBounds<User> pageBounds, int roleId) {
        List<User> userList = userMapperDao.selectUserByRoleId(pageBounds, roleId);

        PageList<User> pageList = new PageList<>(pageBounds.getPageList().getPageNo(), pageBounds.getPageList().getPageSize(), pageBounds.getPageList().getTotalCount(), userList);
        pageBounds.setPageList(pageList);

        return userList;
    }

    public List<JSONObject> findReviewerPage(PageBounds<JSONObject> pageBounds) {
        PageBounds<User> param = new PageBounds<>(pageBounds.getPageNo(), pageBounds.getPageSize(), pageBounds.getOrders());
        List<User> userList = userMapperDao.selectUser(param);
        List<JSONObject> userModelList = new ArrayList<>();
        for (User user : userList) {
            if (user.getRoleId() == 2) {
                JSONObject userModel = new JSONObject();
                userModel.put("reviewPaperNum", reviewerPaperService.getPaperCountByReviewerId(user.getUserId()));
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

    public List<JSONObject> findAuthorPage(PageBounds<JSONObject> pageBounds) {
        PageBounds<User> param = new PageBounds<>(pageBounds.getPageNo(), pageBounds.getPageSize(), pageBounds.getOrders());
        List<User> userList = userMapperDao.selectUser(param);

        List<JSONObject> userModelList = new ArrayList<>();
        for (User user : userList) {
            if (user.getRoleId() == 0) {
                List<Paper> papers = paperService.findPaperByAuthorId(user.getUserId());
                int count = 0;
                for (Paper paper : papers) {
                    if (paper.getPaperStatus() == 2 || paper.getPaperStatus() == 3 || paper.getPaperStatus() == 4) {
                        count++;
                    }
                }

                if (count == 0) {
                    continue;
                }
                JSONObject userModel = new JSONObject();
                List<Paper> paperList = paperMapperDao.selectPaperByUid(user.getUserId());
                userModel.put("paperList", paperList);
                userModel.put("paperNum", paperService.findPaperNumByAuthorId(user.getUserId()));
                userModel.put("passpaperNum", count);
                userModel.put("userId", user.getUserId());
                userModel.put("trueName", user.getTrueName());
                userModel.put("telephone", user.getTelephone());
                userModel.put("email", user.getEmail());
                userModel.put("username", user.getUsername());
                userModel.put("title", user.getTitle());
                userModel.put("isPaymentConfirmed", user.getIsPaymentConfirmed());

                userModelList.add(userModel);
            }
        }

        PageList<JSONObject> pageList = new PageList<>(param.getPageList().getPageNo(), param.getPageList().getPageSize(), param.getPageList().getTotalCount(), userModelList);
        pageBounds.setPageList(pageList);

        return userModelList;
    }

    public List<JSONObject> findReviewerPageByPaperId(PageBounds<JSONObject> pageBounds, int paperId) {
        PageBounds<User> param = new PageBounds<>(pageBounds.getPageNo(), pageBounds.getPageSize(), pageBounds.getOrders());

        List<JSONObject> userModelList = new ArrayList<>();
        List<ReviewerPaper> reviewerPapers = reviewerPaperMapperDao.selectReviewerByPaperId(param , paperId);

        for (ReviewerPaper reviewerPaper : reviewerPapers) {
            User user =userMapperDao.selectByPrimaryKey(reviewerPaper.getUserId());

            JSONObject userModel = new JSONObject();
            userModel.put("reviewPaperNum", reviewerPaperService.getPaperCountByReviewerId(user.getUserId()));
            userModel.put("userId", user.getUserId());
            userModel.put("trueName", user.getTrueName());
            userModel.put("telephone", user.getTelephone());
            userModel.put("email", user.getEmail());
            userModel.put("username", user.getUsername());
            userModel.put("title", user.getTitle());
            userModel.put("isPaymentConfirmed", user.getIsPaymentConfirmed());
            userModel.put("isEmailConfirmed", user.getIsEmailConfirmed());
            userModel.put("isShowName", user.getIsShowName());

            userModelList.add(userModel);
    }

        PageList<JSONObject> pageList = new PageList<>(param.getPageList().getPageNo(), param.getPageList().getPageSize(), param.getPageList().getTotalCount(), userModelList);
        pageBounds.setPageList(pageList);

        return userModelList;
}
}
