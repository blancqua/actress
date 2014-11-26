package org.actressframework.eda.command.scheduler;

public class ScheduleOnceCommand extends ScheduleCommand {

    public static ScheduleOnceCommand scheduleOnce(Object command, int delay) {
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
