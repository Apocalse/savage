import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        redirect: '/login'
    },

    {
        path: '/container',
        name: 'Container',
        component: () => import('../views/common/Container.vue'),
        redirect: '/index',
        children: [
            {
                path: '/index',
                name: 'Index',
                component: () => import('../views/lava/Index.vue'),
                meta: {
                    requireAuth: true
                }
            },
            {
                path: '/manage',
                name: 'Manage',
                component: () => import('../views/lava/Manage.vue'),
                meta: {
                    requireAuth: true
                }
            }
        ]
    },

    {
        path: '/about',
        name: 'about',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import('../views/AboutView.vue')
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('../views/common/Login.vue')
    },
    {
        path: '/register',
        name: 'register',
        component: () => import('../views/common/Register.vue')
    },
    {
        path: '/404',
        name: '400',
        component: () => import('../views/common/404.vue')
    }
]

const router = new VueRouter({
    mode: 'history',
    base: '/savage/',
    // base: process.env.BASE_URL,
    routes
})

// 判断是否存在token，如果没有则跳转至登陆页面
router.beforeEach((to, from, next) => {
    // to 将要访问的路径  from 从哪个路径跳转过来  next 一个函数，表示放行
    if (to.path === '/login') return next()
    if (to.path === '/register') return next()
    // 获取token
    // const usernameStr = sessionStorage.getItem('username')
    const tokenStr = Vue.$cookies.get('token')
    if (!tokenStr) return next('/login')
    next()
})

export default router
