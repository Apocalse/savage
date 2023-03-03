<template>
  <div class="mod-menu">
    <el-form>
      <el-form-item>
        <el-button type="primary" icon="el-icon-plus" @click="addOrUpdateHandle(null, 0)">新增</el-button>
        <el-button type="primary" icon="el-icon-refresh-right" @click="reloadMenu">重载</el-button>
      </el-form-item>
    </el-form>
    <el-table
        v-loading="dataListLoading"
        border style="width: 100%"
        :data="dataList"
        :element-loading-text="loadingText"
        ref="dataTable"
        :row-key="getRowKeys"
        :expand-row-keys="openRows"
    >
      <el-table-column
          prop="nameZh"
          header-align="center"
          align="left"
          label="菜单名称">
      </el-table-column>
      <el-table-column
          header-align="center"
          align="center"
          label="菜单图标"
      >
        <template v-slot="scope">
          <i :class=scope.row.icon></i>
        </template>
      </el-table-column>
      <el-table-column
          prop="type"
          header-align="center"
          align="center"
          label="类型"
      >
        <template v-slot="scope">
          <el-tag v-if="scope.row.type === 0" size="small">目录</el-tag>
          <el-tag v-else-if="scope.row.type === 1" size="small" type="success">菜单</el-tag>
          <el-tag v-else-if="scope.row.type === 2" size="small" type="info">按钮</el-tag>
        </template>
      </el-table-column>
      <el-table-column
          prop="orderNum"
          header-align="center"
          align="center"
          label="排序">
      </el-table-column>
      <el-table-column
          prop="url"
          header-align="center"
          align="center"
          :show-overflow-tooltip="true"
          label="菜单路由">
      </el-table-column>
      <el-table-column
          prop="status"
          header-align="center"
          align="center"
          :show-overflow-tooltip="true"
          label="状态">
        <template v-slot="scope">
          <i class="el-icon-view" v-if="scope.row.status === 1"></i>
          <i class="el-icon-hide" v-if="scope.row.status === 2"></i>
          <i class="el-icon-circle-close" v-if="scope.row.status === 3"></i>
        </template>
      </el-table-column>
      <el-table-column
          header-align="center"
          align="center"
          label="操作"
          width="250"
      >
        <template v-slot="scope">
          <el-button v-if="scope.row.type===0" type="text" icon="el-icon-plus"
                     @click="addOrUpdateHandle(scope.row, 1)">
            新增
          </el-button>
          <el-button type="text" icon="el-icon-edit" @click="addOrUpdateHandle(scope.row, 2)">
            修改
          </el-button>
          <el-button type="text" icon="el-icon-delete" @click="deleteHandle(scope.row.id)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <MenuAddOrUpdate v-if="menuAddOrUpdateVisible" ref="menuAddOrUpdate" @ok="getDataList"></MenuAddOrUpdate>
  </div>
</template>

<script>
import MenuAddOrUpdate from './models/MenuAddOrUpdate.vue'
import router from "@/router";

export default {
  name: "MenuManagement",
  components: {
    MenuAddOrUpdate
  },
  data() {
    return {
      dataList: [],
      dataListLoading: false,
      loadingText: '',
      openRows: [],
      menuAddOrUpdateVisible: false
    }
  },
  mounted() {
    this.getDataList()
  },
  methods: {
    getRowKeys(row) {
      return row.id + ''
    },
    // 获取数据列表
    getDataList(openFirst = true) {
      this.dataListLoading = true
      this.loadingText = '正在查询数据...'
      this.$get('/menu/list', {
        id: '0',
        status: '1,2,3'
      }).then(data => {
        this.dataListLoading = false
        this.dataList = data
        if (data.length > 0 && openFirst) {
          this.$nextTick(() => {
            this.openRows.push(data[0].id)
          })
        }
      }).catch(data => {
        this.dataListLoading = false
      })
    },
    // 新增 / 修改
    addOrUpdateHandle(row, status) {
      // status用于区分主新增还是下级新增
      this.menuAddOrUpdateVisible = true
      let that = this
      this.$nextTick(() => {
        this.$refs.menuAddOrUpdate.init(row, status, that.dataList)
      })
    },
    // 删除
    deleteHandle(id) {
      this.$confirm(`确定进行删除操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.dataListLoading = true
        this.loadingText = '正在删除数据...'
        this.$post(`/menu/delete/${id}`).then(data => {
          this.dataListLoading = false
          this.getDataList()
          this.$message.success('操作成功')
        }).catch(data => {
          this.$message.error('操作失败')
          this.dataListLoading = false
        })
      }).catch(() => {
      })
    },

    reloadMenu() {
      this.$get('/menu/list', {
        id: '0',
        status: '1,2'
      }).then(data => {
        localStorage.setItem("dynamicMenuRoutes", JSON.stringify(data))
        this.$router.go(0)
      }).catch(err => {

      })
    }


  }
}
</script>

<style scoped>
/*.el-icon-show {*/
/*  background: url('../../assets/icons/svg/icon-show.svg') center center no-repeat;*/
/*  background-size: 15px;*/
/*}*/

/*.el-icon-show:before {*/
/*  content: '显示';*/
/*  font-size: 15px;*/
/*  visibility: hidden;*/
/*}*/

</style>
