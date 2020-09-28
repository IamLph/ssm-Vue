package com.lph.service.impl;

import com.lph.dao.QuestionDao;
import com.lph.domain.Question;
import com.lph.service.QuestionService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Override
    public List<Question> getQuestion() {
        return questionDao.getQuestion();
    }
    //获取题目数量
    public int getAllCount(){
        return questionDao.getAllCount();
    }

    @Override
    public List<Question> getAllQuestion() {
        return questionDao.getAllQuestion();
    }

    @Override
    public Question selectById(int id) {
        return questionDao.selectById(id);
    }

    /**
     * 添加问题
     * @param question
     * @return
     */
    @Override
    public int AddQuestion(Question question) {
        //需要获取数据库的最后一个ID,生成对应的ID,从而不用自己设置ID编号,需要加1
        int id = questionDao.getAllCount()+1;
        question.setId(id);
        if(questionDao.AddQuestion(question) > 0){
            //成功返回1
            return 1;
        }else {
            //失败返回0
            return 0;
        }
    }

    /**
     * 修改问题
     * @param question
     * @return
     */
    @Override
    public int UpdateQuestion(Question question) {
        if(questionDao.UpdateQuestion(question) > 0){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public int deleteById(int id) {
        // 先看看有没有这个数据
        if(questionDao.selectById(id)!=null){
            //如果存在，可以去删除
            if(questionDao.deleteById(id)>0){
                return 1;
            }else {
                //删除失败
                return 0;
            }
        }else {
            //不存在数据
            return 2;
        }
    }
}
