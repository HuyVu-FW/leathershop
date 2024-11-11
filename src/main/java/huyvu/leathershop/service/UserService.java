package huyvu.leathershop.service;

import huyvu.leathershop.dto.responAPI.UserAuthenticateRespon;
import huyvu.leathershop.dto.user.UserLoginDTO;
import huyvu.leathershop.model.User;
import io.jsonwebtoken.Claims;

import java.util.List;


public interface UserService {
    User registerUser(User user);
    List< User > getAllUsers();
    User getUser( String id );
    User updateUser( String id, User newUser );
    String deleteUser( String id );
    UserAuthenticateRespon loginUser(UserLoginDTO userdto);
    Claims getInfo(String jwt);
    User findByUsername(String username);
}
