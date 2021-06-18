package ts.mm.webservices;

import ts.mm.domein.Match;
import ts.mm.utils.Utils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap;

/**
 * Hello world!
 *
 */
@Path("/")
public class MatchResource {

    @POST
    @Path("newmatch")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response NewMatch(@FormParam("matchname") String matchname, @FormParam("orgname") String orgname, @FormParam("orgpass") String orgpass) {

        try {
            Match m = Match.matchFromPost(matchname, orgname, orgpass);
            return Response.ok(m).build();
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity(new AbstractMap.SimpleEntry<String, String>("result", e.toString())).build();
        }
    }

    @GET
    @Path("{matchid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMatch(@PathParam("matchid") String id){
        Match m = Match.zoekMatch(id);
        if (m != null){
            return Response.ok(m).build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
