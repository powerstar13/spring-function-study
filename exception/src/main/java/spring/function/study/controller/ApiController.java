package spring.function.study.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.function.study.dto.User;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/api")
@Validated // Contoller 클래스에 장착
public class ApiController {

    @GetMapping("/user")
    public User get(
        @NotBlank(message = "이름을 입력해 주세요.")
        @Size(min = 2)
        @RequestParam String name,

        @NotNull(message = "나이를 입력해 주세요.")
        @Min(1)
        @RequestParam Integer age
    ) {
        User user = new User();
        user.setName(name);
        user.setAge(age);

        int a = 10 + age;

        return user;
    }

    @PostMapping("/user")
    public User post(@Valid @RequestBody User user) {
        System.out.println("user = " + user);
        return user;
    }
}
