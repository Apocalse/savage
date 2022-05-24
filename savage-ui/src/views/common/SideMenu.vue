<template>
  <el-menu
      style="overflow: hidden; height: 100%"
      default-active="2"
      class="el-menu-vertical-demo"
      :default-active="defaultRouteId"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">
    <template v-for="(item, index) in menuList">
      <!-- 一级菜单 -->
      <template v-if="item.children.length===0">
        <el-menu-item :index="item.id" :key="index" @click="pushRoute(item)">
          <i :class="item.icon"></i>
          <span slot="title">{{ item.name }}</span>
        </el-menu-item>
      </template>
      <!-- 二级菜单 -->
      <template v-if="item.children.length!==0">
        <el-submenu :index="item.id" :key="index">
          <template slot="title">
            <i :class="item.icon"></i>
            <span slot="title">{{ item.name }}</span>
          </template>
          <template v-for="(subItem, idx) in item.children">
            <el-menu-item v-if="subItem.children.length===0" :index="subItem.id" :key="idx" @click="pushRoute(subItem)">
              {{ subItem.name }}
            </el-menu-item>
            <!--三级菜单-->
            <el-submenu v-if="subItem.children.length!==0" :index="subItem.id" :key="idx">
              <template slot="title">
                <span slot="title">{{ subItem.name }}</span>
              </template>
              <el-menu-item v-for="(threeItem, i) in subItem.children" :index="threeItem.id" :key="i" @click="pushRoute(threeItem)">
                {{ threeItem.name }}
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
  data() {
    return {
      defaultRouteId: '',
      menuList: [
        {
          id: '1',
          url: '/index',
          name: '测试',
          icon: 'el-icon-document',
          children: []
        },
        {
          id: '2',
          url: '',
          name: '测试',
          icon: 'el-icon-document',
          children: [
            {
              id: '3',
              url: '/manage',
              name: '测试',
              icon: 'el-icon-document',
              children: []
            }
          ]
        },
        {
          id: '4',
          url: '',
          name: '测试',
          icon: 'el-icon-document',
          children: [
            {
              id: '5',
              url: '',
              name: '测试',
              icon: 'el-icon-document',
              children: [
                {
                  id: '6',
                  url: '/index',
                  name: '测试',
                  icon: 'el-icon-document',
                  children: []
                }
              ]
            }
          ]
        }
      ]
    }
  },
  mounted() {
    this.setRouteId()
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
      console.log('路由跳转至 => ' + val.url)
      Vue.$cookies.set('routeId', val.id)
      if (this.$route.path !== val.url) {
        this.$router.push({path: val.url})
      }
    },
    setRouteId(){
      // 将当前路径写入cookies，确保页面刷新时可以重更新定位
      this.defaultRouteId = Vue.$cookies.get('routeId')
    }
  }
}
</script>

<style scoped>
</style>