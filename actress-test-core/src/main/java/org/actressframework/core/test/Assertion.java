package org.actressframework.core.test;


import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class Assertion {

    private final Condition condition;

    public Assertion() {
        condition = new DefaultCondition(new Runnable() {

            @Override
            public void run() {
                try {
                    assertion();
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    Condition getCondition() {
        return condition;
    }

    public static Assertion forCondition(final Condition condition) {
        return new Assertion() {

            @Override
            public void assertion() throws Exception {
                assertTrue(condition.validate());
            }
        };
    }

    public abstract void assertion() throws Exception;

}
