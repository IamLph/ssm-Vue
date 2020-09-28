package com.lph.dao;

import com.lph.domain.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao {

    //获取随机题目
    @Select("SELECT * FROM examination_question1  ORDER BY  RAND() LIMIT 100")
    List<Question> getQuestion();

    //获取题目数量
    @Select("select count(*) from examination_question1")
    int getAllCount();

    //获取所有题目
    @Select("select * from examination_question1")
    List<Question> getAllQuestion();

    //根据Id查找问题
    @Select("select * from examination_question1 where id = #{id}")
    Question selectById(int id);

    //添加问题
    @Insert("  insert into `examination_question1` (`id`,`question`,`answer`,`item1`,`item2`,`item3`,`item4`,`explains`,`url`)\n" +
            "        values (\n" +
            "            #{id},\n" +
            "            #{question},\n" +
            "            #{answer},\n" +
            "            #{item1},\n" +
            "            #{item2},\n" +
            "            #{item3},\n" +
            "            #{item4},\n" +
            "            #{explains},\n" +
            "            #{url}\n" +
            "        )")
    int AddQuestion(Question question);

    //修改问题
    @Update("UPDATE examination_question1\n" +
            "            SET question = #{question},\n" +
            "            answer = #{answer},\n" +
            "            item1 = #{item1},\n" +
            "            item2 = #{item2},\n" +
            "            item3 = #{item3},\n" +
            "            item4 = #{item4},\n" +
            "            explains = #{explains},\n" +
            "            url = #{url}\n" +
            "            WHERE\n" +
            "\t        id = #{id}")
    int UpdateQuestion(Question question);

    //根据ID删除问题
    @Delete("delete from examination_question1 where id = #{id}")
    int deleteById(int id);

}
