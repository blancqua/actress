package integration.org.actressframework.eda;

import com.google.common.eventbus.Subscribe;
import org.actressframework.eda.CommandHandler;
import org.springframework.stereotype.Component;

@Component
public class TestCommandHandler implements CommandHandler {

    private boolean commandReceived = false;

    @Subscribe
    public void handleCommand(TestCommand command) {
        commandReceived = true;
    }
    
    public boolean hasReceivedCommand() {
        return commandReceived;
    }
    
    public void reset() {
        commandReceived = false;
    }
    
    public static final class TestCommand {}
    
}
