package integration.org.actressframework.eda;

import com.google.common.eventbus.Subscribe;
import org.actressframework.eda.EventHandler;
import org.actressframework.eda.event.ApplicationStarted;

import javax.inject.Named;

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
