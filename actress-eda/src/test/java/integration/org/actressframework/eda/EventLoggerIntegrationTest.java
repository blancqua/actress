package integration.org.actressframework.eda;

import org.actressframework.core.test.Assertion;
import org.actressframework.eda.EventBus;
import org.actressframework.eda.EventLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.actressframework.core.test.Poller.aPoller;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class EventLoggerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private EventBus eventBus;

    @Autowired
    private EventLogger logger;
    
    @BeforeEach
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
