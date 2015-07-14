package org.actressframework.core;

import org.actressframework.common.SingleThread;

abstract class ThreadFactory {

    static ThreadFactory instance() {
        return new PerActorThreadFactory();
    }

    abstract SingleThread create(Object target);
}
