package com.lph.service.impl;

import com.lph.dao.StudentDao;
import com.lph.domain.Student;
import com.lph.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    private String getRandomCount(){
        String string = "1234567890";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            int index = (int) Math.floor(Math.random() * string.length());//向下取整0-25
            sb.append(string.charAt(index));
        }
        if(studentDao.getStudentById(sb.toString()) != null){
            //就是存在的意思
            //再次生成
            this.getRandomCount();
        }
        return sb.toString();
    }

    @Override
    public String registerUser(Student student) {
        //1:获取随机账号并且设置
        String id = getRandomCount();
        student.setId(id);
        //判断是否添加成功
        if(studentDao.registerUser(student) > 0){
            return id;
        }else {
            return null;
        }
    }

    @Override
    public Student getStudentById(String id) {
        //System.out.println(studentDao.getStudentById(id));
        return studentDao.getStudentById(id);
    }

    /**
     * 修改用户信息
     * 不修改密码
     * @param student
     * @return
     */
    @Override
    public int updateInfo(Student student) {
        //判断是否修改成功
        if(studentDao.updateInfo(student) > 0){
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 检查密码
     * @param student
     * @return
     */
    @Override
    public Student checkPassword(Student student) {
        if(studentDao.checkPassword(student)!=null){
            return studentDao.checkPassword(student);
        }
        return null;
    }

    /**
     * 修改密码
     * @param student
     * @return
     */
    @Override
    public int updatePassword(Student student) {
        if(studentDao.updatePassword(student) > 0){
            return 1;
        }
        return 0;
    }

    /**
     * 设置状态
     * @param student
     * @return
     */
    @Override
    public int setUserState(Student student) {
        if(studentDao.setUserState(student) > 0){
            return 1;
        }
        return 0;
    }

    /**
     * 设置全部为离线状态
     * @return
     */
    @Override
    public int setAllLeave() {
        if(studentDao.setAllLeave() > 0){
            return 1;
        }
        return 0;
    }

    @Override
    public List<Student> getAllStudent() {
        return studentDao.getAllStudent();
    }

    @Override
    public List<Student> getAllNotAdmin() {
        return studentDao.getAllNotAdmin();
    }

    /**
     *
     * 管理员修改用户
     * @param student
     * @return
     */
    @Override
    public int AdminUpdateUserInfo(Student student) {
        if(studentDao.AdminUpdateUserInfo(student) > 0){
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteUserById(String id) {
        if(studentDao.deleteUserById(id) > 0){
            return 1;
        }
        return 0;
    }
}
