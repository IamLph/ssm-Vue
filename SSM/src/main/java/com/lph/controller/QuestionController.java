package com.lph.controller;

import com.lph.domain.Question;
import com.lph.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.jfunc.json.impl.JSONObject;

import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    //获取随机题目
    @RequestMapping(value = "/getQuestion")
    @ResponseBody
    public List<Question> getQuestion(){
        List<Question> list = questionService.getQuestion();
        return list;
    }

    //获取所有题目
    @RequestMapping(value = "/getAllQuestion")
    @ResponseBody
    public List<Question> getAllQuestion(){
        List<Question> list = questionService.getAllQuestion();
        return list;
    }

    //根据Id查找问题
    @RequestMapping("/selectById")
    @ResponseBody
    public Question selectById(@RequestBody String id){
        //System.out.println("传递过来的ID是：====="+id);
        JSONObject jsonObject = (JSONObject) new JSONObject().parse(id);
        //System.out.println("转换之后的"+(Integer.parseInt((String) jsonObject.get("id"))));
        Question question = questionService.selectById((Integer.parseInt((String) jsonObject.get("id"))));
        return question;
    }

    //添加问题
    @RequestMapping("/AddQuestion")
    @ResponseBody
    public String AddQuestion(@RequestBody Question question){
        int flag = questionService.AddQuestion(question);
        if(flag == 1){
            //System.out.println("操作成功了！！");
            return "1";
        }else {
            //System.out.println("添加失败了！！");
            return "0";
        }
    }

    //修改问题
    @RequestMapping("/UpdateQuestion")
    @ResponseBody
    public String UpdateQuestion(@RequestBody Question question){
        System.out.println(question);
        int flag = questionService.UpdateQuestion(question);
        if(flag == 1){
            return "1";
        }else {
            return "0";
        }
    }

    //根据ID删除问题
    @RequestMapping("/deleteById")
    @ResponseBody
    public String deleteById(@RequestBody String id) {
        JSONObject jsonObject = (JSONObject) new JSONObject().parse(id);
        int flag = questionService.deleteById((Integer.parseInt((String) jsonObject.get("id"))));
        if (flag == 1) {
            return "1";
        } else if (flag == 0) {
            return "0";
        } else {
            //return "数据不存在了";
            return "2";
        }
    }
}
