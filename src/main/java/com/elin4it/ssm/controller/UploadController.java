package com.elin4it.ssm.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

/**
 * Created by jpan on 2016/5/6.
 */
@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController {
    @RequestMapping(value = "/file", method = RequestMethod.GET)
    public String showUploadPage(@RequestParam(value = "multi", required = false) Boolean multi) {
        if (multi != null && multi) {
            return "upload/file";
        }
        return "upload/file";
    }

    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public
    @ResponseBody
    String doUploadFile(int userId,MultipartFile file) throws IOException {
        URL url=this.getClass().getResource("/");
        URL url2=this.getClass().getResource("./");
        URL url3=this.getClass().getResource("../");
        System.out.println(new File(".").getAbsolutePath());
        String url1="D:/conference/out/artifacts/conference_Web_exploded/static/upload/image" +
                "../static/upload/image"+"../webapps\\axis2\\WEB-INF/pojo/images/"+".jpg";
        if (!file.isEmpty()) {
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File("D:\\conference\\web\\static\\upload\\image", System.currentTimeMillis() + file.getOriginalFilename()));
        }

        return file.getName();
        //  return "forward:/index";

    }

    @RequestMapping(value = "/doUpload2", method = RequestMethod.POST)
    public String doUploadFile2(MultipartHttpServletRequest multiRequest, ModelMap model) throws IOException {

        Iterator<String> filesNames = multiRequest.getFileNames();
        while (filesNames.hasNext()) {
            String fileName = filesNames.next();
            MultipartFile file = multiRequest.getFile(fileName);
            if (!file.isEmpty()) {
                //log.debug("Process file: {}", file.getOriginalFilename());
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File("D:\\temp", System.currentTimeMillis() + file.getOriginalFilename()));
            }
        }
        model.put("message", "保存成功");
        model.put("status", true);
        return "index";
    }


}
