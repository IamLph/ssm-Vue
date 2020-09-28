<template>
    <div id="con">
        <div style="text-align: center"><h4>前面标记有&nbsp;&nbsp;*&nbsp;&nbsp;的可以不填</h4></div>
        <div style="margin-right: 41%">
            <form class="form-horizontal" method="post" action="#" style="width: 98%;text-align: right">
                <div class="form-group">
                    <div class="col-sm-7">
                        问题内容:<input type="text" class="form-control" v-model="question" name="question" style="width: 200px">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-7">
                        选项一:<input type="text" class="form-control" v-model="item1" name="item1" style="width: 200px">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-7">
                        选项二:<input type="text" class="form-control" v-model="item2" name="item2" style="width: 200px">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-7">
                        *&nbsp;选项三:<input type="text" class="form-control" v-model="item3" name="item3" style="width: 200px">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-7">
                        *&nbsp;选项四:<input type="text" class="form-control" v-model="item4" name="item4" style="width: 200px">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-7">
                        答案:<input type="text" class="form-control" v-model="answer" name="answer" style="width: 200px">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-7">
                        *&nbsp;问题解析:<input type="text" class="form-control" v-model="explains" name="explains" style="width: 200px">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-7">
                        *&nbsp;图片地址URL:<input type="text" class="form-control" v-model="url" name="url" style="width: 200px">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-5 col-sm-6">
                        <button type="button" class="btn btn-default" @click="add" style="margin-right: 5%;width: 100px;color: red">添加</button>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
            </form>
        </div>
    </div>
</template>

<script>
    import main from "@/main";
    export default {
        name: "addQuestion",
        data:function () {
            return{
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
        methods:{
            add(){
                //1.判空处理
                if(this.question === "" || this.item1 === "" || this.item2 === "" || this.answer === "" || this.item3 === "" && this.item4 === ""){
                    alert("请填写完整数据！");
                }else if(this.item3 !== "" && this.item4 === "" || this.item3 === "" && this.item4 !== ""){
                    alert("请将选项三与选项四填写完整！");
                }else {
                    this.$axios({
                        method: 'post',
                        url: main.url+'/question/AddQuestion',
                        data: {
                            question:this.question,
                            answer:this.answer,
                            item1:this.item1,
                            item2:this.item2,
                            item3:this.item3,
                            item4:this.item4,
                            explains:this.explains,
                            url:this.url,
                        }
                    }).then((response) => {
                        //console.log(response);
                        //如果有的话，将数据添加到list中
                        if(response.data == 1){
                            alert("添加成功")
                            this.$router.push('/adminUpdateQuestion');
                            //alert("有数据")
                        }else {
                            alert("添加失败")
                        }
                    }).catch((error) => {
                        console.log("错误日志:" + error)
                    });
                }

            }
        }
    }
</script>

<style scoped>

</style>