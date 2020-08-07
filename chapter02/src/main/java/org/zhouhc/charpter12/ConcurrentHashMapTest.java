package org.zhouhc.charpter12;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.*;

public class ConcurrentHashMapTest {
    public static void main(String[] args) throws Exception {
        testConcurrentMap();
    }

    public static void testConcurrentMap() throws InterruptedException {
        ConcurrentMap<String, Object> concurrentMap = new ConcurrentHashMap<String, Object>();
        int clidCount = 10;
        int ThreaSize = 100;
        int index_total = 3;
        int forCount = 300;
        Random random = new Random();

        final String[] clidArrays = new String[clidCount];
        for (int i = 0; i < clidCount; i++)
            clidArrays[i] = UUID.randomUUID().toString().replaceAll("-", "");

        ExecutorService executorService = Executors.newFixedThreadPool(ThreaSize);

        for (int i = 0; i < forCount; i++) {
            int num = random.nextInt(index_total) + 1;
            int clidIndex = random.nextInt(clidArrays.length);
            String clid = clidArrays[clidIndex];
            executorService.execute(new RunnableForConcurrentHashMap(concurrentMap, clid + "_" + num, num + "", index_total));
        }

        executorService.shutdown();
        executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);
        if (executorService.isTerminated())
            System.out.println("线程结束了");

    }
}
