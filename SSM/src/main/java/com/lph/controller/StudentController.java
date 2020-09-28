package com.lph.controller;

import com.lph.domain.Student;
import com.lph.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.jfunc.json.impl.JSONObject;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //添加注册用户
    @RequestMapping("/registerUser")
    @ResponseBody
    public String registerUser(@RequestBody Student student){
        String id = studentService.registerUser(student);
        //System.out.println(student.toString());
        if(id != null){
            return id;
        }
        return null;
    }

    //根据ID查找用户
    @RequestMapping("/getStudentById")
    @ResponseBody
    public Student getStudentById(@RequestBody String id){
        //将字符串转化成JsonObject
        JSONObject jsonObject = (JSONObject) new JSONObject().parse(id);
        String NeedId = (String) jsonObject.get("id");
        //System.out.println(NeedId);
        return studentService.getStudentById(NeedId);
    }

    //修改信息
    @RequestMapping("/updateInfo")
    @ResponseBody
    public String updateInfo(@RequestBody Student student){
        //System.out.println(student.toString());
        int flag = studentService.updateInfo(student);
        if(flag == 1){
            return "1";
        }
        return "0";
    }

    //登陆检查密码
    @RequestMapping(value = "/checkPassword",method = RequestMethod.POST)
    @ResponseBody
    public Student checkPassword(@RequestBody Student student){
        //System.out.println(student.toString());
        if(studentService.checkPassword(student) != null){
            return studentService.checkPassword(student);
        }
        return null;
    }

    //修改密码
    @RequestMapping("/updatePassword")
    @ResponseBody
    public String updatePassword(@RequestBody Student student){
        int flag = studentService.updatePassword(student);
        if(flag == 1){
            return "1";
        }
        return "0";
    }

    //设置状态
    @RequestMapping("/setUserState")
    @ResponseBody
    public String setUserState(@RequestBody Student student){
        //System.out.println(student.toString());
        int flag = studentService.setUserState(student);
        if(flag == 1){
            return "1";
        }
        return "0";
    }

    //设置全部为离线状态
    @RequestMapping("/setAllLeave")
    @ResponseBody
    public String setAllLeave(){
        int flag = studentService.setAllLeave();
        if (flag == 1){
            return "1";
        }
        return "0";
    }

    //获取学生状态
    @RequestMapping("/getAllStudent")
    @ResponseBody
    public List<Student> getAllStudent(){
        List<Student> list = studentService.getAllStudent();
        return list;
    }

    //获取非管理员用户
    @RequestMapping("/getAllNotAdmin")
    @ResponseBody
    public List<Student> getAllNotAdmin(){
        List<Student> list = studentService.getAllStudent();
        return list;
    }

    //管理员修改用户
    @RequestMapping("/AdminUpdateUserInfo")
    @ResponseBody
    public String AdminUpdateUserInfo(@RequestBody Student student){
        int flag = studentService.AdminUpdateUserInfo(student);
        if(flag == 1){
            return "1";
        }
        return "0";
    }

    //管理员删除用户
    @RequestMapping("/deleteUserById")
    @ResponseBody
    public String deleteUserById(@RequestBody String id){
        //将字符串转化成JsonObject
        System.out.println(id);
        JSONObject jsonObject = (JSONObject) new JSONObject().parse(id);
        int flag = studentService.deleteUserById((String) jsonObject.get("id"));
        if(flag == 1){
            return "1";
        }
        return "0";
    }
}
