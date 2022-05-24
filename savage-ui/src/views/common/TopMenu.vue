<template>

  <el-menu
        :default-active="defaultPath"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b">
      <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
        {{item.navItem}}
      </el-menu-item>
      <!--    <el-submenu index="2">-->
      <!--      <template slot="title">我的工作台</template>-->
      <!--      <el-menu-item index="2-1">选项1</el-menu-item>-->
      <!--      <el-menu-item index="2-2">选项2</el-menu-item>-->
      <!--      <el-menu-item index="2-3">选项3</el-menu-item>-->
      <!--      <el-submenu index="2-4">-->
      <!--        <template slot="title">选项4</template>-->
      <!--        <el-menu-item index="2-4-1">选项1</el-menu-item>-->
      <!--        <el-menu-item index="2-4-2">选项2</el-menu-item>-->
      <!--        <el-menu-item index="2-4-3">选项3</el-menu-item>-->
      <!--      </el-submenu>-->
      <!--    </el-submenu>-->
      <!--    <el-menu-item index="3" disabled>消息中心</el-menu-item>-->
      <!--    <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">跳转</a></el-menu-item>-->
    </el-menu>

</template>

<script>
export default {
  name: 'NavMenu',
  data() {
    return {
      defaultPath: '/index',
      navList: [
        {name: '/index', navItem: '首页'},
        {name: '/manage', navItem: '管理'}
        // {name: '/admin', navItem: '跳转'}
      ]
    };
  },
  mounted() {
    this.setUrl()
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
      if (this.$route.path !== key) {
        this.$router.push({path: key})
      }
    },
    setUrl(){
      this.defaultPath = this.$route.path
    }
  }
}
</script>

<style scoped>
/*.el-menu{*/
/*  !*margin-left: -20px;*!*/
/*  !*margin-right: -20px;*!*/
/*}*/
/*a{*/
/*  text-decoration: none;*/
/*}*/

/*span {*/
/*  pointer-events: none;*/
/*}*/
</style>
