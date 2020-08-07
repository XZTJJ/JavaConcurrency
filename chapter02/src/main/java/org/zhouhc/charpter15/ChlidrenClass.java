package org.zhouhc.charpter15;

public class ChlidrenClass extends BaseClass {

    public ChlidrenClass() {
    }

    @Override
    public synchronized void doPut(Integer value) {
        System.out.println("子类ChlidrenClass 的 doPut 锁为:" + this);

        super.doPut(value);
    }

    public synchronized void ceTeal() {
        System.out.println("子类ChlidrenClass 的 ceTeal锁为:" + this);

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void testOthers() {
        super.doTake();
    }

    public static void main(String[] args) {
        ChlidrenClass chlidrenClass = new ChlidrenClass();
        chlidrenClass.doPut(10);
        chlidrenClass.ceTeal();
        chlidrenClass.testOthers();
    }
}
