<template>
  <div class="header">
    <a href="/">
      <img src="~@/assets/lxh-logo.png" class="logo" alt="logo">
      <span class="title">Kal'tsit</span>
    </a>
  </div>
  <div>
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
            <span slot="title" style="font-size: 14px">{{ item.nameZh }}</span>
          </el-menu-item>
        </template>
        <!-- 二级菜单 -->
        <template v-if="item.children.length!==0">
          <el-submenu :index="item.url" :key="index">
            <template>
              <!--            <i :class="item.icon"></i>-->
              <span slot="title" style="font-size: 14px">{{ item.nameZh }}</span>
            </template>
            <template v-for="(subItem, idx) in item.children">
              <el-menu-item v-if="subItem.children.length===0" :index="subItem.url" :key="idx"
                            @click="pushRoute(subItem)">
                <i :class="subItem.icon"></i>
                <span slot="title" style="font-size: 12px">{{ subItem.nameZh }}</span>
              </el-menu-item>
              <!--三级菜单-->
              <el-submenu v-if="subItem.children.length!==0" :index="subItem.url" :key="idx">
                <template>
                  <span slot="title" style="font-size: 12px">{{ subItem.nameZh }}</span>
                </template>
                <el-menu-item v-for="(threeItem, i) in subItem.children" :index="threeItem.url" :key="i"
                              @click="pushRoute(threeItem)">
                  {{ threeItem.nameZh }}
                </el-menu-item>
              </el-submenu>
            </template>
          </el-submenu>
        </template>
      </template>
    </el-menu>
  </div>
</template>

<script>

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
    pushRoute(val) {
      if (val.path == null) {
        return
      }
      if (this.$route.path !== val.url) {
        console.log('路由跳转至 => ' + val.url)
        this.$router.push({path: val.url})
      } else {
        console.log('刷新当前路由')
        this.reload()
      }
    },
    setRouteId() {
      // 确保页面刷新时可以重更新定位
      this.defaultRoute = this.$route.path
    },
    getMenuList() {
      let localRoutes = JSON.parse(localStorage.getItem('dynamicMenuRoutes'))
      if (localRoutes == null || localRoutes.length < 1) {
        this.$get('/menu/list', {
          rootId: '0',
          status: '1,2'
        }).then(data => {
          localStorage.setItem("dynamicMenuRoutes", JSON.stringify(data))
          this.menuList = this.filterMenuList(data)
        }).catch(err => {

        })
      } else {
        this.menuList = this.filterMenuList(localRoutes)
      }
    },

    // 递归过滤得到每一项的hidden为false的数据
    filterMenuList(arr) {
      return arr.filter(item => {
        if (item.children) {
          item.children = this.filterMenuList(item.children)
        }
        if (item.status === 1) {
          return true
        }
      })
    }

  }
}
</script>

<style scoped lang="less">
/deep/ .el-submenu .el-menu-item {
  background: linear-gradient(270deg, #333333 0%, #333333 100%);
}
</style>
