package spring.function.study.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
//@WebFilter(urlPatterns = "/api/user/*") // @Component 대신 @WebFilter 애노테이션을 사용하고 ApplicationMain 클래스에 @ServletComponentScan 애노테이션 장착하여 사용
public class GlobalFilter implements Filter { // javax.servlet.Filter 인터페이스를 상속받는다.

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 전처리
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest) request); // HttpServletRequest를 사용할 경우 내용을 한 번 읽으면 다시 읽을 수 없기 때문에 내용을 캐싱할 수 있는 클래스인 ContentCachingRequestWrapper를 사용하면 된다.
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);

        chain.doFilter(httpServletRequest, httpServletResponse);

        // 후처리 (정보는 doFilter() 메서드가 끝난 다음에 읽어야 한다.)
        String requestContent = new String(httpServletRequest.getContentAsByteArray());
        String url = httpServletRequest.getRequestURI();
        log.info("response url: {}, requestBody: {}", url, requestContent);

        String responseContent = new String(httpServletResponse.getContentAsByteArray());
        int httpStatus = httpServletResponse.getStatus();
        log.info("response status: {}, responseBody: {}", httpStatus, responseContent);

        httpServletResponse.copyBodyToResponse(); // 앞에서 이미 읽어버린 Response를 다시 한 번 내용을 채워주어야 Client에게 최종적으로 전달할 수 있다.
    }
}
