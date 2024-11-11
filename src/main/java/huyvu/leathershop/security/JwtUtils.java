package huyvu.leathershop.security;

import huyvu.leathershop.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {

    private String SECRET_KEY = "huyvuvinonganquanenphaivietchodairahahahahahaha";  // Thay bằng khóa bí mật của bạn

    // Tạo JWT
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put( "id", user.getId() );
        claims.put("role", user.getRoles() );

        return createToken(claims,user.getUserName());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)  // Thông tin (claims) có thể là user roles, permissions, etc.
                .setSubject(subject)  // Tên người dùng (username)
                .setIssuedAt(new Date(System.currentTimeMillis()))  // Ngày tạo token
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 ))  // Hết hạn sau x giờ
                .setIssuer("Huy Vũ")
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Ký token với khóa bí mật

                .compact();
    }
// get on imfor from token
    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    // Lấy tên người dùng từ JWT
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Kiểm tra JWT đã hết hạn hay chưa
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }



    // Kiểm tra token có hợp lệ không
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateTokenO2(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

}
