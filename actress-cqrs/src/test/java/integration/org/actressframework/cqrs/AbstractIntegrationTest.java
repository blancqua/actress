package integration.org.actressframework.cqrs;

import org.actressframework.cqrs.CQRSConfiguration;

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
