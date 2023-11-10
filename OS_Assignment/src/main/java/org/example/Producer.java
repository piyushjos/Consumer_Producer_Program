package org.example;

public class Producer implements Runnable {
    private final CircularBuffer buffer;
    private final int max;

    public Producer(CircularBuffer buffer, int max) {
        this.buffer = buffer;
        this.max = max;
    }

    @Override
    public void run() {
        try {
            produce();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void produce() throws InterruptedException {
        for (int i = 0; i < max; i++) {
            while (buffer.isFull()) {
                Thread.sleep(1000); // Buffer is full, wait for consumers
                System.out.println("Buffer is full, waiting...");
            }
            buffer.put(i);
            System.out.println("Produced: " + i);
        }
    }
}
