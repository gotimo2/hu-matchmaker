package ts.mm.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import ts.mm.domein.Persoon;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.AbstractMap;
import java.util.Calendar;


@Path("/auth")
public class JWT {

    final static public Key key = MacProvider.generateKey();

    private String createToken(String username, String Role){
        Calendar expired = Calendar.getInstance();
        expired.add(Calendar.MINUTE, 60);

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expired.getTime())
                .claim("role", Role)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response auth(@FormParam("name") String name, @FormParam("password") String password){
        try{
        String role = Persoon.auth(name, password);
        if (role == null) {throw new IllegalArgumentException("No user found!");}

        String token = createToken(name, role);

        AbstractMap.SimpleEntry<String, String> jwt = new AbstractMap.SimpleEntry<>("JWT", token);
        return Response.ok(jwt).build();
        }
        catch (Exception e){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

    }
}


