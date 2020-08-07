package org.zhouhc.charpter14;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.util.locale.provider.LocaleServiceProviderPool;

public class takeThread {
    private final static Logger LOGGER = LoggerFactory.getLogger(takeThread.class);

    public static void main(String[] args) {
        testTake();
    }

    public static void testTake() {

        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        LOGGER.info("准备休眠3s了");
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw  new RuntimeException();
                    }
                }
            }
        };

        thread.start();
        LOGGER.info("主线程执行完毕");

        try {
            Thread.sleep(15000);
            LOGGER.info("主线程休眠完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        thread.interrupt();
    }
}
