package ts.mm.webservices;

import ts.mm.domein.Match;
import ts.mm.domein.Persoon;
import ts.mm.domein.Speler;
import ts.mm.domein.Team;
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
            if (3 >= matchname.length() | matchname.length() >= 21 | 3 >= orgname.length() | orgname.length() >= 13 | 3 >= orgpass.length() | orgpass.length() >= 13 ){
                throw new IllegalArgumentException("input too long or too short!");
            }
            Match m = Match.matchFromPost(matchname, orgpass, orgname);
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

    @POST
    @Path("joinmatch")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response joinMatch(@FormParam("username") String username, @FormParam("pass") String pass, @FormParam("team") int team, @FormParam("matchid") String matchid){
        if(3 >= username.length() | username.length() >= 13 | 3 >= pass.length() | pass.length() >= 13){
            throw new IllegalArgumentException("Input too short or too long!");
        }
        try{
            Speler s = new Speler(username, pass, Match.zoekMatch(matchid).getTeam(team), Match.zoekMatch(matchid));
            return Response.ok(s).build();
        }
        catch (Exception e){
            return Response.status(Response.Status.CONFLICT).entity(new AbstractMap.SimpleEntry<String, String>("result", e.toString())).build();
        }
    }

    @DELETE
    @Path("leave")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response leaveMatch(@FormParam("name") String name, @FormParam("pass") String pass, @FormParam("matchid") String matchid){
        try {
            Match m = Match.zoekMatch(matchid);
            Persoon p = Persoon.getPersoon(name);
            if (p.getWachtwoord().equals(pass)) {
                if (p.getMatch() == m) ;
                for (Team t : m.getTeams()
                ) {
                    if (t.getSpelers().contains(p)) {
                        t.spelers.remove(p);
                        return Response.ok().build();
                    }
                }
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        catch (Exception e){
            return Response.status(Response.Status.CONFLICT).entity(new AbstractMap.SimpleEntry<String, String>("result", e.toString())).build();
        }
    }
}
