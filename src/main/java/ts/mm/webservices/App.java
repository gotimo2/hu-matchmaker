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
    //C:\Users\Timo\Documents\GitHub\hu-matchmaker\src\main\java\ts\mm\setup\JerseyConfig.java
    //C:\Users\Timo\Documents\GitHub\hu-matchmaker\src\main\webapp\index.html
    @GET
    @Produces("text/html")
    public String getmainPage() throws IOException {
        File f = new File("../webapp/index.html");
        System.out.println(f.canRead());
        return Utils.mainScreenHTML = Files.readString(Paths.get("C:\\Users\\Timo\\Documents\\GitHub\\hu-matchmaker\\src\\main\\webapp\\index.html"));
    }

    @GET
    @Produces("text/css")
    @Path("styles.css")
    public String getCSS() throws IOException {
        return Files.readString(Paths.get("C:\\Users\\Timo\\Documents\\GitHub\\hu-matchmaker\\src\\main\\webapp\\styles.css"));
    }

    @GET
    @Produces("text/html")
    @Path("a")
    public String test() throws IOException {
        System.out.println("ha");
        return Files.readString(Paths.get("C:\\Users\\Timo\\Documents\\GitHub\\hu-matchmaker\\src\\main\\webapp\\index.html"));
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
