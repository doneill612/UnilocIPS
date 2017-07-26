package com.bdrd.unilocips.app;

import java.util.concurrent.Future;

/**
 * Created by doneill on 26/07/2017.
 */

abstract class FixedLengthRunnable implements Runnable {

    private Future<?> future;

    @Override
    public void run() {
        if(!repeat()) cancel();
    }

    abstract boolean repeat();

    void assignFuture(Future<?> future) {
        this.future = future;
    }

    private void cancel() {
        future.cancel(true);
        ThreadingManager.purge();
    }

}
