package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 2) {
            System.out.println("Please provide two arguments: <max integers to produce> <number of consumer threads>");
            return;
        }

        int maxProduced = Integer.parseInt(args[0]);
        int numConsumers = Integer.parseInt(args[1]);
        int bufferSize = 10; // Can be set as needed
        CircularBuffer buffer = new CircularBuffer(bufferSize);

        Thread producerThread = new Thread(new Producer(buffer, maxProduced));
        producerThread.start();

        Thread[] consumers = new Thread[numConsumers];
        for (int i = 0; i < numConsumers; i++) {
            consumers[i] = new Thread(new Consumer(buffer));
            consumers[i].start();
        }

        producerThread.join();
        for (Thread consumer : consumers) {
            consumer.interrupt(); // Signal consumers to exit
            consumer.join();
        }
    }
}
