package org.actressframework.cqrs;

import static org.actressframework.cqrs.events.ApplicationStarted.applicationStarted;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.context.event.ContextRefreshedEvent;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;

public class ApplicationStartedEventPosterTest extends AbstractTest {

    @Mock
    private ContextRefreshedEvent event;
    
    @Mock
    @InjectIntoByType
    private EventBus eventBus;
    
    @TestedObject
    private ApplicationStartedEventPoster poster;
    
    @Test
    public void onApplicationEvent() throws Exception {
        poster.onApplicationEvent(event);
        
        verify(eventBus).post(applicationStarted());
    }
    
}
