package integration.org.actressframework.eda;

import javax.inject.Named;

import org.actressframework.eda.eda.EventHandler;
import org.actressframework.eda.eda.events.ApplicationStarted;

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
