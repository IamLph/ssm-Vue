<template>
    <div>
        <div style="text-align: center"><h4>用户类型type分别为 0:普通用户 1:教师用户</h4></div>
        <table align="center" cellpadding="0" cellspacing="0">
            <tr>
                <th>用户账号</th>
                <th>用户姓名</th>
                <th>用户密码</th>
                <th>用户邮箱</th>
                <th>用户电话</th>
                <th>用户类型</th>
                <th>修改</th>
                <th>删除</th>
            </tr>
            <tr>
                <td>
                    <label for="id"></label><input type="text" id="id" v-model="id" disabled="disabled">
                </td>
                <td>
                    <label for="name"></label><input type="text" id="name" v-model="name">
                </td>
                <td>
                    <label for="password"></label><input type="text" id="password" v-model="password">
                </td>
                <td>
                    <label for="email"></label><input type="text" id="email" v-model="email">
                </td>
                <td>
                    <label for="telephone"></label><input type="text" id="telephone" v-model="telephone">
                </td>
                <td>
                    <label for="type"></label><input type="text" id="type" v-model="type">
                </td>
                <td>
                    <input type="button" value="修改信息" @click="updateInfo" style="color: green;">
                </td>
                <td>
                    <input type="button" value="删除用户" @click="deleteInfo" style="color: red">
                </td>
            </tr>
        </table>
    </div>
</template>

<script>
    import main from "@/main";
    export default {
        name: "UpdateUserInfo",
        data:function(){
            return{
                id:this.$route.query.userId,
                name:'',
                password:'',
                email:'',
                telephone:'',
                type:0,
            }
        },
        mounted(){
            this.getUserById();
        },
        methods:{
            getUserById(){
                this.$axios({
                    method: 'post',
                    url: main.url+'/student/getStudentById',
                    data: {
                        id:this.id,
                    }
                }).then((response) => {
                    //console.log(response);
                    //如果有的话，将数据添加到list中
                    if(response.data != null){
                        this.name = response.data.name;
                        this.password = response.data.password;
                        this.email = response.data.email;
                        this.telephone = response.data.telephone;
                        this.type = response.data.type;
                    }
                }).catch((error) => {
                    console.log("错误日志:" + error)
                });
            },
            updateInfo(){
                //alert("修改！")
                //先做判空处理
                //正则表达式
                const reg = /^\w+((.\w+)|(-\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+).[A-Za-z0-9]+$/;
                const myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
                //1.判空
                if(this.name === "" || this.password === "" || this.email === "" || this.telephone === "" || this.type === ""){
                    alert("修改失败，不要存在空数据！");
                }else {
                    //2.判断邮箱手机
                    if(!reg.test(this.email) || !myreg.test(this.telephone)){
                        alert("请输入正确的邮箱或者手机号！");
                    }else {
                        //3.判断修改的用户类型
                        if(this.type == 1 || this.type == 0){
                            //正常
                            this.$axios({
                                method: 'post',
                                url: main.url+'/student/AdminUpdateUserInfo',
                                data: {
                                    id:this.id,
                                    name:this.name,
                                    password:this.password,
                                    email:this.email,
                                    telephone:this.telephone,
                                    type:this.type,
                                }
                            }).then((response) => {
                                //console.log(response);
                                //如果有的话，将数据添加到list中
                                if(response.data != null){
                                    alert("修改成功!");
                                    this.$router.push('/adminUpdateUserInfo');
                                }else {
                                    alert("修改失败！");
                                }
                            }).catch((error) => {
                                console.log("错误日志:" + error)
                            });
                        }else {
                            alert("用户类型修改失败")
                        }
                    }
                }
            },
            deleteInfo(){
                //alert("删除！");
                this.$axios({
                    method: 'post',
                    url: main.url+'/student/deleteUserById',
                    data: {
                        id:this.id,
                    }
                }).then((response) => {
                    //console.log(response);
                    //如果有的话，将数据添加到list中
                    if(response.data == 1){
                        alert("删除成功!");
                        this.$router.push('/adminUpdateUserInfo');
                    }else {
                        alert("删除失败！");
                    }
                }).catch((error) => {
                    console.log("错误日志:" + error)
                });
            }

        }
    }
</script>

<style scoped>
    table{
        text-align: center;
        border: 1px solid black;
        width: 1400px;
    }
    tr{
        width: 1398px;
        height: auto;
    }
    td{
        border: 1px solid green;
    }
    th{
        border: 1px solid green;
        padding: 0;
    }
    input{
        text-align: center;
        width: 100%;
        font-size: 20px;
        height: 100%;
        margin: 0;
    }
</style>