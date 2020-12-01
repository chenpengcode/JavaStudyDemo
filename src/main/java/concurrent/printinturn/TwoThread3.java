package concurrent.printinturn;

/**
 * @Description
 * @Author CP
 * @Date 2020/12/1
 */
public class TwoThread3 {
    static class Counter {
        private int num = 1;
        private int flag = 1;
    }

    static class Print1 implements Runnable {
        Counter counter;

        public Print1(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (counter) {
                    while (counter.flag != 1) {
                        try {
                            counter.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    for (int i = 0; i < 5; i++) {
                        System.out.println(Thread.currentThread().getName() + " : " + counter.num ++);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    counter.flag = 2;
                    counter.notify();
                }
            }
        }
    }

    static class Print2 implements Runnable {
        Counter counter;

        public Print2(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (counter) {
                    while (counter.flag != 2) {
                        try {
                            counter.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    for (int i = 0; i < 5; i++) {
                        System.out.println(Thread.currentThread().getName() + " : " + counter.num ++);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    counter.flag = 1;
                    counter.notify();
                }
            }
        }
    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        Print1 print1 = new Print1(counter);
        Print2 print2 = new Print2(counter);
        new Thread(print1).start();
        new Thread(print2).start();
    }
}
