<template>
  <body id="paper">
  <el-form class="login-container" label-position="left" label-width="0px" :loading="loading">
    <h3 class="login_title">用户注册</h3>
    <el-form-item>
      <el-input type="text" v-model="registerForm.username" placeholder="账号" autocomplete="new-password"></el-input>
    </el-form-item>
    <el-form-item>
      <el-input type="password" v-model="registerForm.password" autocomplete="new-password" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item>
      <el-input type="text" v-model="registerForm.mobile" autocomplete="new-password" placeholder="手机号"></el-input>
    </el-form-item>
    <el-form-item>
      <el-input type="text" v-model="registerForm.email" autocomplete="new-password" placeholder="邮箱"></el-input>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 40%;background: #505458;border: none" v-on:click="register">注册</el-button>
    </el-form-item>
  </el-form>
  </body>
</template>

<script>
export default {
  name: "Register",
  data() {
    return {
      checked: true,
      registerForm: {
        username: '',
        password: '',
        mobile: '',
        email: ''
      },
      loading: false
    }
  },
  methods: {
    register() {
      const _this = this;
      this.$post('/user/register', {
        username: this.registerForm.username,
        password: this.registerForm.password,
        mobile: this.registerForm.mobile,
        email: this.registerForm.email
      }).then(resp => {
        // if (resp.data.code === 200) {
        //   this.$alert('注册成功', '提示', {
        //     confirmButtonText: '确定'
        //   })
        //   _this.$router.replace('/login')
        // } else {
        //   this.$alert(resp.data.message, '提示', {
        //     confirmButtonText: '确定'
        //   })
        // }
      }).catch(failResponse => {
      })
    }
  }
}
</script>

<style scoped>
#paper {
  /*background:url("../assets/img/bg/eva1.jpg") no-repeat;*/
  /*background-position: center;*/
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}

body {
  margin: -5px 0px;
}

.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 90px auto;
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