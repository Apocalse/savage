<template>
  <el-menu
      style="overflow: hidden; height: 100%"
      class="el-menu-vertical-demo"
      :default-active="defaultRoute"
      background-color="#262626"
      text-color="#fff">
    <template v-for="(item, index) in menuList">
      <!-- 一级菜单 -->
      <template v-if="item.children.length===0">
        <el-menu-item :index="item.url" :key="index" @click="pushRoute(item)">
          <i :class="item.icon"></i>
          <span slot="title" style="font-size: 16px">{{ item.nameZh }}</span>
        </el-menu-item>
      </template>
      <!-- 二级菜单 -->
      <template v-if="item.children.length!==0">
        <el-submenu :index="item.url" :key="index" >
          <template slot="title">
<!--            <i :class="item.icon"></i>-->
            <span slot="title" style="font-size: 16px">{{ item.nameZh }}</span>
          </template>
          <template v-for="(subItem, idx) in item.children">
            <el-menu-item v-if="subItem.children.length===0" :index="subItem.url" :key="idx" @click="pushRoute(subItem)">
              <i :class="subItem.icon"></i>
              <span slot="title">{{ subItem.nameZh }}</span>
            </el-menu-item>
            <!--三级菜单-->
            <el-submenu v-if="subItem.children.length!==0" :index="subItem.url" :key="idx">
              <template slot="title">
                <span slot="title">{{ subItem.nameZh }}</span>
              </template>
              <el-menu-item v-for="(threeItem, i) in subItem.children" :index="threeItem.url" :key="i" @click="pushRoute(threeItem)">
                {{ threeItem.nameZh }}
              </el-menu-item>
            </el-submenu>
          </template>
        </el-submenu>
      </template>
    </template>

  </el-menu>
</template>

<script>
import Vue from "vue";

export default {
  name: 'SideMenu',
  inject: ['reload'],
  data() {
    return {
      defaultRoute: '',
      menuList: []
    }
  },
  mounted() {
    this.setRouteId()
    this.getMenuList()
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    pushRoute(val){
      if(val.path == null){
        return
      }
      //TODO
      if (this.$route.path !== val.url) {
        console.log('路由跳转至 => ' + val.url)
        this.$router.push({path: val.url})
      }else {
        console.log('刷新当前路由')
        this.reload()
      }
    },
    setRouteId(){
      // 确保页面刷新时可以重更新定位
      this.defaultRoute = this.$route.path
    },
    getMenuList(){
      this.$get('/menu/list', {
        id: '0'
      }).then(data => {
        console.log(data)
        this.menuList = data
      }).catch(err =>{

      })
    }
  }
}
</script>

<style scoped>
</style>