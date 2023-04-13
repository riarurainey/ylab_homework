package io.ylab.intensive.lesson02.ratelimiter;

public class RateLimitedPrinterTest {

    public static void main(String[] args) {
        RateLimitedPrinter rateLimitedPrinter = new RateLimitedPrinterImpl(1000);

        for (int i = 0; i < 1_000_000_000; i++) {
            rateLimitedPrinter.print("i");
        }

    }
}
