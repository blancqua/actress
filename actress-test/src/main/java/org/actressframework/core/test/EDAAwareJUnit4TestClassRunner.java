package org.actressframework.core.test;

import org.actressframework.eda.CommandBus;
import org.actressframework.eda.CommandHandler;
import org.actressframework.eda.EventBus;
import org.actressframework.eda.EventHandler;
import org.junit.internal.runners.InitializationError;
import org.junit.internal.runners.MethodRoadie;
import org.junit.internal.runners.TestMethod;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.inject.annotation.TestedObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

import static org.fest.reflect.core.Reflection.field;
import static org.unitils.util.AnnotationUtils.getFieldsAnnotatedWith;

public class EDAAwareJUnit4TestClassRunner extends UnitilsJUnit4TestClassRunner {

    private Class<?> testClass;

    public EDAAwareJUnit4TestClassRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
        this.testClass = testClass;
    }

    @Override
    protected MethodRoadie createMethodRoadie(Object testObject, Method testMethod, TestMethod jUnitTestMethod, RunNotifier notifier, Description description) {
        return new BusAwareTestListenerInvokingMethodRoadie(testObject, testMethod, jUnitTestMethod, notifier, description);
    }

    /**
     * Copy of org.unitils.UnitilsJUnit4TestClassRunner.TestListenerInvokingMethodRoadie
     * + register TestedObject to EventBus of CommandBus if EventHandler/CommandHandler
     */
    private final class BusAwareTestListenerInvokingMethodRoadie extends MethodRoadie {

        protected Object testObject;
        protected Method testMethod;
        protected Throwable throwable;

        public BusAwareTestListenerInvokingMethodRoadie(Object testObject, Method testMethod, TestMethod jUnitTestMethod, RunNotifier notifier,
                Description description) {
            super(testObject, jUnitTestMethod, notifier, description);
            this.testObject = testObject;
            this.testMethod = testMethod;
        }


        @Override
        public void runBeforesThenTestThenAfters(Runnable test) {
            try {
                getTestListener().beforeTestSetUp(testObject, testMethod);
            } catch (Throwable t) {
                addFailure(t);
            }
            if (throwable == null) {
                super.runBeforesThenTestThenAfters(test);
            }
            try {
                getTestListener().afterTestTearDown(testObject, testMethod);
            } catch (Throwable t) {
                addFailure(t);
            }
        }

        @Override
        protected void runTestMethod() {
            try {
                getTestListener().beforeTestMethod(testObject, testMethod);
                registerHandlers(testObject);
            } catch (Throwable t) {
                addFailure(t);
            }
            if (throwable == null) {
                super.runTestMethod();
            }
            try {
                getTestListener().afterTestMethod(testObject, testMethod, throwable);
            } catch (Throwable t) {
                addFailure(t);
            }
        }

        private void registerHandlers(Object testObject) {
            try {
                Set<Field> testedObjectFields = getFieldsAnnotatedWith(testObject.getClass(), TestedObject.class);
                for (Field testedObjectField : testedObjectFields) {
                    testedObjectField.setAccessible(true);
                    Object testedObject = testedObjectField.get(testObject);
                    if (testedObject instanceof EventHandler) {
                        eventBus(testObject).register((EventHandler) testedObject);
                    }
                    if (testedObject instanceof CommandHandler) {
                        commandBus(testObject).register((CommandHandler) testedObject);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


        private CommandBus commandBus(Object testObject) {
            return field("testCommandBus").ofType(CommandBus.class).in(testObject).get();
        }


        private EventBus eventBus(Object testObject) {
            return field("testEventBus").ofType(EventBus.class).in(testObject).get();
        }

        @Override
        protected void addFailure(Throwable t) {
            if (throwable == null) {
                throwable = t;
                super.addFailure(t);
            }
        }
    }

}
