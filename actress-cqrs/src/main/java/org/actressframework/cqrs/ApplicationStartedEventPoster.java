package org.actressframework.cqrs;

import static org.actressframework.cqrs.events.ApplicationStarted.applicationStarted;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

@Named
public class ApplicationStartedEventPoster implements ApplicationListener<ContextRefreshedEvent> {

    @Inject
    private EventBus eventBus;
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        eventBus.post(applicationStarted());
    }

}
