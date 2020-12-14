
package info.hccis.cisflowershop;

import info.hccis.cisflowershop.rest.CustomerService;
import info.hccis.cisflowershop.rest.FlowerOrderService;
import info.hccis.cisflowershop.rest.OrderService;
import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * Jersey Config class 
 * 
 * @author Elikem K. Akatti 
 * @since 20201125
 */
@Component
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig{
    
    @PostConstruct
    private void init() {
        registerClasses(CustomerService.class);
        registerClasses(FlowerOrderService.class);//KW - registering flower OrderService in jersey config
    }
}
