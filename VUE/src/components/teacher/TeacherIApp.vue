<template>
    <div id="con">
        <table align="center" cellspacing="0" cellpadding="0">
            <tr>
                <th>学生账号</th>
                <th>学生姓名</th>
                <th>学生状态</th>
            </tr>
            <tr v-for="item in stuList" :key="item.id">
                <td>{{item.id}}</td>
                <td>{{item.name}}</td>
               <!-- <td ref="stuStateList">{{item.state}}</td>-->
                <td v-if="item.state == 0" style="background-color: gray">离线状态</td>
                <td v-if="item.state == 1" style="background-color: green">在线状态</td>
                <td v-if="item.state == 2" style="background-color: red">考试状态</td>
            </tr>
        </table>
    </div>
</template>

<script>
    import main from "@/main";
    export default {
        name: "TeacherIApp",
        data:function () {
            return{
                stuList:[],
            }
        },
        mounted(){
            this.getStudentState();
        },
        methods:{
            getStudentState(){
                this.$axios({
                    method: 'post',
                    url: main.url+'/student/getAllStudent',
                    data: {}
                }).then((response) => {
                    console.log(response);
                    //如果有的话，将数据添加到list中
                    if(response.data != null){
                        this.stuList = response.data;
                    }
                }).catch((error) => {
                    console.log("错误日志:" + error)
                });
            },
        }
    }
</script>

<style scoped>
    table{
        border: 1px solid black;
        margin-top: 4%;
        width: 800px;
        height:auto;
        text-align: center;
    }
    tr{
        width: 798px;
        height: auto;
    }
    td{
        border: 1px solid green;
    }
    th{
        border: 1px solid green;
    }
</style>