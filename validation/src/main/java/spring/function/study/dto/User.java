package spring.function.study.dto;

import lombok.Getter;
import lombok.Setter;
import spring.function.study.annotation.YearMonth;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
public class User {

    @NotBlank
    private String name;
    @Max(value = 90)
    @Min(value = 0)
    private int age;
    @Email
    private String email;
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "핸드폰 번호의 양식과 맞지 않습니다. 01x-xxx(x)-xxxx")
    private String phoneNumber;
    @YearMonth
    private String reqYearMonth; // yyyyMM

    @Valid // WARN: Validation을 실행하기 위해 검사할 객체에 @Valid 애노테이션 추가
    private List<Car> cars;

    @Override
    public String toString() {
        return "User{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", email='" + email + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", reqYearMonth='" + reqYearMonth + '\'' +
            ", cars=" + cars +
            '}';
    }
}
