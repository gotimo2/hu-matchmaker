package ts.mm.webservices;

import ts.mm.domein.*;
import ts.mm.security.MySecurityContext;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.AbstractMap;

@Path("/admin")
public class AdminResource {

   @PUT
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   @Produces(MediaType.APPLICATION_JSON)
   @RolesAllowed("organisator")
   public Response editMatch(@Context SecurityContext sc, @FormParam("matchname") String newname, @FormParam("t1name") String team1name, @FormParam("t2name") String team2name){
       try {
           if (3 >= newname.length() | newname.length() >= 21 | 3 >= team1name.length() | team1name.length() >= 13 | 3 >= team2name.length() | team2name.length() >= 13 ){
               throw new IllegalArgumentException("input too long or too short! how does that even happen?");
           }
           Organisator o = (Organisator) sc.getUserPrincipal();
           Match m = o.getMatch();
           m.setNaam(newname);
           m.getTeam(1).setNaam(team1name);
           m.getTeam(2).setNaam(team2name);
           return Response.ok().build();
       }
       catch (Exception e) {
           return Response.status(Response.Status.CONFLICT).entity(new AbstractMap.SimpleEntry<String, String>("result", e.toString())).build();
       }
   }


   @DELETE
   @Path("/removeplayer")
   @RolesAllowed("organisator")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public Response removePlayer(@Context SecurityContext sc, @FormParam("player") String player){
       try {
           Organisator o = (Organisator) sc.getUserPrincipal();
           Persoon p = Persoon.getPersoon(player);
           if (p instanceof Speler){
               Speler s = (Speler) p;
               for (Team t : o.getMatch().getTeams()){
                   if (t.spelers.contains(p)){
                       s.Leave();
                       return Response.ok().build();
                   }
               }
           }
           return Response.status(Response.Status.CONFLICT).build();
       }
       catch (Exception e) {
           return Response.status(Response.Status.CONFLICT).entity(new AbstractMap.SimpleEntry<String, String>("result", e.toString())).build();
       }
   }


   //@PUT
   //@Produces(MediaType.APPLICATION_JSON)
   //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   //@RolesAllowed("organisator")
   //public Response changeTeamname(@Context SecurityContext sc, @FormParam("team") int teamnummer, @FormParam("name") String newname){
   //    try{
   //        Organisator o = (Organisator) sc.getUserPrincipal();
   //        Match m = o.getMatch();
   //        m.getTeam(teamnummer).setNaam(newname);
   //        return Response.ok().build();
   //    }
   //    catch (Exception e) {
   //        return Response.status(Response.Status.CONFLICT).entity(new AbstractMap.SimpleEntry<String, String>("result", e.toString())).build();
   //    }
   //}

   @DELETE
   @Produces(MediaType.APPLICATION_JSON)
   @RolesAllowed("organisator")
   public Response deleteMatch(@Context SecurityContext sc){
       try {
           Organisator o = (Organisator) sc.getUserPrincipal();
           o.getMatch().verwijder();
           return Response.ok().build();
       }
       catch (Exception e){
           return Response.status(Response.Status.CONFLICT).entity(new AbstractMap.SimpleEntry<String, String>("result", e.toString())).build();
       }
   }

}
