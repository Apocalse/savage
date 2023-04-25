import axios from 'axios'
import Vue from 'vue'
import router from '@/router'
import { Notification } from 'element-ui';

const httpCustom = axios.create({
    timeout: 1000 * 30,
    // withCredentials: true,
    // baseURL: 'http://localhost:8823/kaltsit',
    baseURL: '/api',
    headers: {
        'Content-Type': 'application/json; charset=utf-8'
    }
});

/**
 * 封装的异常对象
 * @param message
 * @param code
 * @constructor
 */
function SavageException(message, code) {
    this.message = message;
    this.code = code;
}

/**
 * 请求拦截
 */
httpCustom.interceptors.request.use(
    config => {
        config.headers['token'] = Vue.$cookies.get('token'); // 请求头带上token
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
        if (response.data && response.data.code !== 200) {
            console.log(`信息`, response);
            if (response.data.code === 400 || response.data.code === 401) {
                localStorage.removeItem("dynamicMenuRoutes")
                router.push('/login').then((r) => {})
                Notification.error({
                    title: '登录过期',
                    message: '登录过期，请重新登录',
                    duration: 3000
                });
            }else{
                // 错误信息统一在这里处理、页面代码只需要关系正常情况即可
                Vue.prototype.$message.error(response.data.msg);
                throw new SavageException(response.data.msg, response.data.code);
            }
        } else {
            response.data = response.data.data;
            return response;
        }
    },
    error => {
        if (error.message && error.message === 'Network Error') {
            console.log(`错误`, error);
            Vue.prototype.$message.error('无法访问');
            return Promise.reject(error);
        }
        return Promise.reject(error);
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


