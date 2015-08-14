package org.actressframework.core;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ThreadNamerTest {

    private ThreadNamer threadNamer;

    @Before
    public void setUp() throws Exception {
        threadNamer = new ThreadNamer();
    }

    @Test
    public void name() {
        Object actor = new Object();

        String actual = threadNamer.name(actor);

        assertThat(actual).isEqualTo("Object-Actor-1");
    }

    @Test
    public void name_WhenCalledTwice() {
        Object actor = new Object();
        threadNamer.name(actor);

        String actual = threadNamer.name(actor);

        assertThat(actual).isEqualTo("Object-Actor-1");
    }

    @Test
    public void name_WhenCalledWithOtherObjectOfSameType() {
        Object actor = new Object();
        Object secondActor = new Object();
        threadNamer.name(actor);

        String actual = threadNamer.name(secondActor);

        assertThat(actual).isEqualTo("Object-Actor-2");
    }
}
