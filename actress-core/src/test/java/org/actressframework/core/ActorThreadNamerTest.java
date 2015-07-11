package org.actressframework.core;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ActorThreadNamerTest {

    private ActorThreadNamer actorThreadNamer;

    @Before
    public void setUp() throws Exception {
        actorThreadNamer = new ActorThreadNamer();
    }

    @Test
    public void name() {
        Object actor = new Object();

        String actual = actorThreadNamer.name(actor);

        assertThat(actual).isEqualTo("Object-Actor-0");
    }

    @Test
    public void name_WhenCalledTwice() {
        Object actor = new Object();
        actorThreadNamer.name(actor);

        String actual = actorThreadNamer.name(actor);

        assertThat(actual).isEqualTo("Object-Actor-0");
    }

    @Test
    public void name_WhenCalledWithOtherObjectOfSameType() {
        Object actor = new Object();
        Object secondActor = new Object();
        actorThreadNamer.name(actor);

        String actual = actorThreadNamer.name(secondActor);

        assertThat(actual).isEqualTo("Object-Actor-1");
    }
}