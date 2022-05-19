// //在官方的axios的基础上封装一个添加拦截器的axios
// import axios from 'axios'
//
// //配置默认的路由地址头
// axios.defaults.baseURL = 'http://localhost:8823/kaltsit'
// //全局添加拦截器的作用是可以在每个api前面加上headers的token验证
// axios.interceptors.request.use(config => {
//     // 判断token是否存在和是否需要token验证的路由
//     if (sessionStorage.getItem('token')) {
//         config.headers.Authorization = sessionStorage.getItem('token');
//     }
//     return config;
// })
// export default axios

import axios from "axios"
import Vue from "vue";

/**
 * 统一进行异常输出
 * 如果异常只是弹框显示即可，可使用该实例
 */
const httpCustom = axios.create({
    timeout: 1000 * 30,
    // withCredentials: true,
    // baseURL: 'http://localhost:8823/kaltsit',
    baseURL: '/api',
    headers: {
        "Content-Type": "application/json; charset=utf-8"
    }
});


/**
 * 封装的异常对象
 * @param message
 * @param code
 * @constructor
 */
function EipException(message, code) {
    this.message = message;
    this.code = code;
}

/**
 * 请求拦截
 */
httpCustom.interceptors.request.use(
    config => {
        // config.headers["token"] = Vue.$cookies.get("token"); // 请求头带上token
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);

/**
 * 响应拦截
 */
httpCustom.interceptors.response.use(
    response => {
        let res = response.data;
        // 如果是返回的文件
        if (response.config.responseType === 'blob') {
            return res
        }
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        return res;
    },
    error => {
        console.log('err' + error) // for debug
        return Promise.reject(error)
    }
);


/**
 * get请求
 * @param url
 * @param params
 * @returns {Promise<any>}
 */
export function get(url, params = {}) {
    return new Promise((resolve, reject) => {
        httpCustom.get(url, {
            params: params
        }).then(response => {
            resolve(response.data);
        }).catch(err => {
            reject(err);
        });
    });
}

/**
 * post 请求
 * @param url
 * @param data
 * @returns {Promise<any>}
 */
export function post(url, data = {}) {
    data = JSON.stringify(data);
    return new Promise((resolve, reject) => {
        httpCustom.post(url, data).then(response => {
            resolve(response.data);
        }).catch(err => {
            reject(err);
        });
    });
}

/**
 * delete 请求
 * @param url
 * @param data
 * @returns {Promise<any>}
 */
export function del(url, data = {}) {
    data = JSON.stringify(data);
    return new Promise((resolve, reject) => {
        httpCustom.delete(url, data).then(response => {
            resolve(response.data);
        }).catch(err => {
            reject(err);
        });
    });
}


