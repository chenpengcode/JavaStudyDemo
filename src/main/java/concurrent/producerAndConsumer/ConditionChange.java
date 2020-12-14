package concurrent.producerAndConsumer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author CP
 * @Date 2020/12/8
 */
public class ConditionChange {
    private final static List<String> lockObject = new ArrayList<>();

    public static void main(String[] args) {
        Consumer consumer1 = new Consumer(lockObject);
        Consumer consumer2 = new Consumer(lockObject);
        Producer producer = new Producer(lockObject);
        consumer1.start();
        consumer2.start();
        producer.start();
    }


    static class Consumer extends Thread {
        private final List<String> lock;

        public Consumer(List<String> lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    try {
                        //这里使用if的话，就会存在wait条件变化造成程序错误的问题
                        while (lock.isEmpty()) {
                            System.out.println(Thread.currentThread().getName() + " list为空");
                            System.out.println(Thread.currentThread().getName() + " 调用wait方法");
                            lock.wait();
                            System.out.println(Thread.currentThread().getName() + "  wait方法结束");
                        }
                        String element = lock.remove(0);
                        System.out.println(Thread.currentThread().getName() + " 取出第一个元素为：" + element);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


    }


    static class Producer extends Thread {
        private final List<String> lock;

        public Producer(List<String> lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + " 开始添加元素");
                    lock.add(Thread.currentThread().getName());
                    lock.notifyAll();
                }
            }
        }
    }
}