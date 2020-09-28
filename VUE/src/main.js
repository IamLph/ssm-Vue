import Vue from 'vue'
import App from './App.vue'

Vue.config.productionTip = false
//导入路由的包
import VueRouter from "vue-router";
//导入axios的包
import axios from 'axios'

//导入qs的包
import qs from 'qs.js'
//导入时间插件
import moment from 'moment'
//导入vue-resource
import VueResource from 'vue-resource'
//导入MUI的样式
import './lib/mui/css/mui.min.css'
//导入mui的图标样式 购物车
import './lib/mui/css/icons-extra.css'
//安装图片预览插件
import VuePreview from 'vue-preview'
//安装需要导入需要的组件
import { Header,Swipe,SwipeItem,Button,Lazyload,Switch} from 'mint-ui'
//导入vuex包
import Vuex from 'vuex'
//导入路由
import router from "./router";
//导入getter.js包
import * as getters from './vuex/gettters';
import * as mutations from './vuex/mutations';
import {LeaveState,LoginState,ExamState} from './js/setUserState';
Vue.prototype.$LoginState = LoginState;
Vue.prototype.$LeaveState = LeaveState;
Vue.prototype.$ExamState = ExamState;

Vue.prototype.$router = router;
Vue.prototype.$axios = axios; //全局注册，使用方法为:this.$axios
Vue.prototype.qs = qs;           //全局注册，使用方法为:this.qs
//安装路由
Vue.use(VueRouter);
//2.2.安装vue-resource
Vue.use(VueResource);
Vue.config.productionTip = false;
Vue.use(VuePreview);
//图片懒加载
Vue.use(Lazyload);
//使用Vuex
Vue.use(Vuex);
//定义全局的过滤器
Vue.filter('dateFormat',function (dataStr,pattern="YYYY-MM-DD HH:mm:ss") {
  return moment(dataStr).format(pattern)
});
Vue.component(Header.name,Header);
Vue.component(Swipe.name, Swipe);
Vue.component(SwipeItem.name, SwipeItem);
Vue.component(Button.name,Button );
Vue.component(Switch.name,Switch );
//创建vue仓库
const store = new Vuex.Store({
  state:{
    loginType:false,
    logName:'',
    token:'',
    userId:'',
    userType:null,
  },
  getters,
  mutations,
});
Vue.prototype.$store = store;
new Vue({
  render: h => h(App),
  router,
  store,//挂载store状态管理对象
}).$mount('#app');

export default {
  //定义后端地址
  url:'http://39.99.226.148:8080'
};