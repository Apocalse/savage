<template>
    <el-container>
      <el-header><top-menu></top-menu></el-header>
      <el-container>
        <el-aside width="200px"><side-menu></side-menu></el-aside>
        <el-main>
          <el-card :body-style="siteContentViewHeight">
            <router-view v-if="isRouterAlive"/>
          </el-card>
        </el-main>
      </el-container>
<!--      <el-footer>Footer</el-footer>-->
    </el-container>

</template>

<script>
import TopMenu from './TopMenu'
import SideMenu from "./SideMenu"

export default {
  name: "MainContainer",
  components: {TopMenu, SideMenu},
  computed: {
    siteContentViewHeight() {
      let height = 900;
      return {minHeight: height + 'px'}
    },
  },
  provide () {
    return {
      reload: this.reload
    }
  },
  data () {
    return {
      isRouterAlive: true
    }
  },
  methods: {
    reload () {
      this.isRouterAlive = false
      this.$nextTick(function () {
        this.isRouterAlive = true
      })
    }
  }

}
</script>

<style scoped>
.el-header, .el-footer {
  background-color: #0085d0;
  /*color: #333;*/
  text-align: center;
  line-height: 60px;
  height:100%;
  padding: 0 0
}

.el-aside {
  background-color: #545c64;
  /*color: #333;*/
  text-align: center;
  line-height: 100vh;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  /*text-align: center;*/
  /*line-height: 160px;*/
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container.is-vertical {
  flex-direction: column;
  height: 100%;
}

/*.el-container:nth-child(5) .el-aside,*/
/*.el-container:nth-child(6) .el-aside {*/
/*  line-height: 260px;*/
/*}*/

/*.el-container:nth-child(7) .el-aside {*/
/*  line-height: 100%;*/
/*}*/


</style>

