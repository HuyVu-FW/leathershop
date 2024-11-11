package huyvu.leathershop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Map;

@Controller
//@RestController
@RequestMapping("login")
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "loginpage";
    }

    @GetMapping("/dashboard4")
    public String dashboard(Model model, Authentication authentication, OAuth2AuthenticationToken authentication2) {
        OAuth2User oauthUser = authentication2.getPrincipal();

        // Lấy thông tin của người dùng từ OAuth2User
        String name = oauthUser.getAttribute("name");
        String email = oauthUser.getAttribute("email");
        Map<String, Object> attributes = oauthUser.getAttributes(); // Lấy tất cả thuộc tính

        // In ra thông tin người dùng
        System.out.println("User name: " + name);
        System.out.println("User email: " + email);
        System.out.println("All attributes: " + attributes);

        System.out.println("email: " + email + ", name: " + name);
//        if (authentication != null && authentication.isAuthenticated()) {
//            Object principal = authentication.getPrincipal();
//            if (principal instanceof OidcUser) {
//                OidcUser oidcUser = (OidcUser) principal;
//                model.addAttribute("name", oidcUser.getFullName());
//                model.addAttribute("email", oidcUser.getEmail());
//
//            } else {
//                return "redirect:/auth/login";  // Nếu không phải là OidcUser
//            }
//        } else {
//            return "redirect:/auth/login";  // Nếu người dùng chưa đăng nhập
//        }
        return "dashboardpage";
    }

    @GetMapping("/dashboard")
    public ResponseEntity<?> loginSuccess(OAuth2AuthenticationToken authentication) {
        // Lấy Principal (OAuth2User) và các thông tin từ principal
        OAuth2User oauthUser = authentication.getPrincipal();
        Map<String, Object> attributes = oauthUser.getAttributes();

        // Lấy các thuộc tính
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");

        // Lấy Authorities
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // Lấy Authorized Client Registration ID
        String clientRegistrationId = authentication.getAuthorizedClientRegistrationId();

        System.out.println("User email: " + email);
        System.out.println("User name: " + name);
        System.out.println("Authorities: " + authorities);
        System.out.println("Provider: " + clientRegistrationId);

        return ResponseEntity.ok("Login Success");
    }




    @GetMapping("/hello")
    public String hello() {
        return "helo";
    }
}