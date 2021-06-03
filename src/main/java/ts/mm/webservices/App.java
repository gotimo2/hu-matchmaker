package ts.mm.webservices;

import ts.mm.domein.Match;
import ts.mm.utils.Utils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.io.File;
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
    @Path("a")
    public String test() throws IOException {
        System.out.println("ha");
        return Files.readString(Paths.get("/index.html"));
    }

    @POST
    @Path("newmatch")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Match NewMatch(Match m){
        System.out.println("attempting match");
        System.out.println(m.toString());
        return m;
    }
}
