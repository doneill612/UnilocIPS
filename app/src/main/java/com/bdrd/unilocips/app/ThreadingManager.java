package com.bdrd.unilocips.app;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author David O'Neill
 */
final class ThreadingManager {

    private static ScheduledExecutorService scheduler;
    private static ExecutorService executor;
    private static Provider provider;
    private static boolean ready;

    static void init() {

        final int processors = Runtime.getRuntime().availableProcessors();
        final int corePoolSize = processors >= 8 ? 4 : processors >= 4 ? 2 : 1;
        scheduler = Executors.newScheduledThreadPool(corePoolSize,
                        new ScheduledThreadFactory(new ScheduledThreadHandler()));
        executor = Executors.newCachedThreadPool();
        provider = new Provider(true);
        Log.d("ThreadingManager",
                "ScheduledExecutorService established, pool size = " + corePoolSize);
        ready = true;

    }

    static Provider getProvider() {
        return provider;
    }

    static void purge() {
        ((ScheduledThreadPoolExecutor) scheduler).purge();
    }

    static boolean isReady() {
        return ready;
    }

    private static class ScheduledThreadHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            Log.d("ScheduledThreadHandler", "Caught exception on thread " + t.getName() +
                    ": " + e.getMessage() + ": Printing trace.");
            e.printStackTrace();
        }
    }

    private static class ScheduledThreadFactory implements ThreadFactory {

        private final ScheduledThreadHandler handler;
        private final ThreadGroup group;
        private final String namePrefix;
        private final AtomicInteger threadNumber;

        private ScheduledThreadFactory(ScheduledThreadHandler handler) {
            this.handler = handler;
            this.threadNumber = new AtomicInteger(1);
            SecurityManager m = System.getSecurityManager();
            this.group = m == null ? Thread.currentThread().getThreadGroup() : m.getThreadGroup();
            this.namePrefix = "ScheduledThreadPool -- thread -- ";
        }

        @Override
        public Thread newThread(@NonNull Runnable r) {
            Thread thread = new Thread(group, r, namePrefix + threadNumber.getAndIncrement());
            if(thread.isDaemon())
                thread.setDaemon(false);
            if(thread.getPriority() < Thread.NORM_PRIORITY)
                thread.setPriority(Thread.NORM_PRIORITY);
            thread.setUncaughtExceptionHandler(handler);
            return thread;
        }
    }

    static class Provider {

        private boolean verbose;

        private Provider(boolean verbose) {
            this.verbose = verbose;
            Log.d("Provider", "Provider initialized : verbose? " + this.verbose);
        }

        void scheduleFixedLengthTask(FixedLengthRunnable r, long delay,
                                     long frequeuncy, TimeUnit unit) {
            Future<?> f =
                    ThreadingManager.scheduler.scheduleWithFixedDelay(r, delay, frequeuncy, unit);
            r.assignFuture(f);
            if(verbose)
                Log.d("Provider", "Scheduled request : Fixed Length Task");
        }

        void scheduleRepeatingTask(Runnable r, long delay, long frequency, TimeUnit unit) {
            ThreadingManager.scheduler.scheduleWithFixedDelay(r, delay, frequency, unit);
            if(verbose)
                Log.d("Provider", "Scheduled request : Repeating Task");
        }

        void executeTask(Runnable r) {
            ThreadingManager.executor.execute(r);
        }

        Future<?> sumbitTask(Callable c) {
            return ThreadingManager.executor.submit(c);
        }
    }


}
