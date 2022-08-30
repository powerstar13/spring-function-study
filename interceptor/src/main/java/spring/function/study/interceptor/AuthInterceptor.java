package spring.function.study.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;
import spring.function.study.annotation.Auth;
import spring.function.study.exception.AuthException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURI();
        URI uri = UriComponentsBuilder.fromUriString(url)
            .query(request.getQueryString())
            .build()
            .toUri();
        log.info("uri: {}", uri);

        log.info("request url: {}", url);
        boolean hasAnnotation = this.checkAnnotation(handler, Auth.class); // 클래스나 컨트롤러의 정보는 Interceptor에서만 할 수 있다. Filter는 Web Context이기 때문에 이러한 기능을 사용할 수 없다.
        log.info("has annotation: {}", hasAnnotation);

        // 해당 서버는 모두 public으로 동작을 하는데
        // 단, Auth 권한을 가진 요청에 대해서는 인가 절차를 거치도록 처리하겠다!
        if (hasAnnotation) {
            String query = uri.getQuery();
            log.info("query: {}", query);
            // 권한 체크
            if (query.equals("name=master")) {
                return true;
            }
            throw new AuthException();
        }

        return true; // false인 경우 controller에 들어가지 않고 client에게 response를 시켜버린다.
    }

    private boolean checkAnnotation(Object handler, Class clazz) {

        // 1. resource(javascript, html)에 대한 요청인 경우에는 무조건 통과시켜주어야 한다.
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        // 2. annotation check
        HandlerMethod handlerMethod = (HandlerMethod) handler; // 더 다양한 기능은 HandlerMethod에 대해 검색해보면 좋다.

        if (null != handlerMethod.getMethodAnnotation(clazz) || null != handlerMethod.getBeanType().getAnnotation(clazz)) {
            // Auth 애노테이션을 가지고 있는 경우 true
            return true;
        }

        return false;
    }
}
