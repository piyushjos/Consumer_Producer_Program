package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CircularBuffer {
    private final int[] buffer;
    private int count = 0;
    private int in = 0;
    private int out = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public CircularBuffer(int size) {
        buffer = new int[size];
    }

    public boolean isFull(){
        return buffer.length == count;
    }

    public boolean isEmpty(){
        return buffer.length == 0;
    }

    public void put(int value) throws InterruptedException {
        lock.lock();
        try {
            while (count == buffer.length) {
                notFull.await();
            }
            buffer[in] = value;
            in = (in + 1) % buffer.length;
            count++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public int get() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            int result = buffer[out];
            out = (out + 1) % buffer.length;
            count--;
            notFull.signal();
            return result;
        } finally {
            lock.unlock();
        }
    }
}
