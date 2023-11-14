<template>
  <div>
    <el-table
        v-loading="dataLoading"
        :element-loading-text="loadingText"
        :data="dataList"
        border style="width: 100%;margin-bottom: 10px"
        @sort-change="changeTableSort"
        ref="dataTable"
    >
      <template v-for="(item, index) in tableColumn">
        <el-table-column
            :key="index"
            :prop="item.prop"
            :label="item.label"
            :header-align="item.headerAlign || 'center'"
            :align="item.align || 'center'"
            :width="item.width"
            :sortable="item.sort"
            show-overflow-tooltip
        >
          <template v-if="item.tag" v-slot="scope">
            <el-tag v-if="scope.row.type === 1" type="success">{{ scope.row.type }}</el-tag>
            <el-tag v-else-if="scope.row.type === 2" type="danger">{{ scope.row.type }}</el-tag>
            <el-tag v-else-if="scope.row.type === 3" type="danger">{{ scope.row.type }}</el-tag>
            <el-tag v-else-if="scope.row.type === 4" type="success">{{ scope.row.type }}</el-tag>
            <el-tag v-else-if="scope.row.type === 5" type="primary">{{ scope.row.type }}</el-tag>
            <el-tag v-else type="info">未知</el-tag>
          </template>
        </el-table-column>
      </template>
      <slot name="action"></slot>
    </el-table>
    <el-pagination
        v-if="pageConfig.visible"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageConfig.pageIndex"
        :page-sizes="[10, 20, 50, 100]"
        :page-size="pageConfig.pageSize"
        :total="pageConfig.totalPage"
        background
        layout="->, total, sizes, prev, pager, next, jumper"
    >
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: "SavageTable",
  props: {
    tableColumn: {
      type: [Array, Object],
      required: true,
      default() {
        return []
      }
    },
    tableData: {
      type: [Array, Object],
      required: false,
      default() {
        return undefined
      }
    },
    // url
    url: {
      type: Object,
      required: false,
      default() {
        return undefined
      }
    },
    // 查询条件
    queryForm: {
      type: [Array, Object],
      required: false,
      default() {
        return undefined
      }
    },
    // 综合查询字段
    comprehensiveSearchColumns: {
      type: String,
      required: false,
      default() {
        return ''
      }
    },
    pageConfig: {
      type: Object,
      required: false,
      default() {
        return {
          visible: true,
          pageIndex: 1,
          pageSize: 10,
          totalPage: 0
        }
      },
    }
  },

  mounted() {
    this.getDateList()
  },

  data() {
    return {
      dataLoading: false,
      loadingText: '',
      dataList: [],
      tableOrder: {
        column: null,
        sortRule: null
      }
    }
  },

  methods: {
    getDateList(page) {
      if (page) {
        this.pageConfig.pageIndex = page
      }
      // 传入已有的数组，不进行分页处理
      if (this.tableData !== undefined) {
        this.pageConfig.visible = false
        this.dataList = this.tableData
      } else { // 根据接口获取分页列表
        this.dataLoading = true
        let params = this.queryForm !== undefined ? Object.assign({}, this.queryForm) : Object.assign({}, this.$parent.queryForm)
        let url = this.url !== undefined ? this.url.search : this.$parent.url.search
        if (this.comprehensiveSearchColumns != null && this.comprehensiveSearchColumns !== '') {
          params['searchColumns'] = this.comprehensiveSearchColumns
        }
        params['size'] = this.pageConfig.pageSize
        params['page'] = this.pageConfig.pageIndex
        if (this.tableOrder.sortRule != null) {
          params['orderColumn'] = this.tableOrder.column
          params['orderRule'] = this.tableOrder.sortRule
        }
        this.$get(url, params).then(data => {
          this.dataLoading = false
          this.pageConfig.totalPage = data.totalCount
          this.dataList = data.list
        }).catch(e => {
          this.dataLoading = false
        })
      }
    },

    handleSizeChange(val) {
      this.pageConfig.pageSize = val
      this.pageConfig.pageIndex = 1
      this.getDateList()
    },

    handleCurrentChange(val) {
      this.pageConfig.pageIndex = val
      this.getDateList()
    },

    changeTableSort(column) {
      if (column.column.sortable === 'custom') {
        this.tableOrder.sortRule = column.order;
        this.tableOrder.column = column.prop;
        this.getDateList()
      } else {
        this.tableOrder.sortRule = null;
        this.tableOrder.column = null;
      }
    }
  }

}
</script>

<style scoped>

</style>
