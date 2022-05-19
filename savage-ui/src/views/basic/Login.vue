<template>
  <div>
    用户名:<input type="text" v-model="loginForm.username" placeholder="请输入用户名"/>
    <br><br>
    密码： <input type="password" v-model="loginForm.password" placeholder="请输入密码"/>
    <br><br>
    <button @click="login">登录</button>
  </div>
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
      responseResult: []
    }
  },
  methods: {
    login() {
      this.$post('/login', {
        username: this.loginForm.username,
        password: this.loginForm.password
      }).then(res => {
        sessionStorage.setItem("token", '123')
        Vue.$cookies.set('token', '123456')
        this.$router.push({path: '/about'})
      }).catch(err => {
        //window.SITE_CONFIG.baseUrl
      })
    }
  }

}
</script>

<style scoped>

</style>