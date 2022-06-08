<template>
  <body id="poster">
  <el-form :rules="loginRule" ref="loginForm" :model="loginForm" class="login-container" label-position="left"
           label-width="0px">
    <h3 class="login_title">系统登录</h3>
    <el-form-item prop="username">
      <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input type="password" v-model="loginForm.password" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" style="width: 40%;background: #505458;border: none" @click="login">登录</el-button>
      <el-button type="primary" style="width: 40%;background: #505458;border: none" @click="resetForm">注册</el-button>
    </el-form-item>
  </el-form>
  </body>
</template>

<script>
import Vue from "vue";

export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      responseResult: [],
      loginRule: {
        username: [
          {required: true, message: '登录信息不能为空', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '密码不能为空', trigger: 'blur'}
        ]
      },
    }
  },
  methods: {
    login() {
      this.$refs['loginForm'].validate((valid) => {
        if (valid) {
          this.$post('/user/login', {
            username: this.loginForm.username,
            password: this.loginForm.password
          }).then(data => {
            // sessionStorage.setItem('token', data.token)
            // sessionStorage.setItem('username', data.username)
            // sessionStorage.setItem('userId', data.userId)
            Vue.$cookies.set('token', data.token)
            Vue.$cookies.set('username', data.username)
            Vue.$cookies.set('userId', data.userId)
            this.$router.push({path: '/index'})
          }).catch(err => {
            // this.$router.push({path: '/404'})
          })
        }
      })
    },

    resetForm() {
      this.$router.push({path: '/register'})
      // this.$refs['loginForm'].resetFields();
    }

  }

}
</script>

<style scoped>
#poster {
  background: url("../../../src/assets/mzl.png") no-repeat center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}

body {
  margin: 0;
}

.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 120px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.login_title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}
</style>