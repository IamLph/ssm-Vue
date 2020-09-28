<template>
    <div id="container" v-on:mouseleave="changeMouse">
        <div style="text-align: center;font-size: 30px;margin-top: 10%" v-show="!showTable">
            题库正在维护，没有数据，请耐心等待！
        </div>

        <div id="container_middle" v-if="showTable">
            <div style="font-size: 20px" class="setTime">
                考试剩余时间:
                <div v-text="toLiveBtn" style="font-size: 25px;margin-left: 0;display: inline"></div>
            </div>
            <div id="middle_left">
                <table align="center" cellspacing="0" cellpadding="0">
                    <tr style="height: 100px">
                        <td style="padding: 0">
                            <label style="height: 100%">
                                <textarea rows="3" style="font-size: 20px;margin:0;border: 0" v-model="titleQuestion" disabled/>
                            </label>
                        </td>
                    </tr>

                    <tr style="height: 150px" v-if="this.list[titleNum].url">
                        <td>
                            <div style="text-align: center;">
                                <img v-bind:src="this.list[titleNum].url"  alt="没有发现图片">
                            </div>
                        </td>
                    </tr>

                    <tr style="height: 60px">
                        <td>
                            <div style="margin-left: 5%;margin-bottom: 0">
                                <label>
                                    <input type="radio" :value=1 v-model="checkedRadioValue">
                                    A:{{this.list[titleNum].item1}}
                                </label>
                                <br/>
                                <label>
                                    <input type="radio" :value=2 v-model="checkedRadioValue">
                                    B:{{this.list[titleNum].item2}}
                                </label>

                                <label v-if="this.list[titleNum].item3">
                                    <br/>
                                    <input type="radio" :value=3 v-model="checkedRadioValue">
                                    C:{{this.list[titleNum].item3}}
                                </label>
                                <br/>
                                <label v-if="this.list[titleNum].item4">
                                    <input type="radio" :value=4 v-model="checkedRadioValue">
                                    D:{{this.list[titleNum].item4}}
                                </label>
                            </div>
                        </td>
                    </tr>

                    <tr style="margin-bottom: 0;" v-if="getAllAnswer">
                        <td>
                            <div style="">
                                <div style="color: red;">
                                    <h4 style="color: #8a6d3b">
                                        本题选择第{{this.list[titleNum].answer}}个
                                    </h4>

                                </div>
                                <label style="height: 100%">
                                    <textarea rows="5" style="width: 100%;border: 0;" v-model="this.list[titleNum].explains" disabled></textarea>
                                </label>
                            </div>
                        </td>
                    </tr>

                </table>
            </div>

            <table id="middle_right" align="center" cellspacing="0" cellpadding="0">
                <tr v-for="i in 10" :key="i" style="border: 1px solid black;text-align: center">
                    <td v-for="j in 10" :key="j" style="border: 1px solid black;width: 39px;height: 39px" >
                        <p class="testId" style="border: 0;margin: 0;width: 100%;height:100%;text-align: center" @click="toQuestion((i-1)*10+j)" ref="listP">
                            {{(i-1)*10+j}}
                        </p>
                    </td>
                </tr>
            </table>
        </div>

        <br/>
        <div id="exam_bottom" v-show="showTable">
                <button @click="up()">上一题</button>
                <button style="margin-left: 3%" @click="submitTest()">提交</button>
                <button style="margin-left: 3%" @click="next()">下一题</button>
        </div>
    </div>
</template>

<script>
    import main from "@/main";
    export default {
        name: "goExam",
        data:function () {
            return{
                userId:sessionStorage['userId'],
                countTime: 3600,
                interval: '',
                toLiveBtn: '计时器',
                showTable:false,//展示表格
                checkList:[],//勾选数组
                list:[],//问题数组
                titleNum:0,//问题编号默认
                getAllAnswer:false,//是否暴露答案
                titleQuestion:'',//问题内容的拼接
                checkedRadioValue:'',//单选按钮选中的哪一个
                scoreNum:0,//总分
                scoreList:[],//题目分数列表
                weifaOP:0,//违法操作
            }
        },
        watch:{
            checkedRadioValue:function (newVal) {
                //检查是否勾选,勾选改变框的颜色
                if(newVal !== ''){
                    //设置当前选中的按钮的值，存放到数组中
                    //最开始的num是0开始的,第0个是空,代表第一个
                    this.checkList[this.titleNum] = newVal;
                    //this.style.disabled;

                    //改变选中的框样式
                    this.$refs.listP[this.titleNum].style.backgroundColor = "#666666";

                    //判断勾选的内容是否与答案一致
                    if(newVal == this.list[this.titleNum].answer){
                        //alert("正确");
                        this.scoreList[this.titleNum] = 1;
                    }else {
                        //alert("错误");
                        this.scoreList[this.titleNum] = 0;
                    }
                }
            },
        },
        mounted(){
            this.$ExamState();
            this.getExam();
            this.countdown();
        },
        methods:{
            //设置考试状态了
            getExam(){
                //发送请求，获取考试信息
                this.$axios({
                    method: 'post',
                    url: main.url+'/question/getQuestion',
                    data: {}
                }).then((response) => {
                    //如果有的话，将数据添加到list中
                    if(response.data != null){
                        this.list = response.data;
                        this.showTable = true;
                        this.titleQuestion = 1+":"+this.list[0].question
                    }
                }).catch((error) => {
                    console.log("错误日志:" + error)
                });
            },
            countdown() {
                this.interval = setInterval(() => {
                    if(this.countTime === 0) {
                        // 计时结束，清除缓存
                        clearInterval(this.interval);
                        //计时结束自动提交答题卡
                        this.submitTest();
                    } else {
                        this.countTime--;
                        let min = parseInt(this.countTime / 60 % 60);
                        let sec = parseInt(this.countTime % 60);
                        min = min > 9 ? min : '0' + min;
                        sec = sec > 9 ? sec : '0' + sec;
                        this.toLiveBtn = `${min}:${sec}`
                    }
                }, 1000);
            },
            up(){
                if(this.titleNum <= 0){
                    alert("这是第一题了")
                }else {
                    //判断上一个问题是否选过
                    if(this.checkList[this.titleNum-1]){
                        this.checkedRadioValue = this.checkList[this.titleNum-1];
                    }else {
                        this.checkedRadioValue = '';
                    }
                    //进入上一题
                    this.titleNum--;
                    //渲染题号
                    this.titleQuestion = (this.titleNum+1)+":"+this.list[this.titleNum].question
                }
            },
            next(){
                if(this.titleNum >= 99){
                    alert("这已经是最后一题了");
                }else {
                    //判断下一个问题是否选过
                    if(this.checkList[this.titleNum+1]){
                        this.checkedRadioValue = this.checkList[this.titleNum+1];
                    }else {
                        this.checkedRadioValue = '';
                    }
                    this.titleNum++;
                    //渲染题号
                    this.titleQuestion = (this.titleNum+1)+":"+this.list[this.titleNum].question;
                }
            },
            toQuestion(num){
                //判断跳转的问题是否选过
                if(this.checkList[num-1]){
                    this.checkedRadioValue = this.checkList[num-1];
                }else {
                    this.checkedRadioValue = '';
                }
                //alert(num);
                this.titleNum = num-1;
                this.titleQuestion = (this.titleNum+1)+":"+this.list[this.titleNum].question;
            },
            changeMouse(){
                alert("请回到考试界面！！");
                this.weifaOP ++;
            },
            submitTest(){
                for(let i = 0;i<this.scoreList.length;i++){
                    this.scoreNum += this.scoreList[i];
                }
                //添加考试数据了
                this.$axios({
                    method: 'post',
                    url: main.url+'/grade/addGrade',
                    data: {
                        userId:this.userId,
                        core:this.scoreNum+"",
                        weifaOP:this.weifaOP,
                    }
                    // eslint-disable-next-line no-unused-vars
                }).then((response) => {
                    alert("提交成功,将回到首页！");
                    //清空计时器
                    clearInterval(this.interval);
                    this.toLiveBtn = "00:00";
                    //返回首页。
                    this.$router.push('/');
                }).catch((error) => {
                    console.log("错误日志:" + error)
                });
            },

        },
        beforeDestroy(){
            clearInterval(this.interval);
        }
    }
</script>

<style scoped>
    .testId{
        width: 20px;
        height: 20px;
        border: 1px solid red;
    }
    #container{
        word-break:keep-all;
        white-space:nowrap;
        width: 100%;
        margin-top: 0;
    }
    #container_middle{
        display: inline-block;
        width: 100%;
        margin-top: 1%;
    }
    .setTime{
        margin-left: 34%;
    }
    #middle_left{
        display: inline-block;
        white-space:nowrap;
        background-color: white;
        margin-left: 15%;
        margin-top: 2%;
        max-width:820px;
        min-width: 820px;
        height: 495px;
        overflow:hidden;
        border:1px solid #999999;
    }
    #middle_right{
        display: inline-block;
        margin-left: 7%;
        margin-bottom: 7%;
        width: 400px;
        height: 400px;
    }
    #exam_bottom{
        margin-left: 32%;
    }
    img{
        width: auto;
        height: 150px;
        margin: 0;
    }
    table{
        margin: 0;
        width: 100%;
        height: 495px;
    }
    td{
        border: 1px solid green;
    }
</style>