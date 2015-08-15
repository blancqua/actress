package org.actressframework.core;

import org.actressframework.core.test.Assertion;
import org.actressframework.core.test.Poller;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import static java.lang.System.currentTimeMillis;
import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = ActressConfiguration.class)
public class ActorTest extends AbstractJUnit4SpringContextTests {

    private static final String TEST = "test";

    @Autowired
    private TestActor actor;

    @Before
    public void setUp() throws Exception {
        actor.reset();
    }

    @Test
    public void callWithoutReturnValueActorThread() throws Exception {
        actor.callWithoutReturnValueActorThread();
        
        assertNumberOfCallsInActorThread(1);
    }
    
    @Test
    public void callWithInternalCallInActorThread() throws Exception {
        actor.callWithInternalCallInActorThread();
        
        assertNumberOfCallsInActorThread(1);
    }
    
    @Test
    public void callPackageVisibleInActorThread() throws Exception {
        actor.callPackageVisibleInActorThread();
        
        assertNumberOfCallsInActorThread(1);
    }
    
    @Test
    public void callCrashInActorThread() throws Exception {
        actor.callWithoutReturnValueActorThread();
        actor.callWithoutReturnValueActorThread();
        actor.callCrashInActorThread();
        actor.callWithoutReturnValueActorThread();
        actor.callWithoutReturnValueActorThread();
        
        assertNumberOfCallsInActorThread(5);
    }
    
    @Test
    public void callLongRunningProcessInActorThread() throws Exception {
        long then = currentTimeMillis();
        
        actor.callLongRunningProcessInActorThread();
        
        assertThat(currentTimeMillis() - then).isLessThan(100);
    }
        
    @Test
    public void callWithReturnValueInActorThread() throws Exception {
        String returnValue = actor.callWithReturnValueInActorThread(TEST);
        
        assertThat(returnValue).isEqualTo(TEST);
        
        assertNumberOfCallsInActorThread(1);
    }
    
    private void assertNumberOfCallsInActorThread(final int numberOfCalls) {
        Poller.aPoller().doAssert(new Assertion() {
            @Override
            public void assertion() throws Exception {
                assertThat(actor.callingThreads().size()).isEqualTo(1);
                assertThat(actor.callingThreads().get("TestActor-Actor-1")).isEqualTo(numberOfCalls);
            }
        });
    }
}
