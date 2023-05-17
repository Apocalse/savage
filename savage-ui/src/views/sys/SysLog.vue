<template>
  <div>
    <el-form :inline="true" :model="searchForm" ref="searchForm" @submit.native.prevent>
      <el-row>
        <el-form-item label="用户名:" prop="userId">
          <el-select v-model="searchForm.userId" placeholder="请选择" clearable>
            <el-option
                v-for="item in userList"
                :key="item.id"
                :label="item.userRealName"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="类型:" prop="type">
          <el-select v-model="searchForm.type" placeholder="请选择" clearable>
            <el-option
                v-for="item in sysLogTypeList"
                :key="item.key"
                :label="item.value"
                :value="item.key"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker align="left"
                          v-model="searchForm.startTime"
                          type="datetime"
                          format="yyyy-MM-dd HH:mm:ss"
                          value-format="yyyy-MM-dd HH:mm:ss">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-date-picker align="left"
                          v-model="searchForm.endTime"
                          ref="endTime"
                          type="datetime"
                          format="yyyy-MM-dd HH:mm:ss"
                          value-format="yyyy-MM-dd HH:mm:ss">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="操作内容:" prop="searchKey">
          <el-input v-model.trim="searchForm.searchKey" placeholder="操作内容" clearable></el-input>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item style="float: right">
          <el-button type="primary" @click="doSearch">
            查询
          </el-button>
        </el-form-item>
        <el-form-item style="float: right">
          <el-button @click="resetForm('searchForm')">
            重置
          </el-button>
        </el-form-item>
      </el-row>
    </el-form>

    <savage-table
        :table-column="tableColumn"
        :table-order="tableOrder"
        :table-data="dataList"
        :page-config="pageConfig"
        :data-loading="dataLoading"
        :loading-text="loadingText"
    >
    </savage-table>
<!--      <el-table-column-->
<!--          prop="type"-->
<!--          header-align="center"-->
<!--          align="center"-->
<!--          show-overflow-tooltip-->
<!--          width="120"-->
<!--          label="日志类型">-->
<!--        <template v-slot="scope">-->
<!--          <el-tag v-if="scope.row.type === 1" type="success">{{ sysLogTypeMap[scope.row.type] }}</el-tag>-->
<!--          <el-tag v-else-if="scope.row.type === 2" type="danger">{{ sysLogTypeMap[scope.row.type] }}</el-tag>-->
<!--          <el-tag v-else-if="scope.row.type === 3" type="danger">{{ sysLogTypeMap[scope.row.type] }}</el-tag>-->
<!--          <el-tag v-else-if="scope.row.type === 4" type="success">{{ sysLogTypeMap[scope.row.type] }}</el-tag>-->
<!--          <el-tag v-else-if="scope.row.type === 5" type="primary">{{ sysLogTypeMap[scope.row.type] }}</el-tag>-->
<!--          <el-tag v-else type="info">未知</el-tag>-->
<!--        </template>-->
<!--      </el-table-column>-->
  </div>
</template>

<script>
import {getSysLogTypeList, getSysLogTypeMap} from "@/utils/savageUtils";
import moment from 'moment'
import SavageTable from "@/views/common/models/SavageTable.vue";

export default {
  name: "SysLog",
  components: {SavageTable},

  mounted() {
    this.getDateList()
    this.getUserList()
    // this.sysLogType = getSysLogTypeList()
  },

  data() {
    return {
      dataList: [],
      dataLoading: false,
      loadingText: '',
      pageConfig: {
        show: true,
        currentPage: 1,
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
      },
      tableOrder: {
        column: null,
        order: null
      },
      tableColumn: [
        {
          prop: 'username',
          label: '用户名'
        },
        {
          prop: 'type',
          label: '日志类型'
        },
        {
          prop: 'operation',
          label: '操作内容'
        },
        {
          prop: 'requestUri',
          label: '请求uri'
        },
        {
          prop: 'method',
          label: '请求方法'
        },
        {
          prop: 'params',
          label: '请求参数'
        },
        {
          prop: 'time',
          label: '时延/ms',
          sort: 'custom'
        },
        {
          prop: 'ip',
          label: 'ip地址'
        },
        {
          prop: 'createDate',
          label: '调用时间',
          sort: 'custom'
        }
      ],

      searchForm: {
        startTime: null,
        endTime: null,
        userId: null,
        searchKey: null,
        type: null,
      },
      sysLogTypeList: getSysLogTypeList() === null || undefined ? [] : getSysLogTypeList(),
      sysLogTypeMap: getSysLogTypeMap(),
      userList: []
    }
  },

  methods: {
    getDateList() {
      if (this.searchForm.startTime !== '' && this.searchForm.endTime !== '') {
        if (moment(this.searchForm.endTime).isBefore(this.searchForm.startTime)) {
          this.$message.warning('结束时间不能小于开始时间');
          return
        }
      }
      this.dataLoading = true
      this.$get('/sysLog/pageList', {
        size: this.pageConfig.pageSize,
        page: this.pageConfig.pageIndex,
        startDate: this.searchForm.startTime,
        endDate: this.searchForm.endTime,
        searchKey: this.searchForm.searchKey,
        type: this.searchForm.type,
        userId: this.searchForm.userId,
        column: this.tableOrder.column,
        order: this.tableOrder.order
      }).then(data => {
        this.dataLoading = false
        this.pageConfig.totalPage = data.totalCount
        this.dataList = data.list
      }).catch(e => {
        this.dataLoading = false
      })
    },

    doSearch() {
      this.pageIndex = 1
      this.getDateList()
    },

    resetForm(formName) {
      this.$refs[formName].resetFields()
    },

    getUserList() {
      this.$get('/user/list', {}).then(data => {
        this.userList = data
      })
    },

    handleSizeChange(val) {
      this.pageSize = val
      this.pageIndex = 1
      this.getDateList()
    },

    handleCurrentChange(val) {
      this.pageIndex = val
      this.getDateList()
    }
  }
}
</script>

<style scoped>

</style>
