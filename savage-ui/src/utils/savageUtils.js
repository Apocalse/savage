const sysLogTypeMap = {
    0: '未知',
    1: '新增',
    2: '删除',
    3: '修改',
    4: '查询',
    5: '登录/登出',
}

const sysLogTypeList = [
    {
        key: 0,
        value: '未知'
    },
    {
        key: 1,
        value: '新增'
    },
    {
        key: 2,
        value: '删除'
    },
    {
        key: 3,
        value: '修改'
    },
    {
        key: 4,
        value: '查询'
    },
    {
        key: 5,
        value: '登录/登出'
    }
]

/**
 * 根据系统日志类型返回相关的的描述信息
 */
export function getSysLogTypeDesc(status) {
    return sysLogTypeMap[status]
}

export function getSysLogTypeMap() {
    return sysLogTypeMap
}

export function getSysLogTypeList() {
    return sysLogTypeList
}