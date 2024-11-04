package org.actressframework.core.test;

import org.actressframework.core.ActorInterceptor;
import org.actressframework.eda.CommandBus;
import org.actressframework.eda.EventBus;
import org.mockito.MockitoAnnotations;

@RunWith(EDAAwareJUnit4TestClassRunner.class)
public abstract class AbstractActressTest {

    private EventBus testEventBus = new EventBus();
    private CommandBus testCommandBus = new CommandBus();

    @Before
    public void initAnnotations() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    
    @Before
    public void disableActors() {
        ActorInterceptor.disableActors();
    }

    protected void postEvent(Object event) {
        testEventBus.post(event);
    }

    protected void postCommand(final Object command) {
        testCommandBus.post(command);
    }

}
