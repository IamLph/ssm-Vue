<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/9/11
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>开始整合了</title>
</head>
<body>
    <h3>哈哈哈哈</h3>
    <a href="/question/getQuestion">获取随机100问题</a><br/>
    <a href="question/getAllQuestion">获取所有问题</a><br/>
    <a href="/question/selectById?id=2">根据Id查找问题</a><br/>
    <hr>
    <form action="/question/AddQuestion" method="post">
        问题:<input type="text" name="question"><br/>
        答案:<input type="text" name="answer"><br/>
        答案1:<input type="text" name="item1"><br/>
        答案2:<input type="text" name="item2"><br/>
        答案3:<input type="text" name="item3"><br/>
        选项4":<input type="text" name="item4"><br/>
        错误解释:<input type="text" name="explains"><br/>
        图片地址:<input type="text" name="url"><br/>
        <input type="submit" value="提交数据">
    </form>
    <hr>
    <form action="/question/UpdateQuestion" method="post">
        id:<input type="text" name="id"><br/>
        问题:<input type="text" name="question"><br/>
        答案:<input type="text" name="answer"><br/>
        答案1:<input type="text" name="item1"><br/>
        答案2:<input type="text" name="item2"><br/>
        答案3:<input type="text" name="item3"><br/>
        选项4":<input type="text" name="item4"><br/>
        错误解释:<input type="text" name="explains"><br/>
        图片地址:<input type="text" name="url"><br/>
        <input type="submit" value="修改数据">
    </form>
    <hr>
    <form action="/question/deleteById" method="post">
        要删除的ID:<input type="text" name="id">
        <input type="submit" value="开始删除">
    </form>
    <hr>
    <hr>
    <hr>
    <h3>成绩测试</h3>
    <form action="grade/addGrade" method="post">
        用户Id:<input type="text" name="userId"><br/>
        用户分数：<input type="text" name="core"><br/>
        违法操作:<input type="text" name="weifaOP"><br/>
        <input type="submit" value="提交成绩"><br/>
    </form>
    <hr>
    <form action="grade/getGradeById" method="post">
        输入用户ID:<input type="text" name="id"><br/>
        <input type="submit" value="查找"><br/>
    </form>
    <hr>
    <hr>
    <h3>学生测试</h3>
    <form method="post" action="student/getStudentById">
        输入学号:<input type="text" name="id"/><br/>
        <input type="submit" value="查找提交">
    </form>
    <hr>
    <form action="student/registerUser" method="post">
        name:<input type="text" name="name">
        password:<input type="text" name="password">
        email:<input type="text" name="email">
        telephone:<input type="text" name="telephone">
        <input type="submit" value="注册用户">
    </form>
    <hr>
    <h3>修改信息</h3>
    <form action="student/updateInfo" method="post">
        id:<input type="text" name="id"><br/>
        name:<input type="text" name="name"><br/>
        email:<input type="text" name="email"><br/>
        telephone:<input type="text" name="telephone"><br/>
        <input type="submit" value="修改用户"><br/>
    </form>
    <h3>检查密码</h3>
    <form action="student/checkPassword" method="post">
        id:<input type="text" name="id"><br/>
        password:<input type="text" name="password"><br/>
        <input type="submit" value="检查"><br/>
    </form>
    <h3>修改密码</h3>
    <form action="student/updatePassword" method="post">
        id:<input type="text" name="id"><br/>
        password:<input type="text" name="password"><br/>
        <input type="submit" value="修改密码"><br/>
    </form>
    <h3>设置状态</h3>
    <form action="/student/setUserState" method="post">
        state:<input type="text" name="state"><br/>
        id:<input type="text" name="id"><br/>
        <input type="submit" value="设置状态"><br/>
    </form>
    <hr>
    <a href="/student/setAllLeave">设置为全部离线状态</a>
    <br>
    <a href="/student/getAllStudent">获取学生的状态</a>
    <br>
    <a href="/student/getAllNotAdmin">获取非管理员用户</a>
    <hr>
    <h3>管理员修改用户</h3>
    <form action="student/AdminUpdateUserInfo" method="post">
        id:<input type="text" name="id"><br/>
        name:<input type="text" name="name"><br/>
        password:<input type="text" name="password"><br/>
        email:<input type="text" name="email"><br/>
        telephone:<input type="text" name="telephone"><br/>
        type:<input type="text" name="type"><br/>
        <input type="submit" value="修改用户"><br/>
    </form>
    <h3>管理员删除用户</h3>
    <form action="/student/deleteUserById" method="post">
        id:<input type="text" name="id"><br/>
        <input type="submit" value="删除用户"><br/>
    </form>
</body>
</html>
