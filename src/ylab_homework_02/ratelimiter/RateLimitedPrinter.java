package ylab_homework_02.ratelimiter;

public abstract class RateLimitedPrinter {

    protected final int interval;

    public RateLimitedPrinter(int interval) {
        this.interval = interval;
    }

    public void print(String message) {

    }
}

