package com.yorku.green_charge_auto.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Base64;

@Component
public class JwtUtil {

    private static final String SECRET = "e0c1a655dc2f0ab0cd9cc27e76cac35a9dce2fbdd46f1509e634760ffd6019e050a2c106693b0e1de3cb04d1a31c2a71ce42b5a462ea4e0fb1b1ac5fa80b3ff5edbefc24666dc1cbdf01e4fbae70c897d4f9f2e72fc8fe25cab9114de89ed744e1bd1381919f95a41409940229a91b6c4e586d56bb72f8e995756756be8d6455ff395e897213ecbf1b748201161115d71ae505005a984eb53f525613c0b725e2cbd8c86f0eef4bbf69dbace6784cd8ad61497d1370175532287f994072c1618a851f8a9b787e25c9274687a03f93b0c35068110935eb42071743f18538c3dde38737bdb8dc43a6efb7706ab883afaa3b9edc58954fa22be0e2d69d2b0081eb18";
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET));

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day expiry
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, String username) {
        return extractUsername(token).equals(username);
    }
}
