package org.launchcode.liftoffgroup1.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

import static org.launchcode.liftoffgroup1.security.SecurityConstants.JWT_EXPIRATION;

@Component
public class JwtTokenGenerator {
//    private String jwtSecret;

    private SecretKey secretKey = io.jsonwebtoken.security.Keys.secretKeyFor(SignatureAlgorithm.HS512);
    public String generateToken(Authentication authentication){
        String user = authentication.getName();
        Date date = new Date();
        Date expireDate = new Date(date.getTime() + JWT_EXPIRATION);
        String token = Jwts.builder().setSubject(user).
                setIssuedAt(new Date()).setExpiration(expireDate).
                signWith(SignatureAlgorithm.HS512, secretKey).compact();
//        signWith(key(), SignatureAlgorithm.HS256).compact();
       return token;
    }
//    private Key key() {
//        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
//    }

    public String getUsernameFromJwt(String token){
        Claims claims = Jwts.parser().setSigningKey(secretKey).build().parseClaimsJwt(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(secretKey).build().parseClaimsJwt(token);
            return true;
        }catch (Exception e){
            throw new AuthenticationCredentialsNotFoundException("Jwt was expired or incorrect.");
        }
    }
}
