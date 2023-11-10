package org.example;

public class Consumer implements Runnable {
    private final CircularBuffer buffer;

    public Consumer(CircularBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            consume();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void consume() throws InterruptedException {
        while (true) {
            while (buffer.isEmpty()) {
                Thread.sleep(1000); // Buffer is empty, wait for producer
                System.out.println("Buffer is empty, waiting...");
            }
            int value = buffer.get();
            System.out.println("Consumed: " + value);
        }
    }
}
