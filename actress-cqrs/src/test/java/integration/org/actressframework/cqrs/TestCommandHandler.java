package integration.org.actressframework.cqrs;

import javax.inject.Named;

import org.actressframework.cqrs.CommandHandler;

import com.google.common.eventbus.Subscribe;

@Named
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
