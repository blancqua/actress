package org.actressframework.core;

import static com.google.common.collect.Maps.newHashMap;
import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.currentThread;
import static org.actressframework.core.Poller.aPoller;
import static org.actressframework.core.Sleeper.sleep;
import static org.fest.assertions.Assertions.assertThat;

import java.util.Map;

import org.actressframework.core.Actor;
import org.junit.Before;
import org.junit.Test;

public class ActorTest {

    private static final String TEST = "test";
    
    private TestActor actor;
    
    @Before
    public void setUp() {
        actor = new TestActor();
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
        aPoller().doAssert(new Assertion() {
            @Override
            public void assertion() throws Exception {
                assertThat(actor.callingThreads.size()).isEqualTo(1);
                assertThat(actor.callingThreads.get("TestActor-Actor")).isEqualTo(numberOfCalls);
            }
        });
    }
    
    @Actor
    public class TestActor {

        private Map<String, Integer> callingThreads = newHashMap();
        
        public void callWithoutReturnValueActorThread() {
            augmentInThreadCalls();
        }

        public void callLongRunningProcessInActorThread() {
            sleep(2000);
        }

        public void callWithInternalCallInActorThread() {
            callWithoutReturnValueActorThread();
        }
        
        void callPackageVisibleInActorThread() {
            augmentInThreadCalls();
        }

        public void callCrashInActorThread() {
            augmentInThreadCalls();
            throw new RuntimeException("KABOOM");
        }
        
        public String callWithReturnValueInActorThread(String returnMe) {
            augmentInThreadCalls();
            return returnMe;
        }
        
        private synchronized void augmentInThreadCalls() {
            Integer calls = callingThreads.get(currentThread().getName());
            if (calls == null) {
                calls = 0;
            }
            callingThreads.put(currentThread().getName(), ++calls);
        }
        
    }
    
}
