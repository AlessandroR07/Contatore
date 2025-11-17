public class Counter implements Runnable {
    // variabile condivisa tra tutti i thread
    private static int count = 0;

    // se true usa il metodo synchronized, altrimenti usa il metodo non sincronizzato
    private final boolean useSync;
    // numero di incrementi da eseguire per ogni thread (es. 5000)
    private final int increments;

    public Counter(boolean useSync, int increments) {
        this.useSync = useSync;
        this.increments = increments;
    }

    @Override
    public void run() {
        int i = 0;
        // ciclo while con variabile locale i
        while (i < increments) {
            if (useSync) {
                incrementSync();
            } else {
                incrementUnsync();
            }
            i++;
        }
    }

    // metodo synchronized (mutua esclusione)
    public static synchronized void incrementSync() {
        count++;
    }

    // metodo NON synchronized (mostra la race condition)
    public static void incrementUnsync() {
        count++;
    }

    // getter per leggere il contatore
    public static int getCount() {
        return count;
    }

    // reset del contatore (synchronized per sicurezza)
    public static synchronized void reset() {
        count = 0;
    }
}
