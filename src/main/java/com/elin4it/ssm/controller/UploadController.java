package com.elin4it.ssm.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by jpan on 2016/5/6.
 */
@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController{
    @RequestMapping(value="/file", method= RequestMethod.GET)
    public String showUploadPage(@RequestParam(value= "multi", required = false) Boolean multi){
        if(multi != null && multi){
            return "upload/file";
        }
        return "upload/file";
    }

    @RequestMapping(value="/doUpload", method=RequestMethod.POST)
    public String doUploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        if(!file.isEmpty()){
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(".\\temp\\paper\\", System.currentTimeMillis()+ file.getOriginalFilename()));
        }

        return "index";
    }

    @RequestMapping(value="/doUpload2", method=RequestMethod.POST)
    public String doUploadFile2(MultipartHttpServletRequest multiRequest,ModelMap model) throws IOException{

        Iterator<String> filesNames = multiRequest.getFileNames();
        while(filesNames.hasNext()){
            String fileName =filesNames.next();
            MultipartFile file =  multiRequest.getFile(fileName);
            if(!file.isEmpty()){
                //log.debug("Process file: {}", file.getOriginalFilename());
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File("d:\\temp\\paper\\", System.currentTimeMillis()+ file.getOriginalFilename()));
            }
        }
        model.put("message", "保存成功");
        model.put("status", true);
        return "index";
    }
}
