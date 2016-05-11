package com.elin4it.ssm.controller;

import com.elin4it.ssm.model.JsonDataModel;
import com.elin4it.ssm.pojo.AllPaperTheme;
import com.elin4it.ssm.service.AllPaperThemeService;
import com.elin4it.ssm.service.PaperThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jpan on 2016/5/11.
 */
@Controller
@RequestMapping("allpapertheme")
public class AllPaperThemeController {
    @Autowired
    private AllPaperThemeService allPaperThemeService;

    @RequestMapping("")
    public String index(ModelMap model) {

        model.put("themeMap", allPaperThemeService.getAllTheme());
        return "/paper/papertheme";
    }

    @RequestMapping("change")
    public String changeDetail(AllPaperTheme allPaperTheme, ModelMap model) {

        allPaperThemeService.updateTheme(allPaperTheme);

        model.put("message", "保存成功");
        model.put("status", true);

        return "forward:/allpapertheme";
    }

    @RequestMapping("save")
    public String changeDetail(@RequestParam(required = true)String theme, ModelMap model) {

        allPaperThemeService.insertTheme(theme);

        model.put("message", "保存成功");
        model.put("status", true);

        return "forward:/allpapertheme";
    }

    @RequestMapping("get_by_name")
    public @ResponseBody
    String getByName(@RequestParam(required = true) String theme) {
        JsonDataModel jsonDataModel = new JsonDataModel();

        jsonDataModel.setData(allPaperThemeService.getByName(theme));

        return jsonDataModel.toJSONString();
    }
}
