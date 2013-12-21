package org.actressframework.cqrs.command;

public class ScheduleAtFixedRateCommand extends ScheduleCommand {

    private int rate;
    
    public static ScheduleAtFixedRateCommand scheduleAtFixedRate(Object command, int delay, int rate) {
        return new ScheduleAtFixedRateCommand(command, delay, rate);
    }

    private ScheduleAtFixedRateCommand(Object command, int delay, int rate) {
        super(command, delay);
        this.rate = rate;
    }
    
    public int getRate() {
        return rate;
    }
    
    @Override
    public String toString() {
        return "schedule at fixed rate [delay=" + getDelay() + ", rate=" + getRate() + ", command=" + getCommand() + "]";
    }
    
}
