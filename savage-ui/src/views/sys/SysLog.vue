<template>
  <div>
    <el-form :inline="true" :model="queryForm" ref="searchForm" @submit.native.prevent>
      <el-row>
        <el-form-item label="用户名:" prop="userId">
          <el-select v-model="queryForm.userId" placeholder="请选择" clearable>
            <el-option
                v-for="item in userList"
                :key="item.id"
                :label="item.userRealName"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="类型:" prop="type">
          <el-select v-model="queryForm.type" placeholder="请选择" clearable>
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
                          v-model="queryForm.startDate"
                          type="datetime"
                          format="yyyy-MM-dd HH:mm:ss"
                          value-format="yyyy-MM-dd HH:mm:ss">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-date-picker align="left"
                          v-model="queryForm.endDate"
                          ref="endTime"
                          type="datetime"
                          format="yyyy-MM-dd HH:mm:ss"
                          value-format="yyyy-MM-dd HH:mm:ss">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="操作内容:" prop="searchKey">
          <el-input v-model.trim="queryForm.searchKey" placeholder="操作内容" clearable></el-input>
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
        ref="savageTable"
        :table-column="tableColumn"
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
    this.getUserList()
  },
  data() {
    return {
      url: {
        search: '/sysLog/pageList'
      },
      queryForm: {
        startDate: null,
        endDate: null,
        userId: null,
        searchKey: null,
        type: null,
      },
      tableColumn: [
        {
          prop: 'username',
          label: '用户名'
        },
        {
          prop: 'type',
          label: '日志类型',
          //tag: {1: 'success', 2: 'danger', 3: 'danger'}
          // type: 'tag',
          // dict: 'sysLogTypeDict'
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
      sysLogTypeList: getSysLogTypeList() === null || undefined ? [] : getSysLogTypeList(),
      sysLogTypeMap: getSysLogTypeMap(),
      userList: []
    }
  },

  methods: {
    doSearch() {
      if (this.queryForm.startDate !== '' && this.queryForm.endDate !== '') {
        if (moment(this.queryForm.endDate).isBefore(this.queryForm.startDate)) {
          this.$message.warning('结束时间不能小于开始时间');
          return
        }
      }
      this.pageConfig.pageIndex = 1
      this.$refs.savageTable.getDateList()
    },

    resetForm(formName) {
      this.$refs[formName].resetFields()
    },

    getUserList() {
      this.$get('/user/list', {}).then(data => {
        this.userList = data
      })
    },

  }
}
</script>

<style scoped>

</style>
