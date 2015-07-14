package org.actressframework.core;

import org.actressframework.common.SingleThread;

import static org.actressframework.common.SingleThread.singleThread;

class RoundRobinThreadFactory extends ThreadFactory {

    private SingleThread[] threadPool;
    private int currentIndex = 0;

    RoundRobinThreadFactory(int size) {
        threadPool = new SingleThread[size];
    }

    @Override
    synchronized SingleThread create(Object target) {
        SingleThread thread = threadPool[currentIndex];
        if (thread == null) {
            thread = singleThread("Actor-Pool-" + currentIndex);
        }
        currentIndex = currentIndex + 1 % threadPool.length;
        return thread;
    }
}
