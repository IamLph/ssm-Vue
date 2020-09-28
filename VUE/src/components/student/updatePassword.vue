<template>
    <div class="updatePassword">
        <form class="form-horizontal" method="post" action="#" style="width: 98%">
            <div class="form-group">
                <div class="col-sm-7">
                    <input type="password" class="form-control" id="oldPassword" v-model="oldPassword" placeholder="原密码" style="width: 200px">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-7">
                    <input type="password" class="form-control" id="newPassword" v-model="newPassword" placeholder="新密码" style="width: 200px">
                </div>
            </div>
            <div class="form-group">
                <label for="update" class="col-sm-5 control-label"></label>
                <div class="col-sm-7">
                    <input type="button" value="修改" id="update" @click="updatePassword()">
                </div>
            </div>
        </form>
    </div>
</template>

<script>
    import main from "@/main";
    export default {
        name: "updatePassword",
        data:function () {
            return{
                id:'',
                oldPassword:'',
                newPassword:'',
                password:'',
            }
        },
        mounted(){
            this.getOldPassword();
        },


        methods:{
            getOldPassword(){
                this.$axios({
                    method: 'post',
                    url: main.url+'/student/getStudentById',
                    data: {
                        id:sessionStorage['userId'],
                    }
                }).then((response) => {
                    this.password = response.data.password;
                }).catch((error) => {
                    console.log("错误日志:" + error)
                })
            },
            updatePassword(){
                this.id = sessionStorage.getItem("userId");
                //1.判空
                if(this.oldPassword === "" || this.newPassword === ""){
                    alert("请不要输入空的数据");
                }else {
                    //2.判断旧的密码是否一致
                    if(this.oldPassword === this.password){
                        //3.发送请求开始判断
                        this.$axios({
                            method: 'post',
                            url: main.url+'/student/updatePassword',
                            data: {
                                id:this.id,
                                password:this.newPassword
                            }
                        }).then((response) => {
                            console.log(response);
                            if (response.data === 1) {
                                alert("密码修改完成！\n即将跳转页面！");
                                //清理登陆信息
                                //设置退出状态
                                this.$LeaveState();
                                //退出登陆
                                //移除session
                                sessionStorage.clear();
                                this.$router.go(0);
                                //跳转登陆页面
                                this.$router.push('/login');
                                this.$router.go(0);
                            } else {
                                alert("修改失败")
                            }
                        }).catch((error) => {
                            console.log("错误日志:" + error)
                        })
                    }else {
                        alert("旧密码不正确，无法修改")
                    }
                }
            }
        }
    }
</script>

<style scoped>
    .updatePassword{
        margin-top: 8%;
        text-align: center;
    }
    #update{
        background-color: #ffed9b;
        font-size: 15px;
        width: 5%;
    }
</style>