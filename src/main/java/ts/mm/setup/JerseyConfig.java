package ts.mm.setup;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/match")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("ts.mm.webservices","ts.mm.security");
        register(RolesAllowedDynamicFeature.class);
    }
}

