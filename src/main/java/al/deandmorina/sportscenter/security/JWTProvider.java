package al.deandmorina.sportscenter.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTProvider {
    @Value("${app.jwt-secret}")
    private String secret;
    @Value("${app.jwt-expiration-ms}")
    private int expiresIn;
    @Value("${app.jwt-refresh-expiration-ms}")
    private int refreshExpiresIn;

    public String generateToken(Authentication auth) {
        String email = auth.getName();
        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + expiresIn);
        return Jwts.builder().setSubject(email).setExpiration(now).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public String generateRefreshToken(Authentication auth) {
        return Jwts.builder().setSubject(auth.getName()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpiresIn))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new JwtException("Invalid JWT token");
        }
    }
}
