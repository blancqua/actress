package org.actressframework.cqrs;

import java.util.logging.Logger;

import javax.inject.Named;

import com.google.common.eventbus.Subscribe;

@Named
public class CommandLogger implements CommandHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandLogger.class);
    
    @Subscribe
    public void logCommand(Object command) {
        LOGGER.info(command.toString());
    }

}
