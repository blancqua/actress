package org.actressframework.cqrs.command;

public class ScheduleOnceCommand extends ScheduleCommand {

    public static ScheduleCommand scheduleOnce(Object command, int delay) {
        return new ScheduleOnceCommand(command, delay);
    }

    private ScheduleOnceCommand(Object command, int delay) {
        super(command, delay);
    }
    
    @Override
    public String toString() {
        return "schedule once [delay=" + getDelay() + ", command=" + getCommand() + "]";
    }
    
}
