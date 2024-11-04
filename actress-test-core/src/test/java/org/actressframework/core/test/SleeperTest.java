package org.actressframework.core.test;

import org.junit.jupiter.api.Test;

import static java.lang.System.currentTimeMillis;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SleeperTest {

    private static final int TIME = 500;

    @Test
    public void sleep() throws Exception {
        long then = currentTimeMillis();
        Sleeper.sleep(TIME);
        assertTrue(currentTimeMillis() - then >= TIME);
    }
    
}
