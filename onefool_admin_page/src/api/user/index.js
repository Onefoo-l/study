//请求接口
import request from '@/utils/request.js'

//动态路由接口
export function searchSelf(){

    return request({
        url: '/sys/menu/self',
        method:"GET",
    })
}