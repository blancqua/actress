package org.actressframework.cqrs;

import static org.slf4j.LoggerFactory.getLogger;

import javax.inject.Named;

import org.slf4j.Logger;

import com.google.common.eventbus.Subscribe;

@Named
public class EventLogger implements EventHandler {

    private static final Logger LOGGER = getLogger(EventLogger.class);
    
    @Subscribe
    public void logEvent(Object event) {
        LOGGER.info(event.toString());
    }

}
