package huyvu.leathershop.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private  JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JwtUtils jwtUtil) {
        this.jwtUtils = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null && jwtUtils.validateTokenO2(token)) {
            String email = jwtUtils.getEmailFromToken(token);
            // Set user authentication details (implement authentication here)
        }
        filterChain.doFilter(request, response);
    }


}
