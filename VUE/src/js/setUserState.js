import main from "@/main";
function LoginState(){
    this.$axios({
        method: 'post',
        url: main.url+'/student/setUserState',
        data: {
            id:sessionStorage['userId'],
            state:1,
        }
        // eslint-disable-next-line no-unused-vars
    }).then((response) => {
        //alert("提交成功,将回到首页！");
    }).catch((error) => {
        //返回首页。
        console.log("错误日志:" + error)
    });
}
function LeaveState() {
    this.$axios({
        method: 'post',
        url: main.url+'/student/setUserState',
        data: {
            id:sessionStorage['userId'],
            state:0,
        }
        // eslint-disable-next-line no-unused-vars
    }).then((response) => {
        //alert("提交成功,将回到首页！");
    }).catch((error) => {
        //返回首页。
        console.log("错误日志:" + error)
    });
}
function ExamState() {
    this.$axios({
        method: 'post',
        url: main.url+'/student/setUserState',
        data: {
            id:sessionStorage['userId'],
            state:2,
        }
        // eslint-disable-next-line no-unused-vars
    }).then((response) => {
        //alert("提交成功,将回到首页！");
        alert("开始考试了！！");
    }).catch((error) => {
        alert("没有进入考试状态！");
        //返回首页。
        this.$router.push('/');
        console.log("错误日志:" + error)
    });
}
export {LoginState,LeaveState,ExamState}