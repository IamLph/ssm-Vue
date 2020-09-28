<template>
    <div id="con">
        <table align="center" cellspacing="0" cellpadding="0">
            <tr>
                <th>用户账号</th>
                <th>用户姓名</th>
                <th>用户密码</th>
                <th>用户邮箱</th>
                <th>用户电话</th>
                <th>用户类型</th>
                <th>操作</th>
            </tr>
            <tr v-for="item in userList" :key="item.id">
                <td>{{item.id}}</td>
                <td>{{item.name}}</td>
                <td>{{item.password}}</td>
                <td>{{item.email}}</td>
                <td>{{item.telephone}}</td>
                <td>{{item.type}}</td>
                <td><router-link :to="{path:'/updateUserInfo',query:{userId:item.id}}">修改信息</router-link></td>
            </tr>
        </table>
    </div>
</template>

<script>
    import main from "@/main";
    export default {
        name: "AdminUpdateUserInfo",
        data:function () {
            return{
                userList:[],
            }
        },
        mounted(){
            this.getAllUserInfo();
        },
        methods:{
            getAllUserInfo(){
                this.$axios({
                    method: 'post',
                    url: main.url+'/student/getAllNotAdmin',
                    data: {}
                }).then((response) => {
                    //console.log(response);
                    //如果有的话，将数据添加到list中
                    if(response.data != null){
                        this.userList = response.data;
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
        border: 1px solid black;
        width: 1400px;
        height:241px;
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
    }
    #con{
        margin-top: 3%;
        margin-right: auto;
        margin-left: auto;
        left: 0;
        right: 0;
        text-align: center;
        overflow-y:scroll;
        width:1420px;
        height: auto;
        max-height: 495px;
        border:1px solid #999999
    }
</style>