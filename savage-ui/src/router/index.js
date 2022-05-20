import Vue from 'vue'
import VueRouter from 'vue-router'
// import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import(/* webpackChunkName: "about" */ '../views/basic/Login.vue')
  },
  {
    path: '/404',
    name: '400',
    component: () => import(/* webpackChunkName: "about" */ '../views/basic/404.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 判断是否存在token，如果没有则跳转至登陆页面
// router.beforeEach((to, from, next) => {
//   // to 将要访问的路径  from 从哪个路径跳转过来  next 一个函数，表示放行
//   if (to.path === '/login') return next()
//   // 获取token
//   // const tokenStr = sessionStorage.getItem('token')
//   const tokenStr = Vue.$cookies.get('token')
//   if (!tokenStr) return next('/login')
//   next()
// })

export default router
