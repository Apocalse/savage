<template>
  <div>
    <el-table
        v-loading="dataLoading"
        :element-loading-text="loadingText"
        :data="tableData"
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
        </el-table-column>
      </template>
      <slot></slot>
    </el-table>
    <el-pagination v-if="pageConfig.show"
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
    tableOrder: {
      type: [Object],
      required: false
    },
    dataLoading: {
      type: [Boolean],
      required: false,
      default(){
        return false
      }
    },
    loadingText: {
      type: [String],
      required: false,
      default(){
        return ''
      }
    }
  },
  mounted() {
    //console.log(this.pageConfig)
  },
  data() {
    return {

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
    },
    changeTableSort(column){
      if(column.column.sortable === 'custom'){
        this.tableOrder.order = column.order;
        this.tableOrder.column = column.prop;
        this.$parent.getDateList()
      }else {
        this.tableOrder.order = null;
        this.tableOrder.column = null;
      }
    }
  }

}
</script>

<style scoped>

</style>
