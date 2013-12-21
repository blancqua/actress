package org.actressframework.test;

import static org.actressframework.test.Poller.aPoller;
import static org.actressframework.test.Sleeper.sleep;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

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
                assertThat(completed).isTrue();
            }
        };
    }

}
