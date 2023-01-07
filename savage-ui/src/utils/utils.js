import Clipboard from "clipboard";

/**
 * 时间格式转换
 * @param fmt  格式，例：YYYY-mm-dd HH:MM:SS
 * @param date 时间，date类型
 */
export function dateFormat(fmt, date) {
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
}

/**
 * 一键复制
 * @param documentId id
 */
export function copy(documentId) {
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