package org.zhouhc.charpter13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LockTest.class);

    public static void main(String[] args) throws Exception {
        //testLock();

        testOtherUnlock();
    }

    public static void testOtherUnlock() throws Exception {
        final Lock lock = new ReentrantLock();

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    boolean b = lock.tryLock(3000,TimeUnit.MILLISECONDS);
                    if (!b)
                        return;
                    BigComputer();
                    LOGGER.info("子线程执行完毕");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    LOGGER.info("我实在另一个地方释放锁诶");
                    //lock.unlock();
                }
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    LOGGER.info("我是线程2，我要一直持有锁，不释放");
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };

        Thread thread3 = new Thread() {
            @Override
            public void run() {

                try {
                    lock.lockInterruptibly();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LOGGER.info("我是线程3，看看能不能获取锁");

            }
        };

        thread2.start();
        Thread.sleep(200);
        thread.start();
        Thread.sleep(200);
        thread3.start();


    }

    public static void testLock() {
        long startTime = System.currentTimeMillis();
        final Lock lock = new ReentrantLock();
        //线程锁相关性能
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    /* 轮询锁，定时锁
                    if(lock.tryLock()) {
                        BigComputer();
                        LOGGER.info("子线程执行完毕");
                    }
                     */

                    lock.lockInterruptibly();
                    BigComputer();
                    LOGGER.info("子线程执行完毕");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //lock.unlock();
                }
            }
        };


        Thread thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    LOGGER.info("我是线程2，我要一直持有锁，不释放");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                }
            }
        };

        thread2.start();
        thread.start();

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
        try {
            thread.join(2000);
            long endTime = System.currentTimeMillis();
            LOGGER.info("thread线程是否还活着" + thread.isAlive() + "，耗时为:" + (endTime - startTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOGGER.info("主线程执行完毕");
    }


    public static void BigComputer() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
