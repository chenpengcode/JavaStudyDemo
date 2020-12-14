package concurrent.producerAndConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/**
 * @Description
 * @Author CP
 * @Date 2020/12/15
 */
public class ConditionTest {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition full = lock.newCondition();
    private static final Condition empty = lock.newCondition();

    static class Producer implements Runnable {
        private final List<Integer> list;
        private final int maxLength;
        private final Lock lock;

        Producer(List<Integer> list, int maxLength, Lock lock) {
            this.list = list;
            this.maxLength = maxLength;
            this.lock = lock;
        }

        @Override
        public void run() {
            while (true) {
                // 首先获得锁
                lock.lock();
                try {
                    // 判断消息队列是否已经满了
                    while (maxLength == list.size()) {
                        // 将线程加入到等待队列，此时条件为full
                        full.await();
                        System.out.println(Thread.currentThread().getName() + " 队列已经满了");
                    }
                    Random random = new Random();
                    int i = random.nextInt();
                    list.add(i);
                    System.out.println(Thread.currentThread().getName() + " 生产了数据 " + i);
                    // 唤醒条件为empty的所有线程
                    empty.signalAll();
                    // sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        private final List<Integer> list;
        private final Lock lock;

        Consumer(List<Integer> list, Lock lock) {
            this.list = list;
            this.lock = lock;
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (list.isEmpty()) {
                        empty.await();
                        System.out.println(Thread.currentThread().getName() + " 队列为空");
                    }
                    int i = list.remove(0);
                    System.out.println(Thread.currentThread().getName() + " 消费了消息 " + i);
                    // 通知条件为满的线程生产消息
                    full.signalAll();
                    // sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        int maxLength = 8;
        Executor pool = Executors.newFixedThreadPool(15);
        for (int i = 0; i < 5; i++) {
            pool.execute(new Producer(list, maxLength, lock));
        }
        for (int i = 0; i < 10; i++) {
            pool.execute(new Consumer(list, lock));
        }
    }
}
