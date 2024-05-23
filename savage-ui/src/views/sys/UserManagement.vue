<template>
<!--  <div class="index">-->
<!--    <h1>user</h1>-->
<!--  </div>-->
  <el-button type="primary" @click="loginOut">登出</el-button>
</template>

<script>
export default {
  name: 'UserManagement',
  data(){
    return{

    }
  },
  mounted() {
    this.loginOut()
  },
  methods: {
    loginOut(){
      this.$post('/loginOut').then(data =>{
        localStorage.removeItem('dynamicMenuRoutes')
        this.$router.push('login')
      })
    }
  }
}
</script>

<style scoped>

</style>

