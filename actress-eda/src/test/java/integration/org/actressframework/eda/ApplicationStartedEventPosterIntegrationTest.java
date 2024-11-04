package integration.org.actressframework.eda;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApplicationStartedEventPosterIntegrationTest extends AbstractIntegrationTest {
    
    @Autowired
    private TestApplicationStartedHandler handler;
    
    @Test
    public void applicationStarted() throws Exception {
        assertTrue(handler.hasReceivedApplicationStartedEvent());
    }

}
