<template>
  <el-container>
    <el-header>
      <top-menu></top-menu>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <side-menu></side-menu>
      </el-aside>
      <el-main>
        <el-card :body-style="siteContentViewHeight">
          <router-view v-if="isRouterAlive"/>
        </el-card>
      </el-main>
    </el-container>
    <!--      <el-footer>Footer</el-footer>-->
  </el-container>
  <!--    <el-container>-->
  <!--      <el-aside width="200px">-->
  <!--        <side-menu></side-menu>-->
  <!--      </el-aside>-->
  <!--      <el-container>-->
  <!--        <el-header>-->
  <!--          <el-button icon="el-icon-s-fold"></el-button>-->
  <!--          <top-menu></top-menu>-->
  <!--        </el-header>-->
  <!--        <el-main style="height: calc(100vh - 60px)">-->
  <!--&lt;!&ndash;          <el-card :body-style="siteContentViewHeight" style="min-width: 1000px">&ndash;&gt;-->
  <!--&lt;!&ndash;            <router-view v-if="isRouterAlive"/>&ndash;&gt;-->
  <!--&lt;!&ndash;          </el-card>&ndash;&gt;-->

  <!--          <el-tabs v-model="editableTabsValue" type="card" editable @edit="handleTabsEdit">-->
  <!--            <el-tab-pane-->
  <!--                :key="item.name"-->
  <!--                v-for="(item, index) in editableTabs"-->
  <!--                :label="item.title"-->
  <!--                :name="item.name"-->
  <!--            >-->
  <!--              <el-card :body-style="siteContentViewHeight" style="min-width: 1000px">-->
  <!--                <router-view v-if="isRouterAlive"/>-->
  <!--              </el-card>-->
  <!--            </el-tab-pane>-->
  <!--          </el-tabs>-->

  <!--        </el-main>-->
  <!--      </el-container>-->
  <!--&lt;!&ndash;      <el-footer>Footer</el-footer>&ndash;&gt;-->
  <!--    </el-container>-->

</template>

<script>
import TopMenu from './TopMenu'
import SideMenu from "./SideMenu"

export default {
  name: "MainContainer",
  components: {TopMenu, SideMenu},
  computed: {
    siteContentViewHeight() {
      let height = window.innerHeight - 142;
      return {minHeight: height + 'px'}
    },
  },
  provide() {
    return {
      reload: this.reload
    }
  },
  data() {
    return {
      isRouterAlive: true,
      editableTabsValue: '2',
      editableTabs: [{
        title: 'Tab 1',
        name: '1',
        content: 'Tab 1 content'
      }, {
        title: 'Tab 2',
        name: '2',
        content: 'Tab 2 content'
      }],
      tabIndex: 2
    }
  },
  methods: {
    reload() {
      this.isRouterAlive = false
      this.$nextTick(function () {
        this.isRouterAlive = true
      })
    },

    handleTabsEdit(targetName, action) {
      if (action === 'add') {
        let newTabName = ++this.tabIndex + '';
        this.editableTabs.push({
          title: 'New Tab',
          name: newTabName,
          content: 'New Tab content'
        });
        this.editableTabsValue = newTabName;
      }
      if (action === 'remove') {
        let tabs = this.editableTabs;
        let activeName = this.editableTabsValue;
        if (activeName === targetName) {
          tabs.forEach((tab, index) => {
            if (tab.name === targetName) {
              let nextTab = tabs[index + 1] || tabs[index - 1];
              if (nextTab) {
                activeName = nextTab.name;
              }
            }
          });
        }

        this.editableTabsValue = activeName;
        this.editableTabs = tabs.filter(tab => tab.name !== targetName);
      }
    }
  }

}
</script>

<style scoped lang="less">
.el-header, .el-footer {
  background-color: #0085d0;
  /*color: #333;*/
  text-align: center;
  line-height: 60px;
  height: 100%;
  padding: 0 0
}

.el-aside {
  background-color: #545c64;
  /*color: #333;*/
  text-align: center;
  line-height: 100vh;
}

.el-main {
  background-color: #f0f2f5;
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

