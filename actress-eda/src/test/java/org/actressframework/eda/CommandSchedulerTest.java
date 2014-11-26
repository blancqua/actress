package org.actressframework.eda;

import org.actressframework.core.test.Assertion;
import org.actressframework.eda.command.scheduler.CancelScheduledCommand;
import org.actressframework.eda.command.scheduler.CommandScheduler;
import org.actressframework.eda.command.scheduler.ScheduleOnceCommand;
import org.junit.Test;
import org.mockito.Mock;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;

import static java.lang.System.currentTimeMillis;
import static org.actressframework.core.test.Poller.aPoller;
import static org.actressframework.core.test.Sleeper.sleep;
import static org.actressframework.eda.command.scheduler.ScheduleAtFixedRateCommand.scheduleAtFixedRate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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
        scheduler.scheduleCommand(ScheduleOnceCommand.scheduleOnce(COMMAND, DELAY));
        
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
        scheduler.scheduleAtFixedRate(scheduleAtFixedRate(COMMAND, DELAY, DELAY));
        
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
        ScheduleOnceCommand scheduleCommand = ScheduleOnceCommand.scheduleOnce(COMMAND, DELAY);
        
        scheduler.scheduleCommand(scheduleCommand);
        scheduler.cancelSchedule(CancelScheduledCommand.cancel(scheduleCommand));
        sleep(DELAY * 3 / 2);
        
        verifyZeroInteractions(commandBus);
    }
    
    @Test
    public void cancelScheduledCommand_whenAlreadyExecuted() throws Exception {
        ScheduleOnceCommand scheduleCommand = ScheduleOnceCommand.scheduleOnce(COMMAND, DELAY);

        scheduler.scheduleCommand(scheduleCommand);
        sleep(DELAY * 3 / 2);
        scheduler.cancelSchedule(CancelScheduledCommand.cancel(scheduleCommand));
        
        verify(commandBus).post(COMMAND);
    }
    
    @Test
    public void scheduleOnceCommand_whenScheduledTwice() throws Exception {
        ScheduleOnceCommand scheduleCommand = ScheduleOnceCommand.scheduleOnce(COMMAND, DELAY);

        scheduler.scheduleCommand(scheduleCommand);
        scheduler.scheduleCommand(scheduleCommand);
        scheduler.cancelSchedule(CancelScheduledCommand.cancel(scheduleCommand));
        sleep(DELAY * 3 / 2);
        
        verifyZeroInteractions(commandBus);
    }
    
    @Test
    public void scheduleOnceCommand_whenCanceledTwice() throws Exception {
        ScheduleOnceCommand scheduleCommand = ScheduleOnceCommand.scheduleOnce(COMMAND, DELAY);

        scheduler.scheduleCommand(scheduleCommand);
        scheduler.cancelSchedule(CancelScheduledCommand.cancel(scheduleCommand));
        scheduler.cancelSchedule(CancelScheduledCommand.cancel(scheduleCommand));
        sleep(DELAY * 3 / 2);
        
        verifyZeroInteractions(commandBus);
    }
}
