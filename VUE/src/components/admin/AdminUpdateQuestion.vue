<template>
    <div id="con">
        <table align="center" cellpadding="0" cellspacing="0">
            <tr>
                <th>编号</th>
                <th>问题</th>
                <th>选项一</th>
                <th>选项二</th>
                <th>选项三</th>
                <th>选项四</th>
                <th>答案</th>
                <th><a href="/addQuestion">添加问题</a></th>
            </tr>
            <tr v-for="item in questionList" :key="item.id">
                <td>{{item.id}}</td>
                <td>{{item.question}}</td>
                <td>{{item.item1}}</td>
                <td>{{item.item2}}</td>
                <td>{{item.item3}}</td>
                <td>{{item.item4}}</td>
                <td>{{item.answer}}</td>
                <td style="width: 150px"><router-link :to="{path:'/updateQuestion',query:{id:item.id}}">进行修改</router-link></td>
            </tr>
        </table>
    </div>
</template>

<script>
    import main from "@/main";
    export default {
        name: "AdminUpdateQuestion",
        data:function () {
            return{
                questionList:[],
            }
        },
        mounted(){
            this.getAllQuestion();
        },
        methods:{
            getAllQuestion(){
                this.$axios({
                    method: 'post',
                    url: main.url+'/question/getAllQuestion',
                    data: {}
                }).then((response) => {
                    //console.log(response);
                    //如果有的话，将数据添加到list中
                    if(response.data != null){
                        this.questionList = response.data;
                    }
                }).catch((error) => {
                    console.log("错误日志:" + error)
                });
            }
        }
    }
</script>

<style scoped>
    #con{
        margin-top: 3%;
        margin-left: auto;
        margin-right: auto;
        text-align: center;
        left: 0;
        right: 0;
        overflow-y:scroll;
        width:1420px;
        height: 495px;
        border:1px solid #999999
    }
    table{
        border: 1px solid black;
        width: 1400px;
        height: 241px;
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
</style>