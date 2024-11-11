package huyvu.leathershop.dto.responAPI;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAuthenticateRespon {

    String userName;
    String jwttoken;
}
