package huyvu.leathershop.security;


import huyvu.leathershop.enums.Role;
import huyvu.leathershop.repository.UserRepository;
import huyvu.leathershop.security.Filter.JwtRequestFilter;
import lombok.Data;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@Data
public class SecurityConfig {


    private JwtRequestFilter jwtRequestFilter;
    private JwtUtils jwtUtil;
    @Autowired
    private UserRepository userRepository;

    private String[] PULIC_ENDPOINT = {"/user/register/", "/auth/login/", "auth/**", "auth/deleteALl/", "/api/auth/**"};
    private String[] ADMIN_ENDPOINT = {"/user/list/"};

    //   init admin accound
    private String username = "admin";
    private String password = "admin";

    public SecurityConfig(UserRepository userRepository, JwtUtils jwtUtil, JwtRequestFilter jwtRequestFilter) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    //    init Bean
    @Bean
    public int InitAccounAdmin() {

        if (userRepository.existsByUserName("admin")) {
            return 0;

        } else {
            huyvu.leathershop.model.User admin = new huyvu.leathershop.model.User();
            admin.setUserName(username);
            admin.setPassword(passwordEncoder().encode(password));
            admin.setRoles(Set.of(Role.ADMIN.name()));
            userRepository.save(admin);
            return 1;
        }


    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers(PULIC_ENDPOINT).permitAll()
                                .requestMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
                                .requestMatchers("/h2/**").permitAll()


                                .anyRequest().permitAll()

//                                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                        // Cho phép tất cả các yêu cầu mà không cần xác thực
                )
                .httpBasic(withDefaults())
//                .formLogin(withDefaults())
                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/login/login")
                        .defaultSuccessUrl("/api/auth/oauth2/success", true)  // Đặt URL thành công sau khi đăng nhập
                );
//        http.addFilterBefore(new JwtAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }


    // Hiệu xuất cái này chậm hơn Mapstruc vì nó ánh xạ, phù hợp cho dự án nhỏ
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Using BCryptPasswordEncoder for password hashing
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


}
