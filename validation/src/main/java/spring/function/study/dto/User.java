package spring.function.study.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    @Size(min = 6, max = 6)
    private String reqYearMonth; // yyyyMM

    @AssertTrue(message = "날짜 양식에 맞지 않습니다. yyyyMM") // @AssertTrue: return이 true면 정상, false면 실패
    public boolean isReqYearMonthValidation() { // WARN: 메서드명은 boolean return 타입에 맞추어 is*로 시작해야 한다.
        System.out.println("===== isReqYearMonthValidation() =====");

        try {
            LocalDate localDate = LocalDate.parse(reqYearMonth + "01", DateTimeFormatter.ofPattern("yyyyMMdd")); // LocalDate의 구조에 맞추기 위해 임시 일자 추가
        }
        catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "User{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", email='" + email + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", reqYearMonth='" + reqYearMonth + '\'' +
            '}';
    }
}
