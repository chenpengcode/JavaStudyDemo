package concurrent.printinturn;

/**
 * @Description
 * @Author CP
 * @Date 2020/12/1
 */
public class TwoThread {
    static class PrintOdd implements Runnable {
        private Counter counter;

        PrintOdd(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (counter) {
                    while (!counter.isOdd) {
                        try {
                            counter.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + " : " + counter.num);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ++counter.num;
                    counter.isOdd = false;
                    counter.notifyAll();
                }

            }
        }
    }

    static class PrintEven implements Runnable {
        private Counter counter;

        PrintEven(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (counter) {
                    while (counter.isOdd) {
                        try {
                            counter.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + " : " + counter.num);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ++counter.num;
                    counter.isOdd = true;
                    counter.notifyAll();
                }
            }
        }
    }

    static class Counter {
        private int num = 1;
        private boolean isOdd = true;
    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        PrintOdd printOdd = new PrintOdd(counter);
        PrintEven printEven = new PrintEven(counter);
        new Thread(printOdd).start();
        new Thread(printEven).start();
    }
}
