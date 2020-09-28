package com.lph.dao;


import com.lph.domain.Grade;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeDao {
    //添加成绩
    @Insert("insert into grade (`userId`,`core`,`testData`,`weifaOP`) values (#{userId},#{core},#{testData},#{weifaOP})")
    int addGrade(Grade grade);

    //根据ID获取信息
    @Select("SELECT * FROM `grade` WHERE userId = #{id}")
    List<Grade> getGradeById(String id);

}
