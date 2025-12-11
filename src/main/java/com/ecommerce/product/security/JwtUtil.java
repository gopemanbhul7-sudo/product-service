// package com.ecommerce.product.security;

// import java.security.Key;
// import java.util.Date;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Component;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.JwtException;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.io.Decoders;
// import io.jsonwebtoken.security.Keys;

// @Component
// public class JwtUtil {

//     @Value("${security.jwt.secret}")
//     private String secret;

//     private Key getSignKey() {
//         byte[] keyBytes = Decoders.BASE64.decode(secret);
//         return Keys.hmacShaKeyFor(keyBytes);
//     }

//     public Claims extractAllClaims(String token) {
//         return Jwts.parserBuilder()
//                 .setSigningKey(getSignKey())
//                 .build()
//                 .parseClaimsJws(token)
//                 .getBody();
//     }

//     public String extractEmail(String token) {
//         return extractAllClaims(token).getSubject();
//     }

//     public boolean isTokenValid(String token) {
//         try {
//             Claims claims = extractAllClaims(token);
//             return claims.getExpiration().after(new Date());
//         } catch (JwtException | IllegalArgumentException e) {
//             return false;
//         }
//     }
// }