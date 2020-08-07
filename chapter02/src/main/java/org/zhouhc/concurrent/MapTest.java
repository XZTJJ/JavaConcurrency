package org.zhouhc.concurrent;

import org.apache.commons.collections.map.UnmodifiableMap;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MapTest {

    public static void main(String[] args) throws Exception {
        //testMap();
        testConcurrentMap();
    }

    public static void testConcurrentMap() throws Exception{
        //并发线程锁
        ConcurrentMap<String, Object> concurrentMap = new ConcurrentHashMap<String, Object>();
        //测试数据
        AtomicInteger atomicInteger = new AtomicInteger(0);
        concurrentMap.putIfAbsent("188#_#now", atomicInteger);
        concurrentMap.putIfAbsent("188#_#total", new Integer("10"));
        //
        AtomicInteger firstAtomic = (AtomicInteger) concurrentMap.get("188#_#now");
        firstAtomic.addAndGet(1);
        firstAtomic.addAndGet(1);
        int first = firstAtomic.get();
        concurrentMap.putIfAbsent("188#_#now", new AtomicInteger(0));

        AtomicInteger secondAtomic = (AtomicInteger) concurrentMap.get("188#_#now");
        int second = secondAtomic.get();
        Integer total = (Integer)concurrentMap.get("188#_#total");
        total++;
        int totalInt = total.intValue();
        System.out.println();

        int count = 1000000;
        long startTime = System.nanoTime();
        for(int i =0;i<count;i++)
            concurrentMap.putIfAbsent(i+"now",i);
        long endTime = System.nanoTime();
        System.out.println("添加1百万个数据耗时为:"+(endTime-startTime)+"纳秒");

        startTime = System.nanoTime();
        int thisOne = (int)concurrentMap.get("999999now");
        endTime = System.nanoTime();
        System.out.println("从1百万个数据中取一个数据耗时:"+(endTime-startTime)+"纳秒");

        startTime = System.nanoTime();
        for(int i =0;i<count;i++)
            concurrentMap.remove(i+"now");
        endTime = System.nanoTime();
        System.out.println("删掉1百万个数据耗时为:"+(endTime-startTime)+"纳秒,剩余数据大小:"+concurrentMap.size());
    }

    public static void testMap() throws Exception{
        Map<String, MutablePoint> testMap = null;

        Map<String, MutablePoint> testMapV2 = new HashMap<String, MutablePoint>();
        testMapV2.put("testOne", new MutablePoint(1, 1));
        testMapV2.put("testTwo", new MutablePoint(2, 2));
        testMapV2.put("testThree", new MutablePoint(3, 3));
        testMap = Collections.unmodifiableMap(testMapV2);

        if (testMap.containsKey("testOne")) {
            MutablePoint s = testMap.get("testOne");
            s.x = 9;
            s.y = 9;
        }

        System.out.println("");

        Thread.sleep(10);

        Map<String, String> testMapV1 = new HashMap<String, String>();
        testMapV1.put("one", "one");
        String two = testMapV1.get("two");
        System.out.println(two);

        Map<String, Integer> testConMap = new ConcurrentHashMap<String, Integer>();

        testConMap.putIfAbsent("3", 6);

    }
}
