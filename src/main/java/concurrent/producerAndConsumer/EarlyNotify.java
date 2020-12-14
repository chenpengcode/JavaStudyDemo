package concurrent.producerAndConsumer;

/**
 * @Description
 * @Author CP
 * @Date 2020/12/8
 */
public class EarlyNotify {
    private static final String lock = "";
    private static boolean isWait = true;
    static class WaitThread extends Thread {
        private final String lock;

        public WaitThread(String lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                while (isWait) {
                    System.out.println(Thread.currentThread().getName() + " 进入代码块");
                    System.out.println(Thread.currentThread().getName() + " 开始 wait");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " 结束 wait");
                }

            }
        }
    }
    static class NotifyThread extends Thread {
        private final String lock;

        NotifyThread(String lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " 进入代码块");
                System.out.println(Thread.currentThread().getName() + " 开始 notify");
                lock.notifyAll();
                isWait = false;
                System.out.println(Thread.currentThread().getName() + " 结束 notify");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WaitThread waitThread = new WaitThread(lock);
        NotifyThread notifyThread = new NotifyThread(lock);
        notifyThread.start();
        Thread.sleep(2000);
        waitThread.start();
    }
}
