package org.actressframework.eda;

import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

import java.util.ArrayList;
import java.util.List;

import static org.actressframework.eda.events.ApplicationStarted.applicationStarted;

@Named(EventBus.ACTRESS_EVENT_BUS)
public class EventBus extends AbstractBus<EventHandler> {

    static final String ACTRESS_EVENT_BUS = "actress-eventBus";

    @Autowired(required = false)
    private List<EventHandler> handlers = new ArrayList<EventHandler>();

    public EventBus() {
        super(ACTRESS_EVENT_BUS);
    }

    @Override
    protected Iterable<EventHandler> handlers() {
        return handlers;
    }

    @Override
    protected void whenStarted() {
        post(applicationStarted());
    }
}
