package ylab_homework_02.ratelimiter;

public class RateLimitedPrinter {
    private final int interval;
    private long start;

    public RateLimitedPrinter(int interval) {
        this.interval = interval;

    }

    public void print(String message) {
        long currentTimeMillis = System.currentTimeMillis();

        if (start == 0) {
            System.out.println(message);
            start = currentTimeMillis;
        }

        long printInterval = currentTimeMillis - start;

        if (printInterval > interval) {
            System.out.println(message);
            start = currentTimeMillis;
        }
    }
}

