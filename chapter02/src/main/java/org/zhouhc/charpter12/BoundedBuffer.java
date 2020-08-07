package org.zhouhc.charpter12;

import java.util.concurrent.Semaphore;

public class BoundedBuffer<E> {
    private final Semaphore availableItems, availableSpaces;
    private E[] items;
    private int putPosition = 0;
    private int takePosition = 0;

    public BoundedBuffer(int size) {
        this.availableItems = new Semaphore(0);
        this.availableSpaces = new Semaphore(size);
        this.items = (E[]) new Object[size];
    }

    public boolean isEmpty() {
        return availableItems.availablePermits() == 0;
    }

    public boolean isFull() {
        return availableSpaces.availablePermits() == 0;
    }

    public void put(E e) throws InterruptedException {
        availableSpaces.acquire();
        InsertItems(e);
        availableItems.release();
    }

    public E take() throws InterruptedException {
        availableItems.acquire();
        E e = doExtract();
        availableSpaces.release();
        return e;
    }

    public void InsertItems(E e) {
        int i = putPosition;
        items[i] = e;
        putPosition = (++i == items.length) ? 0 : i;
    }

    public E doExtract() {
        int i = takePosition;
        E e = items[i];
        takePosition = (++i == items.length) ? 0 : i;
        return e;
    }
}
