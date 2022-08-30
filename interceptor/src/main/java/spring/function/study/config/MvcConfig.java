package spring.function.study.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import spring.function.study.interceptor.AuthInterceptor;

@Configuration
@RequiredArgsConstructor // @Autowired 애노테이션을 사용할 경우 순환 참조 문제가 발생할 수 있기 때문에 요즘 방식은 Lombok의 @RequiredArgsConstructor를 사용한다.
public class MvcConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor); // HandlerInterceptor 인터페이스를 구현한 Interceptor 등록
//            .addPathPatterns("/api/private/*"); // 특정 경로에 대해서만 검사하고 싶은 경우 추가할 수 있다. (여러 개일 경우 문자열을 계속 나열하면 됨 "a", "b")
//            .excludePathPatterns("/api/public/*"); // 특정 경로를 제외하고 싶은 경우 추가할 수 있다.

//        registry.addInterceptor(anotherInterceptor); // 여러 인터셉터를 등록할 경우 위에서부터 하나씩 단계를 타며 내려온다.
    }
}
