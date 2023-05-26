<template>
  <div>
    <el-form :inline="true" :model="searchForm" @submit.native.prevent>

    </el-form>

    <savage-table
        :table-column="tableColumn"
        :table-data="tableData"
    >
      <template v-slot:action="scope">
        <el-table-column  header-align="center" align="center" label="操作" width="250">
          <template v-slot="scope">
            <el-button type="text" icon="el-icon-edit" @click="handle(scope.row)">
              编辑
            </el-button>
          </template>
        </el-table-column>
      </template>
    </savage-table>
  </div>
</template>

<script>
import SavageTable from "@/views/common/models/SavageTable.vue";

export default {
  name: "RoleManagement",
  components: {SavageTable},
  data() {
    return {
      searchForm: {},
      tableColumn: [
        {
          prop: 'id',
          label: 'id',
          headerAlign: 'center',
          align: 'center',
          width: '150'
        },
        {
          prop: 'name',
          label: '用户名',
          headerAlign: 'center',
          align: 'center',
          width: '150'
        },
        {
          prop: 'sex',
          label: '性别',
          headerAlign: 'center',
          align: 'center',
        }
      ],
      tableData: [
        {
          id: "123",
          name: "aaa",
          sex: "n"
        },
        {
          id: "456",
          name: "bbb",
          sex: "m"
        }
      ],
      pageConfig: {
        visible: true,
        pageIndex: 1,
        pageSize: 10,
        totalPage: 5
      }
    }
  },
  methods: {
    handle(row){
      console.log(row)
    }
  }
}
</script>

<style scoped>

</style>
