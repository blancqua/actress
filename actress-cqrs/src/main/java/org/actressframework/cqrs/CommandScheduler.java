package org.actressframework.cqrs;

import static com.google.common.collect.Maps.newHashMap;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;

import org.actressframework.cqrs.command.CancelScheduledCommand;
import org.actressframework.cqrs.command.ScheduleAtFixedRateCommand;
import org.actressframework.cqrs.command.ScheduleCommand;
import org.actressframework.cqrs.command.ScheduleOnceCommand;

import com.google.common.eventbus.Subscribe;

@Named @Actor
public class CommandScheduler implements CommandHandler {

    private static final Logger LOGGER = getLogger(CommandScheduler.class);
    private ScheduledExecutorService scheduledExecutor = newSingleThreadScheduledExecutor(CommandScheduler.class.getSimpleName() + "-executor");
    
    @Inject
    private CommandBus commandBus;

    private Map<ScheduleCommand, ScheduledFuture<?>> scheduledTasks = newHashMap();
    
    @Subscribe
    public synchronized void scheduleCommand(ScheduleOnceCommand scheduledCommand) {
        if (!isScheduled(scheduledCommand)) {
            ScheduledFuture<?> schedule = scheduledExecutor.schedule(post(scheduledCommand.getCommand()), scheduledCommand.getDelay(), MILLISECONDS);
            scheduledTasks.put(scheduledCommand, schedule);
        } else {
            LOGGER.warn(scheduledCommand + " already scheduled, ignoring.");
        }
    }

    private boolean isScheduled(ScheduleCommand scheduledCommand) {
        return scheduledTasks.containsKey(scheduledCommand);
    }
    
    @Subscribe
    public synchronized void scheduleAtFixedRate(ScheduleAtFixedRateCommand scheduledCommand) {
        ScheduledFuture<?> schedule = scheduledExecutor.scheduleAtFixedRate(post(scheduledCommand.getCommand()), scheduledCommand.getDelay(), scheduledCommand.getRate(), MILLISECONDS);
        scheduledTasks.put(scheduledCommand, schedule);
    }
    
    @Subscribe
    public synchronized void cancelSchedule(CancelScheduledCommand cancelScheduledCommand) {
        if (isScheduled(cancelScheduledCommand.getScheduledCommand())) {
            ScheduledFuture<?> remove = scheduledTasks.remove(cancelScheduledCommand.getScheduledCommand());
            remove.cancel(true);
        } else {
            LOGGER.warn(cancelScheduledCommand.getScheduledCommand() + " already canceled, ignoring.");
        }
    }

    private Runnable post(final Object command) {
        return new Runnable() {
            @Override
            public void run() {
                synchronized (CommandScheduler.this) {
                    scheduledTasks.remove(command);
                }
                commandBus.post(command);
            }
        };
    }
    
}
