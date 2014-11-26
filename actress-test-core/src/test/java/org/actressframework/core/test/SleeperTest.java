package org.actressframework.core.test;

import org.junit.Test;

import static java.lang.System.currentTimeMillis;
import static org.assertj.core.api.Assertions.assertThat;

public class SleeperTest {

    private static final int TIME = 500;

    @Test
    public void sleep() throws Exception {
        long then = currentTimeMillis();
        Sleeper.sleep(TIME);
        assertThat(currentTimeMillis() - then).isGreaterThanOrEqualTo(TIME);
    }
    
}
