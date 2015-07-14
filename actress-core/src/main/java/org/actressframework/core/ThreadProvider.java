package org.actressframework.core;

import org.actressframework.common.SingleThread;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.currentThread;

class ThreadProvider {

    private ThreadFactory threadFactory = ThreadFactory.instance();

    private Map<Object, SingleThread> threads = new HashMap<Object, SingleThread>();

    private ThreadProvider() {}

    static ThreadProvider instance() {
        return new ThreadProvider();
    }

    synchronized SingleThread provide(Object target) {
        SingleThread thread = threads.get(target);
        if (thread == null) {
            thread = threadFactory.create(target);
            threads.put(target, thread);
        }
        return thread;
    }

    boolean inActorThread(Object target) {
        return provide(target).matches(currentThread());
    }
}
