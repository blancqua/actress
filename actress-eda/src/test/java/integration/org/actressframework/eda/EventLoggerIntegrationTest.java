package integration.org.actressframework.eda;

import org.actressframework.core.test.Assertion;
import org.actressframework.eda.EventBus;
import org.actressframework.eda.EventLogger;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static org.actressframework.core.test.Poller.aPoller;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

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
                verify(logger).log(event);
            }
        });
    }
    
}
