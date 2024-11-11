package huyvu.leathershop.security.Filter;

import huyvu.leathershop.security.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Data
public class JwtRequestFilter extends OncePerRequestFilter {
    private UserDetailsService userDetailsService;
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // Lấy JWT từ Authorization header
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        // Kiểm tra xem Authorization header có chứa token không
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); // Loại bỏ tiền tố "Bearer " để lấy token
            username = jwtUtils.extractUsername(jwt); // Giải mã token để lấy username
        }

        // Nếu đã lấy được username và người dùng chưa được xác thực
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Lấy UserDetails từ username
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // Kiểm tra xem token có hợp lệ không
            if (jwtUtils.validateToken(jwt, userDetails)) {

                // Tạo Authentication object và lưu vào SecurityContextHolder
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        // Tiếp tục chain các filter khác
        chain.doFilter(request, response);
    }
}