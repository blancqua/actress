package integration.org.actressframework.eda;

import org.actressframework.eda.EDAConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(
    classes = {
        EDAConfiguration.class,
        AbstractIntegrationTest.InfrastructureTestConfiguration.class
    }
)
public abstract class AbstractIntegrationTest extends AbstractJUnit4SpringContextTests {

    @Configuration
    @ComponentScan("integration.org.actressframework.eda")
    static class InfrastructureTestConfiguration {}
    
}
