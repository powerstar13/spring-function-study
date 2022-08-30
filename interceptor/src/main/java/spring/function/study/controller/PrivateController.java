package spring.function.study.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.function.study.annotation.Auth;

@RestController
@RequestMapping("/api/private")
@Auth // 특정 컨트롤러에 장착시켜주는 것이 가장 좋다.
@Slf4j
public class PrivateController {

//    @Auth // Controller에 장착해도 되고 Method에 장착해도 된다. 다만 메서드 단위로 붙일 경우 일관성이 떨어지고 유지보수 하기도 힘들어진다.
    @GetMapping("/hello")
    public String hello() {
        log.info("private hello controller");
        return "private hello";
    }
}
