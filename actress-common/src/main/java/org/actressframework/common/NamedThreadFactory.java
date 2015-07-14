package org.actressframework.common;

import java.util.concurrent.ThreadFactory;

class NamedThreadFactory implements ThreadFactory {
    
    private String threadName;

    NamedThreadFactory(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, threadName);
    }
}
