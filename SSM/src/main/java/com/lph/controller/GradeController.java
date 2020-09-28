package com.lph.controller;

import com.lph.domain.Grade;
import com.lph.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.jfunc.json.impl.JSONObject;

import java.util.List;

/**
 * 控制层
 */
@Controller
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    /**
     * 添加成绩
     * @param grade
     */
    @RequestMapping("/addGrade")
    @ResponseBody
    public String addGrade(@RequestBody Grade grade){
        int flag = gradeService.addGrade(grade);
        if(flag == 1){
            //成功
            return "1";
        }else {
            return "0";
        }
    }

    /**
     *根据用户ID获取多个成绩信息
     * @param id
     * @return
     */
    @RequestMapping("/getGradeById")
    @ResponseBody
    public List<Grade> getGradeById(@RequestBody String id){
        //System.out.println(id);
        //将字符串转化成JsonObject
        JSONObject jsonObject = (JSONObject) new JSONObject().parse(id);
        List<Grade> list = gradeService.getGradeById((String) jsonObject.get("id"));
        return list;
    }
}
