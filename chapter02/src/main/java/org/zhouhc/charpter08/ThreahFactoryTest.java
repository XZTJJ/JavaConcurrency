package org.zhouhc.charpter08;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

//Java线测试
public class ThreahFactoryTest {

    //测试赛
    public static void main(String[] args) throws  InterruptedException{
        int count = 5;
        //测试方法
        ExecutorService service1 = new MyThreadPool(count);
        //创建任务
        for (int i = 0; i < count; i++)
            service1.execute(new Runnable() {
                @Override
                public void run() {

                    try {
                        System.out.println(Thread.currentThread().getName()+"线程开始准备休眠15s");
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                    }
                }
            });

        //关闭线程
        service1.shutdown();
        service1.awaitTermination(150,TimeUnit.SECONDS);
    }


    //实现工厂模式,
    private static class MyFactory implements ThreadFactory {
        private final AtomicInteger atomicInteger = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            //创建线程
            System.out.println("创建了线程 :  嘿嘿测试线程" + atomicInteger.get());
            return new Thread(r, "嘿嘿测试线程" + atomicInteger.getAndIncrement());
        }
    }

    //创建自定义的线程池
    private static class MyThreadPool extends ThreadPoolExecutor {
        //定义构造函数。必须填写的
        public MyThreadPool(int poolSize) {
            super(poolSize, poolSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new MyFactory());
        }

        //重写新的方法，必选调用原来的方法
        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            super.beforeExecute(t, r);
            try {
                System.out.println(t.getName() + "执行command之前的方法");
            } catch (Exception e) {

            }
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            try {
                System.out.println(Thread.currentThread().getName() + "执行command之后的方法");
            } catch (Exception e) {
            } finally {
                super.afterExecute(r, t);
            }
        }
    }
}
