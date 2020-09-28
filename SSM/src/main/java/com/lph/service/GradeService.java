package com.lph.service;

import com.lph.domain.Grade;

import java.util.List;

/**
 * 业务层
 */
public interface GradeService {
    /**
     *
     * 添加成绩
     * @param grade
     */
    int addGrade(Grade grade);

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    List<Grade> getGradeById(String id);

}
