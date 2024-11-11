package huyvu.leathershop.dto.user;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class UserUpdateDTO {

    private String password;
    private String fullname;
    private String email;
    private String phone;
    private String address;}

