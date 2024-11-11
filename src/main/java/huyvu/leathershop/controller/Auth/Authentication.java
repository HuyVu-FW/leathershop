package huyvu.leathershop.controller.Auth;

import huyvu.leathershop.dto.ApiResponse;
import huyvu.leathershop.dto.responAPI.UserAuthenticateRespon;
import huyvu.leathershop.dto.user.UserLoginDTO;
import huyvu.leathershop.repository.UserRepository;
import huyvu.leathershop.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/auth/")
@RestController

public class Authentication {


    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login/")
    public ApiResponse<UserAuthenticateRespon> login(@RequestBody UserLoginDTO userdto) {
        return new ApiResponse<>(200, "Login successful", userService.loginUser(userdto));
    }

    @GetMapping("/info/")
    public ApiResponse getJwtInfo(@RequestHeader("Authorization") String token) {

       Claims info = userService.getInfo(token);

        // Create a response with extracted info
        return new ApiResponse<>(200,"Retrieve data successfully",info);
    }



//    onlly for dev
    @GetMapping("deleteALl/")
    public String deleteALl() {
        userRepository.deleteAll();
        return  "Deleted";
    }

}
