package org.actressframework.core.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

public class Executors {

    public static ExecutorService newSingleThreadExecutor(String prefix) {
        return java.util.concurrent.Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory(prefix));
    }
    
    public static ScheduledExecutorService newSingleThreadScheduledExecutor(String prefix) {
        return java.util.concurrent.Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory(prefix));
    }
    
}
