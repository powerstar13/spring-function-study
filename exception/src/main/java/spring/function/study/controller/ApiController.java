package spring.function.study.controller;

import org.springframework.web.bind.annotation.*;
import spring.function.study.dto.User;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/user")
    public User get(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) Integer age
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
