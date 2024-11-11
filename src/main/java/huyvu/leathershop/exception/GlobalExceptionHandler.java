package huyvu.leathershop.exception;

import huyvu.leathershop.dto.ApiResponse;
import huyvu.leathershop.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class GlobalExceptionHandler {


    // Xử lý RuntimeException
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException exception) {
        // Tạo đối tượng ErrorResponse với thông tin lỗi
        ApiResponse errorResponse = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        // Trả về ResponseEntity chứa ErrorResponse và HTTP status
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    //get message exception.getAllErrors().get(0).getDefaultMessage() || exception.getFieldError().getDefaultMessage()
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        String enumkey = exception.getFieldError().getDefaultMessage();
        // get valus from enumkey
        ErrorCode errorCode = ErrorCode.valueOf(enumkey);

        ApiResponse errorResponse = new ApiResponse(errorCode.getCode(), errorCode.getMessage(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    // handle new AppException
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse> handleAppException(AppException exception) {

        ErrorCode errorCode = exception.getErrorCode();

        // Tạo đối tượng ErrorResponse với thông tin lỗi
        ApiResponse errorResponse = new ApiResponse(errorCode.getCode(), errorCode.getMessage(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        // Trả về ResponseEntity chứa ErrorResponse và HTTP status
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //full error for Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException() {


        ApiResponse errorResponse = new ApiResponse(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode(), ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);


    }


}
