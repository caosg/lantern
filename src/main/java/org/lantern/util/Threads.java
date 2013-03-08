package org.lantern.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class Threads {

    public static ExecutorService newCachedThreadPool(final String name) {
        return Executors.newCachedThreadPool(
            new ThreadFactory() {

            private final AtomicInteger count = new AtomicInteger();
            @Override
            public Thread newThread(final Runnable runner) {
                final Thread t = new Thread(runner, name+count);
                t.setDaemon(true);
                count.incrementAndGet();
                return t;
            }
        });
    }
    
    public static ThreadFactory newThreadFactory(final String name) {
        final AtomicInteger counter = new AtomicInteger();
        return new ThreadFactory() {
            @Override
            public Thread newThread(final Runnable run) {
                return new Thread(run, name + '-' + counter.getAndIncrement());
            }
        };
    }

}