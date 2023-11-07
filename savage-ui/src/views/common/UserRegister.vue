<template>
  <body id="paper">
  <el-form class="register-container" label-position="left" label-width="0px" ref="registerForm"
           :loading="loading" :rules="dataRule" :model="registerForm">
    <h3 class="register-title">用户注册</h3>
    <el-form-item prop="username">
      <el-input type="text" v-model="registerForm.username" autocomplete="new-password" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input type="password" v-model="registerForm.password" autocomplete="new-password"
                placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item prop="mobile">
      <el-input type="text" v-model="registerForm.mobile" autocomplete="new-password" placeholder="手机号"></el-input>
    </el-form-item>
    <el-form-item prop="email">
      <el-input type="text" v-model="registerForm.email" autocomplete="new-password"
                placeholder="邮箱(非必填)"></el-input>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 40%;background: #505458;border: none" @click="register">注册</el-button>
    </el-form-item>
  </el-form>
  </body>
</template>

<script>
export default {
  name: "UserRegister",
  data() {
    let checkUsername = (rule, value, callback) => {
      this.$get('/user/isExit', {
        username: value,
      }).then(data => {
        if (data === true) {
          callback(new Error('该用户已存在'))
        } else {
          callback()
        }
      }).catch(err => {
        callback(new Error('未知异常'))
      })
    };

    return {
      checked: true,
      registerForm: {
        username: '',
        password: '',
        mobile: '',
        email: ''
      },
      loading: false,
      dataRule: {
        username: [
          {required: true, message: '用户名不能为空', trigger: 'blur'},
          {validator: checkUsername, trigger: 'blur'}
        ],
        password: [
          {required: true, message: '密码不能为空', trigger: 'blur'}
        ],
        mobile: [
          {required: true, message: '手机号不能为空', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    register() {
      this.$refs['registerForm'].validate((valid) => {
        if (valid) {
          this.$post('/user/register', {
            username: this.registerForm.username,
            password: this.registerForm.password,
            mobile: this.registerForm.mobile,
            email: this.registerForm.email
          }).then(resp => {
            this.$alert('注册成功', '提示', {
              confirmButtonText: '确定'
            })
            this.$router.push({path: '/login'})
          }).catch(err => {
          })
        }
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
  margin: -5px 0;
}

.register-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 90px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
  text-align: center;
}

.register-title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}
</style>
