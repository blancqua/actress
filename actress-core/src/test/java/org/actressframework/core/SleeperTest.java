package org.actressframework.core;

import static org.fest.assertions.Assertions.assertThat;
import static org.joda.time.DateTime.now;

import org.actressframework.core.Sleeper;
import org.joda.time.DateTime;
import org.junit.Test;

public class SleeperTest {

    private static final int TIME = 500;

    @Test
    public void sleep() throws Exception {
        DateTime then = now();
        Sleeper.sleep(TIME);
        assertThat(now().getMillis() - then.getMillis()).isGreaterThanOrEqualTo(TIME);
    }
    
}
