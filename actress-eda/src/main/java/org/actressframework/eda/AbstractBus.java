package org.actressframework.eda;

import com.google.common.eventbus.EventBus;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public abstract class AbstractBus<T> implements ApplicationListener<ContextRefreshedEvent> {

    private com.google.common.eventbus.EventBus delegate;

    protected AbstractBus(String identifier) {
        delegate = new EventBus(identifier);
    }

    public void register(T handler) {
        delegate.register(handler);
    }

    public void unregister(T handler) {
        delegate.unregister(handler);
    }

    public void post(Object command) {
        delegate.post(command);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for (T handler : handlers()) {
            register(handler);
        }
        whenStarted();
    }

    protected abstract Iterable<T> handlers();

    protected void whenStarted() {}
}
