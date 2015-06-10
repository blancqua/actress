package org.actressframework.common;

import java.util.concurrent.ThreadFactory;

class NamedThreadFactory implements ThreadFactory {
    
    private String prefix;

    NamedThreadFactory(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, prefix);
    }
}
