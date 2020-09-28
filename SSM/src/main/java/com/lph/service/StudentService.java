package com.lph.service;

import com.lph.domain.Student;

import java.util.List;

public interface StudentService {
    //添加注册用户
    String registerUser(Student student);

    //根据ID查找用户
    Student getStudentById(String id);

    //修改信息
    int updateInfo(Student student);

    //检查密码
    Student checkPassword(Student student);

    //修改密码
    int updatePassword(Student student);

    //设置状态
    int setUserState(Student student);

    //设置全部为离线状态
    int setAllLeave();

    //获取学生状态
    List<Student> getAllStudent();

    //获取非管理员用户
    List<Student> getAllNotAdmin();

    //管理员修改用户
    int AdminUpdateUserInfo(Student student);

    //管理员删除用户
    int deleteUserById(String id);

}
