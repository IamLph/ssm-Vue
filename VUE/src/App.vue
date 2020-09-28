<template>
  <div id="app">
    <!--设置顶部-->
    <div class="top">
      <div id="shouYe">
        <router-link to="/home" id="shouYe_content">首页</router-link>
      </div>
      <div v-show="userOP" class="top_left_op">
        <router-link to="/goExam" class="login_op">开始考试</router-link>
        <router-link to="/showExamInfo" class="login_op">查看考试记录</router-link>
      </div>
      <div v-show="teacherOP" class="top_left_op">
        <router-link to="/teacherApp" class="teacherOP">查看学生状态信息</router-link>
      </div>
      <div v-show="adminOP" class="top_left_op">
        <router-link to="/adminUpdateQuestion" class="login_op">查看题库信息并修改</router-link>
        <router-link to="/adminUpdateUserInfo" class="login_op">查看用户信息并修改</router-link>
      </div>

      <div style="display: flex" id="show_center" v-show="showLogin" ref="showType">
        <img src="./images/logo.jpg" alt="未找到" class="img">
        <p class="title">驾校在线考试系统</p>
      </div>

      <div v-if="showWelcome" class="welcome">
        <div class="welcome_content">
          欢迎
          <router-link to="/studentInfo" v-text="loginName"></router-link>
          使用
          <br/>
          <a @click="goOut()">退出登陆</a>
        </div>
      </div>

      <div class="op" v-else>
        <router-link to="/login" class="op_login">登陆</router-link>
        <router-link to="/register" class="op_register">注册</router-link>
      </div>
    </div>
    <!--中间部分-->
    <transition>
      <router-view/>
    </transition>

    <!--设置底部-->
    <Foot/>
  </div>
</template>

<script>
  import Foot from "@/components/public/foot";
  export default {
    name: 'App',
    components: {
      Foot,
    },
    data:function() {
      return {
        loginName:"",
        showLogin:true,
        showWelcome:false,
        userOP:false,
        teacherOP:false,
        adminOP:false,
      };
    },
    mounted(){
      //页面更新时调用方法
      this.flashApp();
    },
    watch:{
      $route(to){
        //监听地址的变化
        //console.log(to.path);
        //学生登陆会有考试功能，其他人没有改功能
        if(to.path === '/home'){
          this.getLogin();
        }
      }
    },
    methods:{
      getLogin(){
        this.showLogin = this.$store.getters['getLoginType'];
        this.loginName = this.$store.getters['getLogName'];
        this.showWelcome = true;
        this.showLogin = true;
        //首次登陆进行判断
        if(sessionStorage.getItem('userType') == 0){
          //学生
          this.userOP = true;
          this.$refs.showType.style.marginLeft = "13%";
        }else if (sessionStorage.getItem('userType') == 1){
          //教师
          this.showLogin = true;
          this.teacherOP = true;
          this.$refs.showType.style.marginLeft = "15%";
        }else if(sessionStorage.getItem('userType') == 2){
          //管理员
          this.adminOP = true;
          this.showLogin = true;
          this.$refs.showType.style.marginLeft = "6%";
        }
        //检测到回到主页就重新设置为登陆状态
        this.$LoginState();
      },
      flashApp(){
        //判断用户的类型
        if(sessionStorage.getItem('username')){
          this.loginName = sessionStorage.getItem('username');
          this.showWelcome = true;
          this.showLogin = true;
          if(sessionStorage.getItem('userType') == 1 ){
            this.teacherOP = true;
            this.$refs.showType.style.marginLeft = "15%";
          }else if(sessionStorage.getItem('userType') == 0){
            this.userOP = true;
            this.$refs.showType.style.marginLeft = "13%";
          }else if(sessionStorage.getItem('userType') == 2){
            this.adminOP = true;
            this.$refs.showType.style.marginLeft = "6%";
          }
        }
      },
      goOut(){
        //设置退出状态
        this.$LeaveState();
        //退出登陆
        //移除session
        this.showLogin = false;
        sessionStorage.clear();
        this.$router.push('/');
        this.$router.go(0);
      }
    }
  }
</script>

<style>
  .top{
    height: 100px;
    display:flex;
    background-color: rgba(92, 171, 147, 0.26)
  }
  .img{
    height: 100%;
    width: auto;
    margin-left: 10%
  }
  .title{
    display: block;
    word-break:keep-all;
    white-space:nowrap;
    margin-top: 8%;
    font-size: 36px;
    color: black;
    font-weight: normal;
  }
  a{
    font-size: 20px;
    height: 20px;
    color: black;
  }
  .op{
    display: block;
    word-break:keep-all;
    white-space:nowrap;
    height: 100%;
    margin-left: 23%;
  }
  .op_register{
    display: inline-block;
    margin-top: 20%;
    margin-left: 10px;
  }
  .op_login{
    display: inline-block;
    margin-top: 40%;
  }
  .teacherOP{
    display: inline-block;
    margin-top: 20%;
    margin-left: 10px;
  }
  .welcome{
    display: inline-block;
    word-break:keep-all;
    white-space:nowrap;
    margin-left: 20%;
  }
  .welcome_content{
    display: block;
    word-break:keep-all;
    white-space:nowrap;
    margin-top: 20%;
  }
  #shouYe{
    display: block;
    word-break:keep-all;
    white-space:nowrap;
    margin-left: 3%;
    font-size: 50px;
    color: black;
    font-weight: bold;
    height: 100%;
  }
  #shouYe_content{
    display: block;
    word-break:keep-all;
    white-space:nowrap;
    margin-top: 36%;
    font-size: 50px;
    color: black;
    font-weight: bold;
    height: 100%;
  }
  #show_center{
    height: 100%;
    width: auto;
    margin-left: 30%
  }
  .top_left_op{
    display: inline-block;
    word-break:keep-all;
    white-space:nowrap;
    margin-left: 2%;
    height: 100%;
    width:auto;
  }
  .login_op{
    display: inline-block;
    word-break:keep-all;
    white-space:nowrap;
    font-size: 20px;
    margin-left: 3%;
    margin-top: 15%;
    color: blue;
    border: 0 solid #2feeff;
  }
</style>
