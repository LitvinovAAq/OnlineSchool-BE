package ru.litvinov.onlineSchool.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.litvinov.onlineSchool.services.AppUserDetailsService;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

@Component
public class JWTUtil{

    private final AppUserDetailsService appUserDetailsService;

    @Autowired
    public JWTUtil(AppUserDetailsService appUserDetailsService) {
        this.appUserDetailsService = appUserDetailsService;
    }

    @Value("${jwt_secret}")
    private String secret;

    @Value("${jwt_lifetime}")
    private Duration jwtLifeTime;



    public String generateToken(String email){
        Date issuedDate = new Date();
        Date expirationDate = new Date(issuedDate.getTime() + jwtLifeTime.toMillis());
        UserDetails appUserDetail = appUserDetailsService.loadUserByUsername(email);

        return JWT.create()
                .withSubject("User Details")
                .withClaim("email", appUserDetail.getUsername())
                .withClaim("role", appUserDetail.getAuthorities().stream().
                        findFirst().orElse(null).getAuthority())
                .withIssuedAt(new Date())
                .withIssuer("OnlineSchool")
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(secret));
    }

    public String getEmail(String token){
        return validateTokenAndRetrieveClaim(token).get("email").asString();
    }

    public String getRole(String token){
        return validateTokenAndRetrieveClaim(token).get("role").asString();
    }

    public Map<String, Claim> validateTokenAndRetrieveClaim(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User Details")
                .withIssuer("OnlineSchool")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaims();
    }
}
