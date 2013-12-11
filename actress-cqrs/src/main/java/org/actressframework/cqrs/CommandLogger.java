package org.actressframework.cqrs;

import static org.slf4j.LoggerFactory.getLogger;

import javax.inject.Named;

import org.slf4j.Logger;

import com.google.common.eventbus.Subscribe;

@Named
public class CommandLogger implements CommandHandler {

    private static final Logger LOGGER = getLogger(CommandLogger.class);
    
    @Subscribe
    public void logCommand(Object command) {
        LOGGER.info(command.toString());
    }

}
