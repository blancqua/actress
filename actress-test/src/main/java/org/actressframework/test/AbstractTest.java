package org.actressframework.test;

import org.actressframework.core.ActorInterceptor;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;

import com.google.common.eventbus.EventBus;

@RunWith(CQRSAwareJUnit4TestClassRunner.class)
public abstract class AbstractTest {

    private EventBus testEventBus = new EventBus();
    private EventBus testCommandBus = new EventBus();

    @Before
    public void initAnnotations() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    
    @Before
    public void disableActors() {
        ActorInterceptor.disableActors();
    }

    @Before
    @After
    public void defreezeTime() {
        DateTimeUtils.setCurrentMillisSystem();
    }

    protected void freezeTime(DateTime dateTime) {
        DateTimeUtils.setCurrentMillisFixed(dateTime.getMillis());
    }
    
    protected void postEvent(Object event) {
        testEventBus.post(event);
    }

    protected void postCommand(final Object command) {
        testCommandBus.post(command);
    }

}
