import Vue from 'vue'
import VueRouter from 'vue-router'
import {getMenuList} from "@/api/sys";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        redirect: '/index'
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('../views/common/UserLogin.vue'),
        meta:{
            title:"登录"
        }
    },
    {
        path: '/register',
        name: 'register',
        component: () => import('../views/common/UserRegister.vue'),
        meta:{
            title:"注册"
        }
    },
    {
        path: '/404',
        name: '400',
        component: () => import('../views/common/UndefinedPage.vue')
    },
    {
        path: '/about',
        name: 'about',
        component: () => import('../views/AboutView.vue')
    },
    {
        path: '/main',
        name: 'Main',
        redirect: '/index',
        component: () => import('../views/common/MainContainer.vue')
    }
]

const router = new VueRouter({
    mode: 'history',
    base: '/savage/',
    // base: process.env.BASE_URL,
    routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
    // 根据路由元信息设置文档标题
    window.document.title = to.meta.title || 'savage'
    // to将要访问的路径 from从哪个路径跳转过来 next一个函数，表示放行
    if (to.path === '/login') return next()
    if (to.path === '/register') return next()
    // 获取token
    const tokenStr = Vue.$cookies.get('token')
    if (!tokenStr) return next('/login')
    // 获取路由信息
    let localRoutes = JSON.parse(localStorage.getItem('dynamicMenuRoutes'))
    try {
        if (localRoutes == null) {
            getMenuList({
                rootId: '0',
                status: '1,2'
            }).then(data => {
                localStorage.setItem("dynamicMenuRoutes", JSON.stringify(data))
                next({...to, replace: true})
            })
        } else {
            // routes为静态路由
            if (router.getRoutes().length <= routes.length) {
                let routeArr = addDynamicMenuRoutes(localRoutes, [])
                console.log(routeArr)
                for (let item of routeArr) {
                    router.addRoute('Main', {
                        path: item.path,
                        name: item.name,
                        component: loadPageByRoutes(item.component),
                        meta: item.meta
                    })
                }
                next({...to, replace: true})
            } else {
                if(to.matched.length === 0){
                    next({path: '/404', replace: true})
                }else{
                    next()
                }
            }
        }
    } catch (error) {
        console.log('出错了')
        next({...to})
    }

})

function loadPageByRoutes(str) {
    return function (resolve) {
        require([`@/views${str}`], resolve)
    };
}

// 用于处理动态菜单数据，将其转为 route 形式
export function addDynamicMenuRoutes(menuList = [], routes = []) {
    for (let i = 0; i < menuList.length; i++) {
        let menu = menuList[i]
        if (menuList[i].children && menuList[i].children.length > 0) {
            addDynamicMenuRoutes(menuList[i].children, routes)
        }
        if (menu.type === 1) {
            const route = {
                type: menu.type,
                // 路由的路径
                path: menu.url,
                // 路由名
                name: menu.name,
                // 路由所在组件
                component: menu.path,
                meta: {
                    id: menu.id,
                    icon: menu.icon,
                    title: menu.nameZh
                }
            }
            try {
                routes.push(route);
            } catch (e) {
                console.error('加载菜单失败');
            }
        }
    }
    return routes
}

export default router
