package com.twitter.clone.twitterclone.security;

import com.twitter.clone.twitterclone.entity.User;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.xml.bind.SchemaOutputResolver;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    /**
     * generate token
     */
    public String genereteToken (Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());

        Date expireDate = new Date(now.getTime() + SecurityConstant.EXPIRE_TIME);

        String userId = Long.toString(user.getId());

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", Long.toString(user.getId()));
        claims.put("username", user.getUsername());
        claims.put("fullname", user.getFullname());

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstant.SECRET)
                .compact();
    }

    /**
     * validate token
     */
    public boolean validateToken (String token) {
        try{
            Jwts.parser().setSigningKey(SecurityConstant.SECRET).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex) {
            System.out.println("Invalid JWt Signature");
        }catch (MalformedJwtException ex) {
            System.out.println("Invalid JWt Token");
        }catch (ExpiredJwtException ex) {
            System.out.println("Unssuported JWT TOken");
        }catch (IllegalArgumentException ex) {
            System.out.println("JWT Claims string is empty");
        }catch (UnsupportedJwtException ex) {
            System.out.println("Unssported JWT TOken");
        }

        return false;
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(SecurityConstant.SECRET).parseClaimsJws(token).getBody();
        String id = (String)claims.get("id");

        return Long.parseLong(id);
    }
}
