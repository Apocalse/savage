import Vue from 'vue'
import App from '@/App.vue'
import router from './router'
import store from './store'
import './assets/icons/iconfont.css'
import {get, post} from './utils/axios'
import VueCookies from "vue-cookies"
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

Vue.config.productionTip = false

// import axios from "axios";
// // 全局注册，之后可在其他组件中通过 this.$axios 发送数据
// Vue.prototype.$axios = axios
// axios.defaults.baseURL = 'http://localhost:8823/kaltsit'

// 全局挂载
// axios
Vue.prototype.$get = get; // http.get
Vue.prototype.$post = post; // http.post
// cookie
Vue.use(VueCookies);
// element
Vue.use(ElementUI);

// 全局注册组件
import SavageTable from '@/views/common/models/SavageTable.vue'
Vue.component('SavageTable', SavageTable)

// window.SITE_CONFIG = {};
// window.SITE_CONFIG['baseUrl'] = "http://localhost:8823/kaltsit";

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
