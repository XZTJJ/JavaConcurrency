package org.zhouhc.charpter12;

import junit.framework.TestCase;

public class TestBoundedBuffer extends TestCase {
    private static final long LOCKUP_DETECT_TIMEOUT = 1000;
    private static final int CAPACITY = 10000;
    private static final int THRESHOLD = 10000;

    public void testTakeBlocksWhenEmpty() {
        final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
        Thread tasker = new Thread() {
            @Override
            public void run() {
                try {
                    bb.take();
                    fail();
                } catch (InterruptedException e) {
                    System.out.println("成功了");
                }
            }
        };

        try {
            tasker.start();
            Thread.sleep(LOCKUP_DETECT_TIMEOUT);
            tasker.interrupt();
            tasker.join(LOCKUP_DETECT_TIMEOUT);
            assertFalse(tasker.isAlive());
            tasker.getState();
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
