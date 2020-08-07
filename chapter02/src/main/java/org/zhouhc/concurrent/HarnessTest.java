package org.zhouhc.concurrent;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.concurrent.CountDownLatch;

public class HarnessTest {
    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        CountDownLatch startGate = new CountDownLatch(1);
        CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        System.out.println("当前线程:"+Thread.currentThread().getId()+"，等待开始锁");
                        startGate.await();
                        task.run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        endGate.countDown();
                    }

                }
            };
            t.start();
        }

        long start = System.currentTimeMillis();
        startGate.countDown();
        endGate.await();
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static void main(String[] args) throws InterruptedException {
        HarnessTest harnessTest = new HarnessTest();
        long l = harnessTest.timeTasks(10, new HarnessRunnable());
        System.out.println("执行所有的任务的时间为:" + l);
    }
}
