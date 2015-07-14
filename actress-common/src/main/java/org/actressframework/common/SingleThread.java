package org.actressframework.common;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

import static org.actressframework.common.Executors.newSingleThreadExecutor;

public class SingleThread implements ExecutorService {

    private String threadName;
    private ExecutorService delegate;

    private SingleThread(String threadName, ExecutorService delegate) {
        this.threadName = threadName;
        this.delegate = delegate;
    }

    public static SingleThread singleThread(String threadName) {
        return new SingleThread(threadName, newSingleThreadExecutor(threadName));
    }

    public boolean matches(Thread thread) {
        return thread.getName().equals(threadName);
    }

    public void shutdown() {
        delegate.shutdown();
    }

    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return delegate.awaitTermination(timeout, unit);
    }

    public boolean isTerminated() {
        return delegate.isTerminated();
    }

    public Future<?> submit(Runnable task) {
        return delegate.submit(task);
    }

    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        return delegate.invokeAll(tasks, timeout, unit);
    }

    public <T> Future<T> submit(Callable<T> task) {
        return delegate.submit(task);
    }

    public List<Runnable> shutdownNow() {
        return delegate.shutdownNow();
    }

    public boolean isShutdown() {
        return delegate.isShutdown();
    }

    public void execute(Runnable command) {
        delegate.execute(command);
    }

    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return delegate.invokeAny(tasks);
    }

    public <T> Future<T> submit(Runnable task, T result) {
        return delegate.submit(task, result);
    }

    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return delegate.invokeAll(tasks);
    }

    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return delegate.invokeAny(tasks, timeout, unit);
    }
}
