<template>
  <el-form>
    <el-form-item
        label='菜单图标：'
        prop='icon'
    >
      <el-popover
          placement="bottom"
          width="450"
          trigger="click"
          @show="popoverShow"
      >
    <span slot="reference">
      <el-input suffix-icon='data-icon' v-model='form.icon' placeholder='请输入'>
       <template slot="append"><i :class="form.icon"/></template>
      </el-input>
    </span>
        <div class="iconList">
          <el-tabs v-model="activeName">
            <el-tab-pane
                v-for="(item, index) in iconList"
                :key="item.label"
                :name="'name'+ index"
                :label="item.label"
            >
              <i v-for="item in item.list" :key="item" :class="item" @click="setIcon(item)"
                 style="font-size:20px"></i>
            </el-tab-pane>
          </el-tabs>
        </div>
      </el-popover>
    </el-form-item>
  </el-form>
  <!--  </el-form>-->
</template>

<script>
export default {
  name: "RoleManagement",
  data() {
    return {
      form: {
        icon :'',
      },
      activeName:'',
      iconList: [
        {
          label: '通用图标',
          id: 'first',
          list: [
            'iconfont iconicon_roundadd',
            'iconfont iconicon_compile',
            'iconfont iconicon_glass',
            'iconfont iconicon_roundclose',
            'iconfont iconicon_roundreduce',
            'iconfont iconicon_delete',
            'iconfont iconicon_shakehands',
            'iconfont iconicon_task_done',
            'iconfont iconicon_voipphone',
            'iconfont iconicon_safety',
            'iconfont iconicon_work',
            'iconfont iconicon_study',
            'iconfont iconicon_task',
            'iconfont iconicon_subordinate',
            'iconfont iconicon_star',
            'iconfont iconicon_setting',
            'iconfont iconicon_sms',
            'iconfont iconicon_share',
            'iconfont iconicon_secret',
            'iconfont iconicon_scan_namecard',
            'iconfont iconicon_principal',
            'iconfont iconicon_group',
            'iconfont iconicon_send',
            'iconfont iconicon_scan',
            'iconfont iconicon_search',
            'iconfont iconicon_refresh',
            'iconfont iconicon_savememo',
            'iconfont iconicon_QRcode',
            'iconfont iconicon_im_keyboard',
            'iconfont iconicon_redpacket',
            'iconfont iconicon_photo',
            'iconfont iconicon_qq',
            'iconfont iconicon_wechat',
            'iconfont iconicon_phone',
            'iconfont iconicon_namecard',
            'iconfont iconicon_notice',
            'iconfont iconicon_next_arrow',
            'iconfont iconicon_left',
            'iconfont iconicon_more',
            'iconfont iconicon_details',
            'iconfont iconicon_message',
            'iconfont iconicon_mobilephone',
            'iconfont iconicon_im_voice',
            'iconfont iconicon_GPS',
            'iconfont iconicon_ding',
            'iconfont iconicon_exchange',
            'iconfont iconicon_cspace',
            'iconfont iconicon_doc',
            'iconfont iconicon_dispose',
            'iconfont iconicon_discovery',
            'iconfont iconicon_community_line',
            'iconfont iconicon_cloud_history',
            'iconfont iconicon_coinpurse_line',
            'iconfont iconicon_airplay',
            'iconfont iconicon_at',
            'iconfont iconicon_addressbook',
            'iconfont iconicon_boss',
            'iconfont iconicon_addperson',
            'iconfont iconicon_affiliations_li',
            'iconfont iconicon_addmessage',
            'iconfont iconicon_addresslist',
            'iconfont iconicon_add'
          ]
        },
        {
          label: '系统图标',
          id: 'second',
          list: [
            'iconfont icon-zhongyingwen',
            'iconfont icon-caidan',
            'iconfont icon-rizhi1',
            'iconfont icon-zhuti',
            'iconfont icon-suoping',
            'iconfont icon-bug',
            'iconfont icon-qq1',
            'iconfont icon-weixin1',
            'iconfont icon-shouji',
            'iconfont icon-mima',
            'iconfont icon-yonghu',
            'iconfont icon-yanzhengma',
            'iconfont icon-canshu',
            'iconfont icon-dongtai',
            'iconfont icon-iconset0265',
            'iconfont icon-shujuzhanshi2',
            'iconfont icon-tuichuquanping',
            'iconfont icon-rizhi',
            'iconfont icon-cuowutishitubiao',
            'iconfont icon-debug',
            'iconfont icon-iconset0216',
            'iconfont icon-quanxian',
            'iconfont icon-quanxian',
            'iconfont icon-shuaxin',
            'iconfont icon-bofangqi-suoping',
            'iconfont icon-quanping',
            'iconfont icon-navicon',
            'iconfont icon-biaodan',
            'iconfont icon-liuliangyunpingtaitubiao08',
            'iconfont icon-caidanguanli',
            'iconfont icon-cuowu',
            'iconfont icon-wxbgongju',
            'iconfont icon-tuichu',
            'iconfont icon-daohanglanmoshi02',
            'iconfont icon-changyonglogo27',
            'iconfont icon-biaoge',
            'iconfont icon-baidu1',
            'iconfont icon-tubiao',
            'iconfont icon-souhu',
            'iconfont icon-msnui-360',
            'iconfont icon-iframe',
            'iconfont icon-huanyingye'
          ]
        }
      ]
    }
  },
  methods: {
    popoverShow() {
      this.$nextTick(() => {
        this.activeName = '0'
      })
    },
    /**
     * @description 给icon绑定的点击事件
     * @author 饺子
     * @param { String } icon 图标
     */
    setIcon(icon) {
      this.$set(this.form, 'icon', icon)
    }
  }
}
</script>

<style scoped>

</style>
