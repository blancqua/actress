package integration.org.actressframework.eda;

import org.junit.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationStartedEventPosterIntegrationTest extends AbstractIntegrationTest {
    
    @Inject
    private TestApplicationStartedHandler handler;
    
    @Test
    public void applicationStarted() throws Exception {
        assertThat(handler.hasReceivedApplicationStartedEvent()).isTrue();
    }

}
