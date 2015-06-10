package integration.org.actressframework.eda;

import com.google.common.eventbus.Subscribe;
import org.actressframework.eda.EventHandler;
import org.springframework.stereotype.Component;

@Component
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
