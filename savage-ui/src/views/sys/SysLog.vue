<template>
  <div>
    <el-form :inline="true" :model="searchForm" @submit.native.prevent>
      <el-row>
        <el-form-item label="用户名:">
          <el-select v-model="searchForm.userId" placeholder="请选择" clearable>
            <el-option
                v-for="item in userList"
                :key="item.id"
                :label="item.userRealName"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="类型:">
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
        <el-form-item label="操作内容:">
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
          <el-button @click="reSet">
            重置
          </el-button>
        </el-form-item>
      </el-row>
    </el-form>
    <savage-table :table-column="tableColumn" :table-data="dataList" :page-config="pageConfig">

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
<!--    <el-pagination-->
<!--        @size-change="handleSizeChange"-->
<!--        @current-change="handleCurrentChange"-->
<!--        :current-page="pageIndex"-->
<!--        :page-sizes="[10, 20, 50, 100]"-->
<!--        :page-size="pageSize"-->
<!--        :total="totalPage"-->
<!--        background-->
<!--        layout="->, total, sizes, prev, pager, next, jumper">-->
<!--    </el-pagination>-->
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
      dataListLoading: false,
      loadingText: '',


      pageConfig: {
        visible: true,
        currentPage: 1,
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
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
          label: '时延/ms'
        },
        {
          prop: 'ip',
          label: 'ip地址'
        },
        {
          prop: 'createDate',
          label: '调用时间'
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
      this.dataListLoading = true
      this.$get('/sysLog/pageList', {
        size: this.pageConfig.pageSize,
        page: this.pageConfig.pageIndex,
        startDate: this.searchForm.startTime,
        endDate: this.searchForm.endTime,
        searchKey: this.searchForm.searchKey,
        type: this.searchForm.type,
        userId: this.searchForm.userId
      }).then(data => {
        this.dataListLoading = false
        this.pageConfig.totalPage = data.totalCount
        this.dataList = data.list
      }).catch(e => {
        this.dataListLoading = false
      })
    },

    doSearch() {
      this.pageIndex = 1
      this.getDateList()
    },

    reSet() {
      this.searchForm.startTime = null
      this.searchForm.endTime = null
      this.searchForm.userId = null
      this.searchForm.searchKey = null
      this.searchForm.type = null
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
