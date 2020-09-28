<template>
    <div class="register">
        <form class="form-horizontal" method="post" action="#" id="form">
            <div class="form-group">
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="name" v-model="name" placeholder="请输入用户名" style="width: 200px">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-7">
                    <input type="password" class="form-control" id="password" v-model="password" placeholder="请输入密码" style="width: 200px">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-7">
                    <input type="password" class="form-control" id="password1" v-model="password1" placeholder="请再次输入密码" style="width: 200px">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-7">
                    <input type="email" class="form-control" id="email" v-model="email" placeholder="请输入邮箱" style="width: 200px">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="telephone" v-model="telephone" placeholder="请输入手机号" style="width: 200px">
                </div>
            </div>

            <div class="form-group">
                <div>
                    <input type="checkbox" id="agree" name="agree" @click="checkbox()">
                    我同意<router-link to="/rule" style="font-size: 15px" id="userRule">《遵纪守法》</router-link>用户协议
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-5 col-sm-6">
                    <button type="button" class="btn btn-default" id="reg" v-on:click="reg">注册</button>
                </div>
            </div>
            <br><br>
            <br><br>
            <br><br>
        </form>
    </div>
</template>

<script>
    import main from "@/main";
    export default {
        name: "register",
        data:function () {
            return{
                name:"",
                password:"",
                password1:"",
                email:"",
                telephone:"",
                isCheck:false,
            }
        },
        methods:{
            //判断是否勾选用户协议
            checkbox(){
                if(event.target.checked){
                    this.isCheck = true
                }else {
                    this.isCheck = false
                }
            },
            reg:function () {
                //正则表达式
                const reg = /^\w+((.\w+)|(-\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+).[A-Za-z0-9]+$/;
                const myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
                //  先判断输入的内容类型与格式等
                //1.先判断协议是否被勾选
                if(this.isCheck){
                    //2.对数据进行判断
                    if(this.name === "" || this.password === "" || this.password1 === "" || this.email === "" || this.telephone === ""){
                        alert("请您输入完整的信息");
                    }else {
                        //3.判断两次输入的密码是否一致
                        if(this.password1 === this.password) {
                            //4.验证邮箱是否符合规范
                            if(reg.test(this.email)){
                               //5.判断电话号是否符合规范
                                if(myreg.test(this.telephone)){
                                    //判断完成，发送数据请求注册
                                    this.$axios({
                                        method:'post',
                                        url:main.url+'/student/registerUser',
                                        data:{
                                            name:this.name,
                                            password:this.password1,
                                            email:this.email,
                                            telephone:this.telephone,
                                        }
                                    }).then((response)=>{
                                        console.log(response);
                                        if(response.data != null){
                                            alert("注册完成\n您的账号是:"+response.data+"\n请妥善保管！！")
                                            //跳转登陆页面
                                            this.$router.push('/login');
                                        }else {
                                            alert("注册失败")
                                        }
                                    }).catch((error)=>{
                                        console.log("错误日志:"+error)
                                    })
                                }else {
                                    alert("请输入正确的11位电话号码");
                                }
                            }else {
                                //格式不对
                                alert("请输入正确的邮箱")
                            }
                        }else {
                            alert("两次密码不一致")
                        }
                    }
                }else {
                    alert("请您先同意用户协议");
                }
            },
        }
    }
</script>

<style scoped>
    *{
        margin: 0;
        padding: 0;
    }
    .register{
        height: 100%;
        display:flex;
        background-image: url("../../images/001.jpg");
        background-size: 100% 100%;
        width: 100%;
    }
    #form{
        margin-left: 43%;
        margin-top: 9%;
        width: 98%;
        height: 100%;
    }
    #userRule{
        color: red;
    }
    #reg{
        background-color: rgba(248, 247, 255, 0.4);
        width: 100px;
        margin-top: 5%;
        margin-left: 5%;
    }
    input{
        background-color: rgba(248, 247, 255, 0.4);
    }
</style>