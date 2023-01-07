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
        <el-input style="width: 220px" disabled v-model="timeResult"></el-input>
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
            disabled
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
import {dateFormat, copy} from "@/utils/utils";

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
    copy,
    getNowTime() {
      const date = new Date()
      this.nowTime = date.getTime()
      this.nowDate = dateFormat('YYYY-mm-dd HH:MM:SS', date)
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

    timeToStrDate() {
      const date = new Date(Number(this.timeInput))
      this.dateResult = dateFormat('YYYY-mm-dd HH:MM:SS', date)
    },

    strDateToTime() {
      const date = new Date(this.dateInput)
      this.timeResult = date.getTime()
      if (!this.timeResult) {
        this.timeResult = ''
      }
    },
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