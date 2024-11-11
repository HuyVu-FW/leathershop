package huyvu.leathershop.service.imp;

import huyvu.leathershop.dto.responAPI.UserAuthenticateRespon;
import huyvu.leathershop.dto.user.UserLoginDTO;
import huyvu.leathershop.exception.AppException;
import huyvu.leathershop.enums.ErrorCode;
import huyvu.leathershop.security.JwtUtils;
import huyvu.leathershop.model.Cart;
import huyvu.leathershop.model.User;
import huyvu.leathershop.repository.UserRepository;
import huyvu.leathershop.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserImp implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;







    @Override
    public User registerUser(User user) {
        if (userRepository.existsByUserName(user.getUserName()))
            throw new AppException(ErrorCode.USER_EXIST);

        else {

//            init user when create
            Cart cart = new Cart();
            cart.setUser(user);
            return userRepository.save(user);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String id) {
        return userRepository.findById(id).orElseThrow( () -> new RuntimeException("Not found user with id: " + id ) );
    }

    @Override
    public User updateUser(String id, User newUser) {

        return userRepository.save(getUser(id));
    }

    @Override
    public String deleteUser(String id) {
        userRepository.deleteById(id);
        return "User deleted";
    }
// for login
    @Override
    public UserAuthenticateRespon loginUser(UserLoginDTO userdto) {
       User user = userRepository.findByUserName(userdto.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_INVALID));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(userdto.getPassword(), user.getPassword()))
            throw new AppException(ErrorCode.USER_INVALID);

        String token = jwtUtils.generateToken(user);

        return new UserAuthenticateRespon(user.getUserName(),token);
    }
//    infor jwt

    @Override
    public Claims getInfo(String token) {
        String jwt = token.replace("Bearer ", "");
        return  jwtUtils.extractAllClaims(jwt);
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUserName(username);
        return user.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


}
