<template>
  <el-dialog :title="title"
             :close-on-click-modal="false"
             @close="close"
             :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" v-loading="dataFormLoading"
             ref="dataForm" label-width="80px">
      <el-form-item label="类型" prop="type">
        <el-radio-group v-model="dataForm.type" :disabled="status!==0">
          <el-radio v-for="(type, index) in dataForm.typeList" :label="index" :key="index">{{ type }}
          </el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item :label="dataForm.typeList[dataForm.type] + '名称'" prop="nameZh">
        <el-input v-model="dataForm.nameZh" :placeholder="dataForm.typeList[dataForm.type] + '名称'"></el-input>
      </el-form-item>
      <el-form-item v-if="dataForm.type === 1 && dataForm.parentId!=='0'" label="上级菜单" prop="parentName">
        <el-popover v-model="menuVisible" ref="menuListPopover" placement="bottom-start" trigger="click">
          <el-tree accordion node-key="id" ref="menuListTree"
                   :data="menuList"
                   :props="menuListTreeProps"
                   :default-expanded-keys="defaultExpandedKeys"
                   @current-change="menuListTreeCurrentChangeHandle"
                   :highlight-current="true"
                   :expand-on-click-node="false">
          </el-tree>
        </el-popover>
        <el-input v-model="dataForm.parentName" v-popover:menuListPopover
                  :readonly="true" placeholder="点击选择上级菜单">
        </el-input>
      </el-form-item>
      <el-form-item :label="dataForm.typeList[dataForm.type] + '路由'" prop="url">
        <el-input v-model="dataForm.url"
                  :placeholder="dataForm.typeList[dataForm.type] + '路由, 如: /sys/menu'"></el-input>
      </el-form-item>
      <el-form-item v-if="dataForm.type !== 3" label="排序" prop="orderNum">
        <el-input-number v-model="dataForm.orderNum" controls-position="right" :min="0" label="排序"></el-input-number>
      </el-form-item>
      <el-form-item label="状态" prop="status" v-if="dataForm.type !== 3">
        <el-radio-group v-model="dataForm.status">
          <el-radio :label="1">显示</el-radio>
          <el-radio :label="2">隐藏</el-radio>
          <el-radio :label="3">禁用</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <span slot="footer">
      <el-button @click="close" icon="el-icon-close">取消</el-button>
      <el-button type="primary" icon="el-icon-check" @click="dataFormSubmit()" v-loading="submitLoading"
                 :disabled="submitDisabled">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
export default {
  data() {
    const validateUrl = (rule, value, callback) => {
      if (this.dataForm.type === 1 && !/\S/.test(value)) {
        callback(new Error('菜单URL不能为空'))
      } else {
        callback()
      }
    };
    return {
      visible: false,
      // 控制菜单显示
      menuVisible: false,
      dataFormLoading: false,
      submitLoading: false,
      submitDisabled: false,
      title: '',
      status: 0,
      dataForm: {
        id: 0,
        type: 1,
        typeList: ['目录', '菜单'],
        nameZh: '',
        parentId: 0,
        parentName: '',
        url: '',
        perms: '',
        orderNum: 0,
        icon: '',
        status: 1,
        iconList: []
      },
      dataRule: {
        name: [
          {required: true, message: '菜单名称不能为空', trigger: 'blur'}
        ],
        parentName: [
          {required: true, message: '上级菜单不能为空', trigger: 'change'}
        ],
        url: [
          {required: true, message: '菜单URL不能为空', validator: validateUrl, trigger: 'blur'}
        ]
      },
      menuList: [],
      menuListTreeProps: {
        label: 'nameZh',
        children: 'children'
      },
      defaultExpandedKeys: []
    }
  },

  methods: {
    close() {
      this.visible = false
      const that = this
      this.$nextTick(() => {
        that.$refs['dataForm'].resetFields()
      })
    },
    init(row, status) {
      this.status = status
      this.defaultExpandedKeys = []
      this.$get('/menu/list', {
        id: '0',
        status: '1,2,3'
      }).then(data => {
        this.menuList = data
      }).then(() => {
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
        })
      }).then(() => {
        if (status === 0) {
          // 新增
          this.dataForm.parentId = 0
          this.title = '新增'
        } else if (status === 1) {
          //下级新增
          this.dataForm.type = row.type + 1
          this.dataForm.parentId = row.id
          this.dataForm.status = row.status
          this.title = '新增子菜单'
          this.menuListTreeSetCurrentNode()
        } else {
          //修改
          this.dataForm.id = row.id
          this.dataForm.type = row.type
          this.dataForm.nameZh = row.nameZh
          this.dataForm.parentId = row.parentId
          this.dataForm.url = row.url
          this.dataForm.orderNum = row.orderNum
          //this.dataForm.icon = data.icon
          this.dataForm.status = row.status
          this.title = '修改'
          this.menuListTreeSetCurrentNode()
        }

      })
    },
    // 菜单树设置当前选中节点
    menuListTreeSetCurrentNode() {
      this.$refs.menuListTree.setCurrentKey(this.dataForm.parentId)
      this.dataForm.parentName = (this.$refs.menuListTree.getCurrentNode() || {})['nameZh']
    },
    // 菜单树选中
    menuListTreeCurrentChangeHandle(data, node) {
      // 修改的时候上级菜单不能为自身
      if (this.dataForm.id && this.dataForm.id === data.menuId) {
        this.$message.error('上级菜单不能为自身')
        return
      }
      this.dataForm.parentId = data.menuId
      this.dataForm.parentName = data.nameZh
      this.menuVisible = false
    },

    // 图标选中
    // iconActiveHandle(iconName) {
    //   this.dataForm.icon = iconName
    //   this.iconVisible = false
    // },
    // 获取默认展开的key
    // getDefaultExpandedKeys(list) {
    //   if (!list) {
    //     return
    //   }
    //   if (list.length > 0) {
    //     this.defaultExpandedKeys.push(list[0].menuId)
    //     this.getDefaultExpandedKeys(list[0].children)
    //   }
    // },
    // 表单提交
    dataFormSubmit() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.submitLoading = true
          this.submitDisabled = true
          this.$post(`/menu/${!this.dataForm.id ? 'add' : 'update'}`, {
            'id': this.dataForm.id || undefined,
            'type': this.dataForm.type,
            'nameZh': this.dataForm.nameZh,
            'parentId': this.dataForm.parentId,
            'url': this.dataForm.url,
            'orderNum': this.dataForm.orderNum,
            //'icon': this.dataForm.icon,
            'status': this.dataForm.status
          }).then(data => {
            this.visible = false
            this.$emit('ok')
            this.$message.success('操作成功')
          }).finally(() => {
            this.submitLoading = false
            this.submitDisabled = false
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
