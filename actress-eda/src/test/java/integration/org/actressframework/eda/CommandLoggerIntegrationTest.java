package integration.org.actressframework.eda;

import static org.actressframework.core.test.Poller.aPoller;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import javax.inject.Inject;

import org.actressframework.eda.CommandBus;
import org.actressframework.eda.CommandLogger;
import org.actressframework.core.test.Assertion;
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
                verify(logger).log(command);
            }
        });
    }

}
