<template>
  <div>
    <el-form class="form" :inline="true" :model="queryForm" ref="queryForm" @submit.native.prevent>
      <el-row>
        <el-col :span="6">
          <el-form-item label="用户名" prop="userId">
            <el-select v-model="queryForm.createUserId" placeholder="请选择" clearable filterable>
              <el-option
                  v-for="item in userList"
                  :key="item.id"
                  :label="item.userRealName"
                  :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="日志类型" prop="type">
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
        </el-col>
        <el-col :span="6">
          <el-form-item label="请求uri" prop="requestUri">
            <el-input v-model.trim="queryForm.requestUri" placeholder="请求uri" clearable></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="操作内容" prop="searchKey">
            <el-input v-model="queryForm.searchKey" placeholder="操作内容" clearable></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6">
          <el-form-item label="开始时间" prop="createDate_begin">
            <el-date-picker align="left"
                            v-model="queryForm.createDate_begin"
                            type="datetime"
                            format="yyyy-MM-dd HH:mm:ss"
                            value-format="yyyy-MM-dd HH:mm:ss">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="结束时间" prop="createDate_end">
            <el-date-picker align="left"
                            v-model="queryForm.createDate_end"
                            ref="endTime"
                            type="datetime"
                            format="yyyy-MM-dd HH:mm:ss"
                            value-format="yyyy-MM-dd HH:mm:ss">
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-form-item style="float: right">
          <el-button type="primary" @click="doSearch">
            查询
          </el-button>
        </el-form-item>
        <el-form-item style="float: right">
          <el-button @click="resetForm('queryForm')">
            重置
          </el-button>
        </el-form-item>
      </el-row>
    </el-form>

    <savage-table
        ref="savageTable"
        :table-column="tableColumn"
        :comprehensive-search-columns="searchColumns"
    >
      <!--      <template v-slot:action="action">-->
      <!--        <el-table-column  header-align="center" align="center" label="操作" width="250">-->
      <!--          <template v-slot="scope">-->
      <!--            <el-button type="text" icon="el-icon-edit" @click="edit(scope.row)">-->
      <!--              编辑-->
      <!--            </el-button>-->
      <!--          </template>-->
      <!--        </el-table-column>-->
      <!--      </template>-->
    </savage-table>
  </div>
</template>

<script>
import {getSysLogTypeList, getSysLogTypeMap} from "@/utils/savageUtils";
import moment from 'moment'
import {getUserList} from "@/api/sys";

export default {
  name: "SysLog",

  mounted() {
    this.getUserList()
  },
  data() {
    return {
      url: {
        search: '/sysLog/pageList',
      },
      // 综合查询字段
      searchColumns: 'operation',
      queryForm: {},
      tableColumn: [
        {
          prop: 'username',
          label: '用户名'
        },
        {
          prop: 'type',
          label: '日志类型',
          // tag: {1: 'success', 2: 'danger', 3: 'danger', 4: 'success', 5: 'primary'}
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
      if (this.queryForm.createDate_begin != null && this.queryForm.createDate_end != null) {
        if (moment(this.queryForm.createDate_end).isBefore(this.queryForm.createDate_begin)) {
          this.$message.warning('结束时间不能小于开始时间');
          return
        }
      }
      this.$refs.savageTable.getDateList(1)
    },

    edit(row) {
      // this.$refs.savageTable.edit(row)
    },

    resetForm(formName) {
      this.queryForm = {}
      this.$refs[formName].resetFields()
    },

    getUserList() {
      getUserList().then(data => {
        this.userList = data
      })
    },

  }
}
</script>

<style lang="less" scoped>
/deep/ .el-form--inline .el-form-item__label {
  float: none;
  display: inline-block;
  width: 80px;
}
</style>
