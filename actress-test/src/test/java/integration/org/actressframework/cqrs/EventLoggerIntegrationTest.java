package integration.org.actressframework.cqrs;

import static org.actressframework.test.Poller.aPoller;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import javax.inject.Inject;

import org.actressframework.cqrs.EventBus;
import org.actressframework.cqrs.EventLogger;
import org.actressframework.test.Assertion;
import org.junit.Before;
import org.junit.Test;

public class EventLoggerIntegrationTest extends AbstractIntegrationTest {

    @Inject
    private EventBus eventBus;
    
    @Inject
    private EventLogger logger;
    
    @Before
    public void setUp() throws Exception {
        eventBus.unregister(logger);
        EventLogger spy = spy(logger);
        eventBus.register(spy);
        logger = spy;
    }
    
    @Test
    public void logEvent() throws Exception {
        final String event = "Don't listen to events!";
        
        eventBus.post(event);
        
        aPoller().doAssert(new Assertion() {
            @Override
            public void assertion() throws Exception {
                verify(logger).logEvent(event);
            }
        });
    }
    
}
