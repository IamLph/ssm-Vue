package com.lph.service;

import com.lph.domain.Question;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 业务层接口，处理问题
 */
public interface QuestionService {
    //获取随机题目
    List<Question> getQuestion();

    //获取题目数量
    int getAllCount();

    //获取所有题目
    public List<Question> getAllQuestion();

    //根据Id查找问题
    Question selectById(int id);

    //添加问题
    int AddQuestion(Question question);

    //修改问题
    int UpdateQuestion(Question question);

    //根据ID删除问题
    int deleteById(int id);
}
