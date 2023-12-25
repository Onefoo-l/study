<template>
    <!-- 根div -->
    <div class="login_container">
        <!-- 登陆表单 -->
        <div class="login_form">
            <h3 class="title">Onefool平台</h3>
            <!-- ref是引用  model是创建绑定 -->
            <el-form ref="formRef" :model="loginForm" label-width="auto">
                <!--用户名  -->
                <el-form-item label="用户名">
                    <el-input v-model="loginForm.account" placeholder="请输入账号">
                        <template #prefix>
                            <el-icon class="el-input__icon"><User /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <!-- 密码 -->
                <el-form-item label="密码">
                    <el-input v-model="loginForm.password" type="password" placeholder="请输入密码" >
                        <template #prefix>
                            <el-icon class="el-input__icon"><Lock /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <!-- 记住我和忘记密码 -->
            <div class="rememberMe">
                    <el-checkbox v-model="loginForm.rememberMe" label="记住我" size="large">
                    </el-checkbox>
                    <!-- 忘记密码？ -->
                    <el-text class="forgetpass" type="primary">忘记密码</el-text>
                </div>
                <!-- 分割线 -->
                <el-divider>
                    其他登录方式
                </el-divider>
                <!-- 其他的登录方式 -->
                <div class="other_login">
                    <el-icon class="other_login_item"><ChromeFilled /></el-icon>
                    <el-icon class="other_login_item"><SwitchFilled /></el-icon> 
                    <el-icon class="other_login_item"><ElementPlus /></el-icon>
                </div>
                <el-form-item>
                       <!-- 按钮 -->
            <el-button style="width:100%" type="primary" @click="handleLogin">登陆</el-button>
                </el-form-item>
            </el-form>
         
        </div>
    </div>
</template>

<script setup>
//导入 ref
import { ref } from 'vue'
//导入login方法
import {login} from '@/api/auth/index.js'
//声明表单绑定值
const loginForm = ref({
    account: undefined,
    password: undefined,
    rememberMe: undefined
})

//声明方法
function handleLogin(){
    //调用login方法
    login(this.loginForm).then((res) => {
        console.log("登录====>",res);
        //判断是否成功
        if(res.data.code == 20000){
            //将token存储到redis中
            console.log("登录成功!");
        }
    })
};
</script>

<style lang="scss" scoped>
.login_container{
    //背景图
    background-image: url('../assets/bgimg/1.jpg');
    background-size: 100%;
    height: 100vh;
    display: flex;
    justify-content: flex-end;
    .login_form{
        display: flex;
        justify-content: center;
        align-items: center;
        //设置换行
        flex-direction: column;
        width: 50%;
        background-color: white;
        .title{
            margin-bottom: 20px;
        }
        .rememberMe{
            display: flex;
            justify-content: space-between;
            align-items: center;
            .forgetpass{
                cursor: pointer;
            }
        }
        .other_login{
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
            .other_login_item{
                margin-right: 60px;
                cursor: pointer;
            }
        }
    }
}

.el-form{
    width: 60%;
}

.el-form-item{
    width: 100%;
}

</style>