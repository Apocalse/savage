import {request} from '@/utils/axios'

export function sysLog_pageList(params){
    return request( 'get', '/sysLog/pageList', params)
}
