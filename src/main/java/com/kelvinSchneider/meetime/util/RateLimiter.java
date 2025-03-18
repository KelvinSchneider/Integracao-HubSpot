package com.kelvinSchneider.meetime.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiter {

    private final int limit;
    private final AtomicInteger counter;
    private final ScheduledExecutorService scheduler;

    public RateLimiter(int limit, int intervalInSeconds) {
        this.limit = limit;
        this.counter = new AtomicInteger(0);

        this.scheduler = Executors.newScheduledThreadPool(1);
        this.scheduler.scheduleAtFixedRate(this::resetCounter, intervalInSeconds, intervalInSeconds, TimeUnit.SECONDS);
    }

    public boolean tryAcquire() {
        if (counter.get() < limit) {
            counter.incrementAndGet();
            return true;
        }
        return false;
    }

    private void resetCounter() {
        counter.set(0);
    }

    public void shutdown() {
        scheduler.shutdown();
    }
}