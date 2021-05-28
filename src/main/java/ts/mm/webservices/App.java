package ts.mm.webservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
@Path("/")
public class App
{

    @GET
    @Produces("text/html")
    public String getmainPage() throws IOException {
        return Files.readString(Paths.get("webapp/index.html"));
    }

    @GET
    @Produces("text/css")
    @Path("styles.css")
    public String getCSS() throws IOException {
        return Files.readString(Paths.get("webapp/index.html"));
    }
}
