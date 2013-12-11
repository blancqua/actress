package integration.org.actressframework.cqrs;

import org.actressframework.cqrs.CQRSConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(
    classes = {
        CQRSConfiguration.class,
        AbstractIntegrationTest.InfrastructureTestConfiguration.class
    }
)
public abstract class AbstractIntegrationTest extends AbstractJUnit4SpringContextTests {

    @Configuration
    @ComponentScan( "integration.be.brail.sigw.infrastructure" )
    static class InfrastructureTestConfiguration {}
    
}
