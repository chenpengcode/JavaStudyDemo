package concurrent.producerAndConsumer;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @Description
 * @Author CP
 * @Date 2020/11/25
 */
public class PCWithWaitNotify {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        Producer producer = new Producer(list, 8);
        new Thread(producer).start();
        Consumer consumer = new Consumer(list);
        new Thread(consumer).start();
    }

    static class Producer implements Runnable {
        private final List<Integer> list;
        private final int capacity;

        public Producer(List<Integer> list, int capacity) {
            this.list = list;
            this.capacity = capacity;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (list) {
                    while (capacity == list.size()) {
                        System.out.println("producer: " + Thread.currentThread().getName() + " : list is full, waiting");
                        try {
                            list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("producer: " + Thread.currentThread().getName() + " : end waiting");
                    }
                    Random random = new Random();
                    int i = random.nextInt();
                    System.out.println("Producer: " + Thread.currentThread().getName() + " : product data" + i);
                    list.add(i);
                    list.notifyAll();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Consumer implements Runnable {
        private final List<Integer> list;

        Consumer(List<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (list) {
                    while (list.isEmpty()) {
                        System.out.println("consumer: " + Thread.currentThread().getName() + " can not consume, reason: list is empty, waiting");
                        try {
                            list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("consumer: " + Thread.currentThread().getName() + " : end waiting");
                    }
                    int i = list.remove(0);
                    System.out.println("consumer: " + Thread.currentThread().getName() + " : consume data: " + i);
                    list.notifyAll();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}