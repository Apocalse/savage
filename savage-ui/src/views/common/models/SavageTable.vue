<template>
  <div>
    <el-table
        v-loading="dataListLoading"
        border style="width: 100%;margin-bottom: 10px"
        :data="tableData"
        :element-loading-text="loadingText"
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
            show-overflow-tooltip
        >
        </el-table-column>
      </template>
      <slot></slot>
    </el-table>
    <el-pagination v-if="pageConfig.visible"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageConfig.pageIndex"
        :page-sizes="[10, 20, 50, 100]"
        :page-size="pageConfig.pageSize"
        :total="pageConfig.totalPage"
        background
        layout="->, total, sizes, prev, pager, next, jumper">
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: "SavageTable",
  props: {
    tableData: {
      type: [Array, Object],
      required: true,
      default() {
        return []
      }
    },
    tableColumn: {
      type: [Array, Object],
      required: true,
      default() {
        return []
      }
    },
    pageConfig: {
      type: [Object],
      required: false,
      default() {
        return {
          visible: false,
          pageIndex: 1,
          pageSize: 10,
          totalPage: 10
        }
      },
    },
    config: {
      type: [Object],
      required: false
    },
    search: {
      type: [String],
      required: false
    },
  },
  mounted() {
    console.log(this.pageConfig)
  },
  data() {
    return {
      dataList: [],
      dataListLoading: false,
      loadingText: '',
    }
  },
  methods: {
    handleSizeChange(val) {
      this.pageConfig.pageSize = val
      this.pageConfig.pageIndex = 1
      this.$parent.getDateList()
    },
    handleCurrentChange(val) {
      this.pageConfig.pageIndex = val
      this.$parent.getDateList()
    }
  }

}
</script>

<style scoped>

</style>
