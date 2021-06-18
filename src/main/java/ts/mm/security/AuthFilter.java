package ts.mm.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import ts.mm.domein.Persoon;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestCtx){

        boolean isSecure = requestCtx.getSecurityContext().isSecure();
        String scheme = requestCtx.getUriInfo().getRequestUri().getScheme();
        SecurityContext msc = new MySecurityContext(null, scheme);
        String authHeader = requestCtx.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")){
            System.out.print(authHeader);
            String token = authHeader.substring(7).trim();

            try{
                System.out.println("token" + token);
                JwtParser parser = Jwts.parser().setSigningKey(JWT.key);
                Claims claims = parser.parseClaimsJws(token).getBody();

                String user = claims.getSubject();
                System.out.println("username " + user);
                msc = new MySecurityContext(Persoon.getPersoon(user), scheme);
                System.out.println("user is in");

            } catch (Exception e) {
                System.out.println("Processing guest");
                System.out.println(e);

            }

        }
        requestCtx.setSecurityContext(msc);
    }


}
