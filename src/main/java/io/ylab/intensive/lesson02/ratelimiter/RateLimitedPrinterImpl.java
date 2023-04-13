package io.ylab.intensive.lesson02.ratelimiter;

public class RateLimitedPrinterImpl extends RateLimitedPrinter {

    private static long start;

    public RateLimitedPrinterImpl(int interval) {
        super(interval);
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


