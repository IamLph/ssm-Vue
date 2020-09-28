<template>
    <nav class="mui-bar mui-bar-tab" id="fot">
        <img src="../../images/logo.jpg" alt="未找到图片" id="img">
        <div class="info">
            <span>愤怒的绿猪驾校有限公司</span>
            <br/>
            <span>电话:110-110-119</span>
            <br/>
            <span>地址:中国--河南</span>
            <div><a href="http://www.beian.miit.gov.cn/" style="text-decoration : none;color: black">豫ICP备20022705号-1</a></div>
        </div>
    </nav>
</template>

<script>
    export default {
        name: "foot"
    }
</script>

<style scoped>
    #fot{
        width: 100%;
        height: 10%;
        display:flex;
        background-color: rgba(77, 65, 85, 0.19)
    }
    #img{
        height: 100%;
        width: auto;
        margin-left: 38%
    }
    .info{
        display: block;
        word-break:keep-all;
        white-space:nowrap;
        margin-top: 0;
        margin-left: 5%;
    }
</style>