package spring.function.study.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.function.study.dto.User;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/user")
    public User user(@RequestBody User user) {

        log.info("User: {}", user);
        return user;
    }
}
