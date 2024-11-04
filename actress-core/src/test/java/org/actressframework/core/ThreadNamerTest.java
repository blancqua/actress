package org.actressframework.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThreadNamerTest {

    private ThreadNamer threadNamer;

    @BeforeEach
    public void setUp() throws Exception {
        threadNamer = new ThreadNamer();
    }

    @Test
    public void name() {
        Object actor = new Object();

        String actual = threadNamer.name(actor);

        assertEquals(actual, "Object-Actor-1");
    }

    @Test
    public void name_WhenCalledTwice() {
        Object actor = new Object();
        threadNamer.name(actor);

        String actual = threadNamer.name(actor);

        assertEquals(actual, "Object-Actor-1");
    }

    @Test
    public void name_WhenCalledWithOtherObjectOfSameType() {
        Object actor = new Object();
        Object secondActor = new Object();
        threadNamer.name(actor);

        String actual = threadNamer.name(secondActor);

        assertEquals(actual, "Object-Actor-2");
    }
}
