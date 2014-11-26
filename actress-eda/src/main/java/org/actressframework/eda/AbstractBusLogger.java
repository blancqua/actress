package org.actressframework.eda;

import com.google.common.eventbus.Subscribe;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class AbstractBusLogger {

    @Subscribe
    public void log(Object item) {
        getLogger(this.getClass()).info(item.toString());
    }

}
