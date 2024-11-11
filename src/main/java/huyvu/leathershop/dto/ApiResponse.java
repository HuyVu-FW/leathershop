package huyvu.leathershop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

//Hide field null
@JsonInclude( JsonInclude.Include.NON_NULL )
public class ApiResponse <T>{
    private int status_code;
    private String message;
    private T result;

}
