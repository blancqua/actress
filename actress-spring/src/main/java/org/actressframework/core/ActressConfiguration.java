package org.actressframework.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackageClasses = ActressConfiguration.class)
@EnableAspectJAutoProxy
public class ActressConfiguration {

    @Bean
    public ActorInterceptor actorInterceptor() {
        return new ActorInterceptor();
    }
}
