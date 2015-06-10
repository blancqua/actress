package integration.org.actressframework.eda;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationStartedEventPosterIntegrationTest extends AbstractIntegrationTest {
    
    @Autowired
    private TestApplicationStartedHandler handler;
    
    @Test
    public void applicationStarted() throws Exception {
        assertThat(handler.hasReceivedApplicationStartedEvent()).isTrue();
    }

}
