package org.actressframework.eda;

import com.google.common.eventbus.EventBus;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.inject.Inject;
import java.util.List;

public abstract class AbstractBus<T> implements ApplicationListener<ContextRefreshedEvent> {

    private com.google.common.eventbus.EventBus delegate;

    @Inject
    private List<T> handlers;

    protected AbstractBus(String identifier) {
        delegate = new EventBus(identifier);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for (T handler : handlers) {
            register(handler);
        }
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
}
