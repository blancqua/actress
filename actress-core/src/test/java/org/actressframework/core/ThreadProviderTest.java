package org.actressframework.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;

import static org.junit.jupiter.api.Assertions.*;

public class ThreadProviderTest {

    private ThreadProvider provider = ThreadProvider.instance();

    private Object actor = new Object();
    private Object otherActor = new Object();

    @Test
    public void provide() {
        ExecutorService actorThread = provider.provide(actor);
        ExecutorService otherActorThread = provider.provide(otherActor);

        ExecutorService actual = provider.provide(actor);

        assertSame(actual, actorThread);
        assertNotSame(actual, otherActorThread);
    }
}