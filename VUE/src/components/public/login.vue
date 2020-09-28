<template>
    <div class="login">
        <form class="form-horizontal" method="post" action="#" id="form">
            <div class="form-group">
                <div class="col-sm-7">
                    <input type="text" class="form-control" v-model="id" placeholder="请输入账号" style="width: 200px">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-7">
                    <input type="password" class="form-control" v-model="password" placeholder="请输入密码" style="width: 200px">
                </div>
            </div>
            <div class="form-group" id="ButtonGroup">
                <div class="col-sm-offset-5 col-sm-6">
                    <input type="button" value="登陆" id="submit" v-on:click="denglu()">
                    <input type="button" value="重置" id="restart" v-on:click="restart()">
                </div>
            </div>
            <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
        </form>
    </div>
</template>

<script>
    import main from "@/main";
    export default {
        name: "login",
        data:function() {
            return {
                id:"",
                password:""
            };
        },
        methods:{
            denglu:function () {
                //实现登陆
                this.$axios({
                    method:'post',
                    url:main.url+'/student/checkPassword',
                    data:{
                        id:this.id,
                        password:this.password
                    }
                }).then((response)=>{
                    //console.log(response.data);
                    if(response.data){
                        //存储用户名
                        sessionStorage.setItem('username',response.data.name);
                        //存储用户Id
                        sessionStorage.setItem('userId',response.data.id);
                        //存储用户Type类型
                        sessionStorage.setItem('userType',response.data.type);

                        //修改状态为登陆true,并且设置名字
                        this.$store.commit('changeLogName',response.data.name);
                        this.$store.commit('changeLoginType',true);
                        this.$store.commit('changeId',response.data.id);
                        this.$store.commit('changeType',response.data.type);
                        //进入就设置登陆状态
                        this.$LoginState();
                        //判断登陆的数据类型,然后根据不同的属性进入不同的页面
                        this.$router.push('/');
                    }else {
                        alert("账号密码错误！！");
                    }
                }).catch((error)=>{
                    console.log("错误日志:"+error)
                })
            },
            restart:function () {
                this.id = "";
                this.password = "";
            }
        }
    }
</script>

<style scoped>
    *{
        margin: 0;
        padding: 0;
    }
    .login{
        height: 100%;
        display:flex;
        background-image: url("../../images/001.jpg");
        background-size: 100% 100%;
        width: 100%;
    }
    #form{
        margin-left: 43%;
        margin-top: 12%;
        width: 98%;
        height: 100%;
    }
    #ButtonGroup{
        margin-top: 9%;
        font-size: 23px;
    }
    #submit,#restart{
        width: 100px;
        height: 30px;
    }
    #restart{
        width: 100px;
        height: 30px;
        margin-left: 2%;
    }
    input{
        background-color: rgba(248, 247, 255, 0.4);
    }
</style>