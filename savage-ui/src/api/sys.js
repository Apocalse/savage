import {get, post} from '@/utils/axios'

export function getMenuList(params){
    return get( '/menu/list', params)
}

export function getUserList(params={}){
    return get( '/user/list', params)
}
