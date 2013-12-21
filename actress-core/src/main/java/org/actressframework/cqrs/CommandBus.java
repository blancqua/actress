package org.actressframework.cqrs;

import javax.inject.Named;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

@Named
public class CommandBus implements ApplicationListener<ContextRefreshedEvent> {

    private com.google.common.eventbus.EventBus guavaCommandBus;

    public void register(CommandHandler commandHandler) {
        guavaCommandBus.register(commandHandler);
    }

    public void unregister(CommandHandler commandHandler) {
        guavaCommandBus.unregister(commandHandler);
    }

    public void post(Object command) {
        guavaCommandBus.post(command);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        guavaCommandBus = event.getApplicationContext().getBean("guavaCommandBus", com.google.common.eventbus.EventBus.class);
    }

}
