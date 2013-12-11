package integration.org.actressframework.cqrs;

import javax.inject.Named;

import org.actressframework.cqrs.EventHandler;
import org.actressframework.cqrs.events.ApplicationStarted;

import com.google.common.eventbus.Subscribe;

@Named
public class TestApplicationStartedHandler implements EventHandler {

    private boolean applicationStarted = false;
    
    @Subscribe
    public void applicationStarted(ApplicationStarted event) {
        applicationStarted = true;
    }
    
    public boolean hasReceivedApplicationStartedEvent() {
        return applicationStarted;
    }
    
}
