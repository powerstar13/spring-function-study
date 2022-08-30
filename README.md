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