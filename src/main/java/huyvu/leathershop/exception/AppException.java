package huyvu.leathershop.exception;



import huyvu.leathershop.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
//This class will replace Runtime Exception
public class AppException extends  RuntimeException{

    private ErrorCode errorCode;

//    public AppException(ErrorCode errorCode ) {
//        super( errorCode.getMessage() );
//        this.errorCode = errorCode;
//    }
}
