package com.lph.dao;


import com.lph.domain.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao {

    //添加注册用户
    @Insert("insert into `student` (`id`,`name`,`password`,`email`,`telephone`,`state`,`type`)\n" +
            "        values (#{id},#{name},#{password},#{email},#{telephone},0,0)")
    int registerUser(Student student);

    //根据ID查找用户
    @Select("select * from `student` where id = #{id}")
    Student getStudentById(String id);

    //修改信息
    @Update("update `student` set name = #{name},email = #{email},telephone = #{telephone} where id = #{id}")
    int updateInfo(Student student);

    //检查密码
    @Select("select * from `student` where id = #{id} and password = #{password}")
    Student checkPassword(Student student);

    //修改密码
    @Update("update `student` set password = #{password} where id = #{id}")
    int updatePassword(Student student);

    //设置状态
    @Update("update `student` set state = #{state} where id = #{id}")
    int setUserState(Student student);

    //设置全部为离线状态
    @Update("update `student` set state = 0")
    int setAllLeave();

    //获取学生状态
    @Select("SELECT * FROM `student` WHERE type != 2")
    List<Student> getAllStudent();

    //获取非管理员用户
    @Select("SELECT * FROM `student` WHERE type != 2")
    List<Student> getAllNotAdmin();

    //管理员修改用户
    @Update(" update `student` set name = #{name},password = #{password},email = #{email},telephone = #{telephone},type = #{type}\n" +
            "        where id = #{id}")
    int AdminUpdateUserInfo(Student student);

    //管理员删除用户
    @Delete("delete from student where id = #{id}")
    int deleteUserById(String id);

}
