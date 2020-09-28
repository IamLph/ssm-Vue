package com.lph.test;

import com.lph.dao.QuestionDao;
import com.lph.domain.Question;
import com.lph.service.QuestionService;
import com.lph.service.impl.QuestionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件位置
@ContextConfiguration({ "classpath:applicationContext.xml"})
public class Questiontest {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private QuestionService questionService;

    @Test
    public void zhujie(){
        System.out.println("开始");
        //questionService = new QuestionServiceImpl();
        //List<Question> list = questionService.getAllQuestion();
        List<Question> list = questionDao.getAllQuestion();
        for (Question q: list) {
            System.out.println(q.toString());
        }
    }

    @Test
    public void run1(){
        //加载Spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        //获取对象
        QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
        //QuestionDao questionDao = (QuestionDao) applicationContext.getBean("questionDao");
        List<Question> list = questionService.getAllQuestion();
//        List<Question> list = questionDao.getAllQuestion();
        for (Question q: list) {
            System.out.println(q.toString());
        }

    }
}
