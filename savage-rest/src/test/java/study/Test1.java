package study;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Test1 {
    @Test
    public void test() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add(i);
        }
        for (int i = 0; i < 4; i++) {
            list.add(i);
        }
        list.forEach(System.out::print);
        System.out.println();
        Iterator<Integer> it = list.listIterator();
        while (it.hasNext()) {
            if (0 == it.next()) {
                it.remove();
            }
        }
        list.forEach(System.out::print);
        System.out.println();
        list = list.stream().distinct().collect(Collectors.toList());
        list.forEach(System.out::print);
        System.out.println();
    }

    @Test
    public void test1() {
        char[] a = {'1','2','3'};
        System.out.println(a);
    }

    @Test
    public void test2() {
        Map<String, Integer> map = new LinkedHashMap<>();
        List<String> list = getList();
        for (int i = 0; i < list.size(); i++) {
            if (map.get(list.get(i)) == null) {
                map.put(list.get(i), 1);
            } else {
                map.put(list.get(i), map.get(list.get(i)) + 1);
            }
        }
        Set<String> s1 = map.keySet();
        List<Integer> numList = new ArrayList<>();
        List<Integer> num = listInt();
        List<Integer> sum = new ArrayList<>();
        int flag = 0;
        for (String key : s1) {
            Integer value = map.get(key);
            System.out.println(key + "->>>>" + value);
            numList.add(value);
            int sumTemp = 0;
            for (int i = 0; i < value; i++) {
                sumTemp = sumTemp + num.get(flag);
                flag++;
            }
            sum.add(sumTemp);
            for (int i = 1; i < value; i++) {
                numList.add(0);
                sum.add(0);
            }
        }
        System.out.println(numList);
        System.out.println(sum);
        System.out.println(num);
    }


    public static List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("碧溪新区");
        list.add("碧溪新区");
        list.add("常福街道");
        list.add("常福街道");
        list.add("常福街道");
        list.add("东南街道");
        list.add("董浜镇");
        list.add("古里镇");
        list.add("古里镇");
        list.add("海虞镇");
        list.add("海虞镇");
        list.add("海虞镇");
        list.add("梅李镇");
        list.add("莫城街道");
        list.add("莫城街道");
        list.add("琴川街道");
        list.add("琴川街道");
        list.add("沙家浜镇");
        list.add("尚湖镇");
        list.add("尚湖镇");
        list.add("辛庄镇");
        list.add("辛庄镇");
        list.add("虞山街道");
        list.add("虞山街道");
        list.add("支塘镇");
        list.add("支塘镇");
        return list;
    }

    public List<Integer> listInt(){
        List<Integer> list = new ArrayList<>();
        list.add(28207);
        list.add(44720);
        list.add(31774);
        list.add(31723);
        list.add(19212);
        list.add(100548);
        list.add(18484);
        list.add(12302);
        list.add(35784);
        list.add(10879);
        list.add(22307);
        list.add(17439);
        list.add(43179);
        list.add(37997);
        list.add(67250);
        list.add(35257);
        list.add(54188);
        list.add(46681);
        list.add(23670);
        list.add(20645);
        list.add(49902);
        list.add(8472);
        list.add(45493);
        list.add(26935);
        list.add(8457);
        list.add(24799);
        return list;

    }
}
