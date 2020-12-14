package concurrent.producerAndConsumer;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Description
 * @Author CP
 * @Date 2020/12/15
 */
public class BlockingQueueTest {
    private static final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(8);

    static class Producer implements Runnable {
        private final BlockingQueue<Integer> blockingQueue;

        Producer(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            while (true) {
                Random random = new Random();
                int i = random.nextInt();
                System.out.println(Thread.currentThread().getName() + " 生产了数据 " + i);
                try {
                    blockingQueue.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        private final BlockingQueue<Integer> blockingQueue;

        Consumer(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    int i = blockingQueue.take();
                    System.out.println(Thread.currentThread().getName() + " 消费了 " + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(15);
        for (int i = 0; i < 5; i++) {
            pool.submit(new Producer(queue));
        }
        for (int i = 0; i < 10; i++) {
            pool.submit(new Consumer(queue));
        }
    }
}
