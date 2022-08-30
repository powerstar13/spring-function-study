# 스프링의 기능을 활용해보자

1. Spring Boot Validation
2. Spring Boot Custom Validation
    - @AssertTrue 애노테이션을 사용하여 유효성 검사 정의
    - Validation 형태를 일부 차용하여 Custom Annotation을 만들고, ConstraintValidator 인터페이스를 구현하여 재사용 가능한 Custom Validation 구현
3. Spring Boot Exception 처리
   - @RestControllerAdvice 애노테이션을 사용하여 한 곳에서 Exception 관리
   - @ExceptionHandler 애노테이션을 사용하여 Exception 종류별로 핸들링
4. Spring Boot Validation을 통한 모범 사례
   - Exception 핸들러의 Response 메시지 모듈화
5. Spring Boot Filter
   - javax.servlet.Filter 인터페이스 상속받아 구현하기
   - Request와 Response의 내용을 읽을 경우 이미 읽은 내용에 대해 대비하기 위해 전처리와 후처리가 필요함에 주의
6. Spring Boot Interceptor
   - Interceptor란 Filter와 매우 유사한 형태로 존재하지만, 차이점은 Spring Context에 등록됨
   - AOP와 유사한 기능을 제공할 수 있으며, 주로 인증 단계를 처리하거나, Logging을 하는 데 사용
   - Interceptor에서도 Filter와 마찬가지로 Request, Response의 내용을 한 번 읽으면 다시 읽을 수 없는 문제가 발생할 수 있음 
     - 그래서 Filter에서 ContentCachingRequestWrapper, ContentCachingResponseWrapper 클래스를 사용하여 감싸주면 Interceptor에서 HttpServletRequest, HttpServletResponse를 형변환하여 사용할 수 있음
7. Spring Boot Async
   - Spring이 지정해주는 Thread가 아닌 직접 정의한 Thread를 사용할 수도 있음
     - WARN: Thread의 설정값은 경험치가 많이 쌓여야 하는 부분이기 때문에 신중해야 함
   - @Async: 비동기로 작동하기 위한 애노테이션
   - CompletableFuture: Client에게 Response를 내보내주기 위해 사용
