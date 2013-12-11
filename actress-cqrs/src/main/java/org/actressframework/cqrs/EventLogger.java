package org.actressframework.cqrs;

import java.util.logging.Logger;

import javax.inject.Named;

import com.google.common.eventbus.Subscribe;

@Named
public class EventLogger implements EventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventLogger.class);
    
    @Subscribe
    public void logEvent(Object event) {
        LOGGER.info(event.toString());
    }

}
