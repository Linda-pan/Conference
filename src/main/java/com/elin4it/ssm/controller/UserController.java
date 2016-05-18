package com.elin4it.ssm.controller;


import com.alibaba.fastjson.JSONObject;
import com.elin4it.ssm.constant.ErrorCodeConst;
import com.elin4it.ssm.constant.UserRoleConst;
import com.elin4it.ssm.exception.BusinessException;
import com.elin4it.ssm.mapper.dao.ConferenceStatusMapperDao;
import com.elin4it.ssm.mapper.dao.PaperThemeMapperDao;
import com.elin4it.ssm.mapper.dao.ReviewerThemeMapperDao;
import com.elin4it.ssm.model.JsonDataModel;
import com.elin4it.ssm.pojo.*;
import com.elin4it.ssm.service.*;
import com.elin4it.ssm.utils.ConfigPropertiesUtil;
import com.elin4it.ssm.utils.WebUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;


/**
 * Created by jpan on 2016/4/5.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private PaperService paperService;

    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private ConferenceStatusMapperDao conferenceStatusMapperDao;

    @Autowired
    private AllPaperThemeService allPaperThemeService;

    @Autowired
    private PaperThemeMapperDao paperThemeMapperDao;

    @Autowired
    private ReviewerThemeMapperDao reviewerThemeMapperDao;

    @RequestMapping("detail")
    public String showDetail(HttpServletRequest request, @RequestParam(required = false) Integer type, ModelMap model) {
        JSONObject statusMap = new JSONObject();
        statusMap.put("0", "否");
        statusMap.put("1", "是");
        model.put("StatusMap", statusMap);

        model.put("type", allPaperThemeService.getAllTheme());

        int statu = 0;
        if (type == null) {
            type = WebUtil.getCurrentUser().getUserId();
            statu = 1;
        }
        model.put("userId", type);
        model.put("statu", statu);

        User user = userService.selectById(type);
        model.put("roleId", user.getRoleId());

        return "user/userdetail";
    }

    @RequestMapping("userdetail")
    public
    @ResponseBody
    String getUserDetail(int userId) {
        JsonDataModel jsonDataModel = new JsonDataModel();
        try {
            User user = userService.selectById(userId);
            jsonDataModel.setData(user);

        } catch (BusinessException e) {
            jsonDataModel.setErrorCode(ErrorCodeConst.BUS_EXCEPTION);
            jsonDataModel.setStatus("false");
            jsonDataModel.setMessage(e.getLocalizedMessage());
        }

        return jsonDataModel.toJSONString();
    }

    @RequestMapping("save")
    public
    @ResponseBody
    String add(User user, ModelMap model) {
        JsonDataModel jsonDataModel = new JsonDataModel();

        try {
            userService.update(user);

        } catch (BusinessException e) {
            jsonDataModel.setErrorCode(ErrorCodeConst.BUS_EXCEPTION);
            jsonDataModel.setStatus("false");
            jsonDataModel.setMessage(e.getLocalizedMessage());
        }

        return jsonDataModel.toJSONString();
    }

    @RequestMapping("save/password")
    public
    @ResponseBody
    String changepassword(@RequestParam int userId, String password, ModelMap model) {

        JsonDataModel jsonDataModel = new JsonDataModel();
        try {
            User user = new User();
            user.setUserId(userId);
            user.setPassword(password);

            userService.update(user);
        } catch (BusinessException e) {
            jsonDataModel.setErrorCode(ErrorCodeConst.BUS_EXCEPTION);
            jsonDataModel.setStatus("false");
            jsonDataModel.setMessage(e.getLocalizedMessage());
        }
        model.put("message", "保存成功");
        model.put("status", true);

        return jsonDataModel.toJSONString();
    }

    @RequestMapping("pic")
    public String uploadpicture(ModelMap model) {

        int a = userService.isPassPaperNumByAuthorId(WebUtil.getCurrentUser().getUserId());
        if (a >= 1) {
            a = 1;
        }
        model.put("statu", a);

        JSONObject conference = conferenceService.selectLastConference();
        int id = (int) conference.get("conferenceId");
        ConferenceStatus conferenceStatus = conferenceStatusMapperDao.selectLastOne(id);
        int b = 0;
        /**b=0,会议结束**/
        if (conferenceStatus.getConferenceStatus() == 4) {
            b = 1;
        }
        model.put("cstatu", b);

        User user = userService.selectById(WebUtil.getCurrentUser().getUserId());
        model.put("pic", user.getPaymentVoucher());

        model.put("userId", user.getUserId());
        return "user/picture";
    }

    @RequestMapping("save/pic")
    public String savepicture(int userId, MultipartFile file, ModelMap model) {

        try {
            if (!file.isEmpty()) {
                String name = System.currentTimeMillis() + file.getOriginalFilename();
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File("D:/conference/out/artifacts/conference_Web_exploded/static/upload/image", name));

                String name1 = "../static/upload/image/" + name;
                userService.savePicture(userId, name1);
                paperService.changeStatus(userId);
            }
            model.put("message", "success");
            model.put("statu", true);

        } catch (Exception e) {
            model.put("message", e.getLocalizedMessage());
            model.put("statu", false);
        }

        return "redirect:/user/pic";
    }

    @RequestMapping("paper")
    public String upload(ModelMap model) {

        JSONObject conference = conferenceService.selectLastConference();
        int id = (int) conference.get("conferenceId");
        ConferenceStatus conferenceStatus = conferenceStatusMapperDao.selectLastOne(id);
        int b = 0;
        /**b=1,可以投稿**/
        if (conferenceStatus.getConferenceStatus() == 1) {
            b = 1;
        }
        model.put("cstatu", b);

        model.put("type", allPaperThemeService.getAllTheme());
        User user = userService.selectById(WebUtil.getCurrentUser().getUserId());
        model.put("userId", user.getUserId());
        return "paper/newpaper";
    }

    @RequestMapping("save/paperurl")
    public String savepaperurl(MultipartFile file, ModelMap model) {

        try {
            if (!file.isEmpty()) {
                String name = System.currentTimeMillis() + ".pdf";
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File("D:/conference/out/artifacts/conference_Web_exploded/static/upload/paper", name));

                String name1 = "../static/upload/paper/" + name;
                /**借用全局变量的保存功能**/
                WebUtil.getAppAdminRights().setUsername(name1);

                model.put("message", "上传成功");
                model.put("statu", true);
                return "redirect:/user/paper";
            }

        } catch (Exception e) {
            model.put("message", e.getLocalizedMessage());
            model.put("statu", false);
        }
        model.put("message", "上传失败");
        model.put("statu", false);

        return "redirect:/user/paper";
    }

    @RequestMapping("save/paper")
    public
    @ResponseBody
    String savepaper(Paper paper, int type1, Integer type2, ModelMap model) {
        JsonDataModel jsonDataModel = new JsonDataModel();
        try {
            paperService.insert(paper);

            PaperTheme paperTheme = new PaperTheme();

            paperTheme.setPaperId(paper.getPaperId());
            paperTheme.setFirstThemeId(type1);
            if (type2 != null) {
                paperTheme.setSecondThemeId(type2);
            }

            paperThemeMapperDao.insertSelective(paperTheme);

        } catch (Exception e) {
            jsonDataModel.setErrorCode(ErrorCodeConst.BUS_EXCEPTION);
            jsonDataModel.setStatus("false");
            jsonDataModel.setMessage(e.getLocalizedMessage());
        }

        return jsonDataModel.toJSONString();
    }

    @RequestMapping("savetheme")
    public
    @ResponseBody
    String saveUserDetail(int userId, int type1, int type2, Integer type3, Integer type4) {
        JsonDataModel jsonDataModel = new JsonDataModel();
        try {
            ReviewerTheme reviewerTheme = new ReviewerTheme();
            reviewerTheme.setUserid(userId);
            reviewerTheme.setTheme1(type1);
            reviewerTheme.setTheme2(type2);
            if (type3 != null) {
                reviewerTheme.setTheme3(type3);
            }
            if (type4 != null) {
                reviewerTheme.setTheme4(type4);
            }

            ReviewerTheme reviewerTheme1 = reviewerThemeMapperDao.selectByPrimaryKey(userId);
           if (reviewerTheme1==null) {
               reviewerThemeMapperDao.insert(reviewerTheme);
           }else{
               reviewerThemeMapperDao.updateByPrimaryKeySelective(reviewerTheme);
           }

        } catch (BusinessException e) {
            jsonDataModel.setErrorCode(ErrorCodeConst.BUS_EXCEPTION);
            jsonDataModel.setStatus("false");
            jsonDataModel.setMessage(e.getLocalizedMessage());
        }

        return jsonDataModel.toJSONString();
    }
}
