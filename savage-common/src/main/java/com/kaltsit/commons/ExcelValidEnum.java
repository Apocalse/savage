package com.kaltsit.commons;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

@AllArgsConstructor
public enum ExcelValidEnum {

    NULL("", "可以为空"),//不校验
    NOTNULL("\\S", "不为空"),
    BIRTHDAY("[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}", "生日"),
    BIRTHDAY_NOTNULL("[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}", "生日"),
    EMAIL("\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?", "邮件"),
    EMAIL_NOTNULL("\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?", "邮件"),
    IDCARD("[1-9]\\d{13,16}[a-zA-Z0-9]{1}", "身份证"),
    IDCARD_NOTNULL("[1-9]\\d{13,16}[a-zA-Z0-9]{1}", "身份证"),
    DIGIT("\\-?[1-9]\\d+", "整数"),
    DIGIT_NOTNULL("\\-?[1-9]\\d+", "整数"),
    DECIMAL("[-+]?[0-9]*\\.?[0-9]+", "浮点数"),
    DECIMAL_NOTNULL("[-+]?[0-9]*\\.?[0-9]+", "浮点数"),
    POSTCODE("[1-9]\\d{5}", "邮编"),
    POSTCODE_NOTNULL("[1-9]\\d{5}", "邮编"),
    CHINESE("[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}", "中文"),
    CHINESE_NOTNULL("[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}", "中文"),
    PHONE("^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$", "手机号码"),
    PHONE_NOTNULL("^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$", "手机号码"),
    CP("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{3,4}[A-Z0-9挂学警港澳]{1}$", "车牌号"),
    CP_NOTNULLL("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{3,4}[A-Z0-9挂学警港澳]{1}$", "车牌号");



    private String regex;
    private String msg;

    /**
     * 正则校验
     * @param enums
     * @param name
     * @param val
     * @return
     */
    public static String regexCheck(ExcelValidEnum enums, String name, Object val) {
        String msgs = null;

        if (enums.name().equals(ExcelValidEnum.NULL.name())) {
            return msgs;
        }
        if (enums.name().equals(ExcelValidEnum.NOTNULL.name())) {
            boolean notEmpty = StringUtils.isNotEmpty((CharSequence) val);
            if (notEmpty) {
                return msgs;
            } else {
                msgs = ("【" + name + "】不符合" + enums.getMsg() + "的规则");
                return msgs;
            }
        }
        if (StringUtils.isNotEmpty(enums.regex)&& StringUtils.isNotEmpty((CharSequence) val)) {
            boolean flag = Pattern.matches(enums.getRegex(), String.valueOf(val));
            if (!flag) {
                msgs = ("【" + name + "】不符合" + enums.getMsg() + "的规则");
                return msgs;
            }
        }
        if (StringUtils.isNotEmpty(enums.regex)&& enums.name().endsWith("NOTNULL") ){
            if (StringUtils.isEmpty((CharSequence) val)){
                msgs = ("【" + name + "】不能为空");
                return msgs;
            }
            boolean flag = Pattern.matches(enums.getRegex(), String.valueOf(val));
            if (!flag) {
                msgs = ("【" + name + "】不符合" + enums.getMsg() + "的规则");
                return msgs;
            }

        }


        return msgs;

    }

    private String getRegex() {
        return regex;
    }

    private String getMsg() {
        return msg;
    }

}


