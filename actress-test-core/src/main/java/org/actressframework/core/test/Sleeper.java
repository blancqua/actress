package org.actressframework.core.test;

public class Sleeper {

    private static final int DEFAULT = 5000;

    public static void sleep() {
        sleep(DEFAULT);
    }

    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

}
