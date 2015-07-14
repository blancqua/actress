package org.actressframework.core;

import org.actressframework.common.SingleThread;

import static org.actressframework.common.SingleThread.singleThread;

class PerActorThreadFactory extends ThreadFactory {

    private ThreadNamer threadNamer = new ThreadNamer();

    @Override
    synchronized SingleThread create(Object target) {
        return singleThread(threadNamer.name(target));
    }
}
