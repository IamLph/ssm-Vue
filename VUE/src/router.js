//导入设置路由的包
import VueRouter from 'vue-router'
import login from "@/components/public/login";
import register from "@/components/public/register";
import rule from "@/components/public/rule";
import home from "@/components/public/home";
import StudentInfo from "@/components/student/StudentInfo";
import updatePassword from "@/components/student/updatePassword";
import showExamInfo from "@/components/student/showExamInfo";
import goExam from "@/components/student/goExam";
import TeacherIApp from "@/components/teacher/TeacherIApp";
import AdminUpdateQuestion from "@/components/admin/AdminUpdateQuestion";
import AdminUpdateUserInfo from "@/components/admin/AdminUpdateUserInfo";
import UpdateUserInfo from "@/components/admin/UpdateUserInfo";
import addQuestion from "@/components/admin/addQuestion";
import updateQuestion from "@/components/admin/updateQuestion";
var router = new VueRouter({
    mode:'history',
    routes:[
        //配置路由规则
        //{path:'/',redirect:'/home'},//设置默认路径
        {path:'/home',component:home},//首页
        {path:'/login',component:login},//登陆组件
        {path:'/register',component:register},//注册组件
        {path:'/rule',component:rule},//用户协议
        {path:'/studentInfo',component:StudentInfo},//学生信息
        {path:'/updatePassword',component:updatePassword},//修改密码界面
        {path:'/showExamInfo',component:showExamInfo},//获取考试记录
        {path:'/goExam',component:goExam},//前往考试
        {path:'/teacherApp',component:TeacherIApp},//教师界面a
        {path:'/adminUpdateQuestion',component:AdminUpdateQuestion},//管理员界面
        {path:'/adminUpdateUserInfo',component:AdminUpdateUserInfo},//教师界面
        {path:'/updateUserInfo',component:UpdateUserInfo},//用户信息
        {path:'/addQuestion',component:addQuestion},//添加问题
        {path:'/updateQuestion',component:updateQuestion},//修改问题
    ],
    linkActiveClass:'mui-active',//覆盖默认高亮的类，叫做router-link-active
});

//暴露路由
export default router;