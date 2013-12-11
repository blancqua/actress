package org.actressframework.core;




public class Poller {

    private static final int POLLING_INTERVAL_MS = 50;
    private static final int TIMEOUT_MS = 60000;

    private int pollingInterval = POLLING_INTERVAL_MS;
    private int timeout = TIMEOUT_MS;
    private Condition condition;

    private Poller() {
    }

    public static Poller aPoller() {
        return new Poller();
    }

    public void doAssert(Assertion assertion) {
        withCondition(assertion.getCondition()).poll();
    }

    public void run(Runnable runnable) {
        withCondition(new DefaultCondition(runnable)).poll();
    }

    public Poller withCondition(Condition condition) {
        this.condition = condition;
        return this;
    }

    public Poller withTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public Poller withPollingInterval(int pollingInterval) {
        this.pollingInterval = pollingInterval;
        return this;
    }

    public void poll() {
        long startTime = System.currentTimeMillis();
        while (!condition.validate() && getDurationSince(startTime) < timeout) {
            Sleeper.sleep(pollingInterval);
        }
        if (getDurationSince(startTime) >= timeout) {
            throw condition.exceptionToThrowAfterTimeout();
        }
    }

    private long getDurationSince(long startTime) {
        return System.currentTimeMillis() - startTime;
    }

}
