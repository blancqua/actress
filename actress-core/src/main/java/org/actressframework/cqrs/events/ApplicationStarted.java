package org.actressframework.cqrs.events;

public class ApplicationStarted {

    private static final ApplicationStarted EVENT = new ApplicationStarted();

    public static ApplicationStarted applicationStarted() {
        return EVENT;
    }
    
    @Override
    public String toString() {
        return "application started";
    }

}
