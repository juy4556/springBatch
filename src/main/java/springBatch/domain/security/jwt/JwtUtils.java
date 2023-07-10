package springbatch.domain.security.jwt;

import lombok.extern.slf4j.Slf4j;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import springbatch.domain.user.entity.User;
import java.util.Date;

@Component
@Slf4j
public class JwtUtils {

    @Value("${security.jwt.token.secretKey}")
    private String jwtSecret;

    @Value("${security.jwt.token.expirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(User user) {
        return generateTokenFromSubject(user);
    }

    private String generateTokenFromSubject(User user) {
        return Jwts.builder().claim("id",user.getId()).setSubject(user.getName()).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
