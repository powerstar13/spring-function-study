package spring.function.study.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring.function.study.controller.ApiController;
import spring.function.study.dto.Error;
import spring.function.study.dto.ErrorResponse;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestControllerAdvice(basePackageClasses = ApiController.class)
public class ApiControllerAdvice {

    @ExceptionHandler(value = Exception.class) // Exception을 모두 처리할 수 있다.
    public ResponseEntity<?> exception(Exception e, HttpServletRequest httpServletRequest) { // 예외를 매개 변수 값으로 받을 수 있다.

        List<Error> errorList = new ArrayList<>();

        String exceptionName = e.getClass().getName();
        String message = e.getMessage();

        Error errorMessage = this.getErrorMessage(exceptionName, message, null);

        errorList.add(errorMessage);

        ErrorResponse errorResponse = this.getErrorResponse(httpServletRequest, HttpStatus.INTERNAL_SERVER_ERROR, errorList);

        return ResponseEntity.ok(errorResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class) // @Valid 실행 결과의 Exception
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest) {

        List<Error> errorList = new ArrayList<>();

        BindingResult bindingResult = e.getBindingResult();

        bindingResult.getAllErrors().forEach(error -> {
            FieldError field = (FieldError) error;

            String filedName = field.getField();
            String message = field.getDefaultMessage();
            String value = field.getRejectedValue().toString();

            Error errorMessage = this.getErrorMessage(filedName, message, value);

            errorList.add(errorMessage);
        });

        ErrorResponse errorResponse = this.getErrorResponse(httpServletRequest, HttpStatus.BAD_REQUEST, errorList);

        return ResponseEntity.ok(errorResponse);
    }

    @ExceptionHandler(value = ConstraintViolationException.class) // @Validated 실행 결과의 Exception
    public ResponseEntity<?> constraintViolationException(ConstraintViolationException e, HttpServletRequest httpServletRequest) {

        List<Error> errorList = new ArrayList<>();

        e.getConstraintViolations().forEach(constraintViolation -> {

            Stream<Path.Node> stream = StreamSupport.stream(constraintViolation.getPropertyPath().spliterator(), false);
            List<Path.Node> list = stream.collect(Collectors.toList());

            String field = list.get(list.size() -1).getName();
            String message = constraintViolation.getMessage();
            String invalidValue = constraintViolation.getInvalidValue().toString();

            Error errorMessage = this.getErrorMessage(field, message, invalidValue);

            errorList.add(errorMessage);
        });

        ErrorResponse errorResponse = this.getErrorResponse(httpServletRequest, HttpStatus.BAD_REQUEST, errorList);

        return ResponseEntity.ok(errorResponse);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class) // @Validated 실행 결과의 Exception
    public ResponseEntity<?> missingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest httpServletRequest) {

        List<Error> errorList = new ArrayList<>();

        String fieldName = e.getParameterName();
        String message = e.getMessage();

        Error errorMessage = this.getErrorMessage(fieldName, message, null);

        errorList.add(errorMessage);

        ErrorResponse errorResponse = this.getErrorResponse(httpServletRequest, HttpStatus.BAD_REQUEST, errorList);

        return ResponseEntity.ok(errorResponse);
    }

    private Error getErrorMessage(String fieldName, String message, String invalidValue) {

        return Error.builder()
            .field(fieldName)
            .message(message)
            .invalidValue(invalidValue)
            .build();
    }

    private ErrorResponse getErrorResponse(HttpServletRequest httpServletRequest, HttpStatus httpStatus, List<Error> errorList) {

        return ErrorResponse.builder()
            .requestUrl(httpServletRequest.getRequestURI())
            .resultCode(httpStatus.value())
            .message(httpStatus.getReasonPhrase())
            .errorList(errorList)
            .build();
    }
}

