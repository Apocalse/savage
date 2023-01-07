package study;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Test1 {
    @Test
    public void test(){
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
        while (it.hasNext()){
            if(0==it.next()){
                it.remove();
            }
        }
        list.forEach(System.out::print);
        System.out.println();
        list = list.stream().distinct().collect(Collectors.toList());
        list.forEach(System.out::print);
        System.out.println();
    }
}
