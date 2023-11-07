<template>
  <el-menu
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      :default-active="$route.path"
      background-color="#ffffff"
      text-color="#fff"
      active-text-color="#ffd04b">
    <!--      <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">-->
    <!--        {{ item.navItem }}-->
    <!--      </el-menu-item>-->

  </el-menu>
</template>

<script>
export default {
  name: 'TopMenu',
  data() {
    return {
      defaultPath: '/index',
      navList: [
        {name: '/index', navItem: '首页'},
        {name: '/manage', navItem: '管理'}
      ]
    };
  },
  mounted() {
    //this.setUrl()
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
      // if (this.$route.path !== key) {
      //   this.$router.push({path: key})
      // }
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
.el-menu.el-menu--horizontal {
  border-bottom: solid 0 #e6e6e6;
}
</style>
