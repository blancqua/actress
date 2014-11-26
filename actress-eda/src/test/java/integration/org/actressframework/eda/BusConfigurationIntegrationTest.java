package integration.org.actressframework.eda;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;

import org.actressframework.eda.eda.CommandBus;
import org.actressframework.eda.eda.EventBus;
import org.junit.Before;
import org.junit.Test;

public class BusConfigurationIntegrationTest extends AbstractIntegrationTest {

    @Inject
    private EventBus eventBus;
    @Inject
    private CommandBus commandBus;
    
    @Inject
    private TestEventHandler eventHandler;
    @Inject
    private TestCommandHandler commandHandler;
    
    @Before
    public void setUp() {
        eventHandler.reset();
        commandHandler.reset();
    }
    
    @Test
    public void postEvent() throws Exception {
        eventBus.post(new TestEventHandler.TestEvent());
        
        assertThat(eventHandler.hasReceivedEvent()).isTrue();
        assertThat(commandHandler.hasReceivedCommand()).isFalse();
    }
    
    @Test
    public void postCommand() throws Exception {
        commandBus.post(new TestCommandHandler.TestCommand());
        
        assertThat(eventHandler.hasReceivedEvent()).isFalse();
        assertThat(commandHandler.hasReceivedCommand()).isTrue();
    }
}
