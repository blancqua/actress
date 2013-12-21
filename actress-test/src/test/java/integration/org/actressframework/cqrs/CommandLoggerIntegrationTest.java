package integration.org.actressframework.cqrs;

import static org.actressframework.test.Poller.aPoller;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import javax.inject.Inject;

import org.actressframework.cqrs.CommandBus;
import org.actressframework.cqrs.CommandLogger;
import org.actressframework.test.Assertion;
import org.junit.Before;
import org.junit.Test;

public class CommandLoggerIntegrationTest extends AbstractIntegrationTest {

    @Inject
    private CommandBus commandBus;

    @Inject
    private CommandLogger logger;

    @Before
    public void setUp() throws Exception {
        commandBus.unregister(logger);
        CommandLogger spy = spy(logger);
        commandBus.register(spy);
        logger = spy;
    }

    @Test
    public void logCommand() throws Exception {
        final String command = "Don't listen to commands!";

        commandBus.post(command);

        aPoller().doAssert(new Assertion() {
            @Override
            public void assertion() throws Exception {
                verify(logger).logCommand(command);
            }
        });
    }

}
