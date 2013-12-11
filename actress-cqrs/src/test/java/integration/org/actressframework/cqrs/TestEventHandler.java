package integration.org.actressframework.cqrs;

import javax.inject.Named;

import org.actressframework.cqrs.EventHandler;

import com.google.common.eventbus.Subscribe;

@Named
public class TestEventHandler implements EventHandler {

    private boolean eventReceived = false;

    @Subscribe
    public void handleEvent(TestEvent event) {
        eventReceived = true;
    }
    
    public boolean hasReceivedEvent() {
        return eventReceived;
    }
    
    public void reset() {
        eventReceived = false;
    }
    
    public static final class TestEvent {}
    
}
