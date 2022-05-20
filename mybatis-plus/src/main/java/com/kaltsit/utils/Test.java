package com.kaltsit.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test {

    //添加属性：
    private static final int seq = 0;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    /**************
     * @return 此方法返回唯一的订单编号
     */
    public static String generateNo(Long time, int i) {
        //格式化数字的方法
        //“%03d" 的意思是：3表示占3位字符宽度，0表示前补0，当目标数字不足3位时，前面补0以达到3位，%d表示数字
        String iStr = String.format("%03d", i);
        return time + iStr;
    }

    public static void main(String[] args) {
        int total = 2020;
        //获取当前系统时间
        LocalDateTime startTime = LocalDateTime.now();
        //2. 格式化
        Long start = Long.valueOf(dtf.format(startTime));
        int startNom = 0;
        int sum = 0;
        while (true){
            LocalDateTime nowTime = LocalDateTime.now();
            Long now = Long.valueOf(dtf.format(nowTime));
            if(startNom < 1000 && now.equals(start)){
                System.out.println(generateNo(now, startNom));
                startNom ++;
                sum ++;
            }
            if(now > start){
                start = now;
                startNom = 0;
            }
            if(sum >= total){
                break;
            }
        }

    }


}
