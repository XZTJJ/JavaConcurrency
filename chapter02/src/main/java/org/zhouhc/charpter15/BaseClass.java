package org.zhouhc.charpter15;

public class BaseClass {
    private int count = 0;

    private int index = 0;

    private Integer[] integers = new Integer[10];

    public synchronized void doPut(Integer value){
        System.out.println("基类BaseClass的doPut 的 doPut 锁为:" + this);

    }

    public synchronized void doTake(){
        System.out.println("基类BaseClass的doTake 的 doTake 锁为:" + this);
    }

}
