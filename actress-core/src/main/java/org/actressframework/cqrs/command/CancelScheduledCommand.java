package org.actressframework.cqrs.command;

public class CancelScheduledCommand {

    private ScheduleCommand scheduledCommand;

    public static CancelScheduledCommand cancel(ScheduleCommand scheduledCommand) {
        return new CancelScheduledCommand(scheduledCommand);
    }
    
    private CancelScheduledCommand(ScheduleCommand scheduledCommand) {
        this.scheduledCommand = scheduledCommand;
    }

    public ScheduleCommand getScheduledCommand() {
        return scheduledCommand;
    }
    
    @Override
    public String toString() {
        return "cancel schedule [" + scheduledCommand + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((scheduledCommand == null) ? 0 : scheduledCommand.hashCode());
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
        CancelScheduledCommand other = (CancelScheduledCommand) obj;
        if (scheduledCommand == null) {
            if (other.scheduledCommand != null) {
                return false;
            }
        } else if (!scheduledCommand.equals(other.scheduledCommand)) {
            return false;
        }
        return true;
    }
    
}
