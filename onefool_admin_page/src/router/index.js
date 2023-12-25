import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'

//配置路由规则 ‘const 常量名字’
const constRouter = [
  //重定向,如果什么都不写就重定向到这个登录页面
  {
    path: '',
    redirect: "/login"
  },
  {
    path: '/login',
    name: 'login',
    //跟上面的Loing对应，找到对应行的右边Login.vue地址，存在容器里面
    component: Login
  }
]


//创建路由
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  //所有的路由规则   使用路由规则常量
  routes: constRouter
})
//导出router 
export default router
