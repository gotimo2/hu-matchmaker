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
        //ik probeer nog steed relatieve filepaths te gebruiken maar het werkt maar niet...
    }

    @POST
    @Path("newmatch")
    @Produces(MediaType.APPLICATION_JSON)
    public Match NewMatch(@FormParam("matchname") String matchname, @FormParam("orgname") String orgname, @FormParam("orgpass") String orgpass){
        System.out.println("attempting match");
        Match m = Match.matchFromPost(matchname, orgpass, orgname);
        System.out.println(m.toString());
        return m;
    }
}
