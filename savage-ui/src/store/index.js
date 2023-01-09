import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    //数据，相当于data
    state: {
        dynamicMenuRoutes: [],
        name: ''
    },
    getters: {},
    //里面定义方法，操作state方发
    mutations: {
        setDynamicMenuRoutes(state, dynamicMenuRoutes) {
            state.dynamicMenuRoutes = dynamicMenuRoutes
        },
        clearDynamicMenuRoutes(state) {
            state.dynamicMenuRoutes = []
        },
        setName(state, str){
            state.name = str
        },
        clearName(state){
            state.name = ''
        }
    },
    // 操作异步操作mutation
    actions: {},
    modules: {},
})