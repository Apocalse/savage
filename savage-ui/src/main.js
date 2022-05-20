import Vue from 'vue'
import App from './App.vue'
import router from './router'

Vue.config.productionTip = false

// import axios from "axios";
// // 全局注册，之后可在其他组件中通过 this.$axios 发送数据
// Vue.prototype.$axios = axios
// axios.defaults.baseURL = 'http://localhost:8823/kaltsit'

// 全局挂载
import { get, post } from './utils/axios'
Vue.prototype.$get = get; // http.get
Vue.prototype.$post = post; // http.post

import VueCookies from "vue-cookies";
Vue.use(VueCookies);

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI);

window.SITE_CONFIG = {};
window.SITE_CONFIG['baseUrl'] = "http://localhost:8823/kaltsit";

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
