package org.actressframework.cqrs;

import static java.lang.System.currentTimeMillis;
import static org.actressframework.common.Poller.aPoller;
import static org.actressframework.common.Sleeper.sleep;
import static org.actressframework.cqrs.command.CancelScheduledCommand.cancel;
import static org.actressframework.cqrs.command.ScheduleAtFixedRateCommand.scheduleAtFixedRate;
import static org.actressframework.cqrs.command.ScheduleOnceCommand.scheduleOnce;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.actressframework.common.Assertion;
import org.actressframework.cqrs.command.ScheduleCommand;
import org.actressframework.test.AbstractTest;
import org.junit.Test;
import org.mockito.Mock;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;

public class CommandSchedulerTest extends AbstractTest {

    private static final int DELAY = 1000;

    private static final String COMMAND = "command";
    
    @Mock
    @InjectIntoByType
    private CommandBus commandBus;
    
    @TestedObject
    private CommandScheduler scheduler;
    
    @Test
    public void scheduleOnceCommand() throws Exception {
        long then = currentTimeMillis();
        postCommand(scheduleOnce(COMMAND, DELAY));
        
        aPoller().doAssert(new Assertion() {
            @Override
            public void assertion() throws Exception {
                verify(commandBus).post(COMMAND);
            }
        });
        
        assertThat(System.currentTimeMillis() - then).isGreaterThanOrEqualTo(DELAY);
    }
    
    @Test
    public void scheduleAtFixedRateCommand() throws Exception {
        long then = currentTimeMillis();
        postCommand(scheduleAtFixedRate(COMMAND, DELAY, DELAY));
        
        aPoller().doAssert(new Assertion() {
            @Override
            public void assertion() throws Exception {
                verify(commandBus, times(2)).post(COMMAND);
            }
        });
        
        assertThat(System.currentTimeMillis() - then).isGreaterThanOrEqualTo(2 * DELAY);
    }
    
    @Test
    public void cancelScheduledCommand() throws Exception {
        ScheduleCommand scheduleCommand = scheduleOnce(COMMAND, DELAY);
        
        postCommand(scheduleCommand);
        postCommand(cancel(scheduleCommand));
        sleep(DELAY * 3 / 2);
        
        verifyZeroInteractions(commandBus);
    }
    
    @Test
    public void cancelScheduledCommand_whenAlreadyExecuted() throws Exception {
        ScheduleCommand scheduleCommand = scheduleOnce(COMMAND, DELAY);
        
        postCommand(scheduleCommand);
        sleep(DELAY * 3 / 2);
        postCommand(cancel(scheduleCommand));
        
        verify(commandBus).post(COMMAND);
    }
    
    @Test
    public void scheduleOnceCommand_whenScheduledTwice() throws Exception {
        ScheduleCommand scheduleCommand = scheduleOnce(COMMAND, DELAY);
        
        postCommand(scheduleCommand);
        postCommand(scheduleCommand);
        postCommand(cancel(scheduleCommand));
        sleep(DELAY * 3 / 2);
        
        verifyZeroInteractions(commandBus);
    }
    
    @Test
    public void scheduleOnceCommand_whenCanceledTwice() throws Exception {
        ScheduleCommand scheduleCommand = scheduleOnce(COMMAND, DELAY);
        
        postCommand(scheduleCommand);
        postCommand(cancel(scheduleCommand));
        postCommand(cancel(scheduleCommand));
        sleep(DELAY * 3 / 2);
        
        verifyZeroInteractions(commandBus);
    }
}
