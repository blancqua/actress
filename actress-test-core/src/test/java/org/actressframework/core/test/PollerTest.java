package org.actressframework.core.test;

import org.junit.jupiter.api.Test;

import static org.actressframework.core.test.Poller.aPoller;
import static org.actressframework.core.test.Sleeper.sleep;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PollerTest {

    private Poller poller = aPoller();

    int counter = 5;
    boolean completed = false;

    @Test
    public void withCondition() throws Exception {
        startLongRunningProcess();

        poller.doAssert(processIsCompleted());
    }

    private void startLongRunningProcess() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (counter >= 0) {
                    counter--;
                    sleep(100);
                }
                completed = true;
            }
        }).start();
    }

    private Assertion processIsCompleted() {
        return new Assertion() {
            @Override
            public void assertion() throws Exception {
                assertTrue(completed);
            }
        };
    }

}
