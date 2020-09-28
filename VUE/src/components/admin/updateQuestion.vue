<template>
    <div id="con">
        <div style="text-align: center;margin-right: 3%;margin-top: 1%">
            <h4>前面标记有&nbsp;&nbsp;*&nbsp;&nbsp;的可以不填,带有&nbsp;#&nbsp;的不可修改</h4>
        </div>
        <div style="margin-right: 42%">
            <form class="form-horizontal" method="post" action="#" style="width: 98%;text-align: right">
                <div class="form-group">
                    <div class="col-sm-7">
                        <!--<%&#45;&#45;编号不可以进行修改&#45;&#45;%>-->
                        #问题编号:<input type="text" disabled="disabled" class="form-control" id="id" name="id" v-model="id" style="width: 200px;">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-7">
                        #问题内容:<input type="text" disabled="disabled" class="form-control" id="question" name="question" v-model="question" style="width: 200px">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-7">
                        选项一:<input type="text" class="form-control" id="item1" name="item1" v-model="item1" style="width: 200px">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-7">
                        选项二:<input type="text" class="form-control" id="item2" name="item2" v-model="item2" style="width: 200px">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-7">
                        *&nbsp;选项三:<input type="text" class="form-control" id="item3" name="item3" v-model="item3" style="width: 200px">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-7">
                        *&nbsp;选项四:<input type="text" class="form-control" id="item4" name="item4" v-model="item4" style="width: 200px">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-7">
                        答案:<input type="text" class="form-control" id="answer" name="answer" v-model="answer" style="width: 200px">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-7">
                        *&nbsp;问题解析:<input type="text" class="form-control" id="explains" v-model="explains" name="explains" style="width: 200px">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-7">
                        *&nbsp;图片地址URL:<input type="text" class="form-control" id="urls" v-model="url" name="urls" style="width: 200px">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-5 col-sm-6">
                        <button type="button" class="btn btn-default" id="update" @click="updateQuestion">修改问题</button>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <button type="button" class="btn btn-default" id="delete" @click="deleteQuestion" style="margin-right: 5%">删除问题</button>
                    </div>
                </div>
            </form>
        </div>

    </div>
</template>

<script>
    import main from "@/main";
    export default {
        name: "updateQuestion",
        data:function () {
            return{
                id:this.$route.query.id,
                question:'',
                answer:'',
                item1:'',
                item2:'',
                item3:'',
                item4:'',
                explains:'',
                url:'',
            }
        },
        mounted(){
            this.getQuestion();
        },
        methods:{
            getQuestion(){
                this.$axios({
                    method: 'post',
                    url: main.url+'/question/selectById',
                    data: {
                        id:this.id,
                    }
                }).then((response) => {
                    //console.log(response);
                    //如果有的话，将数据添加到list中
                    if(response.data != null){
                        this.question = response.data.question;
                        this.answer = response.data.answer;
                        this.explains = response.data.explains;
                        this.url = response.data.url;
                        this.item1 = response.data.item1;
                        this.item2 = response.data.item2;
                        this.item3 = response.data.item3;
                        this.item4 = response.data.item4;
                        //alert("有数据")
                    }else {
                        alert("无数据")
                    }
                }).catch((error) => {
                    console.log("错误日志:" + error)
                });
            },
            updateQuestion(){
                //1.判空修改
                if(this.item1 === "" || this.item2 === "" || this.answer === ""){
                    alert("请将没有 * 标记的数据填写完整");
                }else {
                    //2.提交数据
                    this.$axios({
                        method: 'post',
                        url: main.url+'/question/UpdateQuestion',
                        data: {
                            id:this.id,
                            question:this.question,
                            answer:this.answer,
                            explains:this.explains,
                            url:this.url,
                            item1:this.item1,
                            item2:this.item2,
                            item3:this.item3,
                            item4:this.item4,
                        }
                    }).then((response) => {
                        //console.log(response);
                        //如果有的话，将数据添加到list中
                        if(response.data == 1){
                            alert("修改成功");
                            this.$router.push('/adminUpdateQuestion');
                            //alert("有数据")
                        }else {
                            alert("修改失败");
                        }
                    }).catch((error) => {
                        console.log("错误日志:" + error)
                    });
                }
            },
            deleteQuestion(){
                //alert("删除问题");
                this.$axios({
                    method: 'post',
                    url: main.url+'/question/deleteById',
                    data: {
                        id:this.id,
                    }
                }).then((response) => {
                    //console.log(response);
                    //如果有的话，将数据添加到list中
                    if(response.data == 1){
                        alert("删除成功");
                        this.$router.push('/adminUpdateQuestion');
                    }else if(response.data == 0){
                        alert("删除失败");
                    }else if(response.data == 2){
                        alert("该数据已不存在！！");
                    }
                }).catch((error) => {
                    console.log("错误日志:" + error)
                });
            }
        }
    }
</script>

<style scoped>
    .form-group{

    }
</style>