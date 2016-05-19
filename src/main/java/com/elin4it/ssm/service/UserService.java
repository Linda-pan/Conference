package com.elin4it.ssm.service;

import com.alibaba.fastjson.JSONObject;
import com.cpvsn.core.encode.MD5Util;
import com.elin4it.ssm.constant.PaperStatusConst;
import com.elin4it.ssm.exception.BusinessException;
import com.elin4it.ssm.mapper.ReviewerPaperMapper;
import com.elin4it.ssm.mapper.dao.*;
import com.elin4it.ssm.model.SendEmail;
import com.elin4it.ssm.model.UserModel;
import com.elin4it.ssm.mybatis.pagination.Order;
import com.elin4it.ssm.mybatis.pagination.PageBounds;
import com.elin4it.ssm.mybatis.pagination.PageList;
import com.elin4it.ssm.pojo.*;
import com.elin4it.ssm.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;
import sun.plugin2.message.JavaObjectOpMessage;

import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private UserModelService userModelService;
    @Autowired
    private ConferenceService conferenceService;
    @Autowired
    private ReviewerThemeMapperDao reviewerThemeMapperDao;
    @Autowired
    private PaperThemeMapperDao paperThemeMapperDao;
    @Autowired
    private AllPaperThemeMapperDao allPaperThemeMapperDao;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentMapperDao commentMapperDao;

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
        user.setPassword(Md5.encode2hex(user.getPassword()));

        user.setIsEmailConfirmed(false);
        user.setRoleId((byte) 0);
        user.setIsShowName(true);
        user.setTitle("暂无头衔");
        user.setPaymentVoucher("");

        return userMapperDao.insertSelective(user);
    }

    public int update(User user) {
        user.setUpdateTime(new Date());
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
                userModel.put("url", user.getPaymentVoucher());

                int c = 0;
                int a = 0;
                for (Paper paper : paperList) {
                    if (paper.getIsEmailPost()) {
                        c++;
                    }
                    if (paper.getPaperStatus() == 3) {
                        a++;
                    }
                }

                if (c == 0) {
                    userModel.put("inform", 1);
                } else if (a == 0) {
                    //一份论文都没有上传缴费单状态
                    userModel.put("inform", 2);
                } else {
                    //出现上传了缴费单的论文
                    userModel.put("inform", 3);
                }


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
        List<ReviewerPaper> reviewerPapers = reviewerPaperMapperDao.selectReviewerByPaperId(param, paperId);

        for (ReviewerPaper reviewerPaper : reviewerPapers) {
            User user = userMapperDao.selectByPrimaryKey(reviewerPaper.getUserId());

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

    public List<JSONObject> findReviewerPageByTheme(PageBounds<JSONObject> pageBounds, int themeId) {
        PageBounds<ReviewerTheme> param = new PageBounds<>(pageBounds.getPageNo(), pageBounds.getPageSize(), Order.create("userId", "desc"));

        List<JSONObject> userModelList = new ArrayList<>();

        List<ReviewerTheme> reviewerThemes = reviewerThemeMapperDao.getByThemeId(param, themeId);

        for (ReviewerTheme reviewerTheme : reviewerThemes) {

            User user = userMapperDao.selectByPrimaryKey(reviewerTheme.getUserid());

            JSONObject userModel = new JSONObject();
            int num = 0;
            List<ReviewerPaper> reviewpapers = reviewerPaperMapperDao.selectReviewerPaperByReviewerId(reviewerTheme.getUserid());
            for (ReviewerPaper reviewpaper : reviewpapers) {
                CommentKey comKey = new CommentKey();
                comKey.setUserId(user.getUserId());
                comKey.setPaperId(reviewpaper.getPaperId());

                if (commentMapperDao.selectByPrimaryKey(comKey) != null) {
                    num++;
                }
            }
            userModel.put("doReviewPaperNum", num);
            userModel.put("reviewPaperNum", reviewerPaperService.getPaperCountByReviewerId(user.getUserId()));

            userModel.put("userId", user.getUserId());
            userModel.put("trueName", user.getTrueName());

            userModel.put("theme1", allPaperThemeMapperDao.selectByPrimaryKey(reviewerTheme.getTheme1()).getTheme());
            userModel.put("theme1Id", reviewerTheme.getTheme1());
            userModel.put("theme2", allPaperThemeMapperDao.selectByPrimaryKey(reviewerTheme.getTheme2()).getTheme());
            userModel.put("theme2Id", reviewerTheme.getTheme2());
            userModel.put("theme3", allPaperThemeMapperDao.selectByPrimaryKey(reviewerTheme.getTheme3()).getTheme());
            userModel.put("theme3Id", reviewerTheme.getTheme3());
            userModel.put("theme4", allPaperThemeMapperDao.selectByPrimaryKey(reviewerTheme.getTheme4()).getTheme());
            userModel.put("theme4Id", reviewerTheme.getTheme4());

            userModelList.add(userModel);
        }

        PageList<JSONObject> pageList = new PageList<>(param.getPageList().getPageNo(), param.getPageList().getPageSize(), param.getPageList().getTotalCount(), userModelList);
        pageBounds.setPageList(pageList);

        return userModelList;
    }

    public List<User> findReviewerByTheme(int themeId) {
        List<User> userList = new ArrayList<>();
        List<ReviewerTheme> reviewerThemes = reviewerThemeMapperDao.getByThemeId(themeId);

        for (ReviewerTheme reviewerTheme : reviewerThemes) {
            User user = userMapperDao.selectByPrimaryKey(reviewerTheme.getUserid());

            userList.add(user);
        }

        return userList;
    }

    public void processregister(User user) {
        this.insert(user);
        UserModel userModel = userModelService.getByUserId(user.getUserId());

        StringBuffer sb = new StringBuffer("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
        sb.append("<a href=\"http://localhost:8080/emaillogin?id=");
        sb.append(user.getUserId());
        sb.append("&validateCode=");
        sb.append(userModel.getValidateCode());
        sb.append("\">http://localhost:8080/springmvc/user/register");
        sb.append("</a>");

        //发送邮件
        SendEmail.send(user.getEmail(), sb.toString());
    }

    public void processActivate(UserModel userModel, String validateCode) throws BusinessException, ParseException {
        User user = this.selectById(userModel.getUserId());
        //验证用户激活状态
        if (!user.getIsEmailConfirmed()) {
            //没激活
            Date currentTime = new Date();//获取当前时间
            //验证链接是否过期
            if (currentTime.before(userModel.getRegisterTime())) {
                //验证激活码是否正确
                if (validateCode.equals(userModel.getValidateCode())) {
                    //激活成功,并更新用户的激活状态，为已激活
                    user.setIsEmailConfirmed(true);
                    user.setCreateTime(new Date());
                    this.update(user);
                } else {
                    throw new BusinessException("激活码不正确");
                }
            } else {
                throw new BusinessException("激活码已过期！");
            }
        } else {
            throw new BusinessException("邮箱已激活，请登录！");
        }
    }

    public void processInformPass(int userId) throws BusinessException {
        User user = this.selectById(userId);

        StringBuffer sb = new StringBuffer("您已有论文通过审核，请尽快缴费并上传您的缴费单</br>");

        //发送邮件
        SendEmail.send(user.getEmail(), sb.toString());
    }

    public void processInformPasspaper(int userId,String name) throws BusinessException {
        User user = this.selectById(userId);

        StringBuffer sb = new StringBuffer("恭喜您，</br>");
        sb.append("您的论文：");
        sb.append(name);
        sb.append("通过审核，请尽快缴费并上传您的缴费单</br>");
        //发送邮件
        SendEmail.send(user.getEmail(), sb.toString());
    }

    public void processInformFailpaper(int userId,String name) throws BusinessException {
        User user = this.selectById(userId);

        StringBuffer sb = new StringBuffer("很遗憾，</br>");
        sb.append("您的论文：");
        sb.append(name);
        sb.append("未通过审核，</br>");
        //发送邮件
        SendEmail.send(user.getEmail(), sb.toString());
    }

    public void processInformUpload(int userId) throws BusinessException {
        User user = this.selectById(userId);

        StringBuffer sb = new StringBuffer("请尽快缴费并上传您的缴费单</br>");
        JSONObject conference = conferenceService.selectLastConference();
        sb.append(conference.get("conferenceName"));
        sb.append("会议开放截止时间为：");
        sb.append(conference.get("endTime"));
        //发送邮件
        SendEmail.send(user.getEmail(), sb.toString());
    }

    public void processInformReady(int userId, Boolean result) {
        User user = this.selectById(userId);
        StringBuffer sb;
        if (result) {
            sb = new StringBuffer("您的缴费单验证通过！您具有了参加此次会议的机会</br>");
            JSONObject conference = conferenceService.selectLastConference();
            sb.append("会议详情：");
            sb.append(conference.get("conferenceName"));
            sb.append("</br>");
            sb.append(conference.get("conferenceIntro"));
        } else {
            sb = new StringBuffer("抱歉！您的缴费单验证失败，请及时重新上传缴费单</br>");
        }

        //发送邮件
        SendEmail.send(user.getEmail(), sb.toString());
    }

    public void savePicture(int userId,String pic){
        User user =userMapperDao.selectByPrimaryKey(userId);
        user.setPaymentVoucher(pic);
        user.setUpdateTime(new Date());
        this.update(user);
    }

    public int isPassPaperNumByAuthorId(int userId){
        List<Paper> paperList =paperService.findPaperByAuthorId(userId);
        int count=0;
        for (Paper paper : paperList) {
            if(paper.getPaperStatus()==2||paper.getPaperStatus()==3||paper.getPaperStatus()==4)
            {
                count++;
            }
        }
        return count;
    }

    public void checkReviewerToAuthor(int userId)throws BusinessException{
        int num = 0;
        List<ReviewerPaper> reviewpapers = reviewerPaperMapperDao.selectReviewerPaperByReviewerId(userId);
        for (ReviewerPaper reviewpaper : reviewpapers) {

            CommentKey comKey = new CommentKey();
            comKey.setUserId(userId);
            comKey.setPaperId(reviewpaper.getPaperId());

            if (commentMapperDao.selectByPrimaryKey(comKey) == null) {
                num++;
            }
        }

        if(num!=0){
            throw new BusinessException("该审核员还有论文未审核，无法改变权限！");
        }
    }

    public void checkAuthorToReviewer(int userId)throws BusinessException{
        int num = 0;
        List<Paper> paperList = paperService.findPaperByAuthorId(userId);
        for (Paper paper : paperList) {
           num++;
        }
        if(num!=0){
            throw new BusinessException("该作家已经上传论文，无法修改权限！");
        }
    }

}

