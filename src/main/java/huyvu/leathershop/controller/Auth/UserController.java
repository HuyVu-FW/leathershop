package huyvu.leathershop.controller.Auth;

import huyvu.leathershop.dto.ApiResponse;
import huyvu.leathershop.dto.user.UserCreateDTO;
import huyvu.leathershop.enums.Role;
import huyvu.leathershop.model.User;
import huyvu.leathershop.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RequestMapping("/user/")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;

    public UserController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("register/")
    public ApiResponse<User> registerUser(@RequestBody @Valid UserCreateDTO userCreateDTO){

        User user = modelMapper.map(userCreateDTO, User.class);
        // Hash Password
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(Role.USER.name()));
        return  new ApiResponse<>(HttpStatus.CREATED.value(),"Created",userService.registerUser(user));
    }



    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("list/")
    public List<User> getAllUsers(){

        return userService.getAllUsers();
    }

    @GetMapping("get/{id}/")
//    PathVariable nó sẽ lấy tren url
    public User getUserById(@PathVariable String id){
        return userService.getUser(id);

    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("update/")
    public String update(){
        return "Hello This is Put";
    }
    @DeleteMapping("delete/{id}/")
    public String deleteUser(@PathVariable String id){
        userService.deleteUser(id);

        return "Delete Successfull";
    }
    @PostMapping("login/")
    public String login(){
        return "login successfull";
    }
}
