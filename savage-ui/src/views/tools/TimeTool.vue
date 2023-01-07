<template>
  <div>
    <h2>时间戳转换</h2>
    <el-form :inline="true" label-width="80px">
      <el-form-item>
        <span slot="label">时间戳</span>
        <el-tooltip class="item" effect="dark" content="点击复制" placement="right">
            <span id="nowTime" style="cursor:pointer"
                  :data-clipboard-text='nowTime'
                  @click="copy('nowTime')">
              {{ nowTime }}
            </span>
        </el-tooltip>
      </el-form-item>
      <el-form-item>
        <span slot="label">时间</span>
        <el-tooltip class="item" effect="dark" content="点击复制" placement="right">
          <span id="nowDate" style="cursor:pointer"
                :data-clipboard-text='nowDate'
                @click="copy('nowDate')">
            {{ nowDate }}
          </span>
        </el-tooltip>
      </el-form-item>
      <el-form-item>
        <span slot="label">{{ buttonText }}</span>
        <el-button :type="buttonText === '暂停'? 'danger':'success'"
                   size="mini"
                   :icon="buttonText === '暂停'? 'el-icon-stop':'el-icon-continue'"
                   @click="timeController(buttonText)">
        </el-button>
      </el-form-item>
    </el-form>
    <el-form :inline="true" label-width="80px">
      <el-form-item>
        <span slot="label">时间</span>
        <el-date-picker
            v-model="dateInput"
            type="datetime"
            placeholder="选择日期时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <span slot="label">转换</span>
        <el-button size="mini"
                   icon="el-icon-right" type="success"
                   @click="strDateToTime">
        </el-button>
      </el-form-item>
      <el-form-item>
        <span slot="label">时间戳</span>
        <el-input style="width: 220px" v-model="timeResult"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button id="timeResult" size="mini"
                   icon="el-icon-document-copy" type="primary"
                   :data-clipboard-text='timeResult'
                   @click="copy('timeResult')">
        </el-button>
      </el-form-item>
    </el-form>
    <el-form :inline="true" label-width="80px">
      <el-form-item>
        <span slot="label">时间戳</span>
        <el-input v-model="timeInput" style="width: 220px" placeholder="YYYY-mm-dd HH:MM:SS"></el-input>
      </el-form-item>
      <el-form-item>
        <span slot="label">转换</span>
        <el-button size="mini"
                   icon="el-icon-right" type="success"
                   @click="timeToStrDate">
        </el-button>
      </el-form-item>
      <el-form-item>
        <span slot="label">时间</span>
        <el-date-picker
            v-model="dateResult"
            type="datetime"
            placeholder="选择日期时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button id="dateResult" size="mini"
                   icon="el-icon-document-copy" type="primary"
                   :data-clipboard-text='dateResult'
                   @click="copy('dateResult')">
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import Clipboard from "clipboard";

export default {
  name: 'TimeTool',
  data() {
    return {
      nowTime: 0,
      nowDate: 'YYYY-mm-dd HH:MM:SS',
      buttonText: '暂停',
      copyText: '',
      dateInput: '',
      dateResult: '',
      timeInput: 0,
      timeResult: ''
    }
  },
  mounted() {
    this.getNowTime()
    this.dateInput = this.nowDate
    this.timeInput = this.nowTime
    let _this = this
    this.timer = setInterval(function () {
      _this.getNowTime()
    }, 1000)
  },

  beforeDestroy() {
    clearInterval(this.timer)
  },

  methods: {
    getNowTime() {
      const date = new Date()
      this.nowTime = date.getTime()
      this.nowDate = this.dateFormat('YYYY-mm-dd HH:MM:SS', date)
    },

    timeController(val) {
      if (val === '暂停') {
        clearInterval(this.timer)
        this.buttonText = '继续'
      } else if (val === '继续') {
        let _this = this
        this.timer = setInterval(function () {
          _this.getNowTime()
        }, 1000)
        this.buttonText = '暂停'
      }
    },

    //时间格式化
    dateFormat(fmt, date) {
      let ret
      const opt = {
        'Y+': date.getFullYear().toString(),        // 年
        'm+': (date.getMonth() + 1).toString(),     // 月
        'd+': date.getDate().toString(),            // 日
        'H+': date.getHours().toString(),           // 时
        'M+': date.getMinutes().toString(),         // 分
        'S+': date.getSeconds().toString()          // 秒
        // 有其他格式化字符需求可以继续添加，必须转化成字符串
      }
      for (let k in opt) {
        ret = new RegExp('(' + k + ')').exec(fmt);
        if (ret) {
          fmt = fmt.replace(ret[1], (ret[1].length === 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, '0')))
        }
      }
      return fmt
    },

    timeToStrDate() {
      const date = new Date(Number(this.timeInput))
      this.dateResult = this.dateFormat('YYYY-mm-dd HH:MM:SS', date)
    },

    strDateToTime() {
      const date = new Date(this.dateInput)
      this.timeResult = date.getTime()
      if (!this.timeResult) {
        this.timeResult = ''
      }
    },

    // 一键复制
    copy(documentId) {
      // 方法一
      // if (documentId) {
      //   const copyElement = document.getElementById(documentId)
      //   this.copyText = copyElement.innerText
      // }
      // const clipboardObj = navigator.clipboard
      // clipboardObj.writeText(this.copyText).catch(e=>{
      //   this.$message.error('复制失败')
      // })
      // this.$message.success('复制成功')

      // 方法二（已过期）
      // let oInput = document.createElement('input')
      // oInput.value = this.copyText
      // document.body.appendChild(oInput)
      // oInput.select() // 选择对象
      // document.execCommand('Copy') // 执行浏览器复制命令
      // oInput.remove()
      // this.$message.success('复制成功')

      // 方法三（npm i clipboard --save）
      // data-clipboard-action="copy" data-clipboard-target="#nowTime" 可进行关联
      let clipboard = new Clipboard('#' + documentId);
      clipboard.on('success', e => {
        this.$message.success('复制成功')
        // 释放内存
        clipboard.destroy()
      })
      clipboard.on('error', e => {
        this.$message.error('不支持复制')
        clipboard.destroy()
      })
    }
  }
}

</script>

<style scoped>
h2 {
  margin-bottom: 50px;
}

span {
  font-size: 16px;
}
</style>