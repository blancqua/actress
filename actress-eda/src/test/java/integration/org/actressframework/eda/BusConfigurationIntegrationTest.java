package integration.org.actressframework.eda;

import org.actressframework.eda.CommandBus;
import org.actressframework.eda.EventBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BusConfigurationIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private EventBus eventBus;
    @Autowired
    private CommandBus commandBus;

    @Autowired
    private TestEventHandler eventHandler;
    @Autowired
    private TestCommandHandler commandHandler;
    
    @BeforeEach
    public void setUp() {
        eventHandler.reset();
        commandHandler.reset();
    }
    
    @Test
    public void postEvent() throws Exception {
        eventBus.post(new TestEventHandler.TestEvent());
        
        assertTrue(eventHandler.hasReceivedEvent());
        assertFalse(commandHandler.hasReceivedCommand());
    }
    
    @Test
    public void postCommand() throws Exception {
        commandBus.post(new TestCommandHandler.TestCommand());
        
        assertFalse(eventHandler.hasReceivedEvent());
        assertTrue(commandHandler.hasReceivedCommand());
    }
}
