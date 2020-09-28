<template>
    <div id="container">
        <table align="center" cellpadding="0" cellspacing="0">
            <tr>
                <th>考试时间</th>
                <th>分数</th>
                <th>违规操作次数</th>
            </tr>
            <tr v-bind:key="item" v-for="item in list">
                <td>{{item.testData}}</td>
                <td>{{item.core}}</td>
                <td>{{item.weifaOP}}</td>
            </tr>

        </table>
    </div>
</template>

<script>
    import main from "@/main";
    export default {
        name: "showExamInfo",
        data:function () {
            return{
                list:null,
                id:sessionStorage['userId'],
            }
        },
        mounted(){
            this.getAllExam();
        },
        methods:{
            getAllExam(){
                //发送请求，获取考试信息
                this.$axios({
                    method: 'post',
                    url: main.url+'/grade/getGradeById',
                    data: {
                        id:this.id,
                    }
                }).then((response) => {
                    //如果有的话，将数据添加到list中
                    this.list = response.data;
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
        width: 600px;
        text-align: center;
    }
    tr{
        border: 1px solid black;
        width: 200px;
    }
    td{
        border: 1px solid black;
        width: 200px;
    }
    th{
        border: 1px solid black;
        width: 200px;
    }
    #container{
        margin: auto;
        left: 0;
        right: 0;
        background-color: white;
        overflow-y:scroll;
        width:620px;
        height: 540px;
        border: 1px solid black;
        margin-top: 3%;
    }
</style>