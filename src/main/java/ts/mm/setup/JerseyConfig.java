package ts.mm.setup;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/match")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("ts.mm.webservices");
    }
}

