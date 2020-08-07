package org.zhouhc.concurrent;

public class HarnessRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("当前线程:"+Thread.currentThread().getId());
    }
}
