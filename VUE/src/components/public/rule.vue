<template>
    <div class="rule">
        <h1>我要好好学习，天天向上</h1>
        <h2>认真考试</h2>
        <h2>坚决不违法</h2>
        <h2>做一个良好的公民QWQ</h2>
        <button>
            <router-link to="/register" class="agree">确认</router-link>
        </button>
    </div>
</template>

<script>
    export default {
        name: "rule"
    }
</script>

<style scoped>
    .rule{
        margin-top: 8%;
        text-align: center;
    }
    button{
        margin-top: 4%;
        background-color: #dbf3ed;
    }
    .agree{

        font-size: 20px;
        color: #ec667d;
    }
</style>