package br.com.desafionexo.security;

import br.com.desafionexo.enumerator.Role;
import br.com.desafionexo.service.UsuarioDetailsService;
import br.com.desafionexo.util.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@Component
public class JWTTokenProvider {

    private String secretKey;

    @Autowired
    private UsuarioDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(Constants.SECRET.getBytes());
    }

    public String createToken(String username, Role role) {

        Claims claims = Jwts.claims().setSubject(username)
                .setAudience(Constants.AUTHENTICATION_SCHEME)
                .setIssuer(Constants.REALM);

        claims.put("auth", role);

        Date now = new Date();
        Date validity = new Date(now.getTime() + Constants.EXPIRATION_TIME);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String getExpirationDate(String token) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(
                Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration());
    }

    public String getType(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getAudience();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(Constants.HEADER_STRING);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public boolean validateToken(String token) throws Exception {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new Exception("Expired or invalid JWT token!");
        }
    }

}

