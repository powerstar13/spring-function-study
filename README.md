# 스프링의 기능을 활용해보자

1. Spring Boot Validation
2. Spring Boot Custom Validation
    - @AssertTrue 애노테이션을 사용하여 유효성 검사 정의
    - Validation 형태를 일부 차용하여 Custom Annotation을 만들고, ConstraintValidator 인터페이스를 구현하여 재사용 가능한 Custom Validation 구현
3. Spring Boot Exception 처리
   - @RestControllerAdvice 애노테이션을 사용하여 한 곳에서 Exception 관리
   - @ExceptionHandler 애노테이션을 사용하여 Exception 종류별로 핸들링