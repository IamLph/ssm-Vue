package com.lph.service.impl;

import com.lph.dao.GradeDao;
import com.lph.domain.Grade;
import com.lph.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//交给IOC进行管理
@Service("gradeService")
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeDao gradeDao;

    /**
     * 添加成绩
     * @param grade
     */
    @Override
    public int addGrade(Grade grade) {
        Date date = new Date();
        SimpleDateFormat dataFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String data = dataFormat.format(date);
        //添加成绩需要添加当前时间Data
        grade.setTestData(data);

        if(gradeDao.addGrade(grade) > 0){
            //操作成功
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 根据ID获取成绩
     * @param id
     * @return
     */
    @Override
    public List<Grade> getGradeById(String id) {
        return gradeDao.getGradeById(id);
    }
}
