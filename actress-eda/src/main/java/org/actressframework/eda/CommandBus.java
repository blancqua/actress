package org.actressframework.eda;

import javax.inject.Named;

@Named(CommandBus.ACTRESS_COMMAND_BUS)
public class CommandBus extends AbstractBus<CommandHandler> {

    static final String ACTRESS_COMMAND_BUS = "actress-commandBus";

    public CommandBus() {
        super(ACTRESS_COMMAND_BUS);
    }
}
