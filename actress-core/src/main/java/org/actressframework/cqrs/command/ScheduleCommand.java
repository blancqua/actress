package org.actressframework.cqrs.command;

import static java.util.UUID.randomUUID;

import java.util.UUID;

public abstract class ScheduleCommand {
    
    private UUID identifier = randomUUID();

    protected Object command;
    protected int delay;

    public ScheduleCommand(Object command, int delay) {
        this.command = command;
        this.delay = delay;
    }

    public Object getCommand() {
        return command;
    }

    public int getDelay() {
        return delay;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ScheduleCommand other = (ScheduleCommand) obj;
        if (identifier == null) {
            if (other.identifier != null) {
                return false;
            }
        } else if (!identifier.equals(other.identifier)) {
            return false;
        }
        return true;
    }

}
