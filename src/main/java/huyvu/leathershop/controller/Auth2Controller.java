package huyvu.leathershop.controller;


import huyvu.leathershop.model.User;
import huyvu.leathershop.repository.UserRepository;
import huyvu.leathershop.security.JwtUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/api/auth")
@Data
@AllArgsConstructor
public class Auth2Controller {

    private JwtUtils jwtUtil;
    private UserRepository userRepository;



    @GetMapping("/oauth2/success")
    public void oauth2Success(OAuth2AuthenticationToken authentication, HttpServletResponse response) throws IOException {
        OAuth2User oAuth2User = authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        User user  = userRepository.findByEmail(email).get();
        // Generate JWT
        String token = jwtUtil.generateToken(user);

        // Send JWT token in the response (e.g., as a JSON response or redirect with the token)
//        response.sendRedirect("http://yourfrontend.com?token=" + token);  // Replace with your frontend URL
        System.out.println(token);
        response.sendRedirect("http://google.com");  // Replace with your frontend URL

    }
}