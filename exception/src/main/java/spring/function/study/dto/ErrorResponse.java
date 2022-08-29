package spring.function.study.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ErrorResponse {

    private String requestUrl;
    private int resultCode;
    private String message;

    private List<Error> errorList;
}
