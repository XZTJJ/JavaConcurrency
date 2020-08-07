package org.zhouhc.collectionstest;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class CollectionsTest {

    public static void main(String[] args) {

        List<Integer> testList = new ArrayList<Integer>();
        testList.add(new Integer(1));
        testList.add(new Integer(2));
        testList.add(new Integer(3));
        testList.add(new Integer(4));


        List<Integer> strings = new ArrayList<Integer>(testList);
        System.out.println("");



        if (true) {
            try {
             throw new RuntimeException("啊哈哈哈哈哈哈哈");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
