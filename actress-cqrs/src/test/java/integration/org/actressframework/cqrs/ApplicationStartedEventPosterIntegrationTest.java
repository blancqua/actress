package integration.org.actressframework.cqrs;

import static org.fest.assertions.Assertions.assertThat;

import javax.inject.Inject;

import org.junit.Test;

public class ApplicationStartedEventPosterIntegrationTest extends AbstractIntegrationTest {
    
    @Inject
    private TestApplicationStartedHandler handler;
    
    @Test
    public void applicationStarted() throws Exception {
        assertThat(handler.hasReceivedApplicationStartedEvent()).isTrue();
    }

}
