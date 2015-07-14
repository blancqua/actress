package org.actressframework.core;

import org.junit.Test;

import java.util.concurrent.ExecutorService;

import static org.assertj.core.api.Assertions.assertThat;

public class ThreadProviderTest {

    private ThreadProvider provider = ThreadProvider.instance();

    private Object actor = new Object();
    private Object otherActor = new Object();

    @Test
    public void provide() {
        ExecutorService actorThread = provider.provide(actor);
        ExecutorService otherActorThread = provider.provide(otherActor);

        ExecutorService actual = provider.provide(actor);

        assertThat(actual).isSameAs(actorThread);
        assertThat(actual).isNotSameAs(otherActorThread);
    }
}