package org.actressframework.eda;

import javax.inject.Named;

@Named(EventBus.ACTRESS_EVENT_BUS)
public class EventBus extends AbstractBus<EventHandler> {

    static final String ACTRESS_EVENT_BUS = "actress-eventBus";

    public EventBus() {
        super(ACTRESS_EVENT_BUS);
    }
}
