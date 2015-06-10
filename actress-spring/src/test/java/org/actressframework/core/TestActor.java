package org.actressframework.core;

import org.actressframework.core.test.Sleeper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.currentThread;

@Component @Actor
public class TestActor {

    private Map<String, Integer> callingThreads = new HashMap<String, Integer>();

    public void callWithoutReturnValueActorThread() {
        augmentInThreadCalls();
    }

    public void callLongRunningProcessInActorThread() {
        Sleeper.sleep(2000);
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

    public Map<String, Integer> callingThreads() {
        return callingThreads;
    }

    public void reset() {
        callingThreads = new HashMap<String, Integer>();
    }
}
