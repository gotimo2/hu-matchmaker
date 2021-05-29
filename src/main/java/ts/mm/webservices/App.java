package ts.mm.webservices;

import ts.mm.domein.Match;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
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
        return Files.readString(Paths.get("webapp/styles.css"));
    }

    @GET
    @Produces("text/html")
    @Path("newmatch")
    public String test() throws IOException {
        System.out.println("ha");
        return Files.readString(Paths.get("webapp/index.html"));

    }

    //@POST
    //@Path("newmatch")
    //@Produces(MediaType.APPLICATION_JSON)
    //public Match NewMatch(@FormParam("matchname") String matchname, @FormParam("orgname") String orgname, @FormParam("orgpass") String orgpass){
    //    Match m = Match.matchFromPost(matchname, orgpass, orgname);
    //    System.out.println(m.toString());
    //    return m;
    //}
}
