package spring.function.study.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(value = Exception.class) // Exception을 모두 처리할 수 있다.
    public ResponseEntity<?> exception(Exception e) { // 예외를 매개 변수 값으로 받을 수 있다.

        System.out.println("===== exception =====");
        System.out.println("Exception = " + e.getClass().getName());
        System.out.println("Exception getLocalizedMessage = " + e.getLocalizedMessage());
        System.out.println("===== exception =====");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}

