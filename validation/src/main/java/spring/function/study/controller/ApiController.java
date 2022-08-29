package spring.function.study.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.function.study.dto.User;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/user")
    public ResponseEntity<?> user(
        @Valid @RequestBody User user, // Validation을 작동시키기 위해 @Valid 애노테이션을 장착
        BindingResult bindingResult // Validation의 결과를 바로 Exception으로 던지는 것이 아닌 BindingResult를 통해 모아둘 수 있다.
    ) {
        if (bindingResult.hasErrors()) {

            StringBuilder sb = new StringBuilder();

            bindingResult.getAllErrors().forEach(objectError -> {

                FieldError fieldError = (FieldError) objectError;
                String message = objectError.getDefaultMessage();

                String field = fieldError.getField();
                System.out.println("field = " + field);
                System.out.println("message = " + message);

                // Validation Response 메시지 예쁘게 구성
                if (sb != null) sb.append("\n");
                sb.append("field: " + field);
                sb.append("\n");
                sb.append("message: " + message);
            });

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }

        System.out.println("user = " + user);

        // 유효성 검사에 문제가 없을 경우 logic 수행

        return ResponseEntity.ok(user);
    }
}
