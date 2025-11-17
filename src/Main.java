public class Main {
    public static void main(String[] args) throws InterruptedException {
        int threads = 4;
        int incrementsPerThread = 5000;

        System.out.println("Numero thread: " + threads + ", incrementi per thread: " + incrementsPerThread);
        System.out.println("---------------------------------------------------");


        System.out.println("Test senza synchronized:");
        Counter.reset();
        runTest(threads, incrementsPerThread, false);

        System.out.println();


        System.out.println("Test con synchronized:");
        Counter.reset();
        runTest(threads, incrementsPerThread, true);
    }

    private static void runTest(int threads, int incrementsPerThread, boolean useSync) throws InterruptedException {
        Thread[] ts = new Thread[threads];

        for (int i = 0; i < threads; i++) {
            ts[i] = new Thread(new Counter(useSync, incrementsPerThread), "Worker-" + i);
        }

        long start = System.currentTimeMillis();

        for (Thread t : ts) t.start();
        for (Thread t : ts) t.join();

        long end = System.currentTimeMillis();

        int expected = threads * incrementsPerThread;
        int actual = Counter.getCount();

        System.out.println("Expected (threads * increments): " + expected);
        System.out.println("Actual count: " + actual);
        System.out.println("Tempo (ms): " + (end - start));
    }
}
