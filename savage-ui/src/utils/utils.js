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

export const elementIcons = [
    "platform-eleme",
    "eleme",
    "delete-solid",
    "delete",
    "s-tools",
    "setting",
    "user-solid",
    "user",
    "phone",
    "phone-outline",
    "more",
    "more-outline",
    "star-on",
    "star-off",
    "s-goods",
    "goods",
    "warning",
    "warning-outline",
    "question",
    "info",
    "remove",
    "circle-plus",
    "success",
    "error",
    "zoom-in",
    "zoom-out",
    "remove-outline",
    "circle-plus-outline",
    "circle-check",
    "circle-close",
    "s-help",
    "help",
    "minus",
    "plus",
    "check",
    "close",
    "picture",
    "picture-outline",
    "picture-outline-round",
    "upload",
    "upload2",
    "download",
    "camera-solid",
    "camera",
    "video-camera-solid",
    "video-camera",
    "message-solid",
    "bell",
    "s-cooperation",
    "s-order",
    "s-platform",
    "s-fold",
    "s-unfold",
    "s-operation",
    "s-promotion",
    "s-home",
    "s-release",
    "s-ticket",
    "s-management",
    "s-open",
    "s-shop",
    "s-marketing",
    "s-flag",
    "s-comment",
    "s-finance",
    "s-claim",
    "s-custom",
    "s-opportunity",
    "s-data",
    "s-check",
    "s-grid",
    "menu",
    "share",
    "d-caret",
    "caret-left",
    "caret-right",
    "caret-bottom",
    "caret-top",
    "bottom-left",
    "bottom-right",
    "back",
    "right",
    "bottom",
    "top",
    "top-left",
    "top-right",
    "arrow-left",
    "arrow-right",
    "arrow-down",
    "arrow-up",
    "d-arrow-left",
    "d-arrow-right",
    "video-pause",
    "video-play",
    "refresh",
    "refresh-right",
    "refresh-left",
    "finished",
    "sort",
    "sort-up",
    "sort-down",
    "rank",
    "loading",
    "view",
    "c-scale-to-original",
    "date",
    "edit",
    "edit-outline",
    "folder",
    "folder-opened",
    "folder-add",
    "folder-remove",
    "folder-delete",
    "folder-checked",
    "tickets",
    "document-remove",
    "document-delete",
    "document-copy",
    "document-checked",
    "document",
    "document-add",
    "printer",
    "paperclip",
    "takeaway-box",
    "search",
    "monitor",
    "attract",
    "mobile",
    "scissors",
    "umbrella",
    "headset",
    "brush",
    "mouse",
    "coordinate",
    "magic-stick",
    "reading",
    "data-line",
    "data-board",
    "pie-chart",
    "data-analysis",
    "collection-tag",
    "film",
    "suitcase",
    "suitcase-1",
    "receiving",
    "collection",
    "files",
    "notebook-1",
    "notebook-2",
    "toilet-paper",
    "office-building",
    "school",
    "table-lamp",
    "house",
    "no-smoking",
    "smoking",
    "shopping-cart-full",
    "shopping-cart-1",
    "shopping-cart-2",
    "shopping-bag-1",
    "shopping-bag-2",
    "sold-out",
    "sell",
    "present",
    "box",
    "bank-card",
    "money",
    "coin",
    "wallet",
    "discount",
    "price-tag",
    "news",
    "guide",
    "male",
    "female",
    "thumb",
    "cpu",
    "link",
    "connection",
    "open",
    "turn-off",
    "set-up",
    "chat-round",
    "chat-line-round",
    "chat-square",
    "chat-dot-round",
    "chat-dot-square",
    "chat-line-square",
    "message",
    "postcard",
    "position",
    "turn-off-microphone",
    "microphone",
    "close-notification",
    "bangzhu",
    "time",
    "odometer",
    "crop",
    "aim",
    "switch-button",
    "full-screen",
    "copy-document",
    "mic",
    "stopwatch",
    "medal-1",
    "medal",
    "trophy",
    "trophy-1",
    "first-aid-kit",
    "discover",
    "place",
    "location",
    "location-outline",
    "location-information",
    "add-location",
    "delete-location",
    "map-location",
    "alarm-clock",
    "timer",
    "watch-1",
    "watch",
    "lock",
    "unlock",
    "key",
    "service",
    "mobile-phone",
    "bicycle",
    "truck",
    "ship",
    "basketball",
    "football",
    "soccer",
    "baseball",
    "wind-power",
    "light-rain",
    "lightning",
    "heavy-rain",
    "sunrise",
    "sunrise-1",
    "sunset",
    "sunny",
    "cloudy",
    "partly-cloudy",
    "cloudy-and-sunny",
    "moon",
    "moon-night",
    "dish",
    "dish-1",
    "food",
    "chicken",
    "fork-spoon",
    "knife-fork",
    "burger",
    "tableware",
    "sugar",
    "dessert",
    "ice-cream",
    "hot-water",
    "water-cup",
    "coffee-cup",
    "cold-drink",
    "goblet",
    "goblet-full",
    "goblet-square",
    "goblet-square-full",
    "refrigerator",
    "grape",
    "watermelon",
    "cherry",
    "apple",
    "pear",
    "orange",
    "coffee",
    "ice-tea",
    "ice-drink",
    "milk-tea",
    "potato-strips",
    "lollipop",
    "ice-cream-square",
    "ice-cream-round"
].map(s => "el-icon-" + s);
