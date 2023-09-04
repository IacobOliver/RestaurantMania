package com.codecooll.RestaurantMania.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String secretKey = "ca9c492e7509c26222c54f59a5e16e35903f70a766c8a62d4b1d878b2dedda31";
    private static final String secondKey = "bde4636149d4874ca7c31e84ff321cb2604ce2305565e5188e6ff5cbef84f24b";
//   private Key privateKey = Keys.hmacShaKeyFor( Decoders.BASE64.decode(secondKey));
//    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//        return Keys.hmacShaKeyFor(keyBytes);
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    public <T> T extractClaim(String token , Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() * 1000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date()) ;
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

//    private PrivateKey getSigningKey() {
//        try {
//            byte[] keyBytes = Base64.getDecoder().decode(secondKey);
//            KeyFactory keyFactory = KeyFactory.getInstance("EC"); // Use the appropriate algorithm (e.g., "EC" for ECDSA)
//            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
//            return keyFactory.generatePrivate(keySpec);
//        } catch (Exception e) {
//            // Handle exceptions (e.g., InvalidKeySpecException, NoSuchAlgorithmException)
//            throw new RuntimeException("Failed to generate private key", e);
//        }
//    }
}
