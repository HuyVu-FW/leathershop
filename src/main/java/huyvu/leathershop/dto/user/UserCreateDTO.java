package huyvu.leathershop.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class UserCreateDTO {
//    no se tra ve ten cua errror thoong qua MethodArgumentNotValidException đã định nghĩa trong Errocode
    @NotBlank(message = "Username is mandatory")
    @Size(min = 6, message = "USERNAME_INVALID")
    private String userName;
    @Size(min = 6,message = "Password must be at least 6 characters long")
    private String password;

}
