package org.actressframework.cqrs;

import javax.inject.Named;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Named
public class EventBus implements ApplicationContextAware {

    private com.google.common.eventbus.EventBus guavaEventBus;

    public void register(EventHandler eventHandler) {
        guavaEventBus.register(eventHandler);
    };

    public void unregister(EventHandler eventHandler) {
        guavaEventBus.unregister(eventHandler);
    }

    public void post(Object event) {
        guavaEventBus.post(event);
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        guavaEventBus = applicationContext.getBean("guavaEventBus", com.google.common.eventbus.EventBus.class);
    }
}
