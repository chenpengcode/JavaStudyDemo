package concurrent.printinturn;

/**
 * @Description
 * @Author CP
 * @Date 2020/12/1
 */
public class TwoThread2 {

    static class Print implements Runnable {
        int n = 1;
        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    notifyAll();
                    System.out.println(Thread.currentThread().getName() + " : " + n);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ++n;
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Print print = new Print();
        new Thread(print).start();
        new Thread(print).start();
    }
}
