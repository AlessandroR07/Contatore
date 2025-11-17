public class SynchronizedBlockExample implements Runnable {
    private final int increments;

    public SynchronizedBlockExample(int increments) {
        this.increments = increments;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < increments) {
            synchronized (Counter.class) {rf
                Counter.incrementUnsync();
            }
            i++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int threads = 4;
        int incrementsPerThread = 5000;

        System.out.println("SynchronizedBlockExample - usando blocco synchronized su Counter.class");
        Counter.reset();

        Thread[] ts = new Thread[threads];
        for (int i = 0; i < threads; i++) {
            ts[i] = new Thread(new SynchronizedBlockExample(incrementsPerThread), "SB-Worker-" + i);
        }

        for (Thread t : ts) t.start();
        for (Thread t : ts) t.join();

        int expected = threads * incrementsPerThread;
        int actual = Counter.getCount();
        System.out.println("Expected: " + expected + "  Actual: " + actual);
    }
}
