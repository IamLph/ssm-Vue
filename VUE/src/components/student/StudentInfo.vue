<template>
    <div class="userInfo">
        <br>
        <br>
        <br>
        <table align="center" cellpadding="0" cellspacing="0">
            <tr>
                <th>账号</th>
                <th>用户名</th>
                <th>邮箱</th>
                <th>电话</th>
                <th>修改密码</th>
                <th>修改</th>
            </tr>
            <tr>
                <td>
                    <!--用户账号-->
                    <div v-text="id" style="color: red;font-size: 25px"></div>
                </td>
                <td>
                    <input type="text" v-model="name">
                </td>
                <td>
                    <input type="email" v-model="email">
                </td>
                <td>
                    <input type="text" v-model="telephone">
                </td>
                <td>
                    <router-link to="/updatePassword">修改密码</router-link>
                </td>
                <td>
                    <button @click="updateInfo()">修改</button>
                </td>
            </tr>
        </table>
    </div>
</template>

<script>
    import main from "@/main";
    export default {
        name: "StudentInfo",
        data:function () {
            return{
                id:'',
                name:'',
                email:'',
                telephone:'',
            }
        },
        mounted(){
            this.getInfo();
        },
        methods: {
            getInfo() {
                this.$axios({
                    method: 'post',
                    url: main.url+'/student/getStudentById',
                    data: {
                        id:sessionStorage['userId'],
                    }
                }).then((response) => {
                    this.id = response.data.id;
                    this.name = response.data.name;
                    this.email = response.data.email;
                    this.telephone = response.data.telephone;
                }).catch((error) => {
                    console.log("错误日志:" + error)
                })

            },
            updateInfo() {
                //正则表达式
                const reg = /^\w+((.\w+)|(-\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+).[A-Za-z0-9]+$/;
                const myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
                //1.判断输入是否为空
                if (this.name === "" || this.email === "" || this.telephone === "") {
                    alert("请您输入完整的信息");
                } else {
                    //2.格式判断
                    if (reg.test(this.email) || myreg.test(this.telephone)) {
                        if (myreg.test(this.telephone)) {
                            //判断完成，发送数据请求注册
                            this.$axios({
                                method: 'post',
                                url: main.url+'/student/updateInfo',
                                data: {
                                    id:this.id,
                                    name: this.name,
                                    email: this.email,
                                    telephone: this.telephone,
                                }
                            }).then((response) => {
                                console.log(response);
                                if (response.data == 1) {
                                    alert("信息修改完成！");
                                    //跳转登陆页面
                                    //this.$router.push('/login');
                                } else {
                                    alert("修改失败")
                                }
                            }).catch((error) => {
                                console.log("错误日志:" + error)
                            })
                        } else {
                            alert("请输入正确的邮箱或者手机号格式！")
                        }
                    }
                }
            },
        }
    }

</script>

<style scoped>
    table{
        border: 1px solid black;
        text-align: center;
        background-color: white;
    }
    tr{
        border: 1px solid black;
        width: 200px;
        height: 50px;
    }
    td{
        border: 1px solid black;
        width: 200px;
        height: 100%;
    }
    th{
        border: 1px solid black;
        width: 200px;
        height: 50px;
    }
    input{
        border: 0;
        width: 100%;
        font-size: 20px;
        height: 100%;
    }
    button{
        width: 190px;
        height: 50px;
        font-size: 20px;
        border: 0;
        color: blue;
    }
</style>