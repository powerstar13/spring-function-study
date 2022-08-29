package spring.function.study.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Error {

    private String field;
    private String message;
    private String invalidValue;
}
