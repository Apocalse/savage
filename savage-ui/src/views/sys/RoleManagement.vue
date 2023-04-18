<template>
  <el-form>
    <el-form-item label='菜单图标：' prop='icon'>
      <i :class="form.icon" style="font-size: 16px"></i>
      <el-popover placement="bottom" width="450" trigger="click" @show="popoverShow">
        <span slot="reference">
          <el-button type="primary" size="mini">选择图标</el-button>
        </span>
        <div class="iconList">
          <el-tabs v-model="activeName">
            <el-tab-pane
                v-for="(item, index) in iconList"
                :key="item.id"
                :label="item.label"
                :name="item.id"
            >
              <i v-for="item in item.list"
                 :key="item"
                 :class="item"
                 @click="setIcon(item)"
                 style="font-size:20px;margin: 5px"></i>
            </el-tab-pane>
          </el-tabs>
        </div>
      </el-popover>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: "RoleManagement",
  data() {
    return {
      form: {
        icon: '',
      },
      activeName: 'second',
      iconList: [
        {
          label: '通用图标',
          id: 'first',
          list: [
            'el-icon-edit',
            'el-icon-share',
            'el-icon-delete'
          ]
        },
        {
          label: '系统图标',
          id: 'second',
          list: [
            'el-icon-edit',
            'el-icon-share',
            'el-icon-delete'
          ]
        }
      ]
    }
  },
  methods: {
    /**
     * @description 给icon绑定的点击事
     * @param { String } icon 图标
     */
    setIcon(icon) {
      this.form.icon = icon
    }
  }
}
</script>

<style scoped>

</style>
