package org.actressframework.cqrs;

import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.actressframework.cqrs")
public class CQRSConfiguration {
    
    @Inject
    private List<EventHandler> eventHandlers;

    @Inject
    private List<CommandHandler> commandHandlers;

    @Bean
    public com.google.common.eventbus.EventBus guavaEventBus() {
        com.google.common.eventbus.EventBus eventBus = new com.google.common.eventbus.EventBus("eventbus");

        registerHandlers(eventBus, eventHandlers);

        return eventBus;
    }

    @Bean
    public com.google.common.eventbus.EventBus guavaCommandBus() {
        com.google.common.eventbus.EventBus commandBus = new com.google.common.eventbus.EventBus("commandbus");

        registerHandlers(commandBus, commandHandlers);

        return commandBus;
    }

    private void registerHandlers(com.google.common.eventbus.EventBus eventBus, Iterable<?> handlers) {
        for (Object handler : handlers) {
            eventBus.register(handler);
        }
    }
    
}
